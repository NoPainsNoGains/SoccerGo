package com.rangers.soccergo.controller.module;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rangers.soccergo.db.util.JsonUtil;
import com.rangers.soccergo.service.module.ModuleManagerSevice;
import com.rangers.soccergo.vo.module.ModuleMangerTree;

@Controller   
@RequestMapping("/admin/ModuleManage/ModuleManager") 
public class ModuleManagerController {
	private ModuleManagerSevice moduleManagerSeviceImpl;
	
	public ModuleManagerSevice getModuleManagerSeviceImpl() {
		return moduleManagerSeviceImpl;
	}
	@Resource(name="moduleManagerSeviceImpl")
	public void setModuleManagerSeviceImpl(
			ModuleManagerSevice moduleManagerSeviceImpl) {
		this.moduleManagerSeviceImpl = moduleManagerSeviceImpl;
	}

	/** 
	  * 列出模块列表
	 */
	@RequestMapping("/list.action") 
	public void list(HttpServletRequest request,HttpServletResponse response){
		List<ModuleMangerTree> list = moduleManagerSeviceImpl.listService();
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
}
