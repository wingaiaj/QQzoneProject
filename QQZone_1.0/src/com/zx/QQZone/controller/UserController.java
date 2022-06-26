package com.zx.QQZone.controller;

import com.zx.QQZone.Service.TopicService;
import com.zx.QQZone.Service.UserBasicService;
import com.zx.QQZone.pojo.Topic;
import com.zx.QQZone.pojo.UserBasic;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/26 16:10
 * @Version 1.0
 */
public class UserController {
    UserBasicService userBasicService = null;
    TopicService topicService = null;

    public String login(String loginId, String pwd) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException {

        //获取用户信息
        UserBasic userBasic = userBasicService.login(loginId, pwd);

        if (userBasic != null) {
            //获取用户好友列表
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            //获取所有日志信息
            List<Topic> topicList = topicService.getTopicList(userBasic);
            //保存在UserBasic
            //所有日志
            userBasic.setTopicList(topicList);
            //所有好友
            userBasic.setFriendList(friendList);
            //返回主页面
            return "index";
        } else {
            //登入页面
            return "login";
        }
    }
}