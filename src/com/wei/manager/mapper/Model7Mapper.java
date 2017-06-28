package com.wei.manager.mapper;

import com.wei.manager.bean.Model7;

public interface Model7Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model7 record);

    int insertSelective(Model7 record);

    Model7 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model7 record);

    int updateByPrimaryKey(Model7 record);
}