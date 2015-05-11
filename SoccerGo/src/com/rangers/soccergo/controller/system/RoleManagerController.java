package com.rangers.soccergo.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.service.system.RoleManagerService;
import com.rangers.soccergo.vo.system.RolePage;
import com.rangers.soccergo.vo.system.RoleVo;
import com.rangers.soccergo.vo.system.UserPage;

@Controller   
@RequestMapping("/admin/SystemManage/RoleManager") 
public class RoleManagerController { 
	private UserPage userPage;
	private RolePage rolePage;
	private RoleManagerService roleManagerServiceImpl;
	
	public UserPage getUserPage() {
		return userPage;
	}
	@Resource(name="userPage")
	public void setUserPage(UserPage userPage) {
		this.userPage = userPage;
	}
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
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		rolePage.setRows(roleManagerServiceImpl.listService(page,pageSize));
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
	/** 
	  * 列出角色下面的用户列表
	 */
	@RequestMapping("/listRoleUser.action") 
	public void listByRoleUser(HttpServletRequest request,HttpServletResponse response){
		int page = Integer.parseInt(request.getParameter("pageIn"));
		int pageSize = Integer.parseInt(request.getParameter("pageSizeIn"));
		String roleObjectId = request.getParameter("roleObjectId");
		userPage.setRows(roleManagerServiceImpl.listRoleUserService(page,pageSize,roleObjectId));
		userPage.setTotal(roleManagerServiceImpl.countRoleUserService(roleObjectId));
		String jsonStr = JsonUtil.bean2json(userPage);
		PrintWriter out = null;
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 
	  * 增加角色
	 */
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
	/** 
	  * 增加角色用户
	 */
	@RequestMapping("/addUserRole.action") 
	public void addUserRole(HttpServletRequest request,HttpServletResponse response){
		String roleId = request.getParameter("roleId");
		String userId = request.getParameter("userId");
		String jsonStr = JsonUtil.string2json("0");//返回状态0 成功
		roleManagerServiceImpl.deletRoleUser(roleId, userId);
		roleManagerServiceImpl.addRoleUser(roleId, userId);
		PrintWriter out = null;
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 
	  * 列出所有的角色
	 */
	@RequestMapping("/listAllRole.action") 
	public void listAllUser(HttpServletRequest request,HttpServletResponse response){
		List<RoleVo> list = roleManagerServiceImpl.listAllService();
		String jsonStr = JsonUtil.list2json(list);
		PrintWriter out = null;
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 
	  * 列出所有的角色
	 */
	@RequestMapping("/deletRole.action") 
	public void deletRole(HttpServletRequest request,HttpServletResponse response){
		
	}
}
