package com.dooshen.newbabyfivefive.common.util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;


public class JSONUtils {
	/**
     * String转JSON字符串
     * 
     * @param key
     * @param value
     * @return
     */
    public static String stringToJsonByFastjson(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, value);
        return beanToJson(map, null);
    }
    
    /**
     * Bean对象转JSON
     * 
     * @param object
     * @param dataFormatString
     * @return
     */
    public static String beanToJson(Object object, String dataFormatString) {
        if (object != null) {
            if (StringUtils.isEmpty(dataFormatString)) {
                return JSONObject.toJSONString(object);
            }
            return JSON.toJSONStringWithDateFormat(object, dataFormatString);
        } else {
            return null;
        }
    }

    /**
     * Bean对象转JSON
     * 
     * @param object
     * @return
     */
    public static String beanToJson(Object object) {
        if (object != null) {
            return JSON.toJSONString(object);
        } else {
            return null;
        }
    }


    /**
     * 将json字符串转换成对象
     * 
     * @param json
     * @param clazz
     * @return
     */
    public static Object jsonToBean(String json, Object clazz) {
        if (StringUtils.isEmpty(json) || clazz == null) {
            return null;
        }
        return JSON.parseObject(json, clazz.getClass());
    }

    /**
     * json字符串转map
     * 
     * @param json
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonToMap(String json) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return JSON.parseObject(json, Map.class);
    }
    
    //------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------
    
    /**
	 * 将字符串转JSON对象
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject toStringJson(String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		return jsonObject;
	}

	/**
	 * 将对象转为string字符串
	 * 
	 * @param obj
	 * @return
	 */

	public static String toJsonString(Object obj) {
		String str = obj.toString();
		return str;
	}

	/**
		 * 将JSONArray对象转换成list集合
		 * 
		 * @param jsonArr
		 * @return
		 */
	  public static List<Map<String, Object>> getJsonToListMap(String jsonData) {
	        return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
	        });
	    }



	/**
	 * 将JSON字符串转换成对象
	 * 
	 * @param jsonString
	 * @param type
	 * @return
	 */

	// jsonStringfromJson

	/**
	 * 将JSONArray对象转换成list集合
	 * 
	 * @param jsonArr
	 * @return
	 */
	/*
	 * public static List<Object> jsonToList(JSONArray jsonArr) { List<Object> list
	 * = new ArrayList<Object>(); for (Object obj : jsonArr) { if (obj instanceof
	 * JSONArray) { list.add(jsonToList((JSONArray) obj)); } else if (obj instanceof
	 * JSONObject) { list.add(jsonToMap((JSONObject) obj)); } else { list.add(obj);
	 * } } return list; }
	 */
	/**
	 * 将json字符串转换成map对象
	 * 
	 * @param json
	 * @return
	 */
	/*
	 * public static Map<String, Object> jsonStringToMap(String json) { JSONObject
	 * obj = JSONObject.fromObject(json); return jsonToMap(obj); }
	 */

	/**
	 * 将JSONObject转换成map对象
	 * 
	 * @param obj
	 * @return
	 */
	/*
	 * public static Map<String, Object> jsonToMap(JSONObject obj) { Set<?> set =
	 * obj.keySet(); Map<String, Object> map = new HashMap<String,
	 * Object>(set.size()); for (Object key : obj.keySet()) { Object value =
	 * obj.get(key); if (value instanceof JSONArray) { map.put(key.toString(),
	 * jsonToList((JSONArray) value)); } else if (value instanceof JSONObject) {
	 * map.put(key.toString(), jsonToMap((JSONObject) value)); } else {
	 * map.put(key.toString(), obj.get(key)); } } return map; }
	 */
	/**
	 * 将map转换json
	 * 
	 * @param map
	 * @return
	 */
	/*
	 * public static String mapToJson(Map<String, String> map) { Set<String> keys =
	 * map.keySet(); String key = ""; String value = ""; StringBuffer jsonBuffer =
	 * new StringBuffer(); jsonBuffer.append("{"); for (Iterator<String> it =
	 * keys.iterator(); it.hasNext();) { key = (String) it.next(); value =
	 * map.get(key); jsonBuffer.append(key + ":" + "\"" + value + "\""); if
	 * (it.hasNext()) { jsonBuffer.append(","); } } jsonBuffer.append("}"); return
	 * jsonBuffer.toString(); }
	 */
}
