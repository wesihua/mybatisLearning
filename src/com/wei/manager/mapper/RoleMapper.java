package com.wei.manager.mapper;

import java.util.List;

import com.wei.manager.bean.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);
    
    List<Role> selectAll();
    
    List<Role> selectRoleByPage(Role role);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
}