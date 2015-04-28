package com.rangers.soccergo.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
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
		Class[] argsClass = {Date.class};
		Object[] args = {null};
		try {
			Method m1 = t.getClass().getMethod("setUpdatedAt",argsClass);
		    m1.invoke(t, args);
		    Method m2 = t.getClass().getMethod("setCreatedAt",argsClass);
		    m2.invoke(t, args);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	/**   *  
	  * 分页查询
	  * 根据页面大小、第几页查询   
	  * @param s int page ： 查询第几页
	  *          int pageSize  ： 每一页的大小 
	  * @return List<T> 返回该页的所有值得集合   且集合不为null
	  *         当没有返回值时， List的size()为0
	  * @throws 
	 */
	public List<T> findByPage(int page, int pageSize) {
		String cql = "select * from ";
		String cqlClassName = StringUtil.getClassName(entity.toString());
		String limitCondition = " limit ?,?";
		String orderBy = " order by createdAt desc";
		cql = cql + cqlClassName + limitCondition + orderBy;
		System.out.println(cql);
		CloudQuery query = session.executeQuery(cql);
		query.setParam(0, (page-1)*pageSize);
		query.setParam(1, pageSize);
		return query.list();
	}

}
