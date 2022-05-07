package com.xugu.jdbc.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

/**
 * @author zhangkun
 */
public class DataBaseConnection {

    /**
     * 当前连接对象
     */
    private Connection conn;

    /**
     * 连接的数据库驱动
     */
    private String dbDriver;

    /**
     * 连接的url
     */
    private String dbURL;

    /**
     * 数据库用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;


    /**
     * 从配置文件取数据库链接参数
     * @param dbConnFile
     * @throws IOException
     */
    private void loadConnProperties(String dbConnFile) throws IOException {
        Properties props = new Properties();

        try {
            //根据配置文件路径Conf加载配置文件
            //props.load(new FileInputStream(new File(dbConnFile)));
            props.load(new InputStreamReader(DataBaseConnection.class.getClassLoader().getResourceAsStream(dbConnFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //从配置文件中取得相应的参数并设置类变量
        //驱动名
        this.dbDriver = props.getProperty("driver");
        //连接串
        this.dbURL = props.getProperty("url");
        //用户名
        this.userName = props.getProperty("username");
        //密码
        this.password = props.getProperty("password");
    }

   /**
    *
    * @author zhangkun
    * @date 2020/4/11 11:12
    * @description 获取连接属性
    **/
    public boolean openConnection(String dbConnFile){
        try {
            //获取连接属性
            loadConnProperties(dbConnFile);
            //注册驱动
            Class.forName(dbDriver);
            //获取连接对象
            this.conn = DriverManager.getConnection(dbURL,userName,password);
            return true;
        } catch(ClassNotFoundException classnotfoundexception) {
            classnotfoundexception.printStackTrace();
            System.err.println("db: " + classnotfoundexception.getMessage());
        } catch(SQLException | IOException sqlexception) {
            System.err.println("db.getconn(): " + sqlexception.getMessage());
        }
        return  false;
    }

    /**
     * 获取数据库连接
     * @return
     */
    public Connection getConn() {
        System.out.println("数据库已连接");
        return conn;
    }

}

