package com.manager.nacelle_rent.service.Impl;

import com.manager.nacelle_rent.dao.AdminMapper;
import com.manager.nacelle_rent.entity.Admin;
import com.manager.nacelle_rent.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public boolean load(Admin admin) {
        String correctPassword = adminMapper.getAdminById(admin.getAdminId()).getAdminPassword() ;
        return (correctPassword.equals(admin.getAdminPassword()));
    }
}
