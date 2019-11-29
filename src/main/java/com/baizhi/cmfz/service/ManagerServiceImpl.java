package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.ManagerDao;
import com.baizhi.cmfz.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, String> queryByPhone(String phone) {
        Map<String, String> map = new HashMap<>();
        Manager manager = managerDao.queryByPhone(phone);
        if (manager != null) {
            map.put("error", "-200");
            map.put("errorMsg", "该手机号已经存在");
            return map;
        } else {
            map.put("password", manager.getPassword());
            map.put("uid", manager.getId());
            map.put("phone", manager.getPhone());
            return map;
        }
    }

    @Override
    public Map<String, String> insert(Manager manager) {
        Map<String, String> map = new HashMap<>();
        Manager manager1 = managerDao.queryByPhone(manager.getPhone());
        System.out.println("manager1 = " + manager1);
        if (manager1 == null) {
            managerDao.insert(manager);
            map.put("password", manager.getPassword());
            map.put("uid", manager.getId());
            map.put("phone", manager.getPhone());
            return map;
        } else {
            map.put("error", "-200");
            map.put("errorMsg", "该手机号已经存在");
            return map;
        }
    }
}
