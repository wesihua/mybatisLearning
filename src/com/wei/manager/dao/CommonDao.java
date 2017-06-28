package com.wei.manager.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Repository;

import com.wei.manager.bean.Role;
import com.wei.manager.util.CommonUtil;
import com.wei.manager.util.MyRowMapper;

@Repository
public class CommonDao {

	//bean所在包名
	private static final String packageName = "com.wei.manager.bean.";
	//bean中属性为对象的类型的标识：com.wei.manager.bean.Role
	private static final String prefix = "com.wei.manager";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据传入的实体名称（javabean's name）返回所有的数据【效率低】
	 * @param tablename：javabean's name
	 * @return 任意对象的list
	 */
	public <T> List<T> queryAllEntityByTablename(String tablename){
		//将实体名称的第一个字母变成大写
		tablename = CommonUtil.toCapitalString(tablename);
		String entity = packageName+tablename;
		String sql = "select * from "+tablename+" order by id desc";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		//List<Object> data = getAllProperties("com.wei.manager.bean.User",list);
		List<T> data = mapToList(entity, list);
		return data;
	}
	
	/**
	 * 根据传入的实体名称（javabean's name）返回所有的数据,用rowMapper的方法
	 * @param tablename：javabean's name
	 * @return 任意对象的list
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryAllByEntityName(String tablename){
		//将实体名称的第一个字母变成大写
		tablename = toCapitalString(tablename);
		String entity = packageName+tablename;
		String sql = "select * from "+tablename+" order by id desc";
		List<T> list = null;
		T t = null;
		try {
			t = (T) Class.forName(entity).newInstance();
			list = jdbcTemplate.query(sql, new MyRowMapper<T>(t));
			//给bean中的对象赋值
//			if(null != list && list.size() > 0){
//				for(int i=0; i<list.size(); i++){
//					T temp = list.get(i);
//					wrapObject(temp);
//					wrapList(temp);
//				}
//			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据实体返回对象
	 * @param t
	 * @return
	 */
	public <T> T queryObjectByEntity(T t){
		String entity = t.getClass().getName();
		String tablename = entity.substring(entity.lastIndexOf(".")+1,entity.length());
		if(StringUtils.isBlank(CommonUtil.getter(t, "id"))){
			return null;
		}
		int id = Integer.parseInt(CommonUtil.getter(t, "id"));
		String sql = "select * from "+tablename+" where id = "+id;
		try {
			t = jdbcTemplate.queryForObject(sql, new MyRowMapper<T>(t));
		} catch (DataAccessException e) {
			// 改方法如果查不到值不会返回 null 会抛异常【网上建议用queryforlist】
			return null;
		}
		return t;
		//给bean中的对象赋值
		//return wrapObject(t);
	}
	
