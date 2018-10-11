package com.dooshen.newbabyfivefive;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.dooshen.newbabyfivefive.common.HttpClientUtils;

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
    	
    	String kj = HttpClientUtils.sendHttpGet(url);
    	System.out.println(kj);
        System.out.println( "Hello World!" );
        
      
    }
}
