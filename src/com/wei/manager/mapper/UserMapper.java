package com.wei.manager.mapper;

import java.util.List;
import java.util.Map;

import com.wei.manager.bean.Menu;
import com.wei.manager.bean.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectByNameAndPass(Map<String,String> map);
    
    List<Menu> queryMenuByUser(int userId);
}