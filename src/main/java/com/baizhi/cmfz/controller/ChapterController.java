package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/chapter")
@RestController
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("queryByPage")
    public Map<String, Object> queryByPage(Integer page, Integer rows, String id) {
        Map<String, Object> map = chapterService.queryByPage(page, rows, id);
        return map;
    }

    @RequestMapping("edit")
    public String edit(String aId, String oper, Chapter chapter, String[] id) {
        String uid = null;
        if (oper.equals("add")) {
            uid = UUID.randomUUID().toString();
            chapter.setId(uid);
            chapter.setAlbumId(aId);
            chapterService.add(chapter);
        } else if (oper.equals("del")) {
            System.out.println("chapter = " + chapter.getId());
            chapterService.deleteById(chapter.getId());
        } else if (oper.equals("edit")) {
            chapterService.update(chapter);
        }
        return uid;
    }


    @RequestMapping("upload")
    public void upload(HttpServletRequest request, MultipartFile audioFile, String id) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        //System.out.println("bId = " + bId);
        //获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/audio/");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = audioFile.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + filename;
        try {
            audioFile.transferTo(new File(file, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //根据chapterId   newFileName修改
        Chapter chapter = new Chapter();
        chapter.setId(id);
        chapter.setAudioFile(newFileName);
        long l = audioFile.getSize();
        String size = l / 1024 / 1024 + "MB";
        System.out.println("size = " + size);
        chapter.setSize(size);

        AudioFile read = AudioFileIO.read(new File(realPath, newFileName));
        AudioHeader audioHeader = read.getAudioHeader();
        int trackLength = audioHeader.getTrackLength();
        String m = trackLength / 60 + "分";
        String s = trackLength % 60 + "秒";
        chapter.setTimeLength(m + s);
        System.out.println("s = " + s);
        chapterService.updateById(chapter);
    }

    @RequestMapping("/down")
    public void down(String audioFile, HttpSession session, HttpServletResponse response) throws UnsupportedEncodingException {
        String realPath = session.getServletContext().getRealPath("/audio");
        File file = new File(realPath, audioFile);
        //处理下载后的文件名
        String name = audioFile.split("_")[1];
        //String name = split[1];
        String encode = URLEncoder.encode(name, "UTF-8");

        response.setHeader("content-disposition", "attachment;fileName=" + encode);

        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            FileUtils.copyFile(file, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
