package com.wei.manager.mapper;

import com.wei.manager.bean.Model9;

public interface Model9Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model9 record);

    int insertSelective(Model9 record);

    Model9 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model9 record);

    int updateByPrimaryKey(Model9 record);
}