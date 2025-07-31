package com.hotel.util;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static DataSource ds;

    // 文件的读取，只需要读取一次即可拿到这些值，静态代码块
    static{
        try {
            // 读取配置文件
            InputStream is = JdbcUtils.class.getClassLoader().
                    getResourceAsStream("jdbc.properties");
            Properties prop = new Properties();
            //  从流中加载数据
            prop.load(is);
            // 获取连接池对象
            ds = DruidDataSourceFactory.createDataSource(prop);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //  2）获得数据库连接 getConnecation()
    public static Connection getConnection() throws  Exception{
        return ds.getConnection();
    }


    //  3）关闭所有打开的资源  关闭顺序 ResultSet-->Statement-->Connection
    public static void close(ResultSet rs, Statement st, Connection cn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(cn!=null){
            try {
                cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement st, Connection cn){
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(cn!=null){
            try {
                cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}