package com.zx.QQZone.DAO;

import com.zx.QQZone.pojo.UserBasic;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @ClassName UserBasicDAO
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/23 16:27
 * @Version 1.0
 */
public interface UserBasicDAO {
    //根据账号和密码获取特定用户信息
    public UserBasic getUserBasic(String loginId, String pwd);

    //获取指定用户的所有好友列表
    public List<UserBasic> getUserBasicList(UserBasic userBasic) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException;

    //根据id查询用户信息
    public UserBasic getUserBasicById(Integer id);

}
