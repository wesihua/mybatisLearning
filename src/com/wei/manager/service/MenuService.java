package com.wei.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wei.manager.bean.Menu;
import com.wei.manager.bean.Tree;
import com.wei.manager.dao.CommonDao;
import com.wei.manager.mapper.MenuMapper;

@Service
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private CommonDao commonDao;
	
	/**
	 * 构建菜单树
	 * @return
	 */
	public List<Menu> buildTreeMenu(List<Menu> menues){
		//List<Menu> menues = menuMapper.selectAll();
		Tree tree = new Tree(menues);
		List<Menu> list = tree.buildMenu();
		return list;
	}
	
	
	
}
