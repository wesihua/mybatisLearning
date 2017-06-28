package com.wei.manager.mapper;

import com.wei.manager.bean.Model6;

public interface Model6Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model6 record);

    int insertSelective(Model6 record);

    Model6 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model6 record);

    int updateByPrimaryKey(Model6 record);
}