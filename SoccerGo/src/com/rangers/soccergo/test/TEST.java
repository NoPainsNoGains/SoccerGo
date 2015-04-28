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
import com.rangers.soccergo.service.system.UserManagerService;
import com.rangers.soccergo.vo.system.UserVo;

public class TEST {
	private ApplicationContext ac =null;
	@Before
	public void beforeclass(){
		String[] locations = {"classpath:/com/rangers/soccergo/config/*.xml", "classpath:/com/rangers/soccergo/config/*.xml"}; 
		ac = new ClassPathXmlApplicationContext(locations);
	}
	@Test
	public void test() throws Exception{
	
		/*RoleManagerService roleManagerServiceImpl = (RoleManagerService)ac.getBean("roleManagerServiceImpl");
		int number = roleManagerServiceImpl.countService();
		System.out.println(" "+number);*/
		
		/*UserManagerService userManagerServiceImpl = (UserManagerService)ac.getBean("userManagerServiceImpl");
		List<UserVo> list= userManagerServiceImpl.listService();
		for(UserVo userVo:list){
			System.out.println("id:  "+userVo.getObjectId()+"    用户名: "+userVo.getUsername()+" 密码:"+userVo.getPassword()+" 创建时间:"+userVo.getCreatedAt());
		}*/
		/*List<Role> list = roleManagerServiceImpl.listService();
		
		for(Role role:list){
			System.out.println("id:  "+role.getObjectId()+"    父id: "+role.getName());
		}*/
		
	
		

	}
}
