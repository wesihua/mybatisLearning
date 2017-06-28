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

import com.wei.manager.bean.Menu;
import com.wei.manager.dao.CommonDao;
import com.wei.manager.mapper.MenuMapper;
import com.wei.manager.service.MenuService;
import com.wei.manager.util.JsonUtil;

@Controller
@RequestMapping("manager/menu")
public class MenuController {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpServletResponse response,Menu menu){
		//查询所有菜单
		List<Menu> list = menuMapper.selectMenuByPage(menu);
		int count = menuMapper.selectMenuCountByPage(menu);
		menu.setRowCount(count);
		request.setAttribute("bean", menu);
		request.setAttribute("list", list);
		return "menu/menuList";
	}
	@RequestMapping("parentList")
	public String parentList(HttpServletRequest request,HttpServletResponse response,Menu menu){
		//查询所有父级菜单
		List<Menu> list = menuMapper.selectParentMenuByPage(menu);
		int count = menuMapper.selectParentMenuCount(menu);
		menu.setRowCount(count);
		request.setAttribute("bean", menu);
		request.setAttribute("list", list);
		return "menu/parentMenuList";
	}
	
	@RequestMapping("addMenu")
	public String addMenu(){
		return "menu/editMenu";
	}
	
	@ResponseBody
	@RequestMapping("getAllTopMenu")
	public String getAllTopMenu(HttpServletRequest request,HttpServletResponse response){
		//查询所有一级菜单
		List<Menu> list = menuMapper.queryAllParentPage();
		return JsonUtil.list2Json(list);
	} 
	
	@RequestMapping("editMenu")
	public String editMenu(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		//查询role信息
		Menu m = new Menu();
		m.setId(Integer.parseInt(id));
		Menu menu = commonDao.queryObjectByEntity(m);
		request.setAttribute("bean", menu);
		request.setAttribute("type", type);
		return "menu/editMenu";
	}
	@ResponseBody
	@RequestMapping("deleteMenu")
	public String deleteMenu(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			menuMapper.deleteByPrimaryKey(Integer.parseInt(id));
			map.put("flag", "0");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("flag", "1");
		}
		return JsonUtil.object2Json(map);
	}
	
	@RequestMapping("saveMenu")
	public String saveMenu(HttpServletRequest request,HttpServletResponse response,Menu menu){
		String level = "2";
		if(null != menu.getLevel() && menu.getLevel() != 0){
			level = menu.getLevel()+"";
		}
		if(null == menu.getId()){
			//新增
			menu.setCreatedate(new Date());
			menuMapper.insertSelective(menu);
		}
		else{
			//更改
			menuMapper.updateByPrimaryKeySelective(menu);
		}
		if("1".equals(level)){
			return "redirect:parentList";
		}
		return "redirect:list";
	}
}
