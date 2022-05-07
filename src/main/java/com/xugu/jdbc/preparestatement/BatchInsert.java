package com.xugu.jdbc.preparestatement;

import com.xugu.jdbc.connection.DataBaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author
 * @version 1.0
 * @date 2020/4/11
 * @description 批量插入。
 **/
public class BatchInsert {

    /**
     * @description PrepareStatement 对象
     **/
    PreparedStatement ps =null;

    /**
     * @description Connection 对象
     **/
    Connection conn = null;

    /**
     * @description 日志对象
     **/
    Logger log = LoggerFactory.getLogger(BatchInsert.class);

    /**
     * @description DataBaseConnection 对象
     **/
    DataBaseConnection dbConn = new DataBaseConnection();

    /**
     * @description 返回结果集数组
     **/
    int[] results;

    /**
     * @description prepareStatement 实现批量插入
     **/
    public int batchInsert() throws SQLException {
        try {

            // 加载配置文件
            dbConn.openConnection("application.properties");
            //获取数据库连接
             conn = dbConn.getConn();
             //设置事务为非自动提交
             conn.setAutoCommit(false);
            //获取数据库元数据对象
            DatabaseMetaData metaData = conn.getMetaData();
            //判断数据库是否支持批处理
            boolean bool = metaData.supportsBatchUpdates();
            log.info("虚谷数据库是否支持批处理" + bool);
            ps = conn.prepareStatement("insert into xugu_table1 (name)  values(?)");
            // 获取当前时间
            long time1 = System.currentTimeMillis();
            //批量插入 一百次提交一次
            for (int i=1;i<1008;i++){
                ps.setString(1,"aa"+i);
                ps.addBatch();
                if(i%100==0){
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }
            }
            //执行sql语句，获取返回结果
            results=ps.executeBatch();
            //事务提交
            conn.commit();
            //获取当前时间
            long time2 =System.currentTimeMillis();
            //批量插入执行时间
            log.info("执行时间为："+(time2-time1));
            //返回结果数组长度
            return results.length;
        } catch (SQLException e) {
            e.printStackTrace();
            // 若出现异常，对数据库中所有已完成的操作全部撤销，则回滚到事务开始状态
            if(!conn.isClosed()){
                //当异常发生执行catch中SQLException时，记得要rollback(回滚)；
                conn.rollback();
            }
        } finally {
            // 释放Statement资源
            if(ps != null) {
                try {
                    ps.close() ;
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
        return 0;
    }
}

