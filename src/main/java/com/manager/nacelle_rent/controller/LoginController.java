package com.manager.nacelle_rent.controller;

import com.alibaba.fastjson.JSONObject;
import com.manager.nacelle_rent.entity.User;
import com.manager.nacelle_rent.service.UserService;
import com.manager.nacelle_rent.utils.CookieUtil;
import com.manager.nacelle_rent.utils.JwtUtil;
import com.manager.nacelle_rent.utils.UserCheckUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    private static Logger logger=Logger.getLogger(LoginController.class); // 获取logger实例

    @PostMapping("/webLogin")
    public JSONObject loadWebAdmin(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String userId = user.getUserId();
        String userToken = CookieUtil.getCookie(request, "token");
        String password = userToken == null ? user.getUserPassword() : userToken;
        int result = UserCheckUtil.checkUser(userId, password, null);
        if (result == 1) {
            if(userToken == null){
                String token = JwtUtil.getToken(user);
                if(userService.addToken(token,userId)){
                    CookieUtil.writeCookie(response,"token",JwtUtil.getToken(user),7200);
                }
            }
            jsonObject.put("isLogin", true);
            logger.info(userId +" 登录");
        } else {
            jsonObject.put("isLogin", false);
        }
        return jsonObject;
    }

    @GetMapping("/quitLoad")
    public JSONObject quitWebAdmin(@RequestParam String userId, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        CookieUtil.writeCookie(response,"token","1",0);
        jsonObject.put("deleteCookieSuccess", true);
        return jsonObject;
    }
}
