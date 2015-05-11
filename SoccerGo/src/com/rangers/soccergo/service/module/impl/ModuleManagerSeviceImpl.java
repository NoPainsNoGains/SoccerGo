package com.rangers.soccergo.service.module.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import com.rangers.soccergo.common.util.ModuleMangerTreeUtil;
import com.rangers.soccergo.dao.ModuleDao;
import com.rangers.soccergo.model.Module;
import com.rangers.soccergo.service.module.ModuleManagerSevice;
import com.rangers.soccergo.vo.module.ModuleMangerTree;


@Component("moduleManagerSeviceImpl")
public class ModuleManagerSeviceImpl implements ModuleManagerSevice{
	private ModuleDao moduleDaoImpl;
	
	public ModuleDao getModuleDaoImpl() {
		return moduleDaoImpl;
	}
	@Resource(name="moduleDaoImpl")
	public void setModuleDaoImpl(ModuleDao moduleDaoImpl) {
		this.moduleDaoImpl = moduleDaoImpl;
	}

	public List<ModuleMangerTree> listService() {
		List<ModuleMangerTree> treeView =null;
		List<Module> fatherList = new ArrayList<Module>();
		String cql = "select * from Module where father_id=pointer('Module',?) order by module_id asc";
		fatherList = moduleDaoImpl.queryFatherList(cql,"");
		List<Module> moduleList = moduleDaoImpl.getAll();
		treeView = ModuleMangerTreeUtil.getDepartChildrenTree(fatherList, moduleDaoImpl, moduleList);
		return treeView;
	}

}
