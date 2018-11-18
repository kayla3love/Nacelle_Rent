package com.manager.nacelle_rent.dao;


import com.manager.nacelle_rent.entity.WebAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

@Mapper
public interface WebAdminMapper {
    WebAdmin getWebAdminById(String webAdminId) throws DataAccessException;
}
