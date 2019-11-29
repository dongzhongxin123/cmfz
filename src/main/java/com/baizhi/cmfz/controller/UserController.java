package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Pojo;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/queryByTime")
    public Map<String, Integer> queryByTime() {
        Map<String, Integer> map = userService.queryByTime();
        System.out.println("刷新");
        return map;
    }

    @ResponseBody
    @RequestMapping("/queryByLocation")
    public Map<String, List<Pojo>> queryByLocation() {
        Map<String, List<Pojo>> map = userService.queryByLocation();
        return map;
    }

    @ResponseBody
    @RequestMapping("/add")
    public void add(User user) {
        GoEasy goEasy = null;
        try {
            userService.regist(user);
            goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-b1b54ac7bca244428c0bead5c7f1de23");
            goEasy.publish("ASD", "成功注册一名用户");
        } catch (Exception e) {
            e.printStackTrace();
            goEasy.publish("ASD", "注册用户失败");
        }
    }
}
