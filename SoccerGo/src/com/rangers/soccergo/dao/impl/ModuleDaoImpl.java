package com.rangers.soccergo.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;
import com.rangers.soccergo.dao.ModuleDao;
import com.rangers.soccergo.db.util.CloudQuery;
import com.rangers.soccergo.model.Module;
import com.rangers.soccergo.model.Teacher;

@Component("moduleDaoImpl")
public class ModuleDaoImpl extends CommonDaoImpl<Module> implements ModuleDao {

	public List<Module> queryFatherList(String cql,String objectId) {
		CloudQuery query = this.getSession().executeQuery(cql);
		query.setParam(0,objectId);
		return query.list();
	}
	/*查询所有的一级目录*/
	public List<String> getFirstFloor(){
		List<String> firstFloor = new ArrayList<String>();
		String cql = " select * from Module where status = 1 and isCatalogue = true and father_id=pointer('Module','') order by module_code asc ";
		CloudQuery query = this.getSession().executeQuery(cql);
		List<Module> list = query.list();
		if(list!=null){
			for(int i=0;i<list.size();i++){
				if(list.get(i)!=null){
					String temp = list.get(i).getObjectId();
					firstFloor.add(temp);
				}
			}
		}
		return firstFloor;
	}
	/*查询一级目录下面的所有子节点 包括一级节点*/
	public List<String> getAllChildrenByFirst(String firstId){
		
		String cql = "select * from Module where status = 1 and father_id=pointer('Module',?)";
		CloudQuery query = this.getSession().executeQuery(cql);
		query.setParam(0,firstId);
		List<Module> list = query.list();
		List<Module> sonlist = getAllSonByFirstModule(list);
		List<String> listString = new ArrayList<String>();
		sonlist.add(this.getById(firstId));
		for(Module module:sonlist){
			listString.add(module.getObjectId());
		}
	/*	for(Module module:sonlist){
			System.out.println(" id: "+module.getObjectId()+"    name: "+module.getModule_name());
		}*/
		return listString;
	}
	
	public List<Module> getAllSonByFirstModule(List<Module> list){
		List<Module> items  = null;
		List<Module> listString = new ArrayList<Module>();
		List<Module> childrenModule = new ArrayList<Module>();
		String hqlByFatherLevel = "select * from Module where status = 1 and father_id=pointer('Module',?)";
		for(int i = 0; i < list.size();i++){
			Module module = list.get(i);
			childrenModule = queryFatherList(hqlByFatherLevel,module.getObjectId());
			if(childrenModule.size() > 0){//有孩子节点
				items = getAllSonByFirstModule(childrenModule);          
			}
			listString.add(module);
		}
		if(items!=null){
			for(Module e : items){
				listString.add(e);
			}
			
		}
		return listString;
	}
	public List<Module> getAllByAscCode() {
		String cql = "select * from Module where status = 1 order by module_code asc ";
		CloudQuery query = this.getSession().executeQuery(cql);
		List<Module> list = query.list();
		return list;
	}
}
