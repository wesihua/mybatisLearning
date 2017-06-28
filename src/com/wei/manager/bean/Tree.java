package com.wei.manager.bean;

import java.util.ArrayList;
import java.util.List;

public class Tree {

	private List<Menu> menues;
	
	public Tree(List<Menu> list){
		this.menues = list;
	}
	
	/**
	 * 构建菜单树
	 * @return
	 */
	public List<Menu> buildMenu(){
		List<Menu> roots = getRootMenu();
		for(Menu root : roots){
			buildTree(root);
		}
		return roots;
	}
	
	/**
	 * 获取给定菜单的所有子菜单集合
	 * @param menu
	 * @return
	 */
	private List<Menu> getChildren(Menu menu){
		List<Menu> list = new ArrayList<Menu>();
		Integer id = menu.getId();
		for (int i = 0; i < menues.size(); i++) {
			if(null != menues.get(i).getParentId()){
				if(menues.get(i).getParentId().equals(id)){
					list.add(menues.get(i));
					continue;
				}
			}
			
		}
		return list;
	}
	
	/**
	 * 返回所有根节点
	 * @param menues
	 * @return
	 */
	public List<Menu> getRootMenu(){
		List<Menu> rootList = new ArrayList<Menu>();
		for (int i = 0; i < menues.size(); i++) {
			if(null == menues.get(i).getParentId() || menues.get(i).getParentId() == 0){
				rootList.add(menues.get(i));
			}
		}
		return rootList;
	}
	
	/**
	 * 递归构建
	 * @param root
	 */
	public void buildTree(Menu root){
		List<Menu> children = getChildren(root);
		if(!children.isEmpty()){
			root.setChildren(children);
			for(int i=0; i<children.size(); i++){
				Menu m = children.get(i);
				buildTree(m);
			}
		}
	}
}
