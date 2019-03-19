package com.imooc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * @program: springboot_wx_sell
 * @description: 测试获取Openid
 * @author: Gu
 * @create: 2019-03-18 22:27
 **/
@RequestMapping("/weixin")
@Controller
@Slf4j
public class WeiXinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法。。");
        log.info(code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx36d0df1f7ab16df1" +
                "&secret=95cfb769e1f944c9704c6e06400e828f&code=" + code + "&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("resonse = {}", response);
    }
}
