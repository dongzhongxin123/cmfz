package com.baizhi.cmfz.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.cmfz.entity.Rotation;
import com.baizhi.cmfz.service.RotationService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/rotation")
public class RotationController {

    @Autowired
    private RotationService rotationService;


    @RequestMapping("queryByPage")
    @ResponseBody
    public Map<String, Object> queryByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<Rotation> articles = rotationService.queryByPage(page, rows);
        Integer count = rotationService.selectByCount();
        long total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total", total);
        map.put("page", page);
        map.put("records", total);
        map.put("rows", articles);
        return map;
    }

    @RequestMapping("edit")
    public String edit(String oper, Rotation rotation, String[] id) {

        String uid = null;
        if (oper.equals("add")) {
            uid = UUID.randomUUID().toString();
            rotation.setId(uid);
            rotationService.add(rotation);
        } else if (oper.equals("del")) {
            if (id.length != 0) {
                rotationService.deleteByCount(id);
            }
            rotationService.deleteById(rotation.getId());
        } else if (oper.equals("edit")) {
            rotationService.update(rotation);
        }
        return uid;
    }


    @RequestMapping("upload")
    public void upload(MultipartFile photo, String bId, HttpServletRequest request) {
        //获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/img/");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = photo.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + filename;
        try {
            photo.transferTo(new File(file, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rotationService.updateById(bId, newFileName);
    }


    @RequestMapping("/downFile")
    public void downFile(HttpServletResponse response) throws Exception {

        List<Rotation> rotations = rotationService.queryAll();
        List<Rotation> list = new ArrayList<>();
        Rotation ro = null;
        for (Rotation rotation : rotations) {
            ro = new Rotation(rotation.getId(), rotation.getTitle(), rotation.getDescribel(),
                    rotation.getCreateTime(), rotation.getStatus(), rotation.getPhoto());
            list.add(ro);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图模块",
                "单行数据"), Rotation.class, list);

        if (workbook == null) {
            return;
        }
        ServletOutputStream outputStream = null;
        try {
            String fileName = new Date() + ".xls";

            response.setContentType("application/x-download");
            response.setHeader("content-disposition", "attachment;fileName=" + fileName);
            //outputStream = new FileOutputStream("G:\\"+fileName);
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
            workbook.close();
        }
    }
}