package com.rangers.soccergo.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.rangers.soccergo.db.util.CloudQuery;
import com.rangers.soccergo.db.util.CloudSession;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.model.User;

public class RoleTest {

	@Test
	public void testQueryUsers() {
		CloudSession session = new CloudSession();
		//Role r = (Role)session.get(Role.class, "552f2157e4b0643b70a6028b");
		//System.out.println(r);
		String cql = "select * from _User where related users to pointer('_Role',?)";
		CloudQuery query = session.executeQuery(cql);
		query.setParam(0, "552f2157e4b0643b70a6028b");
		List<User> list = query.list();
		for(int i=0;list!=null&&i<list.size();i++){
			//System.out.println(list.get(i)[0]);
			System.out.println(list.get(i));
		}
	}
	@Test
	public void testGet(){
		CloudSession session = new CloudSession();
		Role r = (Role)session.get(Role.class, "552f2157e4b0643b70a6028b");
		System.out.println(r);
		List<Role> rs = (List<Role>)session.get(new Role());
		for(int i=0;rs!=null&&i<rs.size();i++){
			//System.out.println(list.get(i)[0]);
			System.out.println(rs.get(i));
		}
	}
	@Test
	public void testSave(){
		CloudSession session = new CloudSession();
		Role r = new Role();
		r.setName("test2");
		//System.out.println(r.getTest());
		String json = JsonUtil.getInstance().obj2json(r);		
		System.out.println(json);
		//json.lastIndexOf("}")
		session.save(r);
		//System.out.println(JsonUtil.getInstance().toACL());
	}

}
