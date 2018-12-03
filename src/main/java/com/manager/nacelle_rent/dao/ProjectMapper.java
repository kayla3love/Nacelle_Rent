package com.manager.nacelle_rent.dao;

import com.manager.nacelle_rent.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<Project> getProjectList();
    Project getProjectDetail(String projectId);
    //制定ID查询项目
    Project getProjectById(@Param("projectId") String projectId);
    //新建项目
    void createProject(Project project);
}
