package com.wei.manager.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class MyRowMapper<T> implements RowMapper<T>{

	private T t;
	public MyRowMapper(T temp){
		this.t = temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		//必须先实例化，否则会返回重复的数据
		try {
			t = (T) t.getClass().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//取得所有属性
		Field[] fields = t.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String name = field.getName();//id,username
			String type = field.getType().getName();//java.lang.String
			//考虑 int 的情况
			String realType = type.contains(".") ? type.substring(type.lastIndexOf(".")+1,type.length()).toLowerCase() : type.toLowerCase();
			if(isExistColumn(rs, CommonUtil.camelsToUnderline(name).toLowerCase())){
				switch (realType) {
				case "integer":
					CommonUtil.setter(t, name, rs.getInt(CommonUtil.camelsToUnderline(name).toLowerCase()), Integer.class);
					break;
				case "int":
					CommonUtil.setter(t, name, rs.getInt(CommonUtil.camelsToUnderline(name).toLowerCase()), int.class);
					break;
				case "string":
					CommonUtil.setter(t, name, rs.getString(CommonUtil.camelsToUnderline(name).toLowerCase()), String.class);
					break;
				case "float":
					CommonUtil.setter(t, name, rs.getFloat(CommonUtil.camelsToUnderline(name).toLowerCase()), Float.class);
					break;
				case "double":
					CommonUtil.setter(t, name, rs.getDouble(CommonUtil.camelsToUnderline(name).toLowerCase()), Double.class);
					break;
				case "date":
					CommonUtil.setter(t, name, rs.getTimestamp(CommonUtil.camelsToUnderline(name).toLowerCase()), Date.class);
					break;
				default:
					break;
				}
			}
			
		}
		return t;
	}

	/**
	 * 判断查询结果集中是否存在某列
	 * @param rs 查询结果集
	 * @param columnName 列名
	 * @return true 存在; false 不存咋
	 */
	public boolean isExistColumn(ResultSet rs, String columnName) {
	    try {
	        if (rs.findColumn(columnName) > 0 ) {
	            return true;
	        } 
	    }
	    catch (SQLException e) {
	        return false;
	    }
	     
	    return false;
	}
	
	public static void main(String[] args) {
		String key = "string";
		switch (key) {
		case "aaa":
			System.out.println("aaa");
			break;
		case "string":
			System.out.println("string");
			break;
		default:
			break;
		}
	}
}
