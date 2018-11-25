package com.manager.nacelle_rent.dao;


import com.manager.nacelle_rent.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    //根据ID获取web账户登录的信息
    User getUserById(@Param("userId") String userId);

    //第一次登录时将token写进数据库
    void addToken (@Param("userToken") String userToken, @Param("userId") String userId);

    //得到所有区域管理员的id和姓名
    List<User> getAllAreaAdmin();

    //区域管理员、租方管理员、工作人员注册，默认是未认证状态
    void registerUser(User user);

    //获得所有尚未经过审核的账号信息
    List<User>  getRegisterUnChecked();

    //获得所有尚未经审核的账号数目
    int getRegisterUnCheckedNum();

    //根据WEB返回结果处理注册审核
    void deleteRegister(String userId);
    void updateRegisterState(String userId);
}
