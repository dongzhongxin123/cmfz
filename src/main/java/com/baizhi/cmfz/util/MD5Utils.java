package com.baizhi.cmfz.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {

    public static String md5(String target) {
        return DigestUtils.md5Hex(target);
    }

    /*
     * String src =  "123456"
     * String pwd 数据库存的密文
     */
    public static boolean isPasswordValidate(String src, String pwd) {
        if (src == null) throw new RuntimeException("src 为null 不能进行对比");
        if (pwd == null) throw new RuntimeException("pwd 为null 不能进行对比");

        if (md5(src).equals(pwd)) {
            return true;
        }
        return false;
    }

}

