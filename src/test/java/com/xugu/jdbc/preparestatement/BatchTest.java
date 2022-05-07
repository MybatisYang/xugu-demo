package com.xugu.jdbc.preparestatement;

import com.xugu.jdbc.ddl.CreateTable;
import com.xugu.jdbc.ddl.DropTable;
import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * @author
 * @version 1.0
 * @date 2020
 * @description
 **/
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class BatchTest {

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
     * Method: batchInsert()
     */
    @Test
    public void test001BatchInsert() throws Exception {
        BatchInsert bi = new BatchInsert();
        // 实际值
        int actual = bi.batchInsert();
        //期望值
        int expected = 7;
        //使用断言机制判断
        Assert.assertEquals(expected,actual);
    }

    /**
     * Method: batchUpdate()
     */
    @Test
    public void test002BatchUpdate() throws Exception {
        BatchUpdate bu = new BatchUpdate();
        // 实际值
        int actual = bu.batchUpdate();
        //期望值
        int expected = 8;
        //使用断言机制判断
        Assert.assertEquals(expected,actual);
    }

    /**
     * Method: batchDelete()
     */
    @Test
    public void test003BatchDelete() throws Exception {
        BatchDelete bd = new BatchDelete();
        // 实际值
        int actual = bd.batchDelete();
        //期望值
        int expected = 7;
        //使用断言机制判断
        Assert.assertEquals(expected,actual);
    }
}
