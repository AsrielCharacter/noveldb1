package com.lws.blogdb.mapper;

import com.lws.blogdb.entity.Author;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author admin
* @description 针对表【author】的数据库操作Mapper
* @createDate 2024-09-04 16:03:54
* @Entity com.lws.blogdb.entity.Author
*/
@Mapper
public interface AuthorMapper {

    int deleteByPrimaryKey(Long id);

    Integer insert(Author record);

    int insertSelective(Author record);

    Author selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Author record);

    int updateByPrimaryKey(Author record);

    Author selectByNameAndPassword(@Param("name") String name, @Param("hashedPassword") String hashedPassword);
}
