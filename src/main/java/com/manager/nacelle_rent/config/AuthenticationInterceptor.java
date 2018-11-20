package com.manager.nacelle_rent.config;

import com.manager.nacelle_rent.PassToken;
import com.manager.nacelle_rent.service.WebAdminService;
import com.manager.nacelle_rent.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private WebAdminService webAdminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String origin = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        String token = CookieUtil.getCookie(request,"token");
        String webAdminId = request.getParameter("webAdminId");
        if (token == null) {
            throw new RuntimeException("401");
        }
        if(!token.equals(webAdminService.webLoad(webAdminId).getWebAdminToken())){
            throw new RuntimeException("401");
        }
        return true;
    }
}
