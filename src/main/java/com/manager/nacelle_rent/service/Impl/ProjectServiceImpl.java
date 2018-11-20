package com.manager.nacelle_rent.service.Impl;

import com.manager.nacelle_rent.dao.ProjectMapper;
import com.manager.nacelle_rent.entity.Project;
import com.manager.nacelle_rent.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public List<Project> findProject(Project project) {
        return projectMapper.getProject();
    }
}
