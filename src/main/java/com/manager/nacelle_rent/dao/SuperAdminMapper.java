package com.manager.nacelle_rent.dao;


import com.manager.nacelle_rent.entity.SuperAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

@Mapper
public interface SuperAdminMapper {
    SuperAdmin getSuperAdminById(String superAdminId) throws DataAccessException;
}
