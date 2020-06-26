package com.qinpr.follow.mybatis.mapper;

import com.qinpr.follow.mybatis.Blog;

/**
 * Created by qinpr on 2020/6/4.
 */
public interface BlogMapper {
    Blog selectBlog(int id);
}
