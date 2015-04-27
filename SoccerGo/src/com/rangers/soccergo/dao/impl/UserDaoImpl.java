package com.rangers.soccergo.dao.impl;

import java.util.List;

import com.rangers.soccergo.dao.UserDao;
import com.rangers.soccergo.db.util.CloudSession;
import com.rangers.soccergo.model.User;

public class UserDaoImpl implements UserDao {
	private CloudSession session = new CloudSession();
	
	public CloudSession getSession() {
		return session;
	}

	public void setSession(CloudSession session) {
		this.session = session;
	}
	/*
	 * 保存用户
	 */
	public void save(User u) {
		session.save(u);
	}
	/*
	 * 更新操作暂时用不起
	 * 每个用户的session token不一样
	 */
	public void update(User u) {
		session.update(u, "");	
	}
	/*
	 * 删除操作暂时用不起
	 * 每个用户的session token不一样
	 */
	public void delete(User u) {
		session.delete(u);
	}

	public List<User> getAll() {
		return session.get(new User());
	}
	
	public User getById(String objectId) {
		return (User)session.get(User.class,objectId);
	}
	
}
