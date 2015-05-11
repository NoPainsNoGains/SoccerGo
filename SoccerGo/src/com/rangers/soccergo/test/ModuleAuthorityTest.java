package com.rangers.soccergo.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.rangers.soccergo.db.util.CloudQuery;
import com.rangers.soccergo.db.util.CloudSession;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.model.RoleAuthority;


public class ModuleAuthorityTest {
	private ApplicationContext ac =null;
	@Before
	public void beforeclass(){
		String[] locations = {"classpath:/com/rangers/soccergo/config/*.xml", "classpath:/com/rangers/soccergo/config/*.xml"}; 
		ac = new ClassPathXmlApplicationContext(locations);
	}
	@Test
	public void testSave(){
		CloudSession session = new CloudSession();
		RoleAuthority roleAuthority = new RoleAuthority();
		roleAuthority.setRoleId("552e76c2e4b0643b70a29408");
		roleAuthority.setModuleSet("554adf08e4b0c569d92e4a70,554adf57e4b0c569d92e4c23,554adfa2e4b0c569d92e4dc4,554ae054e4b0c569d92e5146");
		//System.out.println(r.getTest());
		/*String json = JsonUtil.getInstance().obj2json(roleAuthority);		
		System.out.println(json);*/
		//json.lastIndexOf("}")
		session.save(roleAuthority);
		//System.out.println(JsonUtil.getInstance().toACL());
	}
	
}
