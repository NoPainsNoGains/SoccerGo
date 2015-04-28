package com.rangers.soccergo.dao.impl;

import org.springframework.stereotype.Component;
import com.rangers.soccergo.dao.UserDao;
import com.rangers.soccergo.model.User;

@Component("userDaoImpl")
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao {

}
