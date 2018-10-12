package com.dooshen.newbabyfivefive.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public final class FastJsonUtils {

//	使用fastjson将不规则的json串转成java对象 
//	JSONObject obj = JSON.parseObject(requestResult); 
//	JSONObject 是一个map嵌套map的对象
//
//	public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray 
//	public static final JSONObject parseObject(String text); // 把JSON文本parse成JSONObject 
//	public static final T parseObject(String text, Class clazz); // 把JSON文本parse为JavaBean 
//	public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray 
//	public static final List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合 
//	public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本 
//	public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本 
//	public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。 
	
	//JSONArray对象通过getJSONObject(index)方法取得数组里面的JSONObject对象 
		/*JSONObject ao = jsarr.getJSONObject(0);
		System.out.println("JSONObject里面的JSONArray里面的第一个JSONObject:/n" + ao + "/n");
	*/
	//JSONobject对象通过key直接取得String的值 
		//String str = ao.getString("骚话1"); 
		//System.out.println("JSONObject里面的JSONArray里面的第一个JSONObject里的键值对对Key取值:/n" + str + "/n"); 
	
	/**
	 * 把JSON文本parse为JSONArray
	 * @param text
	 */
	

}
