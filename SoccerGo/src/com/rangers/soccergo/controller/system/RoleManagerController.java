package com.rangers.soccergo.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.service.system.RoleManagerService;
import com.rangers.soccergo.vo.system.RolePage;

@Controller   
@RequestMapping("/RoleManager") 
public class RoleManagerController {
	private RolePage rolePage;
	private RoleManagerService roleManagerServiceImpl;
	
	public RolePage getRolePage() {
		return rolePage;
	}
	@Resource(name="rolePage")
	public void setRolePage(RolePage rolePage) {
		this.rolePage = rolePage;
	}
	public RoleManagerService getRoleManagerServiceImpl() {
		return roleManagerServiceImpl;
	}
	@Resource(name="roleManagerServiceImpl")
	public void setRoleManagerServiceImpl(RoleManagerService roleManagerServiceImpl) {
		this.roleManagerServiceImpl = roleManagerServiceImpl;
	}
	
	@RequestMapping("/list.action") 
	public void list(HttpServletRequest request,HttpServletResponse response){
	
		rolePage.setRows(roleManagerServiceImpl.listService());
		rolePage.setTotal(roleManagerServiceImpl.countService());
		String jsonStr = JsonUtil.bean2json(rolePage);
		PrintWriter out = null;
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/add.action")
	public String add(Role role,HttpServletRequest request,HttpServletResponse response){
		
		return "/Teacher/list";
	}
}
