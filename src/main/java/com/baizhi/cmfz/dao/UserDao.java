package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.entity.Pojo;
import com.baizhi.cmfz.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    //查询用户的注册使用情况
    Integer queryByTime(@Param("day") Integer day, @Param("sex") String sex);

    //查询用户的地域分布
    List<Pojo> queryByLocation(String sex);

    //用户注册
    void addUser(User user);

    //判断用户是否注册
    User selectByPhone(String telephone);

    //修改用户信息
    void update(Manager manager);
}
