package com.lws.blogdb.service;

import com.lws.blogdb.entity.NovelType;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.List;

public interface NovelTypeService {
    //添加小说类型
    Integer addNovelType(NovelType novelType);
    //根据id查询小说类型
    NovelType selectById(@NonNull Integer id);
    //修改小说类型
    Integer updateNovelType(NovelType novelType);
    //删除小说类型
    Integer deleteNovelType(@NotNull Integer typeID);
    //查询所有小说类型
    List<NovelType> selectAll();
}
