package com.qinpr.follow.spring1.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by qinpr on 2020/4/27.
 */
public interface TestDao1 {

    @Select("select * from test")
    public List<Map<String, Object>> query();
}
