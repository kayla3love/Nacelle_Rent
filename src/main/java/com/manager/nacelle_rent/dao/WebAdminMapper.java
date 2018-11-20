package com.manager.nacelle_rent.dao;


import com.manager.nacelle_rent.entity.WebAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

@Mapper
public interface WebAdminMapper {
    //根据ID和role获取正确的密码
    WebAdmin getWebAdminById(@Param("webAdminId") String webAdminId);

    //第一次登录时将token写进数据库
    void addToken (@Param("webAdminToken") String webAdminToken, @Param("webAdminId") String webAdminId);
}
