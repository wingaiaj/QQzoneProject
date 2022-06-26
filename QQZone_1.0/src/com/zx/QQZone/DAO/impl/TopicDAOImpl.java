package com.zx.QQZone.DAO.impl;

import com.zx.QQZone.DAO.TopicDAO;
import com.zx.QQZone.pojo.Topic;
import com.zx.QQZone.pojo.UserBasic;
import com.zx.myssmSpring.BaseDAO.BaseDAO;
import com.zx.myssmSpring.util.jdbcUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName TopicDAOImpl
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/24 14:41
 * @Version 1.0
 */
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    Connection connection;

    {
        try {
            connection = jdbcUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Topic> getTopicList(UserBasic userBasic) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
        String sql = "select * from t_topic where author = ?";
        return getListRecord(connection, sql, userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {

    }

    @Override
    public Topic getTopic(Integer id) {
        return null;
    }
}
