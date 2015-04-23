package com.rangers.soccergo.service.system;

import java.util.List;

import com.rangers.soccergo.model.Role;

public interface RoleManagerService {
	public List<Role> listService();
	public int countService();
}
