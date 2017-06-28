package com.wei.manager.mapper;

import java.util.List;

import com.wei.manager.bean.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);
    
    List<Menu> selectAll();
    
    List<Menu> selectMenuByPage(Menu menu);
    
    int selectMenuCountByPage(Menu menu);
    
    //查询所有一级菜单
    List<Menu> queryAllParentPage();
    //根据parentId查询其子菜单
    List<Menu> queryPagesByParentId(int parentId);
    //查询父级菜单
    List<Menu> selectParentMenuByPage(Menu menu);
    //查询父级菜单的数量
    int selectParentMenuCount(Menu menu);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}