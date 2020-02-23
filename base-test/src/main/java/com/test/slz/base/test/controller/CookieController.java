package com.test.slz.base.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


@Controller
@Slf4j
public class CookieController {

    @RequestMapping("/session/set")
    @ResponseBody
    public String setSession(HttpServletRequest request,String key,String value){
        HttpSession session = request.getSession();
        session.setAttribute(key,value);
        return "session attr is set successfully.";
    }


    @RequestMapping("/sessions")
    @ResponseBody
    public List<String> readSession(HttpServletRequest request){


        HttpSession session = request.getSession();
        List<String> list=new ArrayList<>();
        Enumeration<String> attributeNames = session.getAttributeNames();
        if(attributeNames.hasMoreElements()){
            String key = attributeNames.nextElement();
            list.add(key+": "+session.getAttribute(key));
        }
        return list;
    }

    @RequestMapping("/cookie/set")
    @ResponseBody
    public String setCookie(HttpServletRequest request, HttpServletResponse response,String key ,String value){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(3600);
        cookie.setPath("/system1");
        response.addCookie(cookie);
        return "cookie设置成功";
    }
    @RequestMapping("/cookies")
    @ResponseBody
    public List<String> setCookie(HttpServletRequest request){
        return getCookis(request);
    }

    @RequestMapping("/cookie/get")
    @ResponseBody
    public List<String> cookies(HttpServletRequest request){
        return getCookis(request);
    }
    private List<String> getCookis(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<String> list = new ArrayList<>();
        for (Cookie cookie : cookies) {
            list.add(cookie.getName() + ": " + cookie.getValue());
        }
        return list;
    }


}
