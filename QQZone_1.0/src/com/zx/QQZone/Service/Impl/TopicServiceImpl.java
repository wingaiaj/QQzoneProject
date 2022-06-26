package com.zx.QQZone.Service.Impl;

import com.zx.QQZone.DAO.TopicDAO;
import com.zx.QQZone.Service.TopicService;
import com.zx.QQZone.pojo.Topic;
import com.zx.QQZone.pojo.UserBasic;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @ClassName TopicServiceImpl
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/26 15:42
 * @Version 1.0
 */
public class TopicServiceImpl implements TopicService {
   //现实TopicDAO
    private TopicDAO topicDAO = null;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
       //获取指定用户所有日志
        List<Topic> topicList = topicDAO.getTopicList(userBasic);
        return topicList;
    }

}
