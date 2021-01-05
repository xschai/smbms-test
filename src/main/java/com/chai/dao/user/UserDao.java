package com.chai.dao.user;

import com.chai.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    //得到要登录的用户
    public User getLoginUer(Connection connection, String uerCode) throws Exception;
    //修改当前密码
    public int updatePwd(Connection connection,int id,String password)throws Exception;
    //查询用户总数
    public int  getUserCount(Connection connection,String username,int userRole)throws SQLException, Exception;
    //获取用户列表
    public List<User> getUserList(Connection connection,String username,int userRole, int currentPageNo, int pageSize)throws  Exception;
    //添加用户信息
    public int add(Connection connection,User user)throws Exception;
}
