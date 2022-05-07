<p align="center">
  <a href="http://192.168.2.211/xugu-pm/xugu-rules">
    <img src="src/main/resources/images/xugu.jpg" alt="Logo" width="80" height="80">
  </a>
</p>
  <h1 align="center">项目名称：xugu-jdbc-demo</h1>

## 项目简介
本项目为虚谷数据库JDBC Driver使用示例程序，从JAVA代码层提供使用虚谷JDBC Driver操作虚谷数据库常见基本操作。


### 开发环境配置
| 工具                     | 版本          | 说明                     |
| ------------------------ | ------------- | ----------------------- |
| IDEA                     | 2019.1.1      | 后端开发IDE              |
| MAVEN                    | 3.5.0         | 项目框架                 |
| SUN JDK                  | 1.8.0_144     | JDK编译版本              |
| slf4j                    | 1.7.25        | 日志                     |


### 功能介绍
- 1.虚谷数据库JDBC Driver配置使用,代码详见(包：driver)
- 2.虚谷数据库JDBC驱动调用接口DML(INSERT、UPDATE、DELTE、SELECT)操作,代码详见(包: dml)
- 3.虚谷数据库JDBC驱动调用接口DDL(CREATE TABLE、ALTER TABLE、DROP TABLE、TRUNCATE TABLE)操作,代码详见(包：ddl)
- 4.虚谷数据库JDBC驱动调用接口PrepareStatement(预编译动态参数绑定、批量INSERT、批量UPDATE、批量DELETE)操作,代码详见(包：preparestatement)
- 5.虚谷数据库JDBC驱动使用流程(建立数据库连接->分配操作对象->数据库交互->资源回收关闭),代码详见(包：driver)
- 6.虚谷数据库JDBC驱动异常处理,代码详见(类：preparestatement.BatchInsert)
- 7.虚谷数据库JDBC驱动获取自增长列值(Statement,prepareStatement),代码详见(包：generatedKey)
- 8.虚谷数据库JDBC驱动获取数据库元数据getMetaData,代码详见(包：metadata)

### 源代码结构(maven工程)
| 目录                          | 目的                     |
| ----------------------------- | ------------------------ |
| ${basedir}                        | 存放pom.xml和所有的子目录|
| ${basedir}/src/main/java          | 项目的java源代码|
| ${basedir}/src/main/resources     | 项目的资源，比如property文件,lib/ 驱动程序包|
| ${basedir}/src/test/java          | 项目的测试类，比如说Junit代码|
| ${basedir}/src/test/resources     | 资源文件(配置文件、初始化脚本文件)|
| ~/.m2/repository                  | Maven默认的本地仓库目录位置|

### 搭建步骤
1. 在本机安装开发工具IDEA、MAVEN软件、Git
2. 从远端服务器克隆项目代码, git 地址:https://gitee.com/XuguDB/xugu-jdbc-demo.git
3. 在开发工具中配置MAVEN
4. 下载相关依赖包
5. 修改项目中虚谷数据库相关连接信息(IP地址、端口、数据库名称等))
6. 在目标数据库执行src/test/resources中的初始化数据库脚本
7. 执行src/test/java目录下的测试用例

### JDBC使用六大步骤  
1. 加载驱动(class.forName())
2. 创建连接connection
3. 创建管道Statement或PrepareStatement预处理
4. 执行sql语句
5. 处理结果集
6. 释放资源（关闭结果集,关闭Statement或PrepareStatement,关闭连接）

### 虚谷JDBC负载均衡使用说明

    JDBC内置均衡接口 基于调用者传入的多个连接ip地址和其他连接信息，通过内部均衡算法实现了在均等的对各个ip进行连接的建立。JDBC负载均衡接口分为配置文件和非配置文件两种方式
	标准jdbc:		
	url连接串：jdbc:xugu://127.0.0.1:5138/SYSTEM?user=SYSDBA&password=SYSDBA&char_set=utf8
	配置文件方式：  
	url连接串：jdbc:xugu:file://listener.xml:5138/SYSTEM?user=SYSDBA&password=SYSDBA&char_set=utf8
	非配置文件方式：
	url连接串：jdbc:xugu://127.0.0.1:5138/SYSTEM?user=SYSDBA&password=SYSDBA&char_set=utf8&ips=192.168.1.2,192.168.1.2,192.168.1.2

