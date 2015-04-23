package com.rangers.soccergo.dao.impl;

import java.util.List;

import com.rangers.soccergo.dao.ModuleDao;
import com.rangers.soccergo.db.util.CloudSession;
import com.rangers.soccergo.model.Module;
import com.rangers.soccergo.util.GenericSuperClass;

public class ModuleDaoImpl implements ModuleDao {
	private CloudSession session = new CloudSession();
	
	
	public CloudSession getSession() {
		return session;
	}

	public void setSession(CloudSession session) {
		this.session = session;
	}
	public void save(Module m) {
		session.save(m);
	}

	public void update(Module m) {
		session.update(m, "");	
	}

	public void delete(Module m) {
		session.delete(m);
	}

	public Module getById(String objectId) {
		return (Module)session.get(Module.class, objectId);
	}

	public List<Module> getAll() {		
		return session.get(new Module());
	}
	
}
