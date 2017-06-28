package com.wei.manager.mapper;

import com.wei.manager.bean.Model4;

public interface Model4Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model4 record);

    int insertSelective(Model4 record);

    Model4 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model4 record);

    int updateByPrimaryKey(Model4 record);
}