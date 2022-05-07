package com.xugu.jdbc.dml;


import com.xugu.jdbc.connection.DataBaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @date 2020/4/11 15:00
 * @description
 **/
public class SelectValues {

    /**
     * @description 执行语句
     **/
    String sql;

    /**
     * @description Statement对象
     **/
    Statement stmt;

    /**
     * @description  数据库连接对象
     **/
    Connection conn;

    /**
     * @description 结果集对象
     **/
    ResultSet rs;

    /**
     * @description 日志对象
     **/
    Logger log = LoggerFactory.getLogger(SelectValues.class);

    /**
     * @description DataBaseConnection 对象
     **/
    DataBaseConnection dbConn =  new DataBaseConnection();

    /**
     * @description 设置日期格式
     **/
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    /**
     * @description 查询表中数据
     **/
    public int selectValues() throws Exception{

        // 加载配置文件
        dbConn.openConnection("application.properties");
        //sql语句
        sql="select id,name,age from xugu_table";
        // 获取当前时间
        Date date1 = new Date();
        log.info("建立连接前时间为:"+df.format(date1));
        try {
            //获取数据库连接
            conn = dbConn.getConn();
            //通过连接获取Statement
            stmt =conn.createStatement();
            Date date2 = new Date();
            log.info("执行语句前的准备时间为:"+(date2.getTime()-date1.getTime())+"毫秒");
            //执行sql语句，获取返回结果
            rs = stmt.executeQuery(sql);
            Date date3 = new Date();
            log.info("查询用时为："+(date3.getTime()-date2.getTime())+"毫秒");
            //打印查询结果集
            while(rs.next()){
                log.info(String.valueOf(rs.getInt(1)));
                log.info(rs.getString(2));
                log.info(String.valueOf(rs.getInt(3)));
            }
            Date date4 = new Date();
            log.info("建立连接到数据处理完毕总耗时为："+(date4.getTime()-date1.getTime())+"毫秒");
            //打印结果集当前行号
            log.info("当前行号为："+rs.getRow());
            return rs.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            //释放result（结果集）资源
            if(rs != null){
                try{
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
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
