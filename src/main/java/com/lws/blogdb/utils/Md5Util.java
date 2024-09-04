package com.lws.blogdb.utils;


import cn.hutool.crypto.digest.DigestUtil;
import com.lws.blogdb.enums.SchoolEnum;

public class Md5Util {
    public static String getMd5(SchoolEnum salt,String message) {
        String salt1=salt.getValue();
//        自定义盐值
        return DigestUtil.md5Hex( DigestUtil.sha256Hex(salt+message));
    }
    //默认UUID盐 hutool工具箱
    public static String getMd5(String message) {
        String salt= SchoolEnum.SCHOOL_STUDENT_MD5.getValue();
        return DigestUtil.md5Hex(DigestUtil.sha256Hex(salt+message));
    }
}
