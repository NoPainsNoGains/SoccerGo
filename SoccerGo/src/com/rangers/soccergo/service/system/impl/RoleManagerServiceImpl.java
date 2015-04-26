package com.rangers.soccergo.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.rangers.soccergo.common.util.ModelToVoUtil;
import com.rangers.soccergo.dao.RoleDao;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.service.system.RoleManagerService;
import com.rangers.soccergo.vo.system.RoleVo;
/**  
 * 类名 
 * @author YMH 
 * 角色表的列出 增加 编辑
 * 2015.0.24
*/
@Component("roleManagerServiceImpl")
public class RoleManagerServiceImpl implements RoleManagerService{
	private  RoleDao roleDaoImpl;
	
	public RoleDao getRoleDaoImpl() {
		return roleDaoImpl;
	}
	@Resource(name="roleDaoImpl")
	public void setRoleDaoImpl(RoleDao roleDaoImpl) {
		this.roleDaoImpl = roleDaoImpl;
	}
	/** 
	  * 创建角色 将前台传回来的jsonObject转换为Role对象
	  * @return Role 返回创建角色对象
	 */
	public Role jsonToModelBySave(JSONObject jsonObject){
		String name = jsonObject.optString("name");
		Role role  = new Role();
		role.setName(name);
		return role;
	}
	/** 
	  * 列出角色记录
	  * @return roleVoList 返回封装为grid需要的对象列表 时间为string类型
	 */
	public List<RoleVo> listService() {
		List<Role> roleList = roleDaoImpl.getAll();
		List<RoleVo> roleVoList = ModelToVoUtil.roleToVoRole(roleList);
		return roleVoList;
	}
	/**
	  * 计算角色(role)总共有多少条记录 用于kendo grid右下角显示总记录数  
	  * @return 返回记录总数
	 */
	public int countService() {
		return roleDaoImpl.count();
	}
	public void addService(JSONObject jsonObject) {
		Role role = this.jsonToModelBySave(jsonObject);
		roleDaoImpl.save(role);
	}

}

 