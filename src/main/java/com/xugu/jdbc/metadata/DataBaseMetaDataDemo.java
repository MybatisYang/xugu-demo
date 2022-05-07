package com.xugu.jdbc.metadata;

import com.xugu.jdbc.dml.DeleteValues;
import com.xugu.jdbc.connection.DataBaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author
 * @version 1.0
 * @date 2020/4/14
 * @description 数据库元数据使用demo。
 **/
public class DataBaseMetaDataDemo {

    /**
     * @description  数据库连接
     **/
    Connection conn;

    /**
     * @description 日志对象
     **/
    Logger log = LoggerFactory.getLogger(DeleteValues.class);

    /**
     * @description DataBaseConnection 对象
     **/
    DataBaseConnection dbConn =  new DataBaseConnection();

    /**
     * @description 定义DatabaseMetaData对象
     **/
    DatabaseMetaData dm = null;

    /**
     * @description 定义结果集对象
     **/
    ResultSet rs = null;


    public int  getDataBaseMetaData()  {

        // 加载配置文件
        dbConn.openConnection("application.properties");
        try{
            //获取数据库连接
            conn = dbConn.getConn();
            //实例化对象
            dm = conn.getMetaData();
            //打印当前数据库名称
            log.info("数据库名称："+dm.getDatabaseProductName());
            //打印数据库主次版本号
            log.info("数据库主版本号："+dm.getDatabaseMajorVersion()
                    + "数据库次版本号："+dm.getDatabaseMinorVersion());
            //获取xugu_table表的主键
            rs = dm.getPrimaryKeys(null,null,"xugu_table");
            //打印结果集信息
            while(rs.next()){
                //打印表类别
                log.info("表类别为："+rs.getString(1));
                //打印表模式
                log.info("表模式为："+rs.getString(2));
                //打印表名称
                log.info("表名称为："+rs.getString(3));
                //打印列名称
                log.info("列名称为："+rs.getString(4));
                //打印主键序列号
                log.info("主键序列号"+rs.getString(5));
                //打印主键名
                log.info("主键名为："+rs.getString(6));
            }
            //返回结果集当前行
            return rs.getRow();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            //关闭结果集资源
            try{
                if(rs != null){
                    rs.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            //关闭连接
            try{
                if(conn != null){
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}
