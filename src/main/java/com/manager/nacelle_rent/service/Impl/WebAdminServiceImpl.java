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
        public WebAdmin webLoad(String webAdminId) {
            return webAdminMapper.getWebAdminById(webAdminId);
    }
    @Override
    public boolean addToken(String token, String webAdminId){
        try{
            webAdminMapper.addToken(token,webAdminId);
            return true;
        }catch (Exception ex){
            System.out.print(ex.toString());
            return false;
        }
    }
}
