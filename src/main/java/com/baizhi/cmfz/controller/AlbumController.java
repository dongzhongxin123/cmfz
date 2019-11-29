package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.dao.AlbumDao;
import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumDao albumDao;

    @RequestMapping("/queryByPage")
    public Map<String, Object> queryByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<Album> albums = albumService.queryByPage(page, rows);
        Integer count = albumService.queryCount();
        long total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total", total);
        map.put("page", page);
        map.put("records", count);
        map.put("rows", albums);
        return map;
    }

    @RequestMapping("edit")
    public String edit(String oper, Album album, String[] id) {
        String uid = null;
        if (oper.equals("add")) {
            uid = UUID.randomUUID().toString();
            album.setId(uid);
            albumService.add(album);
        } else if (oper.equals("del")) {
            if (id.length != 0) {
                albumService.deleteByCount(id);
            }
            albumService.deleteById(album.getId());
        } else if (oper.equals("edit")) {
            albumService.update(album);
        }
        return uid;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile illustration, String bId, HttpServletRequest request) {
        System.out.println("bId = " + bId);
        //获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/img/");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = illustration.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + filename;
        try {
            illustration.transferTo(new File(file, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        albumService.updateById(bId, newFileName);
    }


    //查询一部专辑下所有章节
    @RequestMapping("/wen")
    @ResponseBody
    public Map<String, Object> wen(String id, String uid) {

        return null;
    }
}
