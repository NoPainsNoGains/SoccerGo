package com.rangers.soccergo.dao;

import java.util.List;

import com.rangers.soccergo.model.OfficialMatch;

public interface OfficialMatchDao {
	void save(OfficialMatch o);
	void update(OfficialMatch o);
	void delete(OfficialMatch o);
	OfficialMatch getById(String objectId);
	List<OfficialMatch> getAll();
}
