package com.rangers.soccergo.test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.service.system.RoleManagerService;

public class TEST {
	private ApplicationContext ac =null;
	@Before
	public void beforeclass(){
		String[] locations = {"classpath:/com/rangers/soccergo/config/*.xml", "classpath:/com/rangers/soccergo/config/*.xml"}; 
		ac = new ClassPathXmlApplicationContext(locations);
	}
	@Test
	public void test() throws Exception{
	
		RoleManagerService roleManagerServiceImpl = (RoleManagerService)ac.getBean("roleManagerServiceImpl");
		int number = roleManagerServiceImpl.countService();
		System.out.println(" "+number);
		/*List<Role> list = roleManagerServiceImpl.listService();
		
		for(Role role:list){
			System.out.println("id:  "+role.getObjectId()+"    父id: "+role.getName());
		}*/
		
	
		

	}
}
