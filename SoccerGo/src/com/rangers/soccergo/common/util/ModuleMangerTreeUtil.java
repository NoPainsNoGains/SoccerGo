package com.rangers.soccergo.common.util;

import java.util.ArrayList;
import java.util.List;

import com.rangers.soccergo.dao.ModuleDao;
import com.rangers.soccergo.model.Module;
import com.rangers.soccergo.vo.module.ModuleMangerTree;



public class ModuleMangerTreeUtil {
	public static List<ModuleMangerTree> getDepartChildrenTree(List<Module> list,ModuleDao moduleDaoImpl,List<Module> moduleList){
		String hqlByFatherLevel = "select * from Module where status = 1 and father_id=pointer('Module',?)";
		List<ModuleMangerTree> treeView = new ArrayList<ModuleMangerTree>();          
		List<Module> childrenModule = new ArrayList<Module>();
		List<ModuleMangerTree> items  = null;
		for(int i = 0; i < list.size();i++){
			items = new ArrayList<ModuleMangerTree>();
			Module module = list.get(i);
			childrenModule = moduleDaoImpl.queryFatherList(hqlByFatherLevel,module.getObjectId());
			if(childrenModule.size() > 0){
				items = getDepartChildrenTree(childrenModule,moduleDaoImpl,moduleList);          
			}
			ModuleMangerTree moduleTree = new ModuleMangerTree();
			if(module.getFather_id()==null){//一级目录 father为空 		
				moduleTree.setFatherId("");
			}else{
				moduleTree.setFatherId((String)module.getFather_id().get("objectId"));
			}
			moduleTree.setCatalogue(module.isIsCatalogue());
			moduleTree.setChildren(items);
			moduleTree.setId(module.getObjectId());
			moduleTree.setModule_code(module.getModule_code());
			moduleTree.setRemark(module.getRemark());
			moduleTree.setState(module.getStatus());
			moduleTree.setText(module.getModule_name());
			treeView.add(moduleTree);
		}
		return treeView;
	}
	public static List<String> getAllSonByFirstModule(List<Module> list,ModuleDao moduleDaoImpl){
		List<String> items  = null;
		List<String> listString = new ArrayList<String>();
		List<Module> childrenModule = new ArrayList<Module>();
		String hqlByFatherLevel = "select * from Module where status = 1 and father_id=pointer('Module',?)";
		for(int i = 0; i < list.size();i++){
			Module module = list.get(i);
			childrenModule = moduleDaoImpl.queryFatherList(hqlByFatherLevel,module.getObjectId());
			if(childrenModule.size() > 0){//有孩子节点
				items = getAllSonByFirstModule(childrenModule,moduleDaoImpl);          
			}
			listString.add(module.getObjectId());
		}
		if(items!=null){
			for(String e : items){
				listString.add(e);
			}
			
		}
		return listString;
	}
}
