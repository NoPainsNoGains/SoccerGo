package com.rangers.soccergo.dao.impl;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.rangers.soccergo.dao.RoleDao;
import com.rangers.soccergo.db.util.CloudQuery;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.model.User;

@Component("roleDaoImpl")
public class RoleDaoImpl extends CommonDaoImpl<Role> implements RoleDao  {
	/*
     * 
     * 对该角色添加一个用户
     */	
	public void addUser(Role role, User u) {
		if(u == null)
			return ;
		Role r = operateUser(role,u,"AddRelation");
		this.getSession().update(r,"");
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
		this.getSession().update(r,"");
	}
	/*
     * 
     * 对该角色删除一个用户
     */	
	public void deleteUser(Role role, User u) {
		if(u == null)
			return;
		Role r = operateUser(role,u,"RemoveRelation");
		this.getSession().update(r,"");
	}
	/*
     * 通过角色ID 得到该角色下的所有用户
     * 
     */	
	public List<User> getUsersByRoleId(String objectId) {
		String cql = "select * from _User where related users to pointer('_Role',?)";
		CloudQuery query = this.getSession().executeQuery(cql);
		query.setParam(0,objectId);
		return query.list();
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
		this.getSession().update(r,"");		
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
	public List<User> getPageUsersByRoleId(int page, int pageSize,
			String objectId) {
		String cql = "select * from _User where related users to pointer('_Role',?)";
		String limitCondition = " limit ?,?";
		String orderBy = " order by createdAt desc";
		cql = cql + limitCondition + orderBy;
		CloudQuery query = this.getSession().executeQuery(cql);
		query.setParam(0,objectId);
		query.setParam(1, (page-1)*pageSize);
		query.setParam(2, pageSize);
		return query.list();
	}
	/**   *  
	  * 根据user的ObjectId来查询Role
	  * 
	  * @param s String objectId ： user的Id
	  *          
	  * @return List<Role> 返回Role的集合，可能一个用户有多个角色
	  *         在本系统中，一个用户一个角色，
	  *         所以List的size()为1表示 有角色
	  *                        0表示 未分配角色     
	  * @throws 
	 */
	public List<Role> getRoleByUserId(String objectId) {
		String cql = "select * from _Role where users=pointer('_Role',?)";
		CloudQuery query = this.getSession().executeQuery(cql);
		query.setParam(0, objectId);
		return query.list();
	}

}
