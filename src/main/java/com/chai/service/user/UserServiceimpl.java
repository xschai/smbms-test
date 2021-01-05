package com.chai.service.user;

import com.chai.dao.BaseDao;
import com.chai.dao.user.UserDao;
import com.chai.dao.user.UserDaoimpl;
import com.chai.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class UserServiceimpl  implements UserService{
    private UserDao userDao;
    public UserServiceimpl(){
     userDao= new UserDaoimpl();
    }

    @Override
    public User login(String userCode,String userPassword) {
        // TODO Auto-generated method stub
        Connection connection = null;
        //通过业务层调用对应的具体数据库操作
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getLoginUer(connection, userCode);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            BaseDao.closeResource(connection, null, null);
        }

        // 匹配密码
        if (null != user) {
            if (!user.getUserPassword().equals(userPassword))
                user = null;
        }

        return user;
    }

    @Override
    public boolean updatePwd(int id, String password) throws Exception {
        Connection connection=null;
        boolean flag=false;
        connection=BaseDao.getConnection();
        if(userDao.updatePwd(connection,id,password)>0){
            flag=true;
        }
        BaseDao.closeResource(connection,null,null);
        return flag;
    }

    @Override
    public int getUserCount(String username, int userRole) {
        Connection connection=null;
        int count=0;
        try {
            connection = BaseDao.getConnection();
            count=userDao.getUserCount(connection,username,userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return count;

    }

    @Override
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
       Connection connection=null;
        List<User> userList=null;
        System.out.println("queryUserName ---- > " + queryUserName);
        System.out.println("queryUserRole ---- > " + queryUserRole);
        System.out.println("currentPageNo ---- > " + currentPageNo);
        System.out.println("pageSize ---- > " + pageSize);
        try {
            connection=BaseDao.getConnection();
            userList=userDao.getUserList(connection,queryUserName,queryUserRole,currentPageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return  userList;
    }

    @Override
    public boolean add(User user) {
        Connection connection=null;
        boolean flag=false;

        try {
            connection=BaseDao.getConnection();
            connection.setAutoCommit(false);
            int updateRows=userDao.add(connection,user);
            connection.commit();
            if(updateRows>0){
                flag=true;
                System.out.println("add success");
            }else{
                System.out.println("add failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }


//    @Test
//    public  void test(){
//        UserServiceimpl userServiceimpl=new UserServiceimpl();
//        int usercount=userServiceimpl.getUserCount(null,1);
//        System.out.println(usercount);
//    }
//    @Test
//    public void test(){
//
//        UserServiceimpl userService=new UserServiceimpl();
//        String userCode = "admin";
//        String userPassword = "1234567";
//        User admin = userService.login(userCode, userPassword);
//        System.out.println(admin.getUserPassword());
//    }
}
