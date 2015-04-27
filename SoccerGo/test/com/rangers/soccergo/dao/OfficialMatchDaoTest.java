package com.rangers.soccergo.dao;

import java.util.Date;
import java.util.List;

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
		o.setTitle("你好2");
		o.setStatus("报名中2");
		o.setType(2);
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
	@Test
	public void testfindByPage(){
		impl.findByPage(1, 5);
	}
	@Test
	public void testgetById(){
		OfficialMatch o = impl.getById("553e32fae4b069c225d923d9"); 
		System.out.println(o);
		o.setText("test update");
		o.setCreatedAt(null);
		o.setUpdatedAt(null);
		impl.update(o);
	}
	@Test
	public void testgetAll(){
		List<OfficialMatch> list = impl.getAll();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
}
