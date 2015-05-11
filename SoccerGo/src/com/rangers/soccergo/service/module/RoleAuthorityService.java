package com.rangers.soccergo.service.module;

import java.util.List;
import com.rangers.soccergo.vo.module.RoleModuleTree;

public interface RoleAuthorityService {
	public List<RoleModuleTree> listFunction(String roleId);
	public List<String> listRoleFunctionService(String roleId);
	public void updateFunction(String roleId,String authoritySet);
}
