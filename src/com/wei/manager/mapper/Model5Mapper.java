package com.wei.manager.mapper;

import com.wei.manager.bean.Model5;

public interface Model5Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model5 record);

    int insertSelective(Model5 record);

    Model5 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model5 record);

    int updateByPrimaryKey(Model5 record);
}