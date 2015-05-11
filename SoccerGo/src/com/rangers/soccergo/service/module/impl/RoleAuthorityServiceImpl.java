package com.rangers.soccergo.service.module.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.rangers.soccergo.common.util.RoleModuleTreeUtil;
import com.rangers.soccergo.common.util.SplitUtil;
import com.rangers.soccergo.dao.ModuleDao;
import com.rangers.soccergo.dao.RoleAuthorityDao;
import com.rangers.soccergo.dao.RoleDao;
import com.rangers.soccergo.model.Module;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.model.RoleAuthority;
import com.rangers.soccergo.service.module.RoleAuthorityService;
import com.rangers.soccergo.vo.module.RoleModuleTree;
@Component("roleAuthorityServiceImpl")
public class RoleAuthorityServiceImpl implements RoleAuthorityService{
	private RoleAuthorityDao roleAuthorityDaoImpl;
	private ModuleDao moduleDaoImpl;
	private RoleDao roleDaoImpl;
	
	public RoleDao getRoleDaoImpl() {
		return roleDaoImpl;
	}
	@Resource(name="roleDaoImpl")
	public void setRoleDaoImpl(RoleDao roleDaoImpl) {
		this.roleDaoImpl = roleDaoImpl;
	}
	public ModuleDao getModuleDaoImpl() {
		return moduleDaoImpl;
	}
	@Resource(name="moduleDaoImpl")
	public void setModuleDaoImpl(ModuleDao moduleDaoImpl) {
		this.moduleDaoImpl = moduleDaoImpl;
	}
	public RoleAuthorityDao getRoleAuthorityDaoImpl() {
		return roleAuthorityDaoImpl;
	}
	@Resource(name="roleAuthorityDaoImpl")
	public void setRoleAuthorityDaoImpl(RoleAuthorityDao roleAuthorityDaoImpl) {
		this.roleAuthorityDaoImpl = roleAuthorityDaoImpl;
	}
	/*
	 * 列出该角色的权限
	 * 
	 * */
	public List<RoleModuleTree> listFunction(String roleId) {
		List<RoleModuleTree> treeView =null;
		List<Module> fatherList = new ArrayList<Module>();
		List<String> roleFuncitonList= this.listRoleFunctionService(roleId);		
		String cql= "select * from Module where status = 1 and isCatalogue = true and father_id=pointer('Module',?) order by module_code asc ";
		fatherList = moduleDaoImpl.queryFatherList(cql, "");
		if(roleFuncitonList==null){
			treeView = RoleModuleTreeUtil.getAllModuleChildrenTree(fatherList, moduleDaoImpl);
		}else{
			treeView = RoleModuleTreeUtil.getModuleChildrenTree(fatherList, moduleDaoImpl,roleFuncitonList);
		}
		return treeView;
	}
	public List<String> listRoleFunctionService(String roleId) {
		List<String> functionList =  new ArrayList<String>();
		String functionStr = roleAuthorityDaoImpl.getModuleSetByRoleId(roleId);
		if(functionStr==null||functionStr.length()==0){
			return null;
		}else{
			String[] strArray = SplitUtil.stringplit(functionStr);
			for(String str:strArray){
				functionList.add(new String(str));
			}
			return functionList;
		}
	}
	/*职务权限插入之前 按照字符编码进行排序*/
	public String sortCollection(String functionSet){
		StringBuilder newCollection = new StringBuilder("");
		if (functionSet.length() != 0) {
			String[] strarray =SplitUtil.stringplit(functionSet);
			List<Module> moduleList = new ArrayList<Module>();
			/*找到权限集合*/
			for (int i = 0; i < strarray.length; i++) {
				Module temp = moduleDaoImpl.getById(strarray[i]);
				moduleList.add(temp);
			}
			String[] strArr =new String [moduleList.size()];
			for(int i=0;i<moduleList.size();i++){//得到模块字符串集编码
				strArr[i]=moduleList.get(i).getModule_code();
			}
			Arrays.sort(strArr);//按照编码进行排序		
			for(int i=0;i<strArr.length;i++){//得到模块字符串集编码
				for(Module m:moduleList){
					if(strArr[i].equals(m.getModule_code())){
						newCollection.append(m.getObjectId()+",");
					}
				}	
			}
			//newCollection = newCollection.toString().substring(0, newCollection.length()-1);
		}
		return newCollection.toString().substring(0, newCollection.length()-1);
	}
	/*设置用户权限权限集合没有  增加 */
	public RoleAuthority setFunctionPoToVo(String roleid,String functionSet){
		RoleAuthority s = roleAuthorityDaoImpl.getObjectByRoleId(roleid);
		if(s==null){//新增加角色权限
			Role role = roleDaoImpl.getById(roleid);
			s = new RoleAuthority();
			s.setModuleSet(functionSet);
			s.setRoleId(roleid);
			roleAuthorityDaoImpl.save(s);
			return null;
		}else{//更新 返回更新的对象
			s.setModuleSet(functionSet);
			return s;
		}
	}
	public void updateFunction(String roleId, String authoritySet) {
		String newCollection = this.sortCollection(authoritySet);
		RoleAuthority roleAuthority = this.setFunctionPoToVo(roleId, newCollection);
		if(roleAuthority!=null){
			roleAuthorityDaoImpl.update(roleAuthority);
		}
	}
}
