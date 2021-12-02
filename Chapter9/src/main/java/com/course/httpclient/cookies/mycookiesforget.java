package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class mycookiesforget {
    private String url;
    private ResourceBundle bundle;
    @BeforeTest
    public void beforetest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }
    @Test
    public void gettest() throws IOException {
        String result;
        //从配置文件拼接Url
        String testurl=this.url+bundle.getString("getCookies.url");
        HttpGet get=new HttpGet(testurl);
        //测试代码逻辑
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //获取cookie信息
        CookieStore store=client.getCookieStore();
        List<Cookie> cookieList=store.getCookies();
        for(Cookie cookie:cookieList){
            String value=cookie.getValue();
            String name=cookie.getName();
            System.out.println("cookie name="+name+"cookie value="+value);
        }


    }
}
