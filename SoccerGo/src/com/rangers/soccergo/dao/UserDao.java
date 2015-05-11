package com.rangers.soccergo.dao;

import com.rangers.soccergo.model.User;

public interface UserDao extends CommonDao<User>{
	User login(String userName,String password);
}
