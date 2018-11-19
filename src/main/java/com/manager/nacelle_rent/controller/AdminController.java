package com.manager.nacelle_rent.controller;

import com.manager.nacelle_rent.entity.Admin;
import com.manager.nacelle_rent.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    @Autowired
    private AdminService AdminService;
    @RequestMapping("load")
    @CrossOrigin
    public @ResponseBody String loadAdmin(@RequestBody Admin admin){
        try{
            return (AdminService.load(admin))?"success":"wrong";
        }catch(DataAccessException ex){
            System.out.print(ex);
            return "wrong";
        }
    }
}
