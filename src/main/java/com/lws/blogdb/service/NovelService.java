package com.lws.blogdb.service;

import com.github.pagehelper.PageInfo;
import com.lws.blogdb.entity.Novel;
import com.lws.blogdb.entity.PageBean;
import jakarta.validation.constraints.NotNull;

public interface NovelService {
    //添加小说
    Integer addNovel(Novel novel);
    //分页查询
    PageBean<Novel> getNovel(int pageNum, int pageSize, Integer typeID, Integer state);
    //根据ID查询小说
    Novel selectNovelById(String novelID);
    //根据ID修改小说信息
    Integer updateNovel(Novel novel);
    //根据ID删除小说
    Integer deleteNovel(@NotNull String novelID);
}
