package com.rangers.soccergo.controller.module;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rangers.soccergo.common.util.SplitUtil;
import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.service.module.RoleAuthorityService;
import com.rangers.soccergo.vo.module.RoleModuleTree;

@Controller   
@RequestMapping("/admin/ModuleAuthority") 
public class ModuleAuthorityController {
	private RoleAuthorityService roleAuthorityServiceImpl;
	
	public RoleAuthorityService getRoleAuthorityServiceImpl() {
		return roleAuthorityServiceImpl;
	}
	@Resource(name="roleAuthorityServiceImpl")
	public void setRoleAuthorityServiceImpl(
			RoleAuthorityService roleAuthorityServiceImpl) {
		this.roleAuthorityServiceImpl = roleAuthorityServiceImpl;
	}
	/** 
	  * 列出角色表
	 */
	@RequestMapping("/list.action") 
	public void list(HttpServletRequest request,HttpServletResponse response){
		String roleId = request.getParameter("roleId");
		List<RoleModuleTree> list = null;
		if(roleId!=null){
			list = roleAuthorityServiceImpl.listFunction(roleId);
		}
		JSONArray array = JSONArray.fromObject(list);
		String jsonStr = array.toString();
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
	  * 更新角色表
	 */
	@RequestMapping("/update.action") 
	public void update(HttpServletRequest request,HttpServletResponse response){
		String authoritySetStr = request.getParameter("authoritySet");
		String [] arrary = SplitUtil.stringplit(authoritySetStr);
		if(arrary.length > 1){
			String roleId = arrary[arrary.length-1];
			StringBuilder authoritySet = new StringBuilder("");
			for(int i=0;i<arrary.length-1;i++){
				if(i==arrary.length-2)
					authoritySet.append(arrary[i]);
				else
					authoritySet.append(arrary[i]+",");
			}
			roleAuthorityServiceImpl.updateFunction(roleId, authoritySet.toString());
		}
		PrintWriter out = null;
		String jsonStr = JsonUtil.string2json("0");
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
