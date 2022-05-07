package com.xugu.jdbc.preparestatement;

import com.xugu.jdbc.connection.DataBaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author
 * @version 1.0
 * @date 2020/4/13
 * @description
 **/
public class BatchDelete {

    /**
     * @description PrepareStatement 对象
     **/
    PreparedStatement ps;

    /**
     * @description Connection 对象
     **/
    Connection conn = null;

    /**
     * @description 日志对象
     **/
    Logger log = LoggerFactory.getLogger(BatchDelete.class);

    /**
     * @description DataBaseConnection 对象
     **/
    DataBaseConnection dbConn =  new DataBaseConnection();
    int [] resluts;

    /**
     * @description 批量删除表数据
     **/
    public int batchDelete() throws SQLException {
        try {
            // 加载配置文件
            dbConn.openConnection("application.properties");
            //获取数据库连接
            conn = dbConn.getConn();
            //设置事务为非自动提交
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("delete from  xugu_table  where id =?");
            //批量删除，一百次提交一次
             for (int i=1;i<108;i++){
                 ps.setInt(1,i);
                 ps.addBatch();
                 if(i%100==0){
                     ps.executeBatch();
                     conn.commit();
                     ps.clearBatch();
                 }
             }
            //执行sql语句，返回结果
            resluts = ps.executeBatch();
             //事务提交
            conn.commit();
            //返回结果数组长度
            return resluts.length;
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
        return  0;
    }

}
