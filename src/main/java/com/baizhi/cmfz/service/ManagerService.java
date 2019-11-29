package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Manager;

import java.util.Map;

public interface ManagerService {

    Map<String, String> queryByPhone(String phone);

    Map<String, String> insert(Manager manager);
}
