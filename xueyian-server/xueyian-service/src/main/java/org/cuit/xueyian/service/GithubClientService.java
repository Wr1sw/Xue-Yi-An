package org.cuit.xueyian.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
@Service
public class GithubClientService {
    //前面在github中配置时产生的
    private String clientId = "633829317d15b10f6a94";
    private String clientSecret = "99b9b62d49389701a0d12320f453cb2726531598";
    private String state = "123";
    private String redirectUri = "http://localhost:15005/authorization_code";

    @Autowired
    private RestTemplate restTemplate;

    private WebApplicationContext webApplicationContext;

    //获取accessToken
    public String queryAccessToken(String code) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("client_id", clientId);
        map.put("client_secret", clientSecret);
        map.put("state", state);
        map.put("code", code);
        map.put("redirect_uri", redirectUri);

        String result = HttpUtil.post("https://github.com/login/oauth/access_token", map);
        System.out.println("result = " + result);
        String[] split = result.split("&");
        String tokenStr = split[0];
        String access_token = tokenStr.split("=")[1];
        System.out.println("access_token = " + access_token);
        return access_token;
    }

    //获取用户信息
    public Map<String, Object> queryUser(String accessToken) {
        HttpHeaders httpheaders = new HttpHeaders();

        httpheaders.add("Authorization", "token " + accessToken);

        String authorization = HttpRequest.get("https://api.github.com/user").header("Authorization", "token " + accessToken).execute().body();

        System.out.println("authorization = " + authorization);

        JSONObject jsonObject = JSON.parseObject(authorization);
        return jsonObject;
    }


}
