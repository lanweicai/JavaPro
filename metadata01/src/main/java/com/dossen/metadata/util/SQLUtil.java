package com.dossen.metadata.util;

import com.dossen.metadata.bean.User;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class SQLUtil {

    /**
     * 静态代码块加载后，用map来存储jdbc连接数据库信息
     */
    public static HashMap<String, String> map = new HashMap<String, String>();
    static{

        //加载Properties文件获取连接SQL数据库参数
        Properties properties = new Properties();
        try {

            properties.load(new FileInputStream(new File("src/main/resources/jdbc.properties")));

        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("url", properties.getProperty("jdbc.url"));
        map.put("name", properties.getProperty("jdbc.username"));
        map.put("pwd", properties.getProperty("jdbc.password"));

    }


    /**
     *  传入user进入数据库查询是否存在，返回大于1存在，返回0则不存在
     * @param user
     * @return
     */
    public static int findSQL(User user) {

        //根据SQL查询结果为数量num，假设没有查到为0
        int num = 0;
        //取出需要查询的数据 username 和 password
        String username = user.getUsername();
        String password = user.getPassword();
        String sql = "select count(1) as number from User where name = '" + username + "'" + "and password = '" + password + "'";
        Connection connection = null;
        try {

            //1.创建连接
            connection = DriverManager.getConnection(map.get("url"), map.get("name"), map.get("pwd"));
            //2.获得一个statement对象,将要执行的sql脚本封装到此对象中
            CallableStatement statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                num = resultSet.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return num;
    }




}
