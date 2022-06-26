package com.zx.QQZone.Service;

import com.zx.QQZone.pojo.Topic;
import com.zx.QQZone.pojo.UserBasic;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @ClassName TopicService
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/26 15:41
 * @Version 1.0
 */
public interface TopicService {
   //查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException;

}
