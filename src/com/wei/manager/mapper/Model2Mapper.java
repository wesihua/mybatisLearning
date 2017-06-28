package com.wei.manager.mapper;

import com.wei.manager.bean.Model2;

public interface Model2Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model2 record);

    int insertSelective(Model2 record);

    Model2 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model2 record);

    int updateByPrimaryKey(Model2 record);
}