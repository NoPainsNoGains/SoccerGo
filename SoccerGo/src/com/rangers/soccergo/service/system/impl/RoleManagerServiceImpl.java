package com.rangers.soccergo.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import com.rangers.soccergo.dao.RoleDao;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.service.system.RoleManagerService;

@Component("roleManagerServiceImpl")
public class RoleManagerServiceImpl implements RoleManagerService{
	private  RoleDao roleDaoImpl;
	
	public RoleDao getRoleDaoImpl() {
		return roleDaoImpl;
	}
	@Resource(name="roleDaoImpl")
	public void setRoleDaoImpl(RoleDao roleDaoImpl) {
		this.roleDaoImpl = roleDaoImpl;
	}

	public List<Role> listService() {
		List<Role> roleList = roleDaoImpl.getAll();
		return roleList;
	}
	public int countService() {
		return roleDaoImpl.count();
	}
	
}
