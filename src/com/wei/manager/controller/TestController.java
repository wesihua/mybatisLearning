package com.wei.manager.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wei.manager.bean.Role;
import com.wei.manager.bean.User;
import com.wei.manager.dao.CommonDao;
import com.wei.manager.mapper.UserMapper;
import com.wei.manager.service.TestService;
import com.wei.manager.util.JsonUtil;
import com.wei.manager.util.Person;

@Controller
@RequestMapping("manager")
public class TestController {

	@Autowired
	private TestService testService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CommonDao commonDao;
	
	@ResponseBody
	@RequestMapping("test")
	public String test(HttpServletRequest request,HttpServletResponse response){
		Person p = new Person();
		List<Person> list = new ArrayList<Person>();
		p.setNameStr("魏四化");
		p.setAge(0);
		p.setBirth(new Date());
		
		Person p2 = new Person();
		p2.setNameStr("张三");
		p2.setAge(10);
		p2.setBirth(new Date());
		Person p3 = new Person();
		p3.setNameStr(null);
		p3.setAge(10);
		p3.setBirth(new Date());
		list.add(p2);
		list.add(p3);
		p.setChildren(list);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("key", null);
		map.put("name", null);
		map.put("sss", null);
		map.put("person", p);
		
		return JsonUtil.object2Json(map);
	}
	@RequestMapping("main")
	public String main(){
		return "main";
	}
	@RequestMapping("el")
	public String el(HttpServletRequest request,HttpServletResponse response){
		Person p = new Person();
		p.setNameStr("魏四化");
		p.setAge(0);
		p.setBirth(new Date());
		
		request.setAttribute("bean", p);
		return "person";
	}
	
	@ResponseBody
	@RequestMapping("auto")
	public String auto(HttpServletRequest request,HttpServletResponse response){
		
		Map<String,Object> map = new HashMap<String, Object>();
		String result = testService.pring();
		map.put("key", result);
		return JsonUtil.object2Json(map);
	}
	/**
	 * 查询所有测试
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("commondao")
	public String commoDao(HttpServletRequest request,HttpServletResponse response){
		String tablename = request.getParameter("tablename");
		Map<String,Object> map = new HashMap<String, Object>();
		//List<User> list = commonDao.queryAllEntityByTablename(tablename);
		List<User> list = commonDao.queryAllByEntityName(tablename);
		map.put("data", list);
		return JsonUtil.object2Json(map);
	}
	/**
	 * 分页查询测试
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("pagedao")
	public String commonPageDao(HttpServletRequest request,HttpServletResponse response,Role u){
		Map<String,Object> map = new HashMap<String, Object>();
		List<Role> list = commonDao.getEntityByPage(u);
		map.put("data", list);
		return JsonUtil.object2Json(map);
	}
	/**
	 * 分页查询测试
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("newpagedao")
	public String newPageDao(HttpServletRequest request,HttpServletResponse response,Role u){
		Map<String,Object> map = new HashMap<String, Object>();
		List<Role> list = commonDao.getEntityByPageWithRowMapper(u);
		map.put("data", list);
		return JsonUtil.object2Json(map);
	}
	
	/**
	 * 查询单个对象
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("singledao")
	public String singleDao(HttpServletRequest request,HttpServletResponse response,User u){
		Map<String,Object> map = new HashMap<String, Object>();
		u = commonDao.queryObjectByEntity(u);
		map.put("data", u);
		return JsonUtil.object2Json(map);
	}
	
	@RequestMapping(path="/testurl",method=RequestMethod.PUT)
	public void test(@RequestBody String body,Writer writer) throws IOException{
		writer.write(body);
	}
	
	@ResponseBody
	@RequestMapping("responsebody")
	public String testResponseBody(){
		return "hello world!";
	}
	
}
