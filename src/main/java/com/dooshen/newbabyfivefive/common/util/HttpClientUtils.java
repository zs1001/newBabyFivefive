package com.dooshen.newbabyfivefive.common.util;
import java.io.File;

import java.io.IOException;

import java.security.KeyManagementException;

import java.security.KeyStoreException;

import java.security.NoSuchAlgorithmException;

import java.util.Iterator;

import java.util.List;

import java.util.Map;

import org.apache.http.HttpEntity;

import org.apache.http.HttpHost;

import org.apache.http.HttpStatus;

import org.apache.http.client.config.RequestConfig;

import org.apache.http.client.methods.CloseableHttpResponse;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.config.Registry;

import org.apache.http.config.RegistryBuilder;

import org.apache.http.conn.socket.ConnectionSocketFactory;

import org.apache.http.conn.socket.PlainConnectionSocketFactory;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

import org.apache.http.conn.ssl.SSLContextBuilder;

import org.apache.http.conn.ssl.TrustSelfSignedStrategy;

import org.apache.http.entity.ContentType;

import org.apache.http.entity.StringEntity;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import org.apache.http.entity.mime.content.FileBody;

import org.apache.http.entity.mime.content.StringBody;

import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;

import org.apache.http.impl.client.HttpClients;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import org.apache.http.util.EntityUtils;




public class HttpClientUtils {




    // utf-8字符编码

    public static final String CHARSET_UTF_8 = "utf-8";




    // HTTP内容类型。

    public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";




    // HTTP内容类型。相当于form表单的形式，提交数据

    public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";




    // HTTP内容类型。相当于form表单的形式，提交数据

    public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";




    // 连接管理器

    private static PoolingHttpClientConnectionManager pool;




    // 请求配置

    private static RequestConfig requestConfig;




    static {

        try {

            // 设置代理IP、端口、协议（请分别替换）

            HttpHost proxy = new HttpHost("10.94.4.6", 22222, "http");

            // System.out.println("初始化HttpClientTest~~~开始");

            SSLContextBuilder builder = new SSLContextBuilder();

            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());

            // 配置同时支持 HTTP 和 HTPPS

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()

                    .register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();

            pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry); // 初始化连接管理器

            pool.setMaxTotal(200); // 将最大连接数增加到200，实际项目最好从配置文件中读取这个值

            pool.setDefaultMaxPerRoute(2); // 设置最大路由

            int socketTimeout = 20000; // 根据默认超时限制初始化requestConfig

            int connectTimeout = 20000; // 20秒[单位毫秒]

            int connectionRequestTimeout = 20000;

            // 设置代理

            // requestConfig =

            // RequestConfig.custom().setProxy(proxy).setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();

            // 不设置代理

            requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)

                    .setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();

            // System.out.println("初始化HttpClientTest~~~结束");

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        } catch (KeyStoreException e) {

            e.printStackTrace();

        } catch (KeyManagementException e) {

            e.printStackTrace();

        }

    }




    public static CloseableHttpClient getHttpClient() {

        CloseableHttpClient httpClient = HttpClients.custom()

                // 设置连接池管理

                .setConnectionManager(pool)

                // 设置请求配置

                .setDefaultRequestConfig(requestConfig)

                // 设置重试次数

                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();

        return httpClient;

    }




    /**

     * 功能：发送Post请求 菜单： 思路： 描述：

     * 

     * @Date：2017年8月14日 @author：zhangxb25

     */

    private static String sendHttpPost(HttpPost httpPost) {

        CloseableHttpClient httpClient = null;

        CloseableHttpResponse response = null;

        // 响应内容

        String responseContent = null;

        try {

            httpClient = getHttpClient(); // 创建默认的httpClient实例.

            httpPost.setConfig(requestConfig); // 配置请求信息

            response = httpClient.execute(httpPost); // 执行请求

            HttpEntity entity = response.getEntity(); // 得到响应实例




            // 可以获得响应头

            // Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);

            // for (Header header : headers) {

            // System.out.println(header.getName());

            // }




            // 得到响应类型

            // System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());




            // 判断响应状态

            if (response.getStatusLine().getStatusCode() >= 300) {

                throw new Exception(

                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());

            }

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {

                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);

                EntityUtils.consume(entity);

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (response != null) { // 释放资源

                    response.close();

                }

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        return responseContent;

    }




    /**

     * 功能：发送Get请求 菜单： 思路： 描述：

     * 

     * @Date：2017年8月14日 @author：zhangxb25

     */

    private static String sendHttpGet(HttpGet httpGet) {

        CloseableHttpClient httpClient = null;

        CloseableHttpResponse response = null;

        String responseContent = null; // 响应内容

        try {

            httpClient = getHttpClient(); // 创建默认的httpClient实例.

            httpGet.setConfig(requestConfig); // 配置请求信息

            response = httpClient.execute(httpGet); // 执行请求

            HttpEntity entity = response.getEntity(); // 得到响应实例




            // 可以获得响应头

            // Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);

            // for (Header header : headers) {

            // System.out.println(header.getName());

            // }




            // 得到响应类型

            // System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());




            // 判断响应状态

            if (response.getStatusLine().getStatusCode() >= 300) {

                throw new Exception(

                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());

            }

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {

                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);

                EntityUtils.consume(entity);

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (response != null) { // 释放资源

                    response.close();

                }

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        return responseContent;

    }




    /**

     * 功能：发送 post请求 菜单： 思路： 描述：

     * 

     * @Date：2017年8月14日 @author：zhangxb25

     */

    public static String sendHttpPost(String httpUrl) {

        HttpPost httpPost = new HttpPost(httpUrl); // 创建httpPost

        return sendHttpPost(httpPost);

    }




    /**

     * 功能：发送 get请求 菜单： 思路： 描述：

     * 

     * @Date：2017年8月14日 @author：zhangxb25

     */

    public static String sendHttpGet(String httpUrl) {

        HttpGet httpGet = new HttpGet(httpUrl); // 创建get请求

        return sendHttpGet(httpGet);

    }




    /**

     * 功能：发送 post请求（带文件） 菜单： 思路： 描述：

     * 

     * @Date：2017年8月14日 @author：zhangxb25

     */

    public static String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {

        HttpPost httpPost = new HttpPost(httpUrl); // 创建httpPost

        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();

        if (maps != null) {

            for (String key : maps.keySet()) {

                meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));

            }

        }

        if (fileLists != null) {

            for (File file : fileLists) {

                FileBody fileBody = new FileBody(file);

                meBuilder.addPart("files", fileBody);

            }

        }

        HttpEntity reqEntity = meBuilder.build();

        httpPost.setEntity(reqEntity);

        return sendHttpPost(httpPost);

    }




    /**

     * 功能：发送 post请求 菜单： 思路： 描述：参数(格式:key1=value1&key2=value2)

     * 

     * @Date：2017年8月14日 @author：zhangxb25

     */

    public static String sendHttpPost(String httpUrl, String params) {

        HttpPost httpPost = new HttpPost(httpUrl); // 创建httpPost

        try {

            if (params != null && params.trim().length() > 0) { // 设置参数

                StringEntity stringEntity = new StringEntity(params, "UTF-8");

                stringEntity.setContentType(CONTENT_TYPE_FORM_URL);

                httpPost.setEntity(stringEntity);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return sendHttpPost(httpPost);

    }




    /**

     * 功能：发送 post请求 菜单： 思路： 描述：

     * 

     * @Date：2017年8月14日 @author：zhangxb25

     */

    public static String sendHttpPost(String httpUrl, Map<String, Object> maps) {

        String parem = convertStringParamter(maps);

        return sendHttpPost(httpUrl, parem);

    }




    /**

     * 功能：发送 post请求 发送json数据 菜单： 思路： 描述：参数(格式 json)

     * 

     * @Date：2017年8月14日 @author：zhangxb25

     */

    public static String sendHttpPostJson(String httpUrl, String paramsJson) {

        HttpPost httpPost = new HttpPost(httpUrl); // 创建httpPost

        try {

            if (paramsJson != null && paramsJson.trim().length() > 0) { // 设置参数

                StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");

                stringEntity.setContentType(CONTENT_TYPE_JSON_URL);

                httpPost.setEntity(stringEntity);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return sendHttpPost(httpPost);

    }




    /**

     * 功能：发送 post请求 发送xml数据 菜单： 思路： 描述：参数(格式 Xml)

     * 

     * @Date：2017年8月14日 @author：zhangxb25

     */

    public static String sendHttpPostXml(String httpUrl, String paramsXml) {

        HttpPost httpPost = new HttpPost(httpUrl); // 创建httpPost

        try {

            if (paramsXml != null && paramsXml.trim().length() > 0) { // 设置参数

                StringEntity stringEntity = new StringEntity(paramsXml, "UTF-8");

                stringEntity.setContentType(CONTENT_TYPE_TEXT_HTML);

                httpPost.setEntity(stringEntity);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return sendHttpPost(httpPost);

    }




    /**

     * 功能：将map集合的键值对转化成：key1=value1&key2=value2 的形式 菜单： 思路： 描述：parameterMap

     * 需要转化的键值对集合

     * 

     * @Date：2017年8月14日 @author：zhangxb25

     */

    public static String convertStringParamter(Map parameterMap) {

        StringBuffer parameterBuffer = new StringBuffer();

        if (parameterMap != null) {

            Iterator iterator = parameterMap.keySet().iterator();

            String key = null;

            String value = null;

            while (iterator.hasNext()) {

                key = (String) iterator.next();

                if (parameterMap.get(key) != null) {

                    value = (String) parameterMap.get(key);

                } else {

                    value = "";

                }

                parameterBuffer.append(key).append("=").append(value);

                if (iterator.hasNext()) {

                    parameterBuffer.append("&");

                }

            }

        }

        return parameterBuffer.toString();

    }




}