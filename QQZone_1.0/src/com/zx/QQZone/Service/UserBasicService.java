package com.zx.QQZone.Service;

import com.zx.QQZone.pojo.UserBasic;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @ClassName UserBasicService
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/24 15:08
 * @Version 1.0
 */
//UserBasicService 服务
public interface UserBasicService {
    //登入方法id和密码 获取basicUser
    UserBasic login(String logId, String pwd);
    //获取好友列表
    List<UserBasic> getFriendList(UserBasic userBasic) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException;
}
