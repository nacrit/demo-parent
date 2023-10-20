package com.example.demo.design_pattern.a_head_first_design_patterns.i_facade;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

// 门面角色（facade）
public class JDBCUtils {
    private static Connection con;
    private static String driverClass;
    private static String url;
    private static String user;
    private static String password;
    static {
        try {
            readConfig();
            Class.forName(driverClass);
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void readConfig() throws Exception{
        InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("database.properties");
        Properties pro = new Properties();
        pro.load(in);
        driverClass = pro.getProperty("driverClass");
        url = pro.getProperty("url");
        user = pro.getProperty("user");
        password = pro.getProperty("password");

    }
    public static Connection getConnection(){
        return con;
    }

    public static void close(Connection conn, Statement statement, ResultSet rs) {
        //关闭之前要先判断
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Map<String, String>> query(String sql, String ... filedNames) {
        List<Map<String, String>> list = new ArrayList<>();
        //1. 创建连接
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            //2. 通过连接对象得到语句对象
            statement = conn.createStatement();
            //3. 通过语句对象发送 SQL 语句给服务器
            //4. 执行 SQL
            rs = statement.executeQuery(sql);
            //4) 循环遍历取出每一条记录
            while(rs.next()) {
                //5) 输出的控制台上
                Map<String, String> map = new HashMap<>();
                for (String filedName : filedNames) {
                    map.put(filedName, rs.getString(filedName));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6. 释放资源
            close(conn, statement, rs);
        }
        return list;
    }

    public static String[] getTableFileNames(String tableName) {
        String[] filedNames = null;
        //1. 创建连接
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            //2. 通过连接对象得到语句对象
            statement = conn.createStatement();
            //3. 通过语句对象发送 SQL 语句给服务器
            //4. 执行 SQL
            rs = statement.executeQuery("desc " + tableName);
            //4) 循环遍历取出每一条记录
            List<String> filedNameList = new ArrayList<>();
            while(rs.next()) {
                //5) 输出的控制台上
                filedNameList.add(rs.getString("Field"));
            }
            filedNames = new String[filedNameList.size()];
            filedNameList.toArray(filedNames);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6. 释放资源
//            close(conn, statement, rs);
        }
        return filedNames;
    }

}
