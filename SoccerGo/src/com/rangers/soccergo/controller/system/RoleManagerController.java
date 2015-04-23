package com.rangers.soccergo.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.service.system.RoleManagerService;
import com.rangers.soccergo.vo.system.RolePage;

@Controller   
@RequestMapping("/admin/SystemManage/RoleManager") 
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
	public void add(HttpServletRequest request,HttpServletResponse response){
		String models = request.getParameter("models");
		JSONObject jsonObject = JsonUtil.jsonToJsonObject(models); 
		roleManagerServiceImpl.addService(jsonObject);
		PrintWriter out = null;
		String jsonStr = JsonUtil.string2json("0");//返回状态0 成功
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
