package com.xugu.jdbc.dml;


import com.xugu.jdbc.connection.DataBaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author
 * @version 1.0
 * @date 2020/4/11 14:42
 * @description
 **/
public class InsertValues {

    /**
     * @description 执行语句
     **/
    String sql;

    /**
     * @author zhangkun
     * @date 2020/4/14 8:53
     * @description Statement对象
     **/
    Statement stmt;

    /**

     * @description  数据库连接
     **/
    Connection conn;

    /**
     * @description 日志对象
     **/
    Logger log = LoggerFactory.getLogger(InsertValues.class);

    /**
     * @description DataBaseConnection 对象
     **/
    DataBaseConnection dbConn =  new DataBaseConnection();

    /**
     * @description  向表插入数据
     **/
    public int insertValues() throws Exception{


        // 加载配置文件
        dbConn.openConnection("application.properties");
        //操作语句
        sql="insert into xugu_table values(1,'zk',22)";
        try {
            //获取数据库连接
            conn = dbConn.getConn();
            //通过连接获取Statement
            stmt =conn.createStatement();
            //执行sql语句，获取返回结果
            int result = stmt.executeUpdate(sql);
            log.info("受影响的行数为："+String.valueOf(result));
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            // 释放Statement资源
            if(stmt != null) {
                try {
                    stmt.close() ;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放Connection资源
            if(conn != null) {
                try {
                    conn.close() ;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //错误返回
        return  0;
    }
}
