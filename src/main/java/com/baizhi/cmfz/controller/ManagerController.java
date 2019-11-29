package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.UUID;

@RequestMapping("/manager")
@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;


    @RequestMapping("/queryByPhone")
    @ResponseBody
    public Map<String, String> queryByPhone(String phone) {

        Map<String, String> map = managerService.queryByPhone(phone);
        return map;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, String> insert() {
        Manager manager = new Manager();
        manager.setId(UUID.randomUUID().toString());
        manager.setName("涨薪潮2");
        manager.setPassword("201314");
        manager.setPhone("15139131372");
        Map<String, String> map = managerService.insert(manager);
        return map;

    }
}
