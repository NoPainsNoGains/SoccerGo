package com.rangers.soccergo.test;

import java.util.List;

import org.junit.Test;


import com.rangers.soccergo.db.util.CloudQuery;
import com.rangers.soccergo.db.util.CloudSession;
import com.rangers.soccergo.model.Module;


public class ModuleTest {
	@Test
	public void test(){
		CloudSession session = new CloudSession();
		//知查出father_id实体类的objectId;
		/*Module m = (Module)session.get(Module.class, "552fccd5e4b0643b70adbb51");
		System.out.println(m);*/
		//级联查询，并查出father_id的实体类
		String cql = "select include father_id,* from Module where objectId=?";
		CloudQuery query = session.executeQuery(cql);
		query.setParam(0, "552fccd5e4b0643b70adbb51");
		List<Module> list = query.list();
		System.out.println(list.size());
		for(int i=0;list!=null&&i<list.size();i++){
			//System.out.println(list.get(i)[0]);
			System.out.println(list.get(i));
		}
		
	}
	
}
