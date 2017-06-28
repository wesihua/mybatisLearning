package com.wei.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wei.manager.bean.Model;
import com.wei.manager.dao.CommonDao;
import com.wei.manager.mapper.CommonMapper;
import com.wei.manager.util.JsonUtil;
/**
 * 公共controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("manager/common")
public class CommonController {
	
	@Autowired
	private CommonMapper commonMapper;
	@Autowired
	private CommonDao commonDao;
	/**
	 * 用于查询select组件数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAll")
	public String getAllSelectData(HttpServletRequest request,HttpServletResponse response){
		String result = "";
		//获取table名称
		String table = request.getParameter("table");
		if(StringUtils.isBlank(table)){
			return result;
		}
		else{
			//List<?> list = commonDao.queryAllEntityByTablename(table);
			List<?> list = commonDao.queryAllByEntityName(table);
			result = JsonUtil.list2Json(list);
		}
		return result;
	}
	
}
