package com.manager.nacelle_rent.controller;

import com.manager.nacelle_rent.entity.SuperAdmin;
import com.manager.nacelle_rent.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SuperAdminController {
    @Autowired
    private SuperAdminService superAdminService;
    @RequestMapping("superLoad")
    @CrossOrigin
    public @ResponseBody String loadSuperAdmin(@RequestBody SuperAdmin superAdmin){
        try{
            return (superAdminService.superLoad(superAdmin))?"success":"wrong";
        }catch(DataAccessException ex){
            System.out.print(ex);
            return "wrong";
        }
    }
}
