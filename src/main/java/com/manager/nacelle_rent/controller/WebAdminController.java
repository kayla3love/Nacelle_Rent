package com.manager.nacelle_rent.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.manager.nacelle_rent.entity.WebAdmin;
import com.manager.nacelle_rent.service.WebAdminService;
import com.manager.nacelle_rent.utils.CookieUtil;
import com.manager.nacelle_rent.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@CrossOrigin
@Controller
public class WebAdminController {
    @Autowired
    private WebAdminService webAdminService;
    @RequestMapping(method=RequestMethod.POST, value="webLoad")
    public @ResponseBody JSONObject loadWebAdmin(@RequestBody WebAdmin webAdmin, HttpServletRequest request,HttpServletResponse response){
        JSONObject jsonObject=new JSONObject();
        String webAdminId = webAdmin.getWebAdminId();
        WebAdmin resultWebAdmin = webAdminService.webLoad(webAdminId);
        if(resultWebAdmin == null) {
            jsonObject.put("message", "用户不存在");
        }else if(!resultWebAdmin.getWebAdminPassword().equals(webAdmin.getWebAdminPassword())){
            jsonObject.put("message", "密码错误");
        }else {
            String token = JwtUtil.getToken(resultWebAdmin);
            if(webAdminService.addToken(token,webAdminId)){
                jsonObject.put("message", "true");
                CookieUtil.writeCookie(response,"token",token);
            }
        }
        return jsonObject;
    }

    @RequestMapping(method=RequestMethod.GET, value="webLoad")
    public @ResponseBody JSONObject loadWebAdmin(@RequestParam String webAdminId, HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String token = CookieUtil.getCookie(request, "token");
        if(token != null && webAdminId != null && token.equals(webAdminService.webLoad(webAdminId).getWebAdminToken())){
            jsonObject.put("isLogin",true);
        }else{
            jsonObject.put("isLogin",false);
        }
        return jsonObject;
    }
}
