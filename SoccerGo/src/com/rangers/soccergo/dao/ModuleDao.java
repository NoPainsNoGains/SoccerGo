package com.rangers.soccergo.dao;

import java.util.List;

import com.rangers.soccergo.model.Module;

public interface ModuleDao {
	void save(Module m);
	void update(Module m);
	void delete(Module m);
	Module getById(String objectId);
	List<Module> getAll();
	
	
}
