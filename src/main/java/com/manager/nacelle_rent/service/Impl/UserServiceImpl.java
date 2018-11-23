package com.manager.nacelle_rent.service.Impl;

import com.manager.nacelle_rent.dao.UserMapper;
import com.manager.nacelle_rent.entity.User;
import com.manager.nacelle_rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
        public User webLoad(String webAdminId) {
            return userMapper.getUserById(webAdminId);
    }
    @Override
    public boolean addToken(String token, String webAdminId){
        try{
            userMapper.addToken(token,webAdminId);
            return true;
        }catch (Exception ex){
            System.out.print(ex.toString());
            return false;
        }
    }
    @Override
    public List<User> getAreaAdminList(){
        return userMapper.getAllAreaAdmin();
    }

    @Override
    public int registerUser(User user){
        if(userMapper.getUserById(user.getUserId()) != null){
            return 0;  //用户名已经存在
        }
        try{
            userMapper.registerUser(user);
            return 1;  //成功写进数据库
        }catch (Exception ex){
            System.out.print(ex.toString());
            return 2;  //网络故障
        }
    }
    @Override
    public List<User> getRegisterUnChecked(){
        return userMapper.getRegisterUnChecked();
    }

    @Override
    public int getRegisterUnCheckedNum(){
        return userMapper.getRegisterUnCheckedNum();
    }
}
