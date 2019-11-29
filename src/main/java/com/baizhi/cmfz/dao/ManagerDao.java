package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Manager;

public interface ManagerDao {

    Manager queryByPhone(String phone);

    void insert(Manager manager);
}
