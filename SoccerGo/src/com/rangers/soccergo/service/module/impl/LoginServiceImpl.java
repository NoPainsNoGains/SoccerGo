package com.rangers.soccergo.service.module.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Component;

import com.rangers.soccergo.common.util.LoginTree;
import com.rangers.soccergo.common.util.SplitUtil;
import com.rangers.soccergo.dao.ModuleDao;
import com.rangers.soccergo.dao.RoleAuthorityDao;
import com.rangers.soccergo.dao.RoleDao;
import com.rangers.soccergo.dao.UserDao;
import com.rangers.soccergo.model.Module;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.model.User;
import com.rangers.soccergo.service.module.LoginService;

@Component("loginServiceImpl")
public class LoginServiceImpl implements LoginService{
	private UserDao userDaoImpl;
	private RoleDao roleDaoImpl;
	private RoleAuthorityDao roleAuthorityDaoImpl;
	private ModuleDao moduleDaoImpl;
	private List<String> keyStr = new ArrayList<String>();
	public ModuleDao getModuleDaoImpl() {
		return moduleDaoImpl;
	}
	@Resource(name="moduleDaoImpl")
	public void setModuleDaoImpl(ModuleDao moduleDaoImpl) {
		this.moduleDaoImpl = moduleDaoImpl;
	}
	public RoleDao getRoleDaoImpl() {
		return roleDaoImpl;
	}
	@Resource(name="roleDaoImpl")
	public void setRoleDaoImpl(RoleDao roleDaoImpl) {
		this.roleDaoImpl = roleDaoImpl;
	}
	public RoleAuthorityDao getRoleAuthorityDaoImpl() {
		return roleAuthorityDaoImpl;
	}
	@Resource(name="roleAuthorityDaoImpl")
	public void setRoleAuthorityDaoImpl(RoleAuthorityDao roleAuthorityDaoImpl) {
		this.roleAuthorityDaoImpl = roleAuthorityDaoImpl;
	}
	public UserDao getUserDaoImpl() {
		return userDaoImpl;
	}
	@Resource(name="userDaoImpl")
	public void setUserDaoImpl(UserDao userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}
	/*得到子孙节点*/
	public List<Module> getAllGroundChildren(List<Module> allModuleList,Module parent){
		List<Module> items  = null;
		List<Module> sonList = new ArrayList<Module>();
		List<Module> childrenModule = null;
		for(Module module : allModuleList){
			if((module.getFather_id()!=null)&&(parent.getObjectId().equals((String)module.getFather_id().get("objectId")))&&(parent.getStatus()==1)){
				childrenModule = getAllGroundChildren(allModuleList,module);
				sonList.add(module);
				if(childrenModule != null){
					for(Module m : childrenModule){
						sonList.add(m);
					}
				}
				
			}
		}
		return sonList;
	}
	/*查询一级目录下面的所有子节点 包括一级节点*/
	public List<String> getAllChildrenByFirst(List<Module> allModuleList,String firstId){
		Module firstModule = null;
		List<String> listString = new ArrayList<String>();
		List<Module> test = new ArrayList<Module>();/////////
		for(Module module : allModuleList){
			if(module.getObjectId().equals(firstId) && module.getStatus()==1){//有孩子节点
				firstModule = module;
				listString.add(module.getObjectId());
				test.add(module);///////
				break;
			}
		}
		if(firstModule!=null){
			List<Module> sonList = getAllGroundChildren(allModuleList,firstModule);
			for(Module son :sonList){
				test.add(son);////////
				listString.add(son.getObjectId());
			}
			for(Module module:test){///////////
				System.out.println(" id: "+module.getObjectId()+"    name: "+module.getModule_name());
			}
			return listString;
		}
		return null;
	}

	/*查询所有的一级目录*/
	public List<String> getFirstFloor(List<Module> allModuleList){
		List<String> firstFloor = new ArrayList<String>();
		for(Module module : allModuleList){
			if((module.getStatus()==1)&&(module.isIsCatalogue()==true) &&(module.getFather_id()==null)){
				firstFloor.add(module.getObjectId());
			}
		}
		return firstFloor;
	}
	/*根据用户id 找到用户用户权限集合*/
	public String findAuthoritySet(String userId){
		Role role = null;
		String authoritySet = null;
		List<Role> roleList = roleDaoImpl.getRoleByUserId(userId);
		if(roleList.size()!=0){
			role = roleList.get(0);
			authoritySet = roleAuthorityDaoImpl.getModuleSetByRoleId(role.getObjectId());
		}
		return authoritySet;
	}
	/*根据用户id 查出该用户的所有权限数据表*/
	public List<Module> UserFunctionDataBase(String userId) {
		String collection = this.findAuthoritySet(userId);
		if (collection.length() != 0) {
			String[] strArray = SplitUtil.stringplit(collection);
			List<Module> moduleList = new ArrayList<Module>();
			for (int i = 0; i < strArray.length; i++) {
				Module temp = moduleDaoImpl.getById(strArray[i]);
				moduleList.add(temp);
			}
			return moduleList;
		}
		return null;
	}
	/*得到所有的module数据*/
	public List<Module> getAllModule(){
		List<Module> allModuleList = moduleDaoImpl.getAllByAscCode();
		if(allModuleList.size()==0)
			return null;
		else
			return allModuleList;
	}
	/*检查用户权限集合里面是否有该节点 没有 则插入*/
	public boolean checkThisNode(String collection,String id){
		if (collection.length() != 0) {
			String[] strArray = SplitUtil.stringplit(collection);
			for (int i = 0; i < strArray.length; i++) {
				if(strArray[i].equals(id))
					return true;
			}
		}
		return false;
	}
	/*根据一个节点递归得到该节点的所有的一级一级父亲点*/
	public String getFatherId(List<Module> allModuleList,Module module,String collection){
		if(module.getFather_id()==null){
			/*该根节点没在用户权限集合中 插入*/
			if(this.checkThisNode(collection,module.getObjectId())==false){
				collection=collection+","+module.getObjectId();
			}
		}else{
			for(Module m:allModuleList){
				if(((String)module.getFather_id().get("objectId")).equals(m.getObjectId())){
					collection = getFatherId(allModuleList,m,collection);
					/*父亲节点没在用户权限集合中 插入*/
					if(this.checkThisNode(collection,m.getObjectId())==false){
						collection=collection+","+m.getObjectId();
					}
				}
			}
		}
		return collection;
		
	}
	/*树形 权限集合的构造(防止用户只有叶子节点 需要加入这些叶子节点的父节点)*/
	public String reStructCollection(List<Module> allModuleList,List<Module> userModuleList,String collection){
		String newCollection=collection;
		for(Module m:userModuleList){
			if(m.getFather_id()!=null){
				newCollection= this.getFatherId(allModuleList,m, newCollection);
			}
		}
		return newCollection;
	}
	/*根据新的colleciton 加载能显示的树的model表*/
	public List<Module> showModuleDataBase(String showCollection,List<Module> allModuleList){
		if (showCollection.length() != 0) {
			String[] strArray = SplitUtil.stringplit(showCollection);
			List<Module> moduleList = new ArrayList<Module>();
			for (int i = 0; i < strArray.length; i++) {
				for(Module module : allModuleList){
					if(module.getObjectId().equals(strArray[i])){
						moduleList.add(module);
					}
				}
			}
			return moduleList;
		}
		return null;
	}
	/*用户权限表 找出一级目录 */
	public List<Map<String,List<String>>> divideFirstCatalogue(String collection,List<Module> allModuleList){	
		List<Map<String,List<String>>> differModuleList = new ArrayList<Map<String,List<String>>>();
		/*得到第一层所有节点*/
		List<String> firstFloor = this.getFirstFloor(allModuleList);
		if(collection.length()!=0){
			String[] strArray = SplitUtil.stringplit(collection);
			for(String e:firstFloor){
				List<String> allPageList = null;
				Map<String,List<String>> differmodule = new HashMap<String,List<String>>();
				for(int i=0;i<strArray.length;i++){
					if(strArray[i].equals(e)){//发现一级模块
						allPageList = this.getAllChildrenByFirst(allModuleList,strArray[i]);
						differmodule.put("module"+i, allPageList);
						keyStr.add("module"+i);
						break;
					}
				}
				if(allPageList!=null){
					differModuleList.add(differmodule);
				}
			}
		}
		return differModuleList;
	}
	/*用户权限表 用户所有模块分类*/
	public List<Map<String,List<Module>>> divideUserModule(List<Map<String,List<String>>> differModuleList,List<Module> userModuleList){	
		List<Map<String,List<Module>>> rankModuleList = new ArrayList<Map<String,List<Module>>>();
		for (int j = 0; j < differModuleList.size(); j++) {
			Map<String,List<Module>> differmodule = new HashMap<String,List<Module>>();
			Map<String, List<String>> mp = differModuleList.get(j);
			if (keyStr.size() == differModuleList.size()) {
				List<String> firstFloor = mp.get(keyStr.get(j));
				List<Module> moduleTemp = new ArrayList<Module>();			
				for (Module m:userModuleList) {//匹配查询 用户的权限集合 所有的分集权限集合 
					for(String e:firstFloor){
						if(e.equals(m.getObjectId())){//如果用户有该一级目录权限 进行分类
							moduleTemp.add(m);
							break;
						}
					}
				}			
				if(moduleTemp.size()!=0){
					differmodule.put(keyStr.get(j), moduleTemp);//新的用户权限集合  key-模块名  value-模块集合
				}
			}
			if(differmodule.size()!=0){
				rankModuleList.add(differmodule);
			}
		}
		return rankModuleList;
	}
	/*登陆树封装函数*/
	public List formTree(List<Module> moduleList){
		List treeView = new ArrayList();
		List fatherList = new ArrayList();
		for(Module m:moduleList){
			if((m!=null)&&(m.getFather_id()==null)){
				fatherList.add(m);
			}
		}
		treeView = LoginTree.form(fatherList, moduleList);
		return treeView;
	}
	/*根据分好的用户模块集合 进行树的封装*/
	public Map<String,JSONArray> ModuletransformTree(List<Map<String,List<Module>>> rankModuleList){
		LinkedHashMap<String,JSONArray> treeMap  =new LinkedHashMap<String,JSONArray>();
		for(int i=0;i<rankModuleList.size();i++){
			List treeView = new ArrayList();
			Map<String, List<Module>> mp = rankModuleList.get(i);
			List<Module> moduleList = mp.get(keyStr.get(i));
			treeView = this.formTree(moduleList);//形成树
			String moduleName = null;
			for(Module m:moduleList){
				if(m.getFather_id()==null){
					moduleName = m.getModule_name();
					break;
				}
			}
			JSONArray showTreeView = JSONArray.fromObject(treeView);	
			treeMap.put(moduleName, showTreeView);
		}
		keyStr.clear();
		return treeMap;
	}
	/*
	 * 判断用户是否存在
	 * */
	public User existService(String telephone, String passwd) {
		return userDaoImpl.getById(telephone);//暂时用telephone代替ObjectId
	}
	/*
	 *加载树 
	 * */
	public Map<String, JSONArray> loginTreeService(String userId) {
		/*重新构造后的用户权限集合*/
		String showCollection = null;;
		
	    /*用户权限collection集合 */
		String collection = this.findAuthoritySet(userId);
		
		/*用户所选module集合(可能只有叶子节点)*/
		List<Module> userModuleList = this.UserFunctionDataBase(userId);
		
		/*所有的module数据*/
		List<Module> allModuleList = this.getAllModule();
		
		/*新的用户collection形成*/
		if(allModuleList.size()!=0){
			showCollection = this.reStructCollection(allModuleList,userModuleList,collection);
		}
		
		/*根据新的colleciton 加载能显示的树的model表*/
		List<Module> showModuleList = this.showModuleDataBase(showCollection,allModuleList);
		
		/*数据表一级目录分类*/
		List<Map<String,List<String>>> tempMapList = this.divideFirstCatalogue(showCollection,allModuleList);
		
		/*showCollection进行分模块管理*/
		List<Map<String,List<Module>>> rankModuleList = this.divideUserModule(tempMapList, showModuleList);
		
		/*进行转换*/
		Map<String,JSONArray> treeMap = this.ModuletransformTree(rankModuleList);
		return treeMap;
	}

}
