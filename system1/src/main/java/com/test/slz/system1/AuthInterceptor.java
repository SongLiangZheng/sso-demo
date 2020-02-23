package com.test.slz.system1;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if(name.equals("isLogin")){
                if(Boolean.valueOf(cookie.getValue())){
                    return true;
                }
            }
        }
        String redirectUrl="localhost:8081/system1/"+request.getRequestURI();
        response.sendRedirect("locahost:8080/sso/login.html?redirectUrl="+redirectUrl);
        return false;
    }
}
