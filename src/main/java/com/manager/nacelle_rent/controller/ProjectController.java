package com.manager.nacelle_rent.controller;

import com.manager.nacelle_rent.entity.Project;
import com.manager.nacelle_rent.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService ProjectService;
    @RequestMapping("find")
    @CrossOrigin
    public @ResponseBody List<Project> find(@RequestBody Project project){
        try{
            return ProjectService.findProject(project);
        }catch(DataAccessException ex){
            System.out.print(ex);
            return null;
        }
    }
}
