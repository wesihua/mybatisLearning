package com.wei.manager.mapper;

import java.util.List;

import com.wei.manager.bean.Model;

public interface CommonMapper {

	//根据表名查询相应表中的所有数据
	List<Model> queryAllModel(String tablename);
	
	/*--------------------额外增加的需求---------------------------*/
}
