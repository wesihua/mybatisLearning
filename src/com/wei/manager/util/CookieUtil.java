package com.wei.manager.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对cookie的操作
 * @author weisihua
 *
 */
public class CookieUtil {

	/**
	 * 新增cookie
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
		Cookie cookie = new Cookie(name,value);
		cookie.setPath("/");
		if(maxAge <= 0){
			cookie.setMaxAge(7*24*60*60*1000);//7天
		}
		else{
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}
	/**
	 * 根据name获取cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
		Map<String,Cookie> map = readCookieMap(request);
		if(map.containsKey(name)){
			return map.get(name);
		}
		else{
			return null;
		}
	}
	
	/**
	 * 根据name获取cookie中的值
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getValueByName(HttpServletRequest request,String name){
		Map<String,Cookie> map = readCookieMap(request);
		if(map.containsKey(name)){
			return map.get(name).getValue();
		}
		else{
			return null;
		}
	}
	
	/**
	 * 将cookie放到map中以便读取
	 * @param request
	 * @return
	 */
	public static Map<String,Cookie> readCookieMap(HttpServletRequest request){
		Map<String,Cookie> map = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if(null != cookies){
			for(Cookie cookie : cookies){
				map.put(cookie.getName(), cookie);
			}
		}
		return map;
	}
}
