package com.rangers.soccergo.service.system;

import java.util.List;

import net.sf.json.JSONObject;

import com.rangers.soccergo.vo.system.RoleVo;
import com.rangers.soccergo.vo.system.UserVo;

public interface RoleManagerService {
	public List<RoleVo> listService(int page,int pageSize);
	public int countService();
	public void addService(JSONObject jsonObject);
	public List<UserVo> listRoleUserService(int page,int pageSize,String roleObjectId);
	public int countRoleUserService();
}
