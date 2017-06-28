package com.wei.manager.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wei.manager.util.CommonUtil;

public class Menu extends Page{
    private Integer id;

    private String name;

    private String url;

    private Date createdate;

    private String description;

    private Integer parentId;

    private Integer level;

    private String roleId; //用于和menuright关联查询
    
    private String parentmenuname;
    
    private String createdatemc;
    
    private String levelName;
    
    private List<Menu> children = new ArrayList<Menu>();//子菜单集合


	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public String getParentmenuname() {
		return parentmenuname;
	}

	public void setParentmenuname(String parentmenuname) {
		this.parentmenuname = parentmenuname;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getCreatedatemc() {
		return createdatemc;
	}

	public void setCreatedatemc(String createdatemc) {
		this.createdatemc = createdatemc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
		this.createdatemc = CommonUtil.Date2String(createdate);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


    
}