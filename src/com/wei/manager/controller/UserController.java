package com.wei.manager.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wei.manager.bean.Role;
import com.wei.manager.bean.User;
import com.wei.manager.dao.CommonDao;
import com.wei.manager.mapper.UserMapper;
import com.wei.manager.util.JsonUtil;

@Controller
@RequestMapping("manager/user")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CommonDao commonDao;
	
	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpServletResponse response,User user){
		//查询所有角色
		List<User> list = commonDao.getEntityByPageWithRowMapper(user);
		request.setAttribute("list", list);
		return "user/userList";
	}
	@RequestMapping("query")
	public String query(HttpServletRequest request,HttpServletResponse response,User user){
		//查询所有角色
		List<User> list = commonDao.getEntityByPageWithRowMapper(user);
		request.setAttribute("list", list);
		return "user/userQuery";
	}
	
	@RequestMapping("addUser")
	public String addUser(){
		return "user/editUser";
	}
	
	@RequestMapping("editUser")
	public String editUser(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		//查询role信息
		User u = new User();
		u.setId(Integer.parseInt(id));
		User user = commonDao.queryObjectByEntity(u);
		request.setAttribute("bean", user);
		return "user/editUser";
	}
	@ResponseBody
	@RequestMapping("deleteUser")
	public String deleteUser(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			userMapper.deleteByPrimaryKey(Integer.parseInt(id));
			map.put("flag", "0");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("flag", "1");
		}
		return JsonUtil.object2Json(map);
	}
	
	@RequestMapping("saveUser")
	public String saveUser(HttpServletRequest request,HttpServletResponse response,User user){
		if(null == user.getId()){
			//新增
			user.setCreatedate(new Date());
			userMapper.insertSelective(user);
		}
		else{
			//更改
			userMapper.updateByPrimaryKeySelective(user);
		}
		return "redirect:list";
	}
}
