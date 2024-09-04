package com.lws.blogdb.service;

import com.lws.blogdb.entity.Author;
import jakarta.validation.constraints.Pattern;

public interface AuthorService {
    //作者注册接口
    Integer register(Author author);
    //作者登录接口
    Author login(@Pattern(regexp="^\\S{6,20}$",message="作者名字6~20位非空字符") String name, @Pattern(regexp="^\\S{6,20}$",message="密码6~20位非空字符") String password);
}
