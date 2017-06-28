package com.wei.manager.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

	public String pring(){
		System.out.println("依赖注入成功");
		return "依赖注入成功";
	}
}
