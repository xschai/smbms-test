package com.chai.dao.role;

import com.chai.pojo.Role;

import java.sql.Connection;
import java.util.List;

public interface RoleDao {
    public List<Role> getRoleList(Connection connection)throws Exception;
}
