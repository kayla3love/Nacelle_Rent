package com.manager.nacelle_rent.service;

import com.alibaba.fastjson.JSONObject;
import com.manager.nacelle_rent.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getProjectList();
    JSONObject getProjectDetail(String projectId);
    int createProject(Project project);
}
