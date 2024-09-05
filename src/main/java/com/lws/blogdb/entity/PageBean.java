package com.lws.blogdb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 该类暂时不需要
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean <T> {
    private Long total;//总条数
    private List<T> items;//当前页数据
}
