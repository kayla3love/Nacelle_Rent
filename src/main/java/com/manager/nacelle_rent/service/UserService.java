package com.manager.nacelle_rent.service;

import com.manager.nacelle_rent.entity.User;

import java.util.List;

public interface UserService {
    User webLoad(String webAdminId);
    boolean addToken(String token, String webAdminId);
    List<User> getAreaAdminList();
    int registerUser(User user);
    List<User> getRegisterUnChecked();
    int getRegisterUnCheckedNum();
}
