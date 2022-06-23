package com.zx.QQZone.DAO;

import com.zx.QQZone.pojo.Topic;
import com.zx.QQZone.pojo.UserBasic;

import java.util.List;

/**
 * @ClassName TopicDAO
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/23 16:37
 * @Version 1.0
 */
public interface TopicDAO {
    //获取指定用户的所有日志
    public List<Topic> getTopicList(UserBasic userBasic);

    //添加日志
    public void addTopic(Topic topic);

    //删除日志
    public void delTopic(Topic topic);

    //获取特定日志信息
    public Topic getTopic(Integer id);
}
