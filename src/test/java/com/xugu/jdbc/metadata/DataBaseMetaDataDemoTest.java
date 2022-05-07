package com.xugu.jdbc.metadata;

import com.xugu.jdbc.ddl.CreateTable;
import com.xugu.jdbc.ddl.DropTable;
import org.junit.*;

/**
 * DataBaseMetaDataDemo Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>4月 14, 2020</pre>
 */
public class DataBaseMetaDataDemoTest {

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
     * Method: getDataBaseMetaData()
     */
    @Test
    public void testGetDataBaseMetaData() throws Exception {
        DataBaseMetaDataDemo dmd = new DataBaseMetaDataDemo();
        //实际值
        int actual = dmd.getDataBaseMetaData();
        //期望值
        int expected =  1;
        //使用断言机制判断
        Assert.assertEquals(expected,actual);
    }


} 
