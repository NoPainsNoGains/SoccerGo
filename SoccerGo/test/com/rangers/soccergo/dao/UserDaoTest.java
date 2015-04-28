package com.rangers.soccergo.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rangers.soccergo.dao.UserDao;
import com.rangers.soccergo.dao.impl.UserDaoImpl;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.model.User;

public class UserDaoTest {
	private UserDao userDao = null;
	@Before
	public void before(){
		userDao = new UserDaoImpl();
	}
	@Test
	public void testSave() {
		User u = new User();
		u.setUsername("小李");
		u.setPassword("123456");
		userDao.save(u);
	}
	@Test
	public void testgetById() {
		User u = userDao.getById("552e8646e4b0643b70a316b0");
		System.out.println(u);
		String json = JsonUtil.object2json(u);
		System.out.println(json);
	}
	//user表不能更新
	@Test
	public void testupdate() {
		User u = userDao.getById("553ee6e7e4b017f1157d84f5");
		u.setEmail("12345678@qq.com");
		u.setLevel(9);
		u.setNickname("红孩儿");
		System.out.println(u);
	    userDao.update(u);
	}
	@Test
	public void testgetAll(){
		List<User> list = userDao.getAll();
		for(int i=0;list!=null &&i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	@Test
	public void testcount(){
		System.out.println(userDao.count());
	}
	@Test
	public void testfindByPage(){
		List<User> list = userDao.findByPage(3, 5);
		System.out.println(list == null);
		System.out.println(list.size());
		for(int i=0;list!=null &&i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
}
