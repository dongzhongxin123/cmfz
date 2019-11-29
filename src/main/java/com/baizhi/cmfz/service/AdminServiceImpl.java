package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.AdminDao;
import com.baizhi.cmfz.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;


    @Override
    public Map<String, Object> query(String username, String password, String code, HttpServletRequest request) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("enCode = " + code);
        HashMap<String, Object> map = new HashMap<>();
        String kaptchaVerifyCode = (String) request.getSession()
                .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (code.equals(kaptchaVerifyCode)) {
            Admin admin = adminDao.selectAdminByUserName(username);
            if (admin != null) {
                if (password.equals(admin.getPassword())) {
                    map.put("msg", "ok");
                    map.put("admin", admin);
                    return map;
                } else {
                    map.put("msg", "密码错误");
                    return map;
                }

            } else {
                map.put("msg", "用户名不存在");
                return map;
            }
        } else {
            map.put("msg", "验证码错误");
            return map;
        }
    }
}
