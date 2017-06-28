package com.wei.manager.mapper;

import com.wei.manager.bean.Model3;

public interface Model3Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model3 record);

    int insertSelective(Model3 record);

    Model3 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model3 record);

    int updateByPrimaryKey(Model3 record);
}