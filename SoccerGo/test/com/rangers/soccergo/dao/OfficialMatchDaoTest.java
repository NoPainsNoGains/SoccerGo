package com.rangers.soccergo.dao;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.rangers.soccergo.dao.impl.OfficialMatchDaoImpl;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.model.OfficialMatch;

public class OfficialMatchDaoTest {
	
	private OfficialMatchDao impl = null;
	@Before
	public void before(){
		impl = new OfficialMatchDaoImpl();
	}
	@Test
	public void testCount() {
		System.out.println(impl.count());
		
	}
	@Test
	public void testsave(){
		OfficialMatch o = new OfficialMatch();
		o.setBegin_time(new Date());
		o.setTitle("你好");
		o.setStatus("报名中");
		o.setType(1);
		System.out.println(o);
		impl.save(o);
	}

	@Test
	public void testJson(){
		Date d = new Date();
		System.out.println(d);
		String json = JsonUtil.getInstance().obj2jsondate(d);
		System.out.println(json);
	}
}
