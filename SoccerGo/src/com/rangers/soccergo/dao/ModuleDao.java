package com.rangers.soccergo.dao;

import java.util.List;

import com.rangers.soccergo.model.Module;

public interface ModuleDao extends CommonDao<Module>{
	public List<Module> getAllByAscCode();
	public List<Module> queryFatherList(String cql,String objectId);
	public List<String> getFirstFloor();
	public List<String> getAllChildrenByFirst(String firstId);
}
