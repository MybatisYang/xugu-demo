package com.xugu.jdbc.generatedkey;

import com.xugu.jdbc.ddl.CreateTable;
import com.xugu.jdbc.ddl.DropTable;
import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * GeneratedKeyDemo Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>4月 14, 2020</pre>
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GeneratedKeyDemoTest {

    /**
     * Method: createTable()
     */
    @BeforeClass
    public static void testCreateTable() throws Exception {
        CreateTable ct = new CreateTable();
        //实际值
        int actual = ct.createTable();
        //期望值
        int expected = -1;
        //使用断言机制判断
        Assert.assertEquals(actual,expected);
    }

    /**
     * Method: dropTable()
     */
    @AfterClass
    public static void testDropTable() throws Exception {
        DropTable dt = new DropTable();
        // 实际值
        int actual = dt.dropTable();
        //期望值
        int expected = -1;
        //使用断言机制判断
        Assert.assertEquals(actual,expected);
    }

    /**
     * Method: getGeneratedKeyByStatement()
     */
    @Test
    public void test001GetGeneratedKeyByStatement() throws Exception {
        GeneratedKeyDemo gkd = new GeneratedKeyDemo();
        //实际值
        int actual = gkd.getGeneratedKeyByStatement();
        //期望值
        int expected = 1;
        //使用断言机制判断
        Assert.assertEquals(expected,actual);
    }

    /**
     * Method: getGeneratedKeyByPs()
     */
    @Test
    public void test002GetGeneratedKeyByPs() throws Exception {
        GeneratedKeyDemo gkd = new GeneratedKeyDemo();
        //实际值
        int actual = gkd.getGeneratedKeyByPs();
        //期望值
        int expected = 1;
        //使用断言机制判断
        Assert.assertEquals(expected,actual);
    }


} 
