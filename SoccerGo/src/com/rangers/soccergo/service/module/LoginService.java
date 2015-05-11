package com.rangers.soccergo.service.module;

import java.util.Map;

import net.sf.json.JSONArray;

import com.rangers.soccergo.model.User;

public interface LoginService {
	public User existService(String telephone,String passwd);
	public Map<String,JSONArray> loginTreeService(String userId);
}
