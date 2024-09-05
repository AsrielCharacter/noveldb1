package com.lws.blogdb.mapper;

import com.lws.blogdb.entity.NovelType;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NovelTypeMapper {
    Integer addNovelType(NovelType novelType);

    NovelType selectById(@Param("id") Integer id);

    Integer updateNovelType(NovelType novelType);

    Integer deleteNovelType(@Param("typeID") Integer typeID);
}
