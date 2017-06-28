package com.wei.manager.mapper;

import com.wei.manager.bean.Model1;

public interface Model1Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model1 record);

    int insertSelective(Model1 record);

    Model1 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model1 record);

    int updateByPrimaryKey(Model1 record);
}