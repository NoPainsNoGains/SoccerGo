package com.rangers.soccergo.dao;

import java.util.List;

import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.model.User;

public interface RoleDao extends CommonDao<Role> {
	void addUser(Role role,User u);
	void addUsers(Role role,List<User> list);
	void deleteUser(Role role,User u);
	void deleteUsers(Role role,List<User> list);
	List<User> getUsersByRoleId(String objectId);
	List<User> getPageUsersByRoleId(int page, int pageSize,String objectId);
	List<Role> getRoleByUserId(String objectId);
}
