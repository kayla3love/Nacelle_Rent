package com.manager.nacelle_rent.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.manager.nacelle_rent.entity.WebAdmin;
import com.manager.nacelle_rent.service.WebAdminService;
import com.manager.nacelle_rent.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private WebAdminService webAdminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String s = request.getMethod();
        String origin = request.getHeader("origin");
        if (s.equals("OPTIONS") || s.equals("POST") || s.equals("GET")) {
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            return true;
        }
//        // 获取 token 中的 user id
//        String adminId;
//        try {
//            adminId = JWT.decode(token).getAudience().get(0);
//        } catch (JWTDecodeException j) {
//            throw new RuntimeException("401");
//        }
//        WebAdmin admin = webAdminService.webLoad(adminId);  //根据id和当前角色取数据库里查找
//        if (admin == null) {
//            response.sendRedirect("/");
//            return false;
//        }else{
//            // 验证 token，去数据库查找
//        }
       return true;
    }
}
