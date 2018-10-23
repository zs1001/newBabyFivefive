package com.dooshen.newbabyfivefive;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.dooshen.newbabyfivefive.common.util.FastJsonUtils;
import com.dooshen.newbabyfivefive.common.util.HttpClientUtils;
import com.dooshen.newbabyfivefive.common.util.JSONUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//http://f.apiplus.net/cqssc-20.xml
    	
    	String url= "http://f.apiplus.net/cqssc-20.json";
//    	String kj = "{\"code\":\"cqssc\",\"data\":[{\"expect\":\"20181011054\",\"opencode\":\"5,7,2,8,3\",\"opentimestamp\":1539241240,\"opentime\":\"2018-10-11 15:00:40\"}],\"rows\":1,\"info\":\"免费接口随机延迟3-6分钟，实时接口请访问www.opencai.net查询、购买或续费\"}";
    	//http请求获取开奖
    	String kj = HttpClientUtils.sendHttpGet(url);
    	System.out.println(kj);
    	JSON json = JSONObject.parseObject(kj);
    	System.out.println(json);
    
    	//----------------------------------------------
    	//开奖结果转json
    	JSONObject jsonObject = JSON.parseObject(kj); 
    	
		JSONArray data = jsonObject.getJSONArray("data");
		String  date1= data.toString();
		System.out.println("data>>" + date1);
		
		List<Map<String, Object>>  ss = JSONUtils.getJsonToListMap(date1);
		int ss1= ss.size()-1;
		int[] last  = new int[20];
		for (int i = ss1; i > -1; i--) {
				System.out.println(ss.get(i).get("opencode")+"------>"+ss.get(i).get("opentime"));
				String opencode1 = (String) ss.get(i).get("opencode");
				String foo =  opencode1.substring(opencode1.length()-1,opencode1.length());
//				System.out.println(foo);
//				System.out.println("last"+Integer.parseInt(foo));
				last[ss1]=Integer.parseInt(foo);
				ss1--;
				System.out.println("-----------------ss1-->"+ss1);
		}

		
		
      
    }
}
