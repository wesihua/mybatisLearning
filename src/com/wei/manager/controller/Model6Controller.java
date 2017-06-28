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

import com.wei.manager.bean.Model6;
import com.wei.manager.dao.CommonDao;
import com.wei.manager.mapper.Model6Mapper;
import com.wei.manager.util.JsonUtil;

@Controller
@RequestMapping("manager/model6")
public class Model6Controller {
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private Model6Mapper modelMapper;
	
	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpServletResponse response,Model6 model){
		//查询所有角色
		List<Model6> list = commonDao.getEntityByPageWithRowMapper(model);
		request.setAttribute("list", list);
		return "model6/model6List";
	}
	@RequestMapping("query")
	public String query(HttpServletRequest request,HttpServletResponse response,Model6 model){
		//查询所有角色
		List<Model6> list = commonDao.getEntityByPageWithRowMapper(model);
		request.setAttribute("list", list);
		return "model6/model6Query";
	}
	
	@RequestMapping("editModel")
	public String editModel(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			return "model6/editModel6";
		}
		//查询role信息
		Model6 m = new Model6();
		m.setId(Integer.parseInt(id));
		Model6 model = commonDao.queryObjectByEntity(m);
		request.setAttribute("bean", model);
		return "model6/editModel6";
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
	public String saveModel(HttpServletRequest request,HttpServletResponse response,Model6 model){
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
