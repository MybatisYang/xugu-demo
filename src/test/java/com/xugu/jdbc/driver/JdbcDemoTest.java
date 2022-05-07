package com.xugu.jdbc.driver;

import org.junit.Assert;
import org.junit.Test;

/**
 * ExceptionDemo Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>4月 13, 2020</pre>
 */
public class JdbcDemoTest {

    /**
     * Method: exceptionDemo()
     */
    @Test
    public void testExceptionDemo() throws Exception {
        JdbcDemo ed = new JdbcDemo();
        // 实际值
        int actual = ed.jdbcDemo();
        //期望值
        int expected = 1;
        //使用断言机制判断
        Assert.assertEquals(expected,actual);
    }

} 
