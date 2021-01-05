package com.chai.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
public class BaseDao {

    static{//静态代码块,在类加载的时候执行
        init();
    }

    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    public static void init(){
        Properties properties=new Properties();
        String configFile = "db.properties";
        InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver=properties.getProperty("driver");
        url=properties.getProperty("url");
        //初始化连接参数,从配置文
        user=properties.getProperty("user");
        password=properties.getProperty("password");

    }


    /**
     * 获取数据库连接
     * @return
     */
    //static
    public static  Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return connection;
    }
    /**
     * 查询操作
     */
    //static
    public static ResultSet execute(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet,String sql,Object[] params) throws Exception{
        //预编译的sql不需要传参，直接执行即可
        preparedStatement = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        resultSet = preparedStatement.executeQuery();//新添加sql
        return resultSet;
    }
    /**
     * 更新操作
     */
    //static
    public static int execute(Connection connection,PreparedStatement preparedStatement,String sql,Object[] params) throws Exception{
        int updateRows = 0;
        preparedStatement = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }

    /**
     * 释放资源

     */
    //static
    public static  boolean closeResource(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        boolean flag = true;
        if(resultSet != null){
            try {
                resultSet.close();
                resultSet = null;//GC回收
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                flag = false;
            }
        }
        if(preparedStatement != null){
            try {
                preparedStatement.close();
                preparedStatement = null;//GC回收
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                flag = false;
            }
        }
        if(connection != null){
            try {
                connection.close();
                connection = null;//GC回收
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }
}
