package com.wei.manager.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtil {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 将JSON数据转换为具体的对象
	 * @author weisihua
	 *
	 */
	public static <T> T json2Object(String jsonStr, Class<T> cla) {
		if("".equals(jsonStr)||jsonStr.length()==0||null==jsonStr){
			return null;
		}
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		if (jsonObject == null){
			return null;
		}
		Field[] fb = cla.getDeclaredFields();
		T t;
		try {
			t = cla.newInstance();
			for (int j = 0; j < fb.length; j++) {
				String fieldName = fb[j].getName();
				String fieldNameU = fieldName.substring(0, 1).toUpperCase()	+ fieldName.substring(1);
				Method method = cla.getMethod("set" + fieldNameU,fb[j].getType());
				//解析时间
				if(fb[j].getType().getName().indexOf("Date")>0){
					method.invoke(t,sdf.parse((String) jsonObject.get(fieldName)));
				}
				else{
					method.invoke(t, jsonObject.get(fieldName));
				}
				
			}
			return t;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 对象转json
	 * @param obj
	 * @return
	 */
	public static String object2Json(Object obj){
		if(null == obj){
			return "";
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		String result = JSONObject.fromObject(obj,config).toString();
		return result;
	}
	/**
	 * json数组转成list
	 * @param cls
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> json2List(Class<T> cls,String jsonStr){
		JSONArray array = JSONArray.fromObject(jsonStr);
		@SuppressWarnings("deprecation")
		List<T> list = JSONArray.toList(array, cls);
		return list;
	}
	/**
	 * list转成json
	 * @param list
	 * @return
	 */
	public static String list2Json(List<?> list){
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray array = JSONArray.fromObject(list,config);
		return array.toString();
	}
	
	public static void main(String[] args) {
		
		Person p = new Person();
		List<Person> list = new ArrayList<Person>();
		p.setNameStr("魏四化");
		p.setAge(0);
		p.setBirth(new Date());
		
		Person p2 = new Person();
		p2.setNameStr("张三");
		p2.setAge(10);
		p2.setBirth(new Date());
		Person p3 = new Person();
		p3.setNameStr(null);
		p3.setAge(10);
		p3.setBirth(new Date());
		list.add(p2);
		list.add(p3);
		p.setChildren(list);
		System.out.println("开始解析对象：");
		//System.out.println(JsonUtil.Object2Json(p));
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("key", null);
		map.put("name", null);
		map.put("sss", null);
		map.put("person", p);
		System.out.println(JsonUtil.object2Json(map));
		//String str = "{\"age\":0,\"birth\":\"2015-12-16 13:10:47\",\"children\":[],\"name\":\"魏四化\"}";
		//Person p2 = JsonUtil.convertToObj(str, Person.class);
		//Person p2 = (Person)JsonUtil.convertToObj(str, Person.class);
		//System.out.println(map2.getName());
		
		//测试json数组转List
		//String str = "[{\"age\":10,\"birth\":\"2015-12-16 14:11:42\",\"children\":[],\"nameStr\":\"张三\"},{\"age\":10,\"birth\":\"2015-12-16 14:11:42\",\"children\":[],\"nameStr\":\"李四\"}]";
		//List<Person> list2 = JsonUtil.json2List(Person.class, str);
		
		//测试List转json
		//String str = JsonUtil.object2Json(map);
		//System.out.println(str);
	}
}
