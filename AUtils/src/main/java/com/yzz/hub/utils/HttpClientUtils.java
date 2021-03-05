package com.yzz.hub.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具
 *
 * @author LK
 * @date 2019/9/24
 */
public class HttpClientUtils {

    private static Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

    private static RequestConfig requestConfig;

    static {
        // 设置请求和传输超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
    }

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return String 响应字符串
     */
    public static String httpGet(String url) {
        return httpGet(url, null);
    }

    /**
     * 发送get请求
     *
     * @param url       路径
     * @param headerMap 请求头
     * @return String 响应字符串
     */
    public static String httpGet(String url, Map<String, String> headerMap) {
        // get请求返回结果
        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();

        // 发送get请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        // 设置请求头
        setRequestHeader(headerMap, httpGet);

        try {

            CloseableHttpResponse response = client.execute(httpGet);

            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                // 读取服务器返回过来的json字符串数据
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "UTF-8");
            } else {
                log.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            log.error("get请求提交失败:" + url, e);
        } finally {
            httpGet.releaseConnection();
        }
        return result;
    }

    /**
     * post请求传输json参数
     *
     * @param url   url地址
     * @param param 参数
     * @return String 响应字符串
     */
    public static String httpPost(String url, Object param) {
        return httpPost(url, param, null);
    }

    /**
     * post请求传输json参数
     *
     * @param url       url地址
     * @param param     参数
     * @param headerMap 请求头
     * @return String 响应字符串
     */
    public static String httpPost(String url, Object param, Map<String, String> headerMap) {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        // 设置请求头
        setRequestHeader(headerMap, httpPost);
        try {
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                String paramJson = JsonUtils.obj2json(param);
//                Map<String, String> paramMap = BeanUtils.describe(param);
                Map<String, Object> paramMap = JsonUtils.json2map(paramJson);
                for (String key : paramMap.keySet()) {
                    BasicNameValuePair basicNameValuePair = new BasicNameValuePair(key, (String) paramMap.get(key));
                    paramList.add(basicNameValuePair);
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                // 解决中文乱码问题
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                try {
                    // 读取服务器返回过来的json字符串数据
                    result = EntityUtils.toString(response.getEntity(), "UTF-8");

                } catch (Exception e) {
                    log.error("post请求提交失败:" + url, e);
                }
            }
        } catch (Exception e) {
            log.error("post请求提交失败:" + url, e);
        } finally {
            httpPost.releaseConnection();
        }
        return result;
    }


    /**
     * 这是请求头
     *
     * @param headerMap   请求头参数
     * @param requestBase 请求对象
     */
    private static void setRequestHeader(Map<String, String> headerMap, HttpRequestBase requestBase) {
        if (headerMap != null && !headerMap.isEmpty()) {
            List<Header> headerList = new ArrayList<>();
            for (String key : headerMap.keySet()) {
                BasicHeader header = new BasicHeader(key, headerMap.get(key));
                headerList.add(header);
            }
            requestBase.setHeaders(headerList.toArray(new Header[headerList.size()]));
        }
    }
}
