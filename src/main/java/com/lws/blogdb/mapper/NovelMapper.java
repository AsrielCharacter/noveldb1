package com.lws.blogdb.mapper;

import com.lws.blogdb.entity.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NovelMapper {

    Integer addNovel(Novel novel);

    List<Novel> getNovel(@Param("typeID") Integer typeID, @Param("state") Integer state);

    Novel selectNovelById(@Param("novelID") String novelID);

    Integer updateNovel(Novel novel);

    Integer deleteNovel(@Param("novelID") String novelID);
}
