package com.rangers.soccergo.dao;

import java.util.List;

import com.rangers.soccergo.model.Module;

public interface CommonDao<T> {
	void save(T t);
	void update(T t);
	void delete(T t);
	T getById(String objectId);
	List<T> getAll();
	int count();
}
