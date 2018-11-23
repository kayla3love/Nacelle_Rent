package com.manager.nacelle_rent.controller;

import com.alibaba.fastjson.JSONObject;
import com.manager.nacelle_rent.entity.User;
import com.manager.nacelle_rent.service.UserService;
import com.manager.nacelle_rent.utils.CookieUtil;
import com.manager.nacelle_rent.utils.JwtUtil;
import com.manager.nacelle_rent.utils.UserCheckUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/webLogin")
    public @ResponseBody JSONObject loadWebAdmin(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String userId = user.getUserId();
        String userToken = CookieUtil.getCookie(request, "token");
        String password = userToken == null ? user.getUserPassword() : userToken;
        int result = UserCheckUtil.checkUser(userId, password, null);
        if (result == 1) {
            if(userToken == null){
                CookieUtil.writeCookie(response,"token",JwtUtil.getToken(user),7200);
            }
            jsonObject.put("isLogin", true);
        } else {
            jsonObject.put("isLogin", false);
        }
        return jsonObject;
    }

    @GetMapping("/quitLoad")
    public @ResponseBody JSONObject quitWebAdmin(@RequestParam String userId, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        CookieUtil.writeCookie(response,"token","1",0);
        jsonObject.put("deleteCookieSuccess", true);
        return jsonObject;
    }
}
