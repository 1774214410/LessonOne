package com.liuzw.common.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 * Email :a1774214410@163.com
 *
 * @author: liuzw
 * @date: 2018/10/27 14:36
 */
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String toLoginUrl = "/account/toLogin";
        String loginUrl = "/user/login";
        String key = "user";
        if (request.getRequestURI().equals(toLoginUrl) || request.getRequestURI().equals(loginUrl)){
            System.out.println("不需要拦截的路径");
            return true;
        }
        if (request.getSession().getAttribute(key) == null){
            response.sendRedirect("/account/toLogin");
            return false;
        }
        //设置session过期时间  以秒为单位
        request.getSession().setMaxInactiveInterval(1*60);
        return true;
    }
}
