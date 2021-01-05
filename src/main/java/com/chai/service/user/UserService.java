package com.chai.service.user;

import com.chai.pojo.User;

import java.util.List;

public interface UserService {
    public User login(String userCode,String password);
    public boolean updatePwd(int id,String password)throws  Exception;
    public int getUserCount(String username, int userRole);
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);
    public boolean add(User user);
}
