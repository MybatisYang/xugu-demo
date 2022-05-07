package com.xugu.jdbc.generatedkey;

import com.xugu.jdbc.dml.DeleteValues;
import com.xugu.jdbc.connection.DataBaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author
 * @version 1.0
 * @date 2020/4/14
 * @description 通过Statement PreparedStatement对象获取插入数据的自增长值。
 **/
public class GeneratedKeyDemo {

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
     * @description Statement 对象
     **/
    Statement stmt;

    /**
     * @description PreparedStatement 对象
     **/
    PreparedStatement ps;

    /**
     * @description ResultSet 对象
     **/
    ResultSet rs;

    /**
     * @description Statement 对象获取自增长值。
     **/
    public int  getGeneratedKeyByStatement(){
        try{
            // 加载配置文件
            dbConn.openConnection("application.properties");
            //获取连接
            conn = dbConn.getConn();
            //sql语句 , 表ddl:create table xugu_table1(id int identity,name varchar);
            String sql ="insert into xugu_table1 (name) values('java')";
            //通过连接获取Statement
            stmt = conn.createStatement();
            //执行sql语句
            stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            //获取产生的自增长值
            rs = stmt.getGeneratedKeys();
            while(rs.next()){
                log.info("插入的id为:"+rs.getInt(1));
            }
            //返回结果集长度
            return rs.getRow();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            //关闭结果集资源
            try{
                if(rs != null){
                    rs.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            //关闭PreparedStatement 对象
            try{
                if(stmt != null){
                    stmt.close();
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
        //错误返回
        return  0;
    }

    /**
     * @description PreStatement 对象获取自增长
     **/
    public int  getGeneratedKeyByPs(){
        try{
            // 加载配置文件
            dbConn.openConnection("application.properties");
            //获取连接
            conn = dbConn.getConn();
            //sql语句 , 表ddl:create table xugu_table1(id int identity,name varchar);
            String sql ="insert into xugu_table1 (name) values(?)";
            //通过连接获取Statement
            ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //动态参入name 值
            ps.setString(1,"test");
            //执行sql语句
            ps.executeUpdate();
            //获取产生的自增长值
            rs = ps.getGeneratedKeys();
            while(rs.next()){
                log.info("插入的id为:"+rs.getInt(1));
            }
            //返回结果集长度
            return rs.getRow();
        }catch (SQLException e){
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
            //关闭PreparedStatement 对象
            try{
                if(ps != null){
                    ps.close();
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
