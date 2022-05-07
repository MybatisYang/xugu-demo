package com.xugu.jdbc.connection;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

/**
 * DataBaseConnection Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>4月 11, 2020</pre>
 */
public class DataBaseConnectionTest {

    /**
     * Method: getConn()
     */
    @Test
    public void testGetConn() throws Exception {
        DataBaseConnection dbConn = new DataBaseConnection();
        // 加载配置文件
        dbConn.openConnection("application.properties");
        Connection conn = dbConn.getConn();
        System.out.println("------"+conn.getSchema());
        System.out.println("数据库"+conn.getCatalog());
        System.out.println("用户名"+conn.getMetaData().getUserName());
        // 实际值
        Boolean  actual = conn.isReadOnly();
        //期望值
        Boolean expected = false;
        //使用断言机制判断
        Assert.assertEquals(expected, actual);
        conn.close();
    }

} 
