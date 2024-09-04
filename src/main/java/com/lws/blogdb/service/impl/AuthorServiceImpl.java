package com.lws.blogdb.service.impl;

import cn.hutool.core.util.IdUtil;
import com.lws.blogdb.entity.Author;
import com.lws.blogdb.mapper.AuthorMapper;
import com.lws.blogdb.service.AuthorService;
import com.lws.blogdb.utils.Md5Util;
import com.lws.blogdb.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public Author getAuthorInfo() {
        //从threadlocal中获取authorid
        Map<String, Object> threadLocalMap = ThreadLocalUtil.get();
        String authorId = (String) threadLocalMap.get("id");
        return authorMapper.selectByAuthorId(authorId);
    }

    @Override
    public Integer updateBaseInfoAuthor(Author author) {
        //从threadlocal中获取authorid
        Map<String, Object> threadLocalMap = ThreadLocalUtil.get();
        String authorId = (String) threadLocalMap.get("id");
        author.setAuthorid(authorId);
        return authorMapper.updateBaseInfo(author);
    }

    @Override
    public Integer updateHeadPic(String headPic) {
        Map<String, Object> threadLocalMap = ThreadLocalUtil.get();
        String authorId = (String) threadLocalMap.get("id");

        return authorMapper.updateHeadPic(authorId,headPic);
    }

    @Override
    public Integer updatePwd(String newHashPwd) {
        Map<String, Object> threadLocalMap = ThreadLocalUtil.get();
        String authorId = (String) threadLocalMap.get("id");
        return authorMapper.updatePwd(authorId,newHashPwd);
    }
}
