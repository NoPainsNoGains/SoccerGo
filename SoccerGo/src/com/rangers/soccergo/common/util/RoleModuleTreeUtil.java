package com.rangers.soccergo.common.util;

import java.util.ArrayList;
import java.util.List;
import com.rangers.soccergo.dao.ModuleDao;
import com.rangers.soccergo.model.Module;
import com.rangers.soccergo.vo.module.RoleModuleTree;



public class RoleModuleTreeUtil {
	public static List<RoleModuleTree> getAllModuleChildrenTree(List<Module> list,ModuleDao moduleDaoImpl){
		String hqlByFatherLevel = "select * from Module where father_id=pointer('Module',?) ";
		List<RoleModuleTree> treeView = new ArrayList<RoleModuleTree>();          
		List<Module> childrenModule = new ArrayList<Module>();
		List<RoleModuleTree> items  = null;
		for(int i = 0; i < list.size();i++){
			items = new ArrayList<RoleModuleTree>();
			Module module = list.get(i);
			childrenModule = moduleDaoImpl.queryFatherList(hqlByFatherLevel,module.getObjectId());
			boolean hasChildren = false;
			if(childrenModule.size() > 0){
				items = getAllModuleChildrenTree(childrenModule,moduleDaoImpl);
				hasChildren = true;
			}	 
			RoleModuleTree roleModuleTree= new RoleModuleTree();
			if(module.getFather_id()==null){//一级目录 father为空 		
				roleModuleTree.setFatherId("");
			}else{
				roleModuleTree.setFatherId((String)module.getFather_id().get("objectId"));
			}
			roleModuleTree.setCatalogue(module.isIsCatalogue());
			roleModuleTree.setItems(items);
			roleModuleTree.setId(module.getObjectId());
			roleModuleTree.setModule_code(module.getModule_code());
			roleModuleTree.setRemark(module.getRemark());
			roleModuleTree.setState(module.getStatus());
			roleModuleTree.setText(module.getModule_name());
			roleModuleTree.setHasChildren(hasChildren);
			roleModuleTree.setChecked(false);
			treeView.add(roleModuleTree);
		}
		return treeView;
	} 
	public static List<RoleModuleTree> getModuleChildrenTree(List<Module> list,ModuleDao moduleDaoImpl,List<String> roleFuncitonList){
		String hqlByFatherLevel = "select * from Module where status = 1 and father_id=pointer('Module',?)";
		List<RoleModuleTree> treeView = new ArrayList<RoleModuleTree>();          
		List<Module> childrenModule = new ArrayList<Module>();
		List<RoleModuleTree> items  = null;
		for(int i = 0; i < list.size();i++){
			items = new ArrayList<RoleModuleTree>();
			Module module = list.get(i);
			childrenModule = moduleDaoImpl.queryFatherList(hqlByFatherLevel,module.getObjectId());
			boolean hasChildren = false;
			if(childrenModule.size() > 0){
				items = getModuleChildrenTree(childrenModule,moduleDaoImpl,roleFuncitonList);           //递归
				hasChildren = true;
			}
			RoleModuleTree roleModuleTree = null;
			for(String one:roleFuncitonList){
				if(one.equals(module.getObjectId())){
					roleModuleTree = new RoleModuleTree();
					roleModuleTree.setChecked(true);
					break;
				}
			}
			if(roleModuleTree==null){
				roleModuleTree = new RoleModuleTree();
				roleModuleTree.setChecked(false);
			}
			/*设置其它属性*/
			if(module.getFather_id()==null){		
				roleModuleTree.setFatherId("");
			}else{
				roleModuleTree.setFatherId((String)module.getFather_id().get("objectId"));
			}
			roleModuleTree.setCatalogue(module.isIsCatalogue());
			roleModuleTree.setItems(items);
			roleModuleTree.setId(module.getObjectId());
			roleModuleTree.setModule_code(module.getModule_code());
			roleModuleTree.setRemark(module.getRemark());
			roleModuleTree.setState(module.getStatus());
			roleModuleTree.setText(module.getModule_name());
			roleModuleTree.setHasChildren(hasChildren);
			treeView.add(roleModuleTree);
		}
		return treeView;
	}	
}
