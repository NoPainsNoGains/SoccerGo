package com.rangers.soccergo.common.util;

import java.util.ArrayList;
import java.util.List;

import com.rangers.soccergo.model.Module;
import com.rangers.soccergo.vo.module.ShowModuleTree;

import net.sf.json.JSONObject;


public class LoginTree {
	public static List form(List list,List<Module> moduleList){
		List treeView = new ArrayList();            
		List children = new ArrayList();
		for(int i = 0; i < list.size();i++){
			JSONObject attributes=new JSONObject(); 
			List items  = new ArrayList();
			Module module = (Module)list.get(i);
			children = queryFatherList(moduleList,module.getObjectId());
			boolean hasChildren = false;
			if(children.size() > 0){
				items = form(children,moduleList);
				hasChildren = true;
			}
			attributes.put("url", module.getPage());
			ShowModuleTree showModuleTree = new ShowModuleTree(module.getObjectId(),module.getModule_name(),items,attributes);
			treeView.add(showModuleTree);
		}
		return treeView;
	}
	public static List queryFatherList(List<Module> moduleList,String moduleFatherId){
		List list = new ArrayList(); 
		if(moduleList.size()!=0){
			for(Module m:moduleList){
				if((m.getFather_id()!=null)&&((String)m.getFather_id().get("objectId")).equals(moduleFatherId)){
					list.add(m);
				}
			}
		}
		return list;
	}
}
