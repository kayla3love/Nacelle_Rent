package com.manager.nacelle_rent.dao;

import com.manager.nacelle_rent.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import java.util.List;

@Mapper
public interface ProjectMapper {
    List<Project> getProject() throws DataAccessException;
}
