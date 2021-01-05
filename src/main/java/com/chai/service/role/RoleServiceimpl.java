package com.chai.service.role;

import com.chai.dao.BaseDao;
import com.chai.dao.role.RoleDao;
import com.chai.dao.role.RoleDaoimpl;
import com.chai.pojo.Role;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RoleServiceimpl implements RoleService{
    private RoleDao roleDao;

    public RoleServiceimpl(){
        roleDao=new RoleDaoimpl();
    }


    @Override
    public List<Role> getRoleList() {
        Connection connection=null;
        List<Role> roleList=null;

        try {
            connection= BaseDao.getConnection();
            roleList=roleDao.getRoleList(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return roleList;
    }
//    @Test
//    public void test(){
//        RoleServiceimpl roleServiceimpl=new RoleServiceimpl();
//        List<Role> roleList=null;
//        System.out.println("111111111111111");
//        roleList=roleServiceimpl.getRoleList();
//        System.out.println("111111111111111");
//        for (Role role : roleList) {
//            System.out.println(role.getRoleName());
//        }
//    }
}
