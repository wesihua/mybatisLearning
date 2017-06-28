package com.wei.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wei.manager.bean.Menu;
import com.wei.manager.bean.User;
import com.wei.manager.mapper.MenuRightMapper;
import com.wei.manager.mapper.UserMapper;
import com.wei.manager.service.MenuService;

/**
 * 登录controller
 * @author weish
 *
 */
@Controller
@RequestMapping("manager/login")
public class LoginController {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MenuRightMapper menuRightMapper;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("index")
	public String gotoLogin(){
		return "login";
	}
	
	@RequestMapping("main")
	public String main(HttpServletRequest request,HttpServletResponse response){
		//校验登录
		String username = StringUtils.isBlank(request.getParameter("username")) ? "" : request.getParameter("username");
		String password = StringUtils.isBlank(request.getParameter("password")) ? "" : request.getParameter("password");
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", username);
		map.put("password", password);
		List<User> list = userMapper.selectByNameAndPass(map);
		if(null == list || list.size() == 0){
			request.setAttribute("msg", "用户名或密码错误");
			return "login";
		}
		//查询改用户的所有菜单权限
		User user = list.get(0);
		request.getSession().setAttribute("user", user);
//		List<Menu> menus = menuRightMapper.selectRoleMenu(user.getRoleId());
//		List<Menu> model = new ArrayList<Menu>();
//		List<Menu> pages = new ArrayList<Menu>();
//		Map<String,List<Menu>> result = new HashMap<String, List<Menu>>();
//		//取出所有一级菜单
//		for(int i=0; i<menus.size();i++){
//			if(menus.get(i).getLevel() == 1){
//				model.add(menus.get(i));
//			}
//			else{
//				pages.add(menus.get(i));
//			}
//		}
//		for(int i=0; i<model.size();i++){
//			String key = model.get(i).getName();
//			for(int j=0;j<pages.size();j++){
//				if(pages.get(j).getParentId() == model.get(i).getId()){
//					if(result.get(key) != null){
//						result.get(key).add(pages.get(j));
//					}
//					else{
//						List<Menu> temp = new ArrayList<Menu>();
//						temp.add(pages.get(j));
//						result.put(key, temp);
//					}
//					continue;
//				}
//			}
//		}
//		request.setAttribute("pages", result);
//		return "main";
		return "login_temp";
	}
	
	/**
	 * 查询出用户菜单并跳转到主页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("mainindex")
	public String index(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Menu> menus = menuRightMapper.selectRoleMenu(user.getRoleId());
		List<Menu> list = menuService.buildTreeMenu(menus);
		request.setAttribute("pages", list);
		return "main";
	}
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("logout")
	public String logOut(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("user");
		return "login";
	}
}
