package com.oauth2.controller;

import com.alibaba.fastjson.JSONObject;
import com.oauth2.pojo.Oauth2Property;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;




@Controller
public class LoginController {
    Logger log = LoggerFactory.getLogger(LoginController.class);

    private final Oauth2Property oauth2Property;

    public LoginController(Oauth2Property oauth2Property) {
        this.oauth2Property = oauth2Property;
    }
    /**
     * 让用户跳转到github
     *
     *
     * **/
    @GetMapping("/authorize")
    public String authorize(){
        String url = oauth2Property.getAuthorizeUrl() +
                "？client_id=" + oauth2Property.getClientId() +
                "&redirect_uri=" + oauth2Property.getRedirectUrl();
        log.info("授权url:{}",url);
        return "redirect:" + url;
    }
    /**
     *
     * 回调接口
     * */
    @GetMapping("/authorization_code")
    public String authorization_code(@RequestParam("code") String code){
        log.info("code={}",code);
        String accessToken = getAccessToken(code);
        String userInfo = getUserInfo(accessToken);
        log.info("重定向到首页");
        return "redirect:/home";
    }
    @GetMapping("/home")
    @ResponseBody
    public String home(){
        return "hello world";
    }

    private String getAccessToken(String code){
        String url = oauth2Property.getAccessTokenUrl() +
                "?client_id=" + oauth2Property.getClientId() +
                "&client_secret=" + oauth2Property.getClientSecret() +
                "&code=" + code;
        log.info("getAccessToken:{}",url);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("accept","application/json");
        HttpEntity<String>requestentity = new HttpEntity<String>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String>response = restTemplate.postForEntity(url,requestentity,String.class);
        String responseStr = response.getBody();
        log.info("responseStr={}",responseStr);
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        String accessToken = jsonObject.getString("access_token");
        log.info("access_token={}",accessToken);
        return accessToken;
    }
    private String getUserInfo(String accessToken){
        String url = oauth2Property.getUserInfoUrl();
        log.info("getUserInfo url:{}",url);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("accept","application/json");
        requestHeaders.add("Authorization","token "+accessToken);
        HttpEntity<String> requestEntity = new HttpEntity<String>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity,String.class);
        String userInfo = response.getBody();
        log.info("userInfo:{}",userInfo);
        return userInfo;
    }
}
