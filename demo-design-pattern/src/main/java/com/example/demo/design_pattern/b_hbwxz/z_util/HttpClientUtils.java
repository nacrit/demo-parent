package com.example.demo.design_pattern.b_hbwxz.z_util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

import java.io.IOException;

public class HttpClientUtils {
    public static JSONObject execute(String url, HttpMethod httpMethod) {
        HttpRequestBase http = null;
        try {
            HttpClient client = HttpClients.createDefault();
            //根据 HttpMethod 进行 HttpRequest的创建
            if(httpMethod == HttpMethod.GET) {
                http = new HttpGet(url);
            } else {
                http = new HttpPost(url);
            }
            HttpEntity entity = client.execute(http).getEntity();
            return JSONObject.parseObject(EntityUtils.toString(entity));
        } catch (IOException e) {
            throw new RuntimeException("Request failed. url = " + url);
        } finally {
            http.releaseConnection();
        }
    }
}
