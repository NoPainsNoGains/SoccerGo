package com.rangers.soccergo.dao;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Before;
import org.junit.Test;

import com.rangers.soccergo.dao.impl.ModuleDaoImpl;
import com.rangers.soccergo.db.util.HttpClientUtil;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.model.Module;

public class ModuleDaoTest {
	private ModuleDao moduleDao = null;
	@Before
	public void before(){
		moduleDao = new ModuleDaoImpl();
	}
	
	@Test
	public void testsave() {
		Module m = new Module();
		m.setModule_name("test1");
		m.setModule_code("1001");
		moduleDao.save(m);	
	}
	@Test
	public void testupdate() {
		Module m = moduleDao.getById("5537a642e4b0066130295fee");
		//System.out.println(m);
		m.setPage("10");
		m.setRemark("test update");
		System.out.println(m);
		String json = JsonUtil.getInstance().obj2json(m);
		System.out.println(json);
		//moduleDao.update(m);
	}
	@Test
	public void testdelete() {
		
	}
	@Test
	public void testgetById() {
		Module m = moduleDao.getById("5537a642e4b0066130295fee");
		System.out.println(m);
	}
	@Test
	public void testgetAll() {
		
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

}
