package com.rangers.soccergo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rangers.soccergo.dao.RoleAuthorityDao;
import com.rangers.soccergo.db.util.CloudQuery;
import com.rangers.soccergo.model.RoleAuthority;
@Component("roleAuthorityDaoImpl")
public class RoleAuthorityDaoImpl extends CommonDaoImpl<RoleAuthority> implements RoleAuthorityDao {

	public String getModuleSetByRoleId(String roleId) {
		String cql = "select * from RoleAuthority where roleId = ?";
		CloudQuery query = this.getSession().executeQuery(cql);
		query.setParam(0,roleId);
		List<RoleAuthority> list = query.list();
		if(list.size()!=0)
			return list.get(0).getModuleSet();
		return null;
	}
	public RoleAuthority getObjectByRoleId(String roleId){
		String cql = "select * from RoleAuthority where roleId = ?";
		CloudQuery query = this.getSession().executeQuery(cql);
		query.setParam(0,roleId);
		List<RoleAuthority> list = query.list();
		if(list.size()!=0)
			return list.get(0);
		return null;
	}
}
