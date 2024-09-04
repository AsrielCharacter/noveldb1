package com.lws.blogdb.service.impl;

import cn.hutool.core.util.IdUtil;
import com.lws.blogdb.entity.Author;
import com.lws.blogdb.mapper.AuthorMapper;
import com.lws.blogdb.service.AuthorService;
import com.lws.blogdb.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorMapper authorMapper;


    @Override
    public Integer register(Author author) {
        //雪花算法生成id hutool
        String snowflakeId = IdUtil.getSnowflakeNextIdStr();
        //密码加盐加密 也可以用bcrypt加密(这里用一般加密)
        String HashedPassword = Md5Util.getMd5(author.getLoginpwd());
        author.setAuthorid(snowflakeId);
        author.setLoginpwd(HashedPassword);

        return authorMapper.insert(author);
    }

    @Override
    public Author login(String name, String password) {
        String HashedPassword = Md5Util.getMd5(password);

        return authorMapper.selectByNameAndPassword(name, HashedPassword);
    }
}
