package com.rangers.soccergo.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rangers.soccergo.dao.impl.RoleDaoImpl;
import com.rangers.soccergo.db.util.CloudSession;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.model.User;

public class RoleDaoTest {
	private RoleDao roleDao;
	@Before
	public void before(){
	     roleDao = new RoleDaoImpl();
	}
	@Test
	public void testSave() {
		RoleDao roleDao = new RoleDaoImpl();
		Role r = new Role();
		r.setName("testSave3");
		System.out.println(roleDao.save(r));
	}
	@Test
	public void testgetUsersByRoleId(){
		Role r = new Role();
		r.setObjectId("553e388ce4b069c225d9759a");
		List<User> list = (List<User>) roleDao.getUsersByRoleId(r.getObjectId());
		for(int i=0;list!=null&&i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	@Test
	public void testgetAll(){
		List<Role> list = roleDao.getAll();
		for(int i=0;list!=null&&i<list.size();i++){
			System.out.println(list.get(i));
		}
		Role r = list.get(0);
		System.out.println(r.getUsers().get("className"));
	}
	@Test
	public void testgetById(){
		Role r = roleDao.getById("553e388ce4b069c225d9759a");
		System.out.println(r);
	}
	@Test
	public void testaddUsers(){
		CloudSession session = new CloudSession();
		List<User> users = session.get(new User());
		/*for(int i=0;users!=null&&i<users.size();i++){
			System.out.println(users.get(i));
		}*/
		Role r = roleDao.getById("553e388ce4b069c225d9759a");
		System.out.println(r);
		roleDao.addUsers(r, users);
		
	}
	@Test
	public void testaddUser(){
		CloudSession session = new CloudSession();
		User u = (User) session.get(User.class, "552e8646e4b0643b70a316b0");
		System.out.println(u);
		Role r = roleDao.getById("55375dcde4b0066130264c0b");
		System.out.println(r);
		roleDao.addUser(r, u);
	}
	@Test
	public void testdeleteUsers(){
		CloudSession session = new CloudSession();
		List<User> users = session.get(new User());
		Role r = roleDao.getById("552e76bae4b0643b70a293b1");
		roleDao.deleteUsers(r, users);
	}
	@Test
	public void testdeleteUser(){
		CloudSession session = new CloudSession();
		User u = (User) session.get(User.class, "553e388ce4b069c225d9759a");
		Role r = roleDao.getById("552e76bae4b0643b70a293b1");
		roleDao.deleteUser(r, u);
	}
	@Test
	public void testdeleteRole(){
		Role r = roleDao.getById("553714c1e4b0c62299383d38");
		System.out.println(r);
		roleDao.delete(r);
	}
	@Test
	public void testupdate(){
		Role r = roleDao.getById("55375dcde4b0066130264c0b");
		System.out.println(r);
		r.setName("testupdate");
		r.setRoles(null);r.setUsers(null);r.setCreatedAt(null);r.setTest(null);
		r.setUpdatedAt(null);
		roleDao.update(r);
	}

}
