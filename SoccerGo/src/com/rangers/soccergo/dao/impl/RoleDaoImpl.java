package com.rangers.soccergo.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.rangers.soccergo.dao.RoleDao;
import com.rangers.soccergo.db.util.CloudQuery;
import com.rangers.soccergo.db.util.CloudSession;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.model.User;

public class RoleDaoImpl implements RoleDao {
	private CloudSession session = new CloudSession();
	
	public CloudSession getSession() {
		return session;
	}

	public void setSession(CloudSession session) {
		this.session = session;
	}
	
    /*
     * 
     * 保存角色
     */	
	public void save(Role role) {
		session.save(role);		
	}
	
	/*
     * 通过角色ID 得到该角色下的所有用户
     * 
     */	
	public List<User> getUsersByRoleId(String objectId) {
		String cql = "select * from _User where related users to pointer('_Role',?)";
		CloudQuery query = session.executeQuery(cql);
		query.setParam(0,objectId);
		return query.list();
	}
	
	/*
     * 得到角色表中的所有角色
     * 
     */	
	public List<Role> getAll() {
		return session.get(new Role());
	}
	
	/*
     * 
     * 通过Id查找一个角色
     */	
	public Role getById(String objectId) {
		return (Role) session.get(Role.class, objectId);
	}
	
	/*
     * 
     * 对该角色添加一个用户
     */	
	public void addUser(Role role, User u) {
		if(u == null)
			return ;
		Role r = operateUser(role,u,"AddRelation");
		session.update(r,"");
	}
	/*
     * 
     * 对该角色添加多个用户
     */	
	public void addUsers(Role role, List<User> list) {
		if(list == null)
			return ;
		//定义一个Role临时变量，存储id 与users；
		Role r = operateUser(role,list,"AddRelation");
		session.update(r,"");
	}
	/*
     * 
     * 对该角色删除一个用户
     */	
	public void deleteUser(Role role, User u) {
		if(u == null)
			return;
		Role r = operateUser(role,u,"RemoveRelation");
		session.update(r,"");
	}

	/*
     * 
     * 对该角色删除多个用户
     */	
	public void deleteUsers(Role role, List<User> list) {
		if(list == null)
			return ;
		Role r = operateUser(role,list,"RemoveRelation");
		//System.out.println("r****"+r);
		session.update(r,"");		
	}
	
	private Role operateUser(Role role, List<User> list, String opt) {
		//定义一个Role临时变量，存储id 与users；
		Role updateTemp = new Role();
		updateTemp.setObjectId(role.getObjectId());
		Map<String, Object> users = new LinkedHashMap<String, Object>();
		users.put("__op",opt);
		List<HashMap<String, Object>> tempList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> tempMap = null;
		for(int i=0;i<list.size();i++){
			tempMap = new LinkedHashMap<String, Object>();
			tempMap.put("__type", "Pointer");
			tempMap.put("className", "_User");
			tempMap.put("objectId", list.get(i).getObjectId());
			tempList.add(tempMap);
		}
		users.put("objects",tempList);
		updateTemp.setUsers(users);
		return updateTemp;
	}

	private Role operateUser(Role role, User u, String opt) {
		Role updateTemp = new Role();
		updateTemp.setObjectId(role.getObjectId());
		Map<String, Object> users = new LinkedHashMap<String, Object>();
		users.put("__op", opt);
		List<HashMap<String, Object>> tempList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> tempMap =  new LinkedHashMap<String, Object>();
		tempMap.put("__type", "Pointer");
		tempMap.put("className", "_User");
		tempMap.put("objectId", u.getObjectId());
		tempList.add(tempMap);
		users.put("objects",tempList);
		updateTemp.setUsers(users);
		return updateTemp;
	}
	/*
     * 
     * 删除该角色
     */	
	public void deleteRole(Role role) {	
		session.delete(role);
	}
}
