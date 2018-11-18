package com.manager.nacelle_rent.controller;

import com.manager.nacelle_rent.entity.WebAdmin;
import com.manager.nacelle_rent.service.WebAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebAdminController {
    @Autowired
    private WebAdminService webAdminService;
    @RequestMapping("webLoad")
    @CrossOrigin
    public @ResponseBody String loadSuperAdmin(@RequestBody WebAdmin webAdmin){
        try{
            return (webAdminService.webLoad(webAdmin))?"success":"wrong";
        }catch(DataAccessException ex){
            System.out.print(ex);
            return "wrong";
        }

    }
}
