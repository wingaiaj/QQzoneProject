package com.zx.myssmSpring.BaseDAO;

import com.zx.myssmSpring.util.jdbcUtils;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BaseDAO
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/11 15:43
 * @Version 1.0
 */
public abstract class BaseDAO<T> {
    //获取当前BaseDAO的子类继承的父类中的泛型
    Class<T> clazz = null;

    //当实现类创建对象时
    {
        //当前实现类对象 调用
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        //获取父类泛型参数
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];
    }


    //获取单条记录
    public T getRecord(Connection connection, String sql, Object... args) {

        try {
            //预编译sql语句
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            //执行sql 获取结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            //获取结果集元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            if (resultSet.next()) {
                //创建对象 通过实现类的Class创建对象
                T t = clazz.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    //获取列名
                    String ColumnLabelName = metaData.getColumnLabel(i + 1);
                    //通过列名获取列值
                    Object value = resultSet.getObject(ColumnLabelName);
                    //通过反射获取当前对象属性名
                    Field declaredField = clazz.getDeclaredField(ColumnLabelName);
                    //设置为可更改
                    declaredField.setAccessible(true);
                    //反射给对象属性赋值
                    declaredField.set(t, value);
                }
                //关闭资源
                jdbcUtils.close(null, preparedStatement, resultSet);
                //返回对象
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO getRecord出错了");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取多条记录
    public List<T> getListRecord(Connection connection, String sql, Object... args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //预编译sql语句
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            //执行sql 返回结果集
            resultSet = preparedStatement.executeQuery();
            //获取结果集元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //创建集合存放多个对象
            ArrayList<T> list = new ArrayList();
            //循环判断多条数据
            while (resultSet.next()) {
                //创建实现类对象
                T t = clazz.newInstance();
                int columnCount = metaData.getColumnCount();
                //根据列数 获取列值 通过反射赋值给对象
                for (int i = 0; i < columnCount; i++) {
                    //获取列名
                    String label = metaData.getColumnLabel(i + 1);
                    //通过列名获取列值
                    Object value = resultSet.getObject(i + 1);
                    //通过列名  反射获取对象的属性
                    Field declaredField = clazz.getDeclaredField(label);
                    //设置属性为可编辑
                    declaredField.setAccessible(true);
                    //获取当前属性的类型名
                    String name = declaredField.getType().getName();
                    //为true是自定义类型
                    if (isMyType(name)) {
                        //获取指定类型构造器
                        Constructor<?> declaredConstructor = Class.forName(name).getDeclaredConstructor(java.lang.Integer.class);
                        //创建实例对象
                         value = declaredConstructor.newInstance((Integer) value);

                    }
                    declaredField.set(t, value);
                }
                list.add(t);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO getListRecord出错了");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            jdbcUtils.close(null, preparedStatement, resultSet);
        }
        return null;
    }

    private static boolean isMyType(String name) {
        if ("java.lang.Integer".equals(name)
                || "java.lang.String".equals(name)
                || "java.lang.Date".equals(name)
                || "java.sql.Date".equals(name)
                ||"java.sql.Timestamp".equals(name)) {
            return false;
        }
        return true;
    }

    //更新操作
    public boolean update(Connection connection, String sql, Object... args) {

        try {
            //预编译sql语句
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            //执行sql
            int i = preparedStatement.executeUpdate();
            //改变行数不为0 有数据 返回true 没有返回false
            //关闭资源
            jdbcUtils.close(null, preparedStatement, null);
            if (i > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO update出错了");
        }
    }

    //总条数
    public Object[] getCount(Connection connection, String sql, Object... args) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //预编译sql
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            //执行sql
            resultSet = preparedStatement.executeQuery();
            //获取元数据集
            ResultSetMetaData metaData = resultSet.getMetaData();
            //返回数组对象
            Object[] obj = null;
            //判断是否有数据
            if (resultSet.next()) {
                //获取总列数
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object value = resultSet.getObject(i + 1);
                    //数组大小赋值
                    obj = new Object[columnCount];
                    obj[i] = value;
                }
                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO getCount出错了");
        } finally {
            //关闭资源
            jdbcUtils.close(null, preparedStatement, resultSet);
        }
        return null;
    }
}