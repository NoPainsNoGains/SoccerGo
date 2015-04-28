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
import com.rangers.soccergo.service.system.UserManagerService;
import com.rangers.soccergo.vo.system.UserPage;

@Controller   
@RequestMapping("/admin/SystemManage/UserManager") 
public class UserManagerController { 
	
	private UserPage userPage;
	private UserManagerService userManagerServiceImpl;
	
	public UserPage getUserPage() {
		return userPage;
	}
	@Resource(name="userPage")
	public void setUserPage(UserPage userPage) {
		this.userPage = userPage;
	}
	public UserManagerService getUserManagerServiceImpl() {
		return userManagerServiceImpl;
	}
	@Resource(name="userManagerServiceImpl")
	public void setUserManagerServiceImpl(UserManagerService userManagerServiceImpl) {
		this.userManagerServiceImpl = userManagerServiceImpl;
	}
	
	@RequestMapping("/list.action") 
	public void list(HttpServletRequest request,HttpServletResponse response){
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		userPage.setRows(userManagerServiceImpl.listService(page,pageSize));
		userPage.setTotal(userManagerServiceImpl.countService());
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
}
