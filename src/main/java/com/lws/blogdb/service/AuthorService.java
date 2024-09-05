package com.lws.blogdb.service;

import com.lws.blogdb.entity.Author;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

public interface AuthorService {
    //作者注册接口
    Integer register(Author author);
    //作者登录接口
    Author login(@Pattern(regexp="^\\S{6,20}$",message="作者名字6~20位非空字符") String name, @Pattern(regexp="^\\S{6,20}$",message="密码6~20位非空字符") String password);
    //获取当前登录作者信息接口
    Author getAuthorInfo();
    //更新作者基本信息接口
    Integer updateBaseInfoAuthor(Author author);
    //更新已经登录的作者头像接口
    Integer updateHeadPic(@URL(message = "头像地址格式错误") String headPic);
    //更新已经登录的作者的密码接口
    Integer updatePwd(String newHashPwd);
    //根据作者名字查询作者信息接口
    Author selectAuthorByName(String name);
}
