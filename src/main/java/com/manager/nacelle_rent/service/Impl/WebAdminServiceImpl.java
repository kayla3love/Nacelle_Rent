package com.manager.nacelle_rent.service.Impl;

import com.manager.nacelle_rent.dao.WebAdminMapper;
import com.manager.nacelle_rent.entity.WebAdmin;
import com.manager.nacelle_rent.service.WebAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebAdminServiceImpl implements WebAdminService {
    @Autowired
    private WebAdminMapper webAdminMapper;
    @Override
    public boolean webLoad(WebAdmin webAdmin) {
        String correctPassword = webAdminMapper.getWebAdminById(webAdmin.getWebAdminId()).getWebAdminPassword() ;
        return (correctPassword.equals(webAdmin.getWebAdminPassword()));
    }
}
