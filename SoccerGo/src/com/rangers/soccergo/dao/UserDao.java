package com.rangers.soccergo.dao;

import java.util.List;

import com.rangers.soccergo.model.User;

public interface UserDao {
	void save(User u);
	void update(User u);
	void delete(User u);
	List<User> getAll();
	User getById(String objectId);
	public int count();
}
