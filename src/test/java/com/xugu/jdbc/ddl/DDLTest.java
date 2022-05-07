package com.xugu.jdbc.ddl;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author
 * @version 1.0
 * @date 2020
 * @description
 **/
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class DDLTest {

    /**
     * Method: createTable()
     */
    @Test
    public void test001CreateTable() throws Exception {
        CreateTable ct = new CreateTable();
        //实际值
        int actual = ct.createTable();
        //期望值
        int expected = -1;
        //使用断言机制判断
        Assert.assertEquals(actual,expected);
    }

    /**
     * Method: alterTable()
     */
    @Test
    public void test002AlterTable() throws Exception {
        AlterTable at = new AlterTable();
        //实际值
        int actual = at.alterTable();
        //期望值
        int expected = -1;
        Assert.assertEquals(expected,actual);
    }

    /**
     * Method: truncateTable()
     */
    @Test
    public void test003TruncateTable() throws Exception {
        TruncateTable tt = new TruncateTable();
        // 实际值
        int actual = tt.truncateTable();
        //期望值
        int expected = -1;
        //使用断言机制判断
        Assert.assertEquals(expected,actual);
    }

    /**
     * Method: dropTable()
     */
    @Test
    public void test004DropTable() throws Exception {
        DropTable dt = new DropTable();
        // 实际值
        int actual = dt.dropTable();
        //期望值
        int expected = -1;
        //使用断言机制判断
        Assert.assertEquals(actual,expected);
    }
}
