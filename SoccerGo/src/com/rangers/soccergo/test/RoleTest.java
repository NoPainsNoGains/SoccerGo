package com.rangers.soccergo.test;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rangers.soccergo.dao.RoleDao;
import com.rangers.soccergo.db.util.CloudQuery;
import com.rangers.soccergo.db.util.CloudSession;
import com.rangers.soccergo.db.util.HttpClientUtil;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.model.User;

public class RoleTest {
	private ApplicationContext ac =null;
	@Before
	public void beforeclass(){
		String[] locations = {"classpath:/com/rangers/soccergo/config/*.xml", "classpath:/com/rangers/soccergo/config/*.xml"}; 
		ac = new ClassPathXmlApplicationContext(locations);
	}
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
	@Test
	public void testpage(){
		RoleDao roleDao = (RoleDao) ac.getBean("roleDaoImpl");
		System.out.println(roleDao);
		List<User> list = roleDao.getPageUsersByRoleId(2, 5, "553e388ce4b069c225d9759a");
		for(int i=0;list!=null&&i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	@Test
	public void testroleByuserId() throws URISyntaxException {
		/*CloudSession session = new CloudSession();
		//Role r = (Role)session.get(Role.class, "552f2157e4b0643b70a6028b");
		//System.out.println(r);
		String cql = "select * from _Role where related users to pointer('_User',?)";
		CloudQuery query = session.executeQuery(cql);
		query.setParam(0, "552e6a7ee4b0643b70a2120d");
		List<User> list = query.list();
		for(int i=0;list!=null&&i<list.size();i++){
			//System.out.println(list.get(i)[0]);
			System.out.println(list.get(i));
		}*/
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> submap = new HashMap<String,Object>();
		Map<String,Object> submap2 = new HashMap<String,Object>();
		submap2.put("__type", "Pointer");
		submap2.put("className", "_User");
		submap2.put("objectId", "552e6a7ee4b0643b70a2120d");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(submap2);
		submap.put("object", list);		
		map.put("users", submap);
		String json = JsonUtil.getInstance().obj2json(map);
		//json = "where=" + json;
		System.out.println(json);
		URI uri = new URIBuilder()
        .setScheme("https")
        .setHost("leancloud.cn")
        .setPort(443)
        .setPath("/1.1/roles").setParameter("where", json).build();
		System.out.println(uri);
		String res = HttpClientUtil.getInstance().sendGetRequest(uri);
		System.out.println(res);
	}
	@Test
	public void testroleByuserId2(){
		CloudSession session = new CloudSession();
		//Role r = (Role)session.get(Role.class, "552f2157e4b0643b70a6028b");
		//System.out.println(r);
		String cql = "select * from _Role where users=pointer('_Role',?)";
		CloudQuery query = session.executeQuery(cql);
		query.setParam(0, "552e6a7ee4b0643b70a2120d");
		List<User> list = query.list();
		for(int i=0;list!=null&&i<list.size();i++){
			//System.out.println(list.get(i)[0]);
			System.out.println(list.get(i));
		}
	}
	@Test
	public void testgetRoleByUserId(){
		RoleDao roleDao = (RoleDao) ac.getBean("roleDaoImpl");
		System.out.println(roleDao);
		List<Role> list = roleDao.getRoleByUserId("552e8636e4b0643b70a31630");
		for(int i=0;list!=null&&i<list.size();i++){
			System.out.println(list.get(i));
		}

	}
}
