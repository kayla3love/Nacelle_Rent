package com.manager.nacelle_rent.service;

import com.manager.nacelle_rent.entity.WebAdmin;

public interface WebAdminService {
    WebAdmin webLoad(String webAdminId);
    boolean addToken(String token, String webAdminId);
}