	/**
	 * 根据实体返回对象集合
	 * @param t
	 * @param keyNae 外键名称
	 * @param value 外键值
	 * @return
	 */
	public <T> List<T> queryListByEntityAndForeignKey(T t,String keyName,String value){
		String entity = t.getClass().getName();
		String tablename = entity.substring(entity.lastIndexOf(".")+1,entity.length());
		List<T> result = new ArrayList<T>();
		if(StringUtils.isBlank(value)){
			return null;
		}
		try {
			if(null != t.getClass().getDeclaredField(keyName)){
				String sql = "select * from "+tablename+" where "+CommonUtil.camelsToUnderline(keyName).toLowerCase()+" = "+value;
				result = jdbcTemplate.query(sql, new MyRowMapper<T>(t));
			}
			else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	/**
	 * 包装对象，给bean中的对象属性赋值
	 * 所有对象命名规范：role【对象小写<不是驼峰>】
	 * @param T 传入的对象
	 * @return T 封装好的对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T wrapObject(T t){
		//获取bean中的所有对象属性
		//List<Field> fieldList = new ArrayList<Field>();
		Field[] fields = t.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String name = field.getName();//role
			String type = field.getType().getName();//com.wei.manager.bean.Role
			if(type.startsWith(prefix)){
				//表明该属性是个对象
				//1.拼接该bean中对应于该对象属性的外键，如：roleId -> Role role
				String foreignKey = name+"Id";
				//2.获取该外键的值
				String value = getter(t, foreignKey);
				if(StringUtils.isNotBlank(value)){
					try {
						//3.实例化要封装的属性
						T temp = (T) Class.forName(type).newInstance();
						//4.赋值id
						Field f = temp.getClass().getDeclaredField("id");
						f.setAccessible(true);
						f.set(temp, Integer.parseInt(value));
						//CommonUtil.setter(temp, "id", value, fieldType);
						//5.取得赋过值的对象
						temp = queryObjectByEntity(temp);
						//6.最后放进bean中
						if(null != temp){
							CommonUtil.setter(t, name, temp, temp.getClass());
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				continue;
			}
		}
		return t;
	}
	
	/**
	 * 包装list，给bean中的list属性赋值
	 * 所有的list属性命名规范：userList【对象小写<不是驼峰>+List】
	 * @param T 传入的对象
	 * @return T 封装好的对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T wrapList(T t){
		try {
			//获取对象名称小写，用于外键名
			String className = t.getClass().getName();
			String entityName = className.substring(className.lastIndexOf(".")+1,className.length());
			//获取该实体bean的主键（id）值，用于查询list
			Field f = t.getClass().getDeclaredField("id");
			String id = CommonUtil.getField(f, t);
			//获取bean中的所有list属性
			Field[] fields = t.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String name = field.getName();//例：userList
				String type = field.getType().getName();//例：java.util.List
				if("java.util.List".equals(type)){
					//表明该属性是list
					//1.获取本属性对应的对象
					int a = name.lastIndexOf("L");
					String propertyName = CommonUtil.toCapitalString(name.substring(0,a));//例：user
					//准备查询条件
					T propertyEntity = (T) Class.forName(packageName+propertyName).newInstance();//实体
					String keyName = entityName.toLowerCase()+"Id";
					String value = id;
					//查询数据并封装
					List<T> list = queryListByEntityAndForeignKey(propertyEntity,keyName,value);
					//将查询到的list封装到原来的bean中
					CommonUtil.setField(field, t, list);
					continue;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * 根据传入的实体名称（javabean's name）返回分页数据 【已废弃】
	 * @param tablename：javabean's name
	 * @param conditions：查询条件
	 * @return 任意对象的list
	 */
	public <T> List<T> getEntityByPage2(String tablename,Map<String,String> conditions){
		//将实体名称的第一个字母变成大写
		tablename = toCapitalString(tablename);
		String entity = packageName+tablename;
		//取conditions的值封装Object
		T t = getObjectWithData(entity, conditions);
		String sql = "select * from "+tablename+" where 1=1";
		//拼接查询条件
		for(Map.Entry<String, String> entry : conditions.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			if(StringUtils.isNotBlank(getter(t,key))&&!"pageSize".equals(key)&&!"pageNow".equals(key)){
				if("id".equalsIgnoreCase(key)){
					sql += " and id ="+value;
				}
				else{
					sql += " and "+camelsToUnderline(key)+" like %"+value+"%";
				}
				continue;
			}
		}
		sql += "order by id desc limit "+getter(t, "pageSize")+"*"+(Integer.parseInt(getter(t, "pageNow"))-1)+" , "+getter(t, "pageSize");
		System.out.println("分页的sql："+sql);
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		//转成实体对象list
		List<T> data = mapToList(entity, list);
		return data;
	}
	
	/**
	 * 分页查询--根据传入的实体名称（javabean） mapToList的方法
	 * @param t 传入的实体对象
	 * @return
	 */
	public <T> List<T> getEntityByPage(T t){
		List<T> result = new ArrayList<T>();
		String entity = t.getClass().getName();
		//取得数据库中的tableName
		String tablename = entity.substring(entity.lastIndexOf(".")+1,entity.length());
		String sql = "select * from "+tablename+" where 1=1";
		//拼接查询条件  根据对象中的属性
		Field[] fields = null;
		try {
			//fields中没有继承于page的属性
			fields = t.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				//Class<?> type = field.getType();
				String fieldName = field.getName();
				//从前台传过来的值只能是string
				String value = getter(t, fieldName);
				if(StringUtils.isNotBlank(value)){
					if("id".equalsIgnoreCase(fieldName)){
						sql += " and id ="+value;
					}
					else{
						sql += " and "+camelsToUnderline(fieldName)+" like '%"+value+"%'";
					}
					continue;
				}
			}
			sql += " order by id desc limit "+Integer.parseInt(getter(t, "pageSize"))*(Integer.parseInt(getter(t, "pageNow"))-1)+" , "+getter(t, "pageSize");
			System.out.println("mapToList分页的sql："+sql);
			//查询所有数据
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			//查询rowCount
			int rows = jdbcTemplate.queryForObject("select count(1) from "+tablename, Integer.class);
			result = mapToList(entity, list);
			//计算pageCount
			for(T temp : result){
				//给page赋值
				CommonUtil.setter(temp, "pageNow", Integer.valueOf(CommonUtil.getter(t, "pageNow")), int.class);
				CommonUtil.setter(temp, "rowCount", rows, int.class);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 分页查询--用rowMapper的方法
	 * @param t 传入的实体对象
	 * @return
	 */
	public <T> List<T> getEntityByPageWithRowMapper(T t){
		List<T> result = new ArrayList<T>();
		String entity = t.getClass().getName();
		//取得数据库中的tableName
		String tablename = entity.substring(entity.lastIndexOf(".")+1,entity.length());
		String sql = "select * from "+tablename+" where 1=1";
		//拼接查询条件  根据对象中的属性
		Field[] fields = null;
		try {
			//fields中没有继承于page的属性
			fields = t.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				//Class<?> type = field.getType();
				String fieldName = field.getName();
				//从前台传过来的值只能是string
				String value = getter(t, fieldName);
				if(StringUtils.isNotBlank(value)){
					if("id".equalsIgnoreCase(fieldName)){
						sql += " and id ="+value;
					}
					else{
						sql += " and "+camelsToUnderline(fieldName)+" like '%"+value+"%'";
					}
					continue;
				}
			}
			sql += " order by id desc limit "+Integer.parseInt(getter(t, "pageSize"))*(Integer.parseInt(getter(t, "pageNow"))-1)+" , "+getter(t, "pageSize");
			System.out.println("rowMapper分页的sql："+sql);
			//查询所有数据
			//List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			//result = mapToList(entity, list);
			result = jdbcTemplate.query(sql, new MyRowMapper<T>(t));
			//查询rowCount
			int rows = jdbcTemplate.queryForObject("select count(1) from "+tablename, Integer.class);
			
			if(null != result && result.size() > 0){
				for(T temp : result){
					//给page赋值
					CommonUtil.setter(temp, "pageNow", Integer.valueOf(CommonUtil.getter(t, "pageNow")), int.class);
					CommonUtil.setter(temp, "rowCount", rows, int.class);
					//给bean中的对象赋值
					wrapObject(temp);
					//给bean中的list赋值
					wrapList(temp);
				}
			}
			
//			//给bean中的对象赋值
//			if(null != list && list.size() > 0){
//				for(int i=0; i<list.size(); i++){
//					T temp = list.get(i);
//					wrapObject(temp);
//				}
//			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	/*
	 * 查询对象下的所有属性(必须和数据库一模一样全部小写。不要 _ 直接：roleid)
	 * 此方法废弃
	 */
	public List<Object> getAllProperties(String entity,List<Map<String,Object>> list){
		Field[] fields = null;
		Map<String,Object> map = new HashMap<String, Object>();
		List<Object> data = new ArrayList<Object>();
		try {
			Class<?> demo = Class.forName(entity);
			fields = demo.getDeclaredFields();
			for(int i=0;i<list.size();i++){
				//唯一的结果
				map = list.get(i);
				//一个实例
				Object obj = demo.newInstance();
				for(int j=0;j<fields.length;j++){
					String name = fields[j].getName().toUpperCase();
					Class<?> type = fields[j].getType();
					//获取相应字段的值并调用实体的set方法
					if(null != map.get(name)){
						setter(obj,name,map.get(name),type);
					}
				}
				data.add(obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	/*
	 * 将jdbcTemplate返回的List<Map<String,Object>>
	 * 转成javabean
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> mapToList(String entity,List<Map<String,Object>> list){
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
					String name = CommonUtil.camelsToUnderline(fields[j].getName()).toUpperCase();
					//获取属性的类型
					Class<?> type = fields[j].getType();
					//将bean中属性的第一字母变成大写：name->Name用于拼接setName
					String capitalName = CommonUtil.toCapitalString(fields[j].getName());
					//获取相应字段的值并调用实体的set方法
					if(null != map.get(name)){
						CommonUtil.setter(obj,capitalName,map.get(name),type);
					}
				}
				//给obj中的对象赋值
				wrapObject(obj);
				//给obj中的list赋值
				wrapList(obj);
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
			method = obj.getClass().getMethod("set"+capitalName, type);
			method.invoke(obj, value);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * 将字符串的第一个字母变成大写
	 */
//	public String toCapitalString(String name){
//		String a = name.substring(0, 1).toUpperCase();
//		a = a+name.substring(1,name.length());
//		return a;
//	}
	
	
	/*
	 * 将字符串的第一个字母变成大写（考虑role_id的情况）
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
	 * 驼峰变下划线
	 * @param name
	 * @return
	 */
	public static String camelsToUnderline(String name){
		int index = checkUpperIndex(name);
		StringBuilder sb = new StringBuilder();
		if(index > 0){
			sb.append(name.substring(0, index)).append("_").append(name.substring(index, name.length()));
		}
		else{
			return name;
		}
		return sb.toString();
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
	 * 给对象封装数据并返回
	 * @param entity
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getObjectWithData(String entity,Map<String,String> map){
		T t = null;
		try {
			Class<?> demo = Class.forName(entity);
			t = (T) demo.newInstance();
			//遍历map并封装
			for(Map.Entry<String, String> entry : map.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				//获得T中的相应key的属性
				Field field = demo.getField(key);
				if(field != null){
					Class<?> type = field.getType();
					//调用set方法
					setter(t, key, value, type);
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	public static void main(String[] args) {
//		System.out.println("i".equals("I"));
//		System.out.println(toCapitalString("role_id"));
//		System.out.println(camelsToUnderline("roleId"));
		try {
			//Method method = Page.class.getMethod("getPageNow");
//			Method method = User.class.getMethod("setPageSize",int.class);
//			System.out.println(method.getName());
//			System.out.println(int.class.getName()+"===="+Integer.class.getName());
//			Page p = new User();
//			p.setRowCount(10);
//			User u = (User)p;
//			System.out.println(p.getRowCount());
//			System.out.println(u.getRowCount());
			
			
			//测试对象类型
			System.out.println(Role.class.getName());
			Field[] fields = Role.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String name = field.getName();
				Class<?> type = field.getType();
				System.out.println(name+"    的类型是：   "+type.getName() +"----"+ (type instanceof Object));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
