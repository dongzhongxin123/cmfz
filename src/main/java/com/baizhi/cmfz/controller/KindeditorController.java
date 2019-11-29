package com.baizhi.cmfz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/kindeditor")
public class KindeditorController {


    @RequestMapping("/upload")
    public Map<String, Object> upload(HttpServletRequest request, MultipartFile img) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        String realPath = request.getSession().getServletContext().getRealPath("/img/");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdir();
        }

        String originalFilename = img.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;
        img.transferTo(new File(realPath, newFileName));
        map.put("error", 0);
        //获取url的地址
        String scheme = request.getScheme();
        InetAddress localHost = Inet4Address.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        int serverPort = request.getServerPort();
        String path = request.getContextPath();
        String url = scheme + "://" + hostAddress + ":" + serverPort + path + "/img/" + newFileName;
        map.put("url", url);
        return map;
    }

    @RequestMapping("/findAllImg")
    public Map<String, Object> findAllImg(HttpServletRequest request) throws UnknownHostException {
        HashMap<String, Object> map = new HashMap<>();
        List<Map<String, Object>> l = new ArrayList<>();

        //获取服务器端文件夹路径
        String realPath = request.getServletContext().getRealPath("/img/");
        File file = new File(realPath);
        //遍历文件夹中的照片
        String[] list = file.list();
        for (String s : list) {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("is_dir", false);
            map1.put("has_file", false);
            File file1 = new File(realPath, s);
            map1.put("filesize", file1.length());
            map1.put("is_photo", true);
            String name = s.substring(s.lastIndexOf(".") + 1);
            map1.put("filetype", name);
            map1.put("filename", s);
            boolean contains = s.contains("_");
            if (contains) {
                String name1 = s.split("_")[0];
                Long aLong = Long.valueOf(name1);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = simpleDateFormat.format(aLong);
                map1.put("datetime", format);
            }
            if (!contains) {
                map1.put("datetime", new Date());
            }

            map1.put("dir_path", "");
            l.add(map1);
        }
        String scheme = request.getScheme();
        InetAddress localHost = Inet4Address.getLocalHost();
        String address = localHost.getHostAddress();
        int port = request.getServerPort();
        String path = request.getContextPath();


        String url = scheme + "://" + address + ":" + port + path + "/img/";

        map.put("current_url", url);
        map.put("moveup_dir_path", "");
        map.put("current_dir_path", "");
        map.put("total_count", list.length);
        map.put("file_list", l);
        System.out.println("map = " + map);
        return map;
    }
}
