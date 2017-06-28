package com.wei.manager.mapper;

import com.wei.manager.bean.Model10;

public interface Model10Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model10 record);

    int insertSelective(Model10 record);

    Model10 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model10 record);

    int updateByPrimaryKey(Model10 record);
}