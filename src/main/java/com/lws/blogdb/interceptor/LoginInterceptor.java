package com.lws.blogdb.interceptor;


import com.lws.blogdb.utils.JwtUtil;
import com.lws.blogdb.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中的token
        String token = request.getHeader("Authorization");
        if (token == null||token.isEmpty()) {
            response.setStatus(401);
            return false;
        }
        //验证token是否被篡改
        try {
            Map<String,Object> claims= JwtUtil.parseToken(token);
            //挂载线程 这里应该用redis缓存token，这里只是简单验证用ThreadLocalUtil
            ThreadLocalUtil.set(claims);

        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除线程变量
        ThreadLocalUtil.remove();
    }
}
