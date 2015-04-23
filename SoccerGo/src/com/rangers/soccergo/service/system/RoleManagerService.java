package com.rangers.soccergo.service.system;

import java.util.List;

import net.sf.json.JSONObject;

import com.rangers.soccergo.vo.system.RoleVo;

public interface RoleManagerService {
	public List<RoleVo> listService();
	public int countService();
	public void addService(JSONObject jsonObject);
}
