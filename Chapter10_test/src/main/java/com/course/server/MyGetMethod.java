package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServletResponse 装响应信息的类
        //HttpServletRequest  装请求信息的类
        Cookie cookie=new Cookie("login","true");
        response.addCookie(cookie);
        return ("you get cookie ");
    }
    /*
    * 要求客户端携带cookie访问
    * 这是一个需要携带cookie信息才能访问的get请求*/
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "需要客户端携带cookie",httpMethod = "GET")
    public String getwithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "你必须携带cookies来";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "恭喜你访问成功";
            }
        }
        return "你必须携带cookie信息来";

    }
    /*
    * 开发一个需要携带参数的get请求
    * 第一种实现方式 url:key=value&key=value
    * 我们来模拟获取商品列表*/
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "这是一个需要携带参数的get接口",httpMethod = "GET")
    public Map<String,Integer> getlist(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String, Integer> mylist=new HashMap<>();
        mylist.put("鞋",400);
        mylist.put("方便面",1);
        mylist.put("手表",230);
        return mylist;
    }
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value = "这是另一个需要携带参数的get接口",httpMethod = "GET")
    public Map<String,Integer> getlistA(@PathVariable Integer start,
                                        @PathVariable Integer end){
        Map<String,Integer> mylist=new HashMap<>();
        mylist.put("张三",19);
        mylist.put("零四",29);
        mylist.put("王五",30);
        return mylist;
    }

}
