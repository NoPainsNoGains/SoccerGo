package com.rangers.soccergo.test;

import org.junit.Test;

import com.fasterxml.jackson.databind.Module;
import com.rangers.soccergo.db.util.CloudQuery;
import com.rangers.soccergo.db.util.CloudSession;


public class ModuleTest {
	@Test
	public void test(){
		CloudSession session = new CloudSession();
		Module m = (Module)session.get(Module.class, "552fccd5e4b0643b70adbb51");
		//System.out.println(m);
		/*String cql = "select include father_id,* from Module where father_id=pointer('Module','552fc087e4b0643b70ad429b')";
		CloudQuery query = session.executeQuery(cql);
		query.list();*/
		
	}
	
}
