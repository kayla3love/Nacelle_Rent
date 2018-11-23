package com.manager.nacelle_rent.controller;

import com.alibaba.fastjson.JSONObject;
import com.manager.nacelle_rent.entity.User;
import com.manager.nacelle_rent.service.UserService;
import com.manager.nacelle_rent.utils.CookieUtil;
import com.manager.nacelle_rent.utils.UserCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //注册，不需要进行身份验证
    @PostMapping("/checkRegister")
    public @ResponseBody JSONObject pushToWeb(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        int resultFlag = userService.registerUser(user);
        if(resultFlag == 0) {
            //回复客户端已经有相同的id
            jsonObject.put("message","exist");
        }else if(resultFlag == 1){
            try {
                WebSocketServer.sendInfo("newRegister","superWebAdmin");
                //回复客户端已提交申请请等待
                jsonObject.put("message","success");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            //插入失败
        }
        return jsonObject;
    }

    //超级管理员获取所有区域管理员的信息
    @GetMapping("/getAreaList")
    public @ResponseBody JSONObject getAreaAdmin(@RequestParam String userId, HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String password = CookieUtil.getCookie(request, "token");
        int flag = UserCheckUtil.checkUser(userId,password,"superWebAdmin");
        if(flag == 1){
            List<User> areaAdminList = userService.getAreaAdminList();
            jsonObject.put("resultList",areaAdminList);
        }else{
            jsonObject.put("isLogin",false);
        }
        return jsonObject;
    }

    //获取所有没有审核的注册数目
    @GetMapping("/getUncheckedNum")
    public @ResponseBody JSONObject getUnCheckedNum(@RequestParam String userId, HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();

        //进行身份验证，只能通过cookie认证
        String password = CookieUtil.getCookie(request, "token");
        int flag = UserCheckUtil.checkUser(userId, password, "superWebAdmin");
        if(flag == 1){
            int unCheckedNum = userService.getRegisterUnCheckedNum();
            jsonObject.put("isAllowed",true);
            jsonObject.put("unChecked",unCheckedNum);
        }else{
            jsonObject.put("isAllowed",false);
        }
        return jsonObject;
    }
}
