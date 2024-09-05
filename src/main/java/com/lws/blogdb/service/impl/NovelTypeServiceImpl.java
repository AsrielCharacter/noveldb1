package com.lws.blogdb.service.impl;

import com.lws.blogdb.entity.NovelType;
import com.lws.blogdb.mapper.NovelTypeMapper;
import com.lws.blogdb.service.NovelTypeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovelTypeServiceImpl implements NovelTypeService {
    @Autowired
    private NovelTypeMapper novelTypeMapper;

    @Override
    public Integer addNovelType(NovelType novelType) {
        return novelTypeMapper.addNovelType(novelType);
    }

    @Override
    public NovelType selectById(@NonNull Integer id) {
        return novelTypeMapper.selectById(id);
    }

    @Override
    public Integer updateNovelType(NovelType novelType) {
        return novelTypeMapper.updateNovelType(novelType);
    }

    @Override
    public Integer deleteNovelType(Integer typeID) {
        return novelTypeMapper.deleteNovelType(typeID);
    }

    @Override
    public List<NovelType> selectAll() {
        return novelTypeMapper.selectAll();
    }
}
