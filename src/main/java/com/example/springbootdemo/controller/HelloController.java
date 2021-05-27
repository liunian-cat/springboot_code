package com.example.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liangjunjun
 * @date 2020-04-17-15:54
 **/

@RestController

@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("hello")
    public String hello() {
        System.out.println("Hello this is my first springboot demo");
        return "Hello this is my first springboot demo";

    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public @ResponseBody
    Object users(HttpServletResponse response){
        List<String> userList = new ArrayList<String>();
        userList.add("tom");
        userList.add("marry");
        userList.add("jack");
        System.out.println("get Request,users api");
        Cookie cookie = new Cookie("cok","cool");
        cookie.setPath("/hello");
        response.addCookie(cookie);
        return userList;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody Object login(HttpServletRequest request,String name, String pwd){
        Map<String,Object> map = new HashMap<String, Object>();
        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        for (Cookie cookie : cookies) {
            if("cok".equals(cookie.getName())){
                cookieValue = cookie.getValue();
            }
        }
        if("123".equals(pwd) && "jack".equals(name) && "cool".equals(cookieValue)){
            map.put("status",0);
        }else {
            map.put("status",-1);
        }
        System.out.println("get Request,login api");
        return map;
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public @ResponseBody Object info(String name,String pwd){
        List<String> userList = new ArrayList<>();
        userList.add(name);
        userList.add(pwd);
        userList.add(name.length()+"");
        System.out.println("get request,info api");
        return userList;
    }
}
