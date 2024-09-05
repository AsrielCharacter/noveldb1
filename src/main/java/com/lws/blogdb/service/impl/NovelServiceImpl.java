package com.lws.blogdb.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lws.blogdb.entity.Novel;
import com.lws.blogdb.entity.PageBean;
import com.lws.blogdb.mapper.NovelMapper;
import com.lws.blogdb.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovelServiceImpl implements NovelService {
    @Autowired
    private NovelMapper novelMapper;

    @Override
    public Integer addNovel(Novel novel) {
        //雪花算法生成id hutool
        String snowflakeId = IdUtil.getSnowflakeNextIdStr();
        novel.setNovelId(snowflakeId);
        return novelMapper.addNovel(novel);
    }

    @Override
    public PageBean<Novel> getNovel(int pageNum, int pageSize, Integer typeID, Integer state) {

        //设置当前页码
        PageHelper.startPage(pageNum, pageSize);
        List<Novel> novelList = novelMapper.getNovel(typeID, state);
        //创建PageInfo对象
        PageInfo<Novel> pageInfo = new PageInfo<>(novelList);
//        pageInfo.setTotal(pageInfo.getTotal());
//        pageInfo.setPages(pageInfo.getPages());

        PageBean pageBean = new PageBean();
        pageBean.setTotal(pageInfo.getTotal());
        pageBean.setItems(novelList);


        return pageBean;
    }

    @Override
    public Novel selectNovelById(String novelID) {
        return novelMapper.selectNovelById(novelID);
    }

    @Override
    public Integer updateNovel(Novel novel) {
        return novelMapper.updateNovel(novel);
    }

    @Override
    public Integer deleteNovel(String novelID) {
        return novelMapper.deleteNovel(novelID);
    }
}
