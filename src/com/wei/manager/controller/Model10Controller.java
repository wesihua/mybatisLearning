package com.wei.manager.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wei.manager.bean.Model10;
import com.wei.manager.dao.CommonDao;
import com.wei.manager.mapper.Model10Mapper;
import com.wei.manager.util.JsonUtil;

@Controller
@RequestMapping("manager/model10")
public class Model10Controller {
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private Model10Mapper modelMapper;
	
	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpServletResponse response,Model10 model){
		//查询所有角色
		List<Model10> list = commonDao.getEntityByPageWithRowMapper(model);
		request.setAttribute("list", list);
		return "model10/model10List";
	}
	@RequestMapping("query")
	public String query(HttpServletRequest request,HttpServletResponse response,Model10 model){
		//查询所有角色
		List<Model10> list = commonDao.getEntityByPageWithRowMapper(model);
		request.setAttribute("list", list);
		return "model10/model10Query";
	}
	
	@RequestMapping("editModel")
	public String editModel(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			return "model10/editModel10";
		}
		//查询role信息
		Model10 m = new Model10();
		m.setId(Integer.parseInt(id));
		Model10 model = commonDao.queryObjectByEntity(m);
		request.setAttribute("bean", model);
		return "model10/editModel10";
	}
	@ResponseBody
	@RequestMapping("deleteModel")
	public String deleteModel(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			modelMapper.deleteByPrimaryKey(Integer.parseInt(id));
			map.put("flag", "0");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("flag", "2");
		}
		return JsonUtil.object2Json(map);
	}
	
	@RequestMapping("saveModel")
	public String saveModel(HttpServletRequest request,HttpServletResponse response,Model10 model){
		if(null == model.getId()){
			//新增
			model.setName9(new Date());
			modelMapper.insertSelective(model);
		}
		else{
			//更改
			modelMapper.updateByPrimaryKeySelective(model);
		}
		return "redirect:list";
	}
}
