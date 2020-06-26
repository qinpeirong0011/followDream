package com.qinpr.follow.spring.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by qinpr on 2020/4/27.
 */
public interface XXXDao {

    @Select("select * from XXX")
    public List<Map<String, Object>> query();
}
