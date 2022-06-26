package com.zx.QQZone.DAO.impl;

import com.zx.QQZone.DAO.UserBasicDAO;
import com.zx.QQZone.pojo.UserBasic;
import com.zx.myssmSpring.BaseDAO.BaseDAO;
import com.zx.myssmSpring.util.jdbcUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName UserBaseDAOImpl
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/23 16:44
 * @Version 1.0
 */
public class UserBaseDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    Connection connection = null;

    {
        try {
            connection = jdbcUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        //查询数据库中是否存在 id and pwd
        String sql = "select * from t_user_basic where loginId=? and pwd=?";
        //返回 userBasic 中的所有属性
        return super.getRecord(connection, sql, loginId, pwd);
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
        //返回多个fid包含uid
        String sql = "select fid as id from t_friend where uid = ?";
        return super.getListRecord(connection, sql, userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        String sql = "select * from t_user_basic where id = ? ";
        return super.getRecord(connection, sql, id);
    }
}
