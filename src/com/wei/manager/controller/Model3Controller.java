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

import com.wei.manager.bean.Model3;
import com.wei.manager.dao.CommonDao;
import com.wei.manager.mapper.Model3Mapper;
import com.wei.manager.util.JsonUtil;

@Controller
@RequestMapping("manager/model3")
public class Model3Controller {

	@Autowired
	private CommonDao commonDao;
	@Autowired
	private Model3Mapper modelMapper;
	
	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpServletResponse response,Model3 model){
		//查询所有角色
		List<Model3> list = commonDao.getEntityByPageWithRowMapper(model);
		request.setAttribute("list", list);
		return "model3/model3List";
	}
	@RequestMapping("query")
	public String query(HttpServletRequest request,HttpServletResponse response,Model3 model){
		//查询所有角色
		List<Model3> list = commonDao.getEntityByPageWithRowMapper(model);
		request.setAttribute("list", list);
		return "model3/model3Query";
	}
	
	@RequestMapping("editModel")
	public String editModel(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)){
			return "model3/editModel3";
		}
		//查询role信息
		Model3 m = new Model3();
		m.setId(Integer.parseInt(id));
		Model3 model = commonDao.queryObjectByEntity(m);
		request.setAttribute("bean", model);
		return "model3/editModel3";
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
	public String saveModel(HttpServletRequest request,HttpServletResponse response,Model3 model){
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
