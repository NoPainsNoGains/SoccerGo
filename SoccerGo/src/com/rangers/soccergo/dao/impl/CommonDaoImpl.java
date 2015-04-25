package com.rangers.soccergo.dao.impl;

import java.util.List;

import com.rangers.soccergo.dao.CommonDao;
import com.rangers.soccergo.db.util.CloudQuery;
import com.rangers.soccergo.db.util.CloudSession;
import com.rangers.soccergo.db.util.StringUtil;
import com.rangers.soccergo.util.GenericSuperClass;

public class CommonDaoImpl<T> implements CommonDao<T> {
	
	private CloudSession session = new CloudSession();
	private Class entity = (Class)GenericSuperClass.getClass(this.getClass());
	
	public CloudSession getSession() {
		return session;
	}

	public void setSession(CloudSession session) {
		this.session = session;
	}
	
	public boolean save(T t) {
		return session.save(t);	
	}

	public boolean update(T t) {	
		return session.update(t,"");
	}

	public void delete(T t) {
		session.delete(t);
	}

	public T getById(String objectId) {
		
		return (T) session.get(entity, objectId);
	}

	public List<T> getAll() {
		T t = null;
		try {
			t = (T) entity.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (List<T>) session.get(t);
	}

	public int count() {
		String cql = "select count(*) from ";
		String cqlClassName = StringUtil.getClassName(entity.toString());
		cql = cql + cqlClassName;
		CloudQuery query = session.executeQuery(cql);
		return (Integer) query.exeResult("count");	
	}

}
