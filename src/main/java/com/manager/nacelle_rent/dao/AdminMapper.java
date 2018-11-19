package com.manager.nacelle_rent.dao;


import com.manager.nacelle_rent.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

@Mapper
public interface AdminMapper {
    Admin getAdminById(String adminId) throws DataAccessException;
}
