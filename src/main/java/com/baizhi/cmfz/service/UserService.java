package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Pojo;
import com.baizhi.cmfz.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    //参数：时间、性别
    Map<String, Integer> queryByTime();

    Map<String, List<Pojo>> queryByLocation();

    void regist(User user);
}
