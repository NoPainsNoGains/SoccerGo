package com.rangers.soccergo.dao;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Before;
import org.junit.Test;

import com.rangers.soccergo.dao.impl.ModuleDaoImpl;
import com.rangers.soccergo.db.util.HttpClientUtil;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.model.Module;
import com.rangers.soccergo.model.User;

public class ModuleDaoTest {
	private ModuleDao moduleDao = null;
	@Before
	public void before(){
		moduleDao = new ModuleDaoImpl();
	}
	
	@Test
	public void testsave() {
		Module m = new Module();
		m.setModule_name("test11");
		m.setModule_code("1001");
		moduleDao.save(m);	
	}
	
	@Test
	public void testupdate() {
		Module m = moduleDao.getById("553eec78e4b017f1157ddd63");
		//System.out.println(m);
		m.setPage("11");
		m.setRemark("test update common3");
		HashMap<String,Object> map = new LinkedHashMap<String, Object>();
		map.put("__type","Pointer");
	    map.put("className", "Module");
		map.put("objectId", "552fc087e4b0643b70ad429b");
		m.setFather_id(map);
		System.out.println(m);
		String json = JsonUtil.getInstance().obj2json(m);
		System.out.println(json);
		moduleDao.update(m);
	}
	@Test
	public void testdelete() {
		Module m = moduleDao.getById("553eec78e4b017f1157ddd63");
		moduleDao.delete(m);
	}
	@Test
	public void testgetById() {
		Module m = moduleDao.getById("553eec78e4b017f1157ddd63");
		System.out.println(m);
		String json = JsonUtil.getInstance().obj2json(m);
		System.out.println(json);
	}
	@Test
	public void testgetAll() {
		List<Module> list = moduleDao.getAll();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	@Test
	public void testDate() throws URISyntaxException {
		URI uri = new URIBuilder()
        .setScheme("https")
        .setHost("leancloud.cn")
        .setPort(443)
        .setPath("/1.1/classes/test").build();
		String json = HttpClientUtil.getInstance().sendGetRequest(uri);
		System.out.println(json);
		
	}
	@Test
	public void testcount(){
		System.out.println(moduleDao.count());
	}
	@Test
	public void testfindByPage(){
		List<Module> list = moduleDao.findByPage(1, 5);
		System.out.println(list == null);
		System.out.println(list.size());
		for(int i=0;list!=null &&i<list.size();i++){
			System.out.println(list.get(i));
		}
	}

}
