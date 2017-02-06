package com.cai.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by caibaolong on 2017/2/4.
 * <p>
 * 自定义一个拦截器,拦截所有不带.do的请求,返回到主页面
 */

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 获取请求的url地址：当url中包含.do，就不拦截。
        String url = httpServletRequest.getRequestURI();
        if (url.contains(".do")) {
            return true;
        }
        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/main/main.jsp").forward(httpServletRequest, httpServletResponse);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