一、配置文件方式
	1.协议头由标准方式"jdbc:xugu:"变为"jdbc:xugu:file:"
	2.ip替换为配置文件*.xml,配置文件格式如下：
	
    <?xml version="1.0" encoding="utf-8"?>
    <listeners>
    <listener>
    <ip>192.168.1.206</ip>
    </listener>
    <listener>
    <ip>192.168.1.204</ip>
    </listener>
    <listener>
    <ip>192.168.1.205</ip>
    </listener>
    </listeners>
    
   3.使用方式demo 代码：
  
           public Connection getConnectionByFile() throws ClassNotFoundException, SQLException{
           Class.forName("com.xugu.cloudjdbc.Driver");
           conn = DriverManager.getConnection("jdbc:xugu:file://xuguClouldListener.xml:5138/SYSTEM?user=SYSDBA&password=SYSDBA");
           return conn;
        }

二、非配置文件方式
    
   使用方式demo 代码：
            
            /**
             * 非配置文件，负责均衡使用方式一
             * 均衡节点逗号分隔赋予ips 置于连接串后
             * @return
             * @throws ClassNotFoundException
             * @throws SQLException
             */
            public Connection getConnectionByProps1() throws ClassNotFoundException, SQLException
            {
                Class.forName("com.xugu.cloudjdbc.Driver");
                conn = DriverManager.getConnection("jdbc:xugu://192.168.1.201:5138/SYSTEM?user=SYSDBA&password=SYSDBA" +
                        "&ips=192.168.1.205,192.168.1.204,192.168.1.206");
                return conn;
            }
            /**
             * 非配置文件，负责均衡使用方式二
             * 均衡节点放入Vector<String>对象后put到ips
             * @return
             * @throws ClassNotFoundException
             * @throws SQLException
             */
            public Connection getConnectionByProps2() throws ClassNotFoundException, SQLException
            {
                Class.forName("com.xugu.cloudjdbc.Driver");
                Properties info=new Properties();
            Vector<String> ipsVector=new Vector<String>();
            ipsVector.add("192.168.1.201");
            ipsVector.add("192.168.1.204");
            ipsVector.add("192.168.1.205");
            ipsVector.add("192.168.1.206");
            info.put("ips", ipsVector);
            conn = DriverManager.getConnection("jdbc:xugu://192.168.1.201:5138/SYSTEM?user=SYSDBA&password=SYSDBA",info);
            return conn;
        }
        /**
         * 非配置文件，负责均衡使用方式三
         * 均衡节点放入String数组对象后put到ips
         * @return
         * @throws ClassNotFoundException
         * @throws SQLException
         */
        public Connection getConnectionByProps3() throws ClassNotFoundException, SQLException
        {
            Class.forName("com.xugu.cloudjdbc.Driver");
            Properties info=new Properties();
            String[] ips={"192.168.1.205","192.168.1.204","192.168.1.206"};
            info.put("ips", ips);
            conn = DriverManager.getConnection("jdbc:xugu://192.168.1.201:5138/SYSTEM?user=SYSDBA&password=SYSDBA",info);
            return conn;
        }
        /**
         * 非配置文件，负责均衡使用方式四
         * 均衡节点形成逗号分隔字符串对象后put到ips
         * @return
         * @throws ClassNotFoundException
         * @throws SQLException
         */
        public Connection getConnectionByProps4() throws ClassNotFoundException, SQLException
        {
            Class.forName("com.xugu.cloudjdbc.Driver");
            Properties info=new Properties();
            String ips="192.168.1.205,192.168.1.204,192.168.1.206";
            info.put("ips", ips);
            conn = DriverManager.getConnection("jdbc:xugu://192.168.1.201:5138/SYSTEM?user=SYSDBA&password=SYSDBA",info);
            return conn;
        }




<!-- CONTACT -->
## 联系Contact
技术支持QQ：240370218
技术支持Email：xugu@vip.163.com
项目链接: https://gitee.com/XuguDB/xugu-jdbc-demo.git 

