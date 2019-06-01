package com.newegg.controller;


import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.newegg.ec.common.component.http.ribbon.HARestTemplate;
import com.newegg.ec.common.model.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @program: jdserviceframework
 * @description:
 * @author: gz75
 * @create: 2019-05-30 19:46
 **/

@Controller
public class HttpController {

    @Value("${server.port}")
    private String port;

    @Autowired
    HARestTemplate ht1;

    @Autowired
    HARestTemplate ht2;

    @RequestMapping(value = "/ht1", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        String greeting = this.ht1.getForObject("http://q4s/greeting", String.class);
        return greeting;
    }

    @RequestMapping(value = "/ht2", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse hello2() {
        CommonResponse greeting = this.ht2.getForObject("http://shippingcharge/greeting", CommonResponse.class);
        return greeting;
    }


    @RequestMapping(value = "/ht3", method = RequestMethod.GET)
    @ResponseBody
    public String hello3() {
        RestTemplate restTemplate = new RestTemplate();
        String greeting = restTemplate.getForObject("http://10.16.164.103:9092/greeting", String.class);
        return greeting;
    }

    @RequestMapping(value = "/greeting")
    @ResponseBody
    public CommonResponse greet(HttpServletRequest request) throws InterruptedException {
        CommonResponse cmmResponse = new CommonResponse();
        cmmResponse.setStatus("1");
        cmmResponse.setInfo("");
        cmmResponse.setResult(port);
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/spring-rest/foos";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);

        return cmmResponse;
    }
}
