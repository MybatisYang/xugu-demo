package com.xugu.jdbc.preparestatement;

import com.xugu.jdbc.connection.DataBaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @date 2020/4/13
 * @description
 **/
public class BatchUpdate {


    /**
     * @author zhangkun
     * @date 2020/4/14 9:35
     * @description PrepareStatement 对象
     **/
    PreparedStatement ps =null;

    /**
     * @author zhangkun
     * @date 2020/4/14 9:35
     * @description Connection 对象
     **/
    Connection conn = null;

    /**
     * @author zhangkun
     * @date 2020/4/14 9:35
     * @description 日志对象
     **/
    Logger log = LoggerFactory.getLogger(BatchInsert.class);

    /**
     * @author zhangkun
     * @date 2020/4/14 9:36
     * @description DataBaseConnection 对象
     **/
    DataBaseConnection dbConn = new DataBaseConnection();

    /**
     * @author zhangkun
     * @date 2020/4/14 9:37
     * @description 返回结果集数组
     **/
    int[] results;

    /**
     * @author zhangkun
     * @date 2020/4/13 9:58
     * @description 批量更新表数据
     **/
    public int batchUpdate() throws SQLException {
        try {
            // 加载配置文件
            dbConn.openConnection("application.properties");
            //设置日期格式
             SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            // 获取当前时间
            Date date = new Date();
            log.info("当前时间为"+df.format(date));
            //获取数据库连接
            conn = dbConn.getConn();
            //设置事务为非自动提交
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("update xugu_table set name=? where id =?");
            //批量更新,十次提交一次
            for(int i=1;i<109;i++){
                 ps.setString(1,"bbb");
                 ps.setInt(2,i);
                ps.addBatch();
                if(i%10==0){
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }
            }
            //执行sql语句，获取返回结果
            results = ps.executeBatch();
            //事务提交
            conn.commit();
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
        return 0;
    }
}
