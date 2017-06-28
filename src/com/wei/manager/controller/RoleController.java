package com.wei.manager.controller;

import java.util.ArrayList;
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

import com.wei.manager.bean.Menu;
import com.wei.manager.bean.MenuRight;
import com.wei.manager.bean.Role;
import com.wei.manager.dao.CommonDao;
import com.wei.manager.mapper.MenuMapper;
import com.wei.manager.mapper.MenuRightMapper;
import com.wei.manager.mapper.RoleMapper;
import com.wei.manager.util.JsonUtil;

@Controller
@RequestMapping("manager/role")
public class RoleController {

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private MenuRightMapper menuRightMapper;
	
	@Autowired
	private CommonDao commonDao;
	
	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpServletResponse response,Role role){
		//查询所有角色
		List<Role> list = commonDao.getEntityByPageWithRowMapper(role);
		request.setAttribute("list", list);
		return "role/roleList";
	}
	@RequestMapping("query")
	public String query(HttpServletRequest request,HttpServletResponse response,Role role){
		//查询所有角色
		List<Role> list = commonDao.getEntityByPageWithRowMapper(role);
		request.setAttribute("list", list);
		return "role/roleQuery";
	}
	
	@RequestMapping("addRole")
	public String addRole(){
		return "role/editRole";
	}
	
	@RequestMapping("editRole")
	public String editRole(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		//查询role信息
		Role r = new Role();
		r.setId(Integer.parseInt(id));
		Role role = commonDao.queryObjectByEntity(r);
		request.setAttribute("bean", role);
		return "role/editRole";
	}
	@ResponseBody
	@RequestMapping("deleteRole")
	public String deleteRole(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			roleMapper.deleteByPrimaryKey(Integer.parseInt(id));
			map.put("flag", "0");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("flag", "1");
		}
		return JsonUtil.object2Json(map);
	}
	@ResponseBody
	@RequestMapping("saveRoleMenu")
	public String saveRoleMenu(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		String roleId = request.getParameter("roleId");
		String[] idArr = ids.split(",");
		try {
			//roleMapper.deleteByPrimaryKey(Integer.parseInt(id));
			menuRightMapper.deleteRightByRoleId(roleId);
			for (int i = 0; i < idArr.length; i++) {
				MenuRight bean = new MenuRight();
				bean.setCreatedate(new Date());
				bean.setMenuId(Long.valueOf(idArr[i]));
				bean.setRoleId(Long.valueOf(roleId));
				menuRightMapper.insertSelective(bean);
			}
			List<Menu> parentList = menuRightMapper.selectParentMenuByIds(idArr);
			for (int i = 0; i < parentList.size(); i++) {
				MenuRight bean = new MenuRight();
				bean.setCreatedate(new Date());
				bean.setMenuId(Long.valueOf(parentList.get(i).getParentId()));
				bean.setRoleId(Long.valueOf(roleId));
				menuRightMapper.insertSelective(bean);
			}
			map.put("flag", "0");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("flag", "1");
		}
		return JsonUtil.object2Json(map);
	}
	
	@RequestMapping("saveRole")
	public String saveRole(HttpServletRequest request,HttpServletResponse response,Role role){
		if(null == role.getId()){
			//新增
			role.setCreatedate(new Date());
			roleMapper.insertSelective(role);
		}
		else{
			//更改
			roleMapper.updateByPrimaryKeySelective(role);
		}
		return "redirect:list";
	}
	
	@RequestMapping("distributeMenu")
	public String distributeMenu(HttpServletRequest request,HttpServletResponse response,Role role){
		String roleId = request.getParameter("roleId");
		Map<String,List<Menu>> result = new HashMap<String, List<Menu>>();
		List<Menu> model = new ArrayList<Menu>();
		List<Menu> pages = new ArrayList<Menu>();
		
		List<Menu> menus = menuRightMapper.selectDistributeMenu(roleId);
		//取出所有一级菜单
				for(int i=0; i<menus.size();i++){
					if(menus.get(i).getLevel() == 1){
						model.add(menus.get(i));
					}
					else{
						pages.add(menus.get(i));
					}
				}
				for(int i=0; i<model.size();i++){
					String key = model.get(i).getName();
					for(int j=0;j<pages.size();j++){
						if(pages.get(j).getParentId() == model.get(i).getId()){
							if(result.get(key) != null){
								result.get(key).add(pages.get(j));
							}
							else{
								List<Menu> temp = new ArrayList<Menu>();
								temp.add(pages.get(j));
								result.put(key, temp);
							}
							continue;
						}
					}
				}
//		//查询所有一级菜单
//		List<Menu> parents = menuMapper.queryAllParentPage();
//		if(null != parents && parents.size() > 0){
//			for (int i = 0; i < parents.size(); i++) {
//				String parentName = parents.get(i).getName();
//				int parentId = parents.get(i).getId();
//				//查询子菜单集合
//				List<Menu> list = menuMapper.queryPagesByParentId(parentId);
//				map.put(parentName, list);
//			}
//		}
		request.setAttribute("roleId", roleId);
		request.setAttribute("map", result);
		return "role/selectMenu";
	}
}
