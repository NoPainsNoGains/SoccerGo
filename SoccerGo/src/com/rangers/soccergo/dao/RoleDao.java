package com.rangers.soccergo.dao;

import java.util.List;

import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.model.User;

public interface RoleDao {
	void save(Role role);
	List<User> getUsersByRoleId(String objectId);
	List<Role> getAll();
	Role getById(String objectId);
	void addUser(Role role,User u);
	void addUsers(Role role,List<User> list);
	void deleteUser(Role role,User u);
	void deleteUsers(Role role,List<User> list);
	void deleteRole(Role role);
	int count();
}
