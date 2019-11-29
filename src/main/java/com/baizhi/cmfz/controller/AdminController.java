package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(String username, String password, String code, HttpServletRequest request) {
        Map<String, Object> map = adminService.query(username, password, code, request);
        return map;
    }

}
