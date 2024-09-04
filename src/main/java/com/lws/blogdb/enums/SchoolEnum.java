package com.lws.blogdb.enums;

public enum SchoolEnum {
    SCHOOL_STUDENT_MD5("lws_school_student_md5"),
    SCHOOL_JWT("lws1145"),
    SCHOOL_CODE("lws_school_code_21231"),
    ;


    private final String getValue;
    public String getValue() {
        return getValue;
    }
    SchoolEnum(String number) {
        getValue = number;
    }
}
