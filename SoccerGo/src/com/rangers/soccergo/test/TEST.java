package com.rangers.soccergo.test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rangers.soccergo.controller.module.LoginController;
import com.rangers.soccergo.controller.module.ModuleManagerController;
import com.rangers.soccergo.dao.ModuleDao;
import com.rangers.soccergo.dao.RoleAuthorityDao;
import com.rangers.soccergo.model.Module;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.service.module.LoginService;
import com.rangers.soccergo.service.module.RoleAuthorityService;
import com.rangers.soccergo.service.module.impl.LoginServiceImpl;
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
		
		//RoleManagerService roleManagerServiceImpl = (RoleManagerService)ac.getBean("roleManagerServiceImpl");
		//roleManagerServiceImpl.addRoleUser("5539fe29e4b069c225add71f", "552e6a7ee4b0643b70a2120d");
	/*	ModuleManagerController moduleManagerController = (ModuleManagerController)ac.getBean("moduleManagerController");
		moduleManagerController.list(null, null);*/
		/*HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("__type", "Pointer");
		map.put("className", "Module");
		map.put("objectId", "554adff5e4b0c569d92e4f73");
		
		Module module = new Module();
		module.setModule_name("用户删除");
		module.setFather_id(map);
		*/
		/*ModuleDao moduleDaoImpl = (ModuleDao)ac.getBean("moduleDaoImpl");
		moduleDaoImpl.getAllByAscCode();*/
		//moduleDaoImpl.getAllChildrenByFirst("554adf57e4b0c569d92e4c23");
		/*RoleAuthorityDao roleAuthorityDaoImpl = (RoleAuthorityDao)ac.getBean("roleAuthorityDaoImpl");
		roleAuthorityDaoImpl.getModuleSetByRoleId("552e76c2e4b0643b70a29408");
	/*	
		LoginServiceImpl loginServiceImpl = (LoginServiceImpl)ac.getBean("loginServiceImpl");
		loginServiceImpl.test();
		public void test(){
			List<Module> allModuleList = this.getAllModule();
			getAllChildrenByFirst(allModuleList,"554adf57e4b0c569d92e4c23");
		}*/
	/*	*/
		LoginServiceImpl loginServiceImpl = (LoginServiceImpl)ac.getBean("loginServiceImpl");
		loginServiceImpl.loginTreeService("552e869ae4b0643b70a319c6");
	/*	LoginController loginController = (LoginController)ac.getBean("loginController");
		loginServiceImpl.*/
	}
}
