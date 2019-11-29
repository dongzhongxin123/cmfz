package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.UserDao;
import com.baizhi.cmfz.entity.Pojo;
import com.baizhi.cmfz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public Map<String, Integer> queryByTime() {
        Map<String, Integer> map = new HashMap<>();
        try {
            Integer count1 = userDao.queryByTime(7, "男");
            Integer count2 = userDao.queryByTime(14, "男");
            Integer count3 = userDao.queryByTime(21, "男");
            Integer count4 = userDao.queryByTime(7, "女");
            Integer count5 = userDao.queryByTime(14, "女");
            Integer count6 = userDao.queryByTime(21, "女");
            map.put("nan1", count1);
            map.put("nan2", count2);
            map.put("nan3", count3);
            map.put("nv1", count4);
            map.put("nv2", count5);
            map.put("nv3", count6);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, List<Pojo>> queryByLocation() {
        Map<String, List<Pojo>> map = new HashMap<>();
        List<Pojo> users1 = userDao.queryByLocation("男");
        List<Pojo> users2 = userDao.queryByLocation("女");
        map.put("nan", users1);
        map.put("mv", users2);
        return map;
    }

    @Override
    public void regist(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setName("涨薪潮");
        user.setSex("男");
        user.setProvince("河南省");
        user.setCreateTime(new Date());
        userDao.addUser(user);
    }
}
