package com.baizhi.cmfz.service;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AdminService {

    Map<String, Object> query(String username, String password, String code, HttpServletRequest request);
}
