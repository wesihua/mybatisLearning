package com.wei.manager.mapper;

import com.wei.manager.bean.Model8;

public interface Model8Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model8 record);

    int insertSelective(Model8 record);

    Model8 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model8 record);

    int updateByPrimaryKey(Model8 record);
}