package com.wei.manager.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 公共的util
 * @author weisihua
 *
 */
public class CommonUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 生成随机的uuid
	 * @return
	 */
	public static String newUUID(){
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 判断第一个大写字母的位置
	 * @param str
	 * @return
	 */
	public static int checkUpperIndex(String str){
		int index = 0;
		for (int i = 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			if(!Character.isLowerCase(c)){
				index = i;
				break;
			}
		}
		return index;
	}
	/**
	 * 判断最后一个大写字母的位置
	 * @param str
	 * @return
	 */
	public static int checkLastUpperIndex(String str){
		int index = 0;
		for (int i = str.length()-1; i >= 0; i--) {
			Character c = str.charAt(i);
			if(!Character.isLowerCase(c)&&!Character.isDigit(c)){
				index = i;
				break;
			}
		}
		return index;
	}
	
	/**
	 * 驼峰变下划线
	 * @param name
	 * @return
	 */
	public static String camelsToUnderline(String name){
		int index = checkLastUpperIndex(name);
		StringBuilder sb = new StringBuilder();
		if(index > 0){
			sb.append(name.substring(0, index)).append("_").append(name.substring(index));
		}
		else{
			return name;
		}
		return sb.toString();
	}
	
	/**
	 * 将字符串的第一个字母变成大写（考虑role_id的情况）
	 * @param name
	 * @return
	 */
	public static String toCapitalString(String name){
		String result = "";
		if(name.indexOf("_")>0){
			int a = name.indexOf("_");
			result += toCapitalString(name.substring(0,a));
			String left = name.substring(a+1,name.length());
			result += toCapitalString(left);
		}
		else{
			result = name.substring(0, 1).toUpperCase();
			result = result+name.substring(1,name.length());
		}
		return result;
	}
	
	/**
     * @param obj 操作的对象
     * @param att 操作的属性
     * @param value 设置的值
     * @param type 参数的属性
     */
	public static void setter(Object obj, String att, Object value,Class<?> type){
		Method method;
		try {
			//将att变成第一个字母大写
			String capitalName = toCapitalString(att);
			if(null != value){
				method = obj.getClass().getMethod("set"+capitalName, type);
				method.invoke(obj, value);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * set方法  --根据field
	 * @param field
	 * @param obj
	 * @param value
	 */
	public static void setField(Field field,Object obj,Object value){
		field.setAccessible(true);
		try {
			field.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get方法 --根据field
	 * @param field
	 * @param obj
	 * @return
	 */
	public static String getField(Field field,Object obj){
		String result = "";
		try {
			field.setAccessible(true);
			result = field.get(obj).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * get方法
	 * @param obj
	 * @param att
	 * @return
	 */
	public static String getter(Object obj,String att){
		Method method = null;
		String result = "";
		try {
			//将att变成第一个字母大写
			String capitalName = toCapitalString(att);
			method = obj.getClass().getMethod("get"+capitalName);
			Object o = method.invoke(obj);
			if(null != o){
				result = o.toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * 将jdbcTemplate返回的List<Map<String,Object>>
	 * 转成javabean
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> mapToList(String entity,List<Map<String,Object>> list){
		Field[] fields = null;
		Map<String,Object> map = new HashMap<String, Object>();
		List<T> data = new ArrayList<T>();
		try {
			Class<?> demo = Class.forName(entity);
			//获取当前类中的所有属性
			fields = demo.getDeclaredFields();
			for(int i=0;i<list.size();i++){
				//取出list中的map结果
				map = list.get(i);
				//生成传入对象的实例
				T obj = (T) demo.newInstance();
				for(int j=0;j<fields.length;j++){
					//将javabean中的字段变成jdbc能够识别的形式：roleId->ROLE_ID
					String name = camelsToUnderline(fields[j].getName()).toUpperCase();
					//获取属性的类型
					Class<?> type = fields[j].getType();
					//将bean中属性的第一字母变成大写：name->Name用于拼接setName
					String capitalName = toCapitalString(fields[j].getName());
					//获取相应字段的值并调用实体的set方法
					if(null != map.get(name)){
						setter(obj,capitalName,map.get(name),type);
					}
				}
				//将set完值的对象放入待返回的list中
				data.add(obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 返回对象的所有属性【用于jdbcTemplate查询参数】
	 * @param obj
	 * @return
	 */
	public static Object[] getFields(Object obj){
		Field[] fields = obj.getClass().getDeclaredFields();
		Object[] result = new Object[fields.length];
		for (int i = 0; i < fields.length; i++) {
			result[i] = fields[i].getName();
		}
		return result;
	}
	/**
	 * 返回对象的所有属性类型【用于jdbcTemplate查询参数】
	 * @param obj
	 * @return
	 */
	public static Object[] getFieldTypes(Object obj){
		Field[] fields = obj.getClass().getDeclaredFields();
		Object[] result = new Object[fields.length];
		for (int i = 0; i < fields.length; i++) {
			result[i] = fields[i].getType().getName();
		}
		return result;
	}
	
	/**
	 * 日期转字符串
	 * @param date
	 * @return
	 */
	public static String Date2String(Date date){
		return sdf.format(date);
	}
	
	public static void main(String[] args) {
		String str = "name3";
		System.out.println(camelsToUnderline(str));
		System.out.println(Character.isLowerCase(3));
	}
}
