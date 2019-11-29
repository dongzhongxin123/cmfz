package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {

    //登陆
    Admin selectAdminByUAndP(@Param("username") String username, @Param("password") String password);

    Admin selectAdminByUserName(String username);

}
