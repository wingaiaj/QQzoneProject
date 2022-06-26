package com.zx.QQZone.Service.Impl;

import com.zx.QQZone.DAO.UserBasicDAO;
import com.zx.QQZone.Service.UserBasicService;
import com.zx.QQZone.pojo.UserBasic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserBasicServiceImpl
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/26 15:02
 * @Version 1.0
 */
public class UserBasicServiceImpl implements UserBasicService {
    //实现基本用户DAO
    private UserBasicDAO userBasicDAO = null;

    //登入方法实现
    @Override
    public UserBasic login(String loginId, String pwd) {
        //调用DAO方法 获取UserBasic
        UserBasic userBasic = userBasicDAO.getUserBasic(loginId, pwd);
        return userBasic;
    }

    //获取好友列表方法实现
    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
        //获取当前拥有uid的对象的fid
        List<UserBasic> userBasicList = userBasicDAO.getUserBasicList(userBasic);
        List<UserBasic> friendList = new ArrayList<>(userBasicList.size());
        //遍历
        for (int i = 0; i < userBasicList.size(); i++) {
            //获取所有 fid
            UserBasic friend = userBasicList.get(i);
            //根据fid获取所有userBasic
            friend = userBasicDAO.getUserBasicById(friend.getId());
            friendList.add(friend);
        }
        //返回userBasic集合
        return friendList;
    }
}
