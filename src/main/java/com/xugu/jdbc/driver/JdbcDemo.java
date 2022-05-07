package com.xugu.jdbc.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author
 * @version 1.0
 * @date 2002/4/13
 * @description  jdbc对异常处理的例子
 **/
public class JdbcDemo {

    /**
     * @author zhangkun
     * @date 2020/4/14 9:31
     * @description Connection 对象
     **/ 
    Connection conn = null ;
    
    /**
     * @description Statement 对象
     **/ 
    Statement st = null ;
    
    /**
     * @description 日志对象
     **/ 
    Logger log = LoggerFactory.getLogger(JdbcDemo.class);
    
    /**
     * @description 数据库连接以及sql执行标准流程
     **/ 
    public int jdbcDemo() {

        int count = 0;
        try {
            // 注册驱动
            Class.forName("com.xugu.cloudjdbc.Driver") ;
            // 获取连接对象
            conn = DriverManager.getConnection("jdbc:xugu://127.0.0.1:5138/SYSTEM?characterEncoding=UTF-8", "SYSDBA", "SYSDBA") ;
            // 获取执行sql的对象
            st = conn.createStatement();
            // 执行sql , 获取结果
            String sql = "insert into xugu_table values (2 ,'zhansgan',33)" ;
            count = st.executeUpdate(sql) ;
            // 处理结果
            log.info("count:"+count);
        } catch (Exception e) {
            e.printStackTrace() ;
        } finally {
            // 释放Statement资源
            if(st != null) {
                try {
                    st.close() ;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放连接资源
            if(conn != null) {
                try {
                    conn.close() ;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return  count;
    }
}
