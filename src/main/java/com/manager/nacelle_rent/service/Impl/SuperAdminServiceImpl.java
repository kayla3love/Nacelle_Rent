package com.manager.nacelle_rent.service.Impl;

import com.manager.nacelle_rent.dao.SuperAdminMapper;
import com.manager.nacelle_rent.entity.SuperAdmin;
import com.manager.nacelle_rent.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
    @Autowired
    private SuperAdminMapper superAdminMapper;
    @Override
    public boolean superLoad(SuperAdmin superAdmin) {
        String correctPassword = superAdminMapper.getSuperAdminById(superAdmin.getSuperAdminId()).getSuperAdminPassword() ;
        return (correctPassword.equals(superAdmin.getSuperAdminPassword()));
    }
}
