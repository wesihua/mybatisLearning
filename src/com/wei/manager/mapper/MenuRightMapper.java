package com.wei.manager.mapper;

import java.util.List;

import com.wei.manager.bean.Menu;
import com.wei.manager.bean.MenuRight;

public interface MenuRightMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MenuRight record);

    int insertSelective(MenuRight record);

    MenuRight selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuRight record);

    int updateByPrimaryKey(MenuRight record);
    //根据roleId查询其所有菜单
    List<Menu> selectRoleMenu(String roleId);
    //查询待分配的菜单数据
    List<Menu> selectDistributeMenu(String roleId);
    //根据角色编号删除所有权限数据
    void deleteRightByRoleId(String roleId);
    //查询父菜单
    List<Menu> selectParentMenuByIds(String[] arr);
}