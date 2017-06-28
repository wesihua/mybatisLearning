package com.wei.manager.bean;

import java.util.Date;
import java.util.List;

import com.wei.manager.util.CommonUtil;

public class Role extends Page{
    private Integer id;

    private String name;

    private String description;

    private Date createdate;
    
    private String createdatemc;
    
    private List<User> userList;

    public String getCreatedatemc() {
		return createdatemc;
	}

	public void setCreatedatemc(String createdatemc) {
		this.createdatemc = createdatemc;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
        this.createdatemc = CommonUtil.Date2String(createdate);
    }
}