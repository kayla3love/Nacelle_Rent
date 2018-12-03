package com.manager.nacelle_rent.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.manager.nacelle_rent.dao.ElectricResMapper;
import com.manager.nacelle_rent.dao.ProjectMapper;
import com.manager.nacelle_rent.entity.ElectricRes;
import com.manager.nacelle_rent.entity.Project;
import com.manager.nacelle_rent.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ElectricResMapper electricResMapper;
    private String[] projectStateList = {"立项","进行中","已结束"};
    @Override
    public List<Project> getProjectList(){
       List<Project> projectList = projectMapper.getProjectList();
       for(int i = 0; i < projectList.size();i++){
           Project project = projectList.get(i);
           project.setProjectState(projectStateList[Integer.parseInt(project.getProjectState())]);
           if(project.getProjectEnd() == null){
               project.setProjectEnd("—");
           }
       }
       return projectList;
    }

    @Override
    public JSONObject getProjectDetail(String projectId){
        JSONObject jsonObject = new JSONObject();
        Project projectDetail = projectMapper.getProjectDetail(projectId);
        projectDetail.setProjectState(projectStateList[Integer.parseInt(projectDetail.getProjectState())]);
        if(projectDetail.getProjectEnd() == null){
            projectDetail.setProjectEnd("—");
        }
        List<ElectricRes> electricRes = electricResMapper.getElectricRes(projectId);
        jsonObject.put("projectDetail",projectDetail);
        jsonObject.put("electricRes",electricRes);
        return jsonObject;
    }

    @Override
    public int createProject(Project project){
        if (projectMapper.getProjectById(project.getProjectId()) != null)
            return 0;//项目ID已经存在
        try{
            projectMapper.createProject(project);
            return 1;//成功创建项目
        }catch (Exception ex){
            System.out.print(ex.toString());
            return 2;  //网络故障
        }
    }
}
