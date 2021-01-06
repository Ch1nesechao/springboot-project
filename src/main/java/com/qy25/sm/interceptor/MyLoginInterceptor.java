package com.qy25.sm.interceptor;


import com.qy25.sm.common.http.AjaxStatus;
import com.qy25.sm.exception.MyLoginException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 * 拦截未登录
 */
public class MyLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user!=null){
            return true;
        }
        throw new MyLoginException(AjaxStatus.UN_LOGIN);
    }
}
