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

import com.wei.manager.bean.Model5;
import com.wei.manager.dao.CommonDao;
import com.wei.manager.mapper.Model5Mapper;
import com.wei.manager.util.JsonUtil;

@Controller
@RequestMapping("manager/model5")
public class Model5Controller {

	@Autowired
	private CommonDao commonDao;
	@Autowired
	private Model5Mapper modelMapper;
	
	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpServletResponse response,Model5 model){
		//查询所有角色
		List<Model5> list = commonDao.getEntityByPageWithRowMapper(model);
		request.setAttribute("list", list);
		return "model5/model5List";
	}
	@RequestMapping("query")
	public String query(HttpServletRequest request,HttpServletResponse response,Model5 model){
		//查询所有角色
		List<Model5> list = commonDao.getEntityByPageWithRowMapper(model);
		request.setAttribute("list", list);
		return "model5/model5Query";
	}
	
	@RequestMapping("editModel")
	public String editModel(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			return "model5/editModel5";
		}
		//查询role信息
		Model5 m = new Model5();
		m.setId(Integer.parseInt(id));
		Model5 model = commonDao.queryObjectByEntity(m);
		request.setAttribute("bean", model);
		return "model5/editModel5";
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
	public String saveModel(HttpServletRequest request,HttpServletResponse response,Model5 model){
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
