package com.manager.nacelle_rent.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;

public class UserCheckUtil {
    public static int checkUser(String userId, String password, String roleName) {
        if (userId.equals("null")) {
            return 0; //尚未登录
        }
        //进行身份验证，只能通过cookie认证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userId, password);
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
            if(roleName != null){
                return subject.hasRole(roleName)? 1:-1; //返回-1表示无权，返回1表示有权限
            }
            return 1;
        } catch (Exception ex) {
            return 0; //尚未登录
        }
    }
}

