package com.rangers.soccergo.dao;

import com.rangers.soccergo.model.RoleAuthority;

public interface RoleAuthorityDao extends CommonDao<RoleAuthority> {
	public String getModuleSetByRoleId(String roleId);
	public RoleAuthority getObjectByRoleId(String roleId);
}
