package com.rangers.soccergo.controller.module;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rangers.soccergo.vo.module.LoginUser;

@Controller  
@RequestMapping("/admin/") 
public class LoginOutController{
	@RequestMapping("/loginOut.action") 
	public String login(LoginUser loginUser,HttpServletRequest request,HttpServletResponse response){
		/*取出对象*/
		HttpSession session = request.getSession();
		Enumeration<String> em = session.getAttributeNames();
		while (em.hasMoreElements()) {
			 request.getSession().removeAttribute(em.nextElement().toString());
		}
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return "redirect:"+basePath;
	}
}
