package com.xugu.jdbc.ddl;

import com.xugu.jdbc.connection.DataBaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author zhangkun
 * @date 2020/4/11 14:04
 * @description
 **/
public class DropTable {

    /**
     * @description 日志对象
     **/
    private static final  Logger log = LoggerFactory.getLogger(DropTable.class);

    /**
     * @description 执行语句
     **/
    String sql;

    /**
     * @description Statement对象
     **/
    Statement stmt;

    /**
     * @description  数据库连接
     **/
    Connection conn;


    /**
     * @description DataBaseConnection 对象
     **/
    DataBaseConnection dbConn =  new DataBaseConnection();

    /**
     * @description 删除表
     **/
    public  int dropTable() throws Exception {

        // 加载配置文件
        dbConn.openConnection("application.properties");
        //sql语句
        sql = "drop table xugu_table";
        try {
            //获取数据库连接
            conn = dbConn.getConn();
            //通过连接获取Statement
            stmt = conn.createStatement();
            //执行sql语句，获取返回结果。
            int result = stmt.executeUpdate(sql);
            //打印结果
            log.info("result：" + String.valueOf(result));
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
