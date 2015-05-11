package com.rangers.soccergo.controller.module;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rangers.soccergo.model.User;
import com.rangers.soccergo.service.module.LoginService;
import com.rangers.soccergo.vo.module.LoginTreePage;
import com.rangers.soccergo.vo.module.LoginUser;

@Controller  
@RequestMapping("/admin/") 
public class LoginController{
	private LoginService loginServiceImpl;

	public LoginService getLoginServiceImpl() {
		return loginServiceImpl;
	}
	
	@Resource(name="loginServiceImpl")
	public void setLoginServiceImpl(LoginService loginServiceImpl) {
		this.loginServiceImpl = loginServiceImpl;
	}
	
	@RequestMapping("/login.action") 
	public void login(LoginUser loginUser,HttpServletRequest request,HttpServletResponse response){
		/*取出对象*/
		HttpSession session = request.getSession();
		User user = loginServiceImpl.existService(loginUser.getUserName(),loginUser.getPassWord());//552e869ae4b0643b70a319c6
		if( user!=null ){//有该用户 放入session
			 session.setAttribute("user", user);
		}
		PrintWriter out = null;
		JSONArray array = JSONArray.fromObject(user);
		String jsonStr = array.toString();
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/loadTree.action") 
	public void loginTree(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		System.out.println("id: "+user.getObjectId()+" nickName: "+user.getNickname()+" userName: "+user.getUsername());
		Map<String,JSONArray> treeMap = loginServiceImpl.loginTreeService(user.getObjectId());
		Iterator<String> iter = treeMap.keySet().iterator();
		List<LoginTreePage> loginTreePageList =  new ArrayList<LoginTreePage>();	
	    while (iter.hasNext()) {
		    String key = iter.next();
		    LoginTreePage temp = new LoginTreePage();			 
		    temp.setText(key);
		    temp.setChildren(treeMap.get(key));
		    loginTreePageList.add(temp);
		 } 
		PrintWriter out = null;
		JSONArray array = JSONArray.fromObject(loginTreePageList);
		String jsonStr = array.toString();
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
