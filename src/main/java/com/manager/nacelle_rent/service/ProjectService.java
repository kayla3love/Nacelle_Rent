package com.manager.nacelle_rent.service;

import com.manager.nacelle_rent.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findProject(Project project);
}
