package com.xugu.jdbc.dml;

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
public class DMLTest {

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
     * Method: insertValues()
     */
    @Test
    public void test001InsertValues() throws Exception {
        InsertValues iv = new InsertValues();
        //实际值
        int actual = iv.insertValues();
        //期望值
        int expected = 1;
        //使用断言机制判断
        Assert.assertEquals(actual,expected);
    }

    /**
     * Method: selectValues()
     */
    @Test
    public void test002SelectValues() throws Exception {
        SelectValues sv = new SelectValues();
        //实际值
        int actual = sv.selectValues();
        //期望值
        int expected = 1;
        //使用断言机制判断
        Assert.assertEquals(expected,actual);
    }

    /**
     * Method: updateValues()
     */
    @Test
    public void test003UpdateValues() throws Exception {
        UpdateValues uv = new UpdateValues();
        // 实际值
        int actual = uv.updateValues();
        //期望值
        int expected = 1;
        //使用断言机制判断
        Assert.assertEquals(expected,actual);
    }

    /**
     * Method: deleteValues()
     */
    @Test
    public void test004DeleteValues() throws Exception {
        DeleteValues dv = new DeleteValues();
        // 实际值
        int actual = dv.deleteValues();
        //期望值
        int expected = 1;
        //使用断言机制判断
        Assert.assertEquals(expected,actual);
    }

}
