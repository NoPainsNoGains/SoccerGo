package com.rangers.soccergo.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import com.rangers.soccergo.common.util.Constant;
import com.rangers.soccergo.common.util.DateUtil;
import com.rangers.soccergo.common.util.ModelToVoUtil;
import com.rangers.soccergo.common.util.SplitUtil;
import com.rangers.soccergo.dao.ModuleDao;
import com.rangers.soccergo.dao.RoleAuthorityDao;
import com.rangers.soccergo.dao.RoleDao;
import com.rangers.soccergo.dao.UserDao;
import com.rangers.soccergo.model.Module;
import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.model.User;
import com.rangers.soccergo.service.system.RoleManagerService;
import com.rangers.soccergo.vo.module.ModuleMangerTree;
import com.rangers.soccergo.vo.system.RoleVo;
import com.rangers.soccergo.vo.system.UserVo;
/**  
 * 类名 
 * @author YMH 
 * 角色表的列出 增加 编辑
 * 2015.0.24
*/
@Component("roleManagerServiceImpl")
public class RoleManagerServiceImpl implements RoleManagerService{
	private  RoleDao roleDaoImpl;
	private UserDao userDaoImpl;
	
	public UserDao getUserDaoImpl() {
		return userDaoImpl;
	}
	@Resource(name="userDaoImpl")
	public void setUserDaoImpl(UserDao userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}
	public RoleDao getRoleDaoImpl() {
		return roleDaoImpl;
	}
	@Resource(name="roleDaoImpl")
	public void setRoleDaoImpl(RoleDao roleDaoImpl) {
		this.roleDaoImpl = roleDaoImpl;
	}
	/** 
	  * 列表转换信息 将model转换为kendo标准的vo类
	  * @return List<UserVo> vo列表类
	 */
	public List<UserVo> modelToVoBylist(List<User> userList){
		List<UserVo> userVoList = new ArrayList<UserVo>();
		UserVo userVo = null;
		for(User user : userList){
			userVo = new UserVo();
			userVo.setCreatedAt(DateUtil.dateToString(user.getCreatedAt()));
			userVo.setLevel(user.getLevel());
			userVo.setNickname(user.getNickname());
			userVo.setObjectId(user.getObjectId());
			userVo.setPassword(user.getPassword());
			userVo.setPoints(user.getPoints());
			String preferedRoleName= Constant.getNameByPreferedId(user.getPrefered_role());//获取擅长的中文名
			userVo.setPreferedRole(preferedRoleName);
			userVo.setUpdatedAt(DateUtil.dateToString(user.getUpdatedAt()));
			userVo.setUsername(user.getUsername());
			userVoList.add(userVo);
		}
		return userVoList;
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
	public List<RoleVo> listService(int page,int pageSize) {
		List<Role> roleList = roleDaoImpl.findByPage(page, pageSize);
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
	/**
	  * 保存角色
	  * @param jsonObject 前台传回来的jsonObject
	 */
	public void addService(JSONObject jsonObject) {
		Role role = this.jsonToModelBySave(jsonObject);
		roleDaoImpl.save(role);
	}
	/** 
	  * 列出角色下面的用户列表 分页实现
	  * @return roleVoList 返回封装为grid需要的对象列表 时间为string类型
	 */
	public List<UserVo> listRoleUserService(int page, int pageSize,String roleObjectId) {
		List<User> userList = roleDaoImpl.getPageUsersByRoleId(page, pageSize, roleObjectId);
		List<UserVo> userVoList = this.modelToVoBylist(userList); 
		return userVoList;
	}
	/** 
	  * 计算角色下面的用户总数
	  * @return 角色对应的用户数目
	 */
	public int countRoleUserService(String roleObjectId) {
		return roleDaoImpl.countUsersByRoleId(roleObjectId);
	}
	/** 
	  * 保存角色的新用户
	  * @return 角色对应的用户数目
	 */
	public void addRoleUser(String RoleId, String UserId) {
		User user= userDaoImpl.getById(UserId);
		Role role = roleDaoImpl.getById(RoleId);
		roleDaoImpl.addUser(role, user);
	}
	/** 
	  * 列出所有角色
	  * @return 返回角色列表
	 */
	public List<RoleVo> listAllService() {
		List<Role> roleList = roleDaoImpl.getAll();
		List<RoleVo> roleVoList = ModelToVoUtil.roleToVoRole(roleList);
		return roleVoList;
	}
	/** 
	  * 删除角色
	 */
	public void deletRoleUser(String RoleId, String UserId) {
		User user= userDaoImpl.getById(UserId);
		List<Role> roleList = roleDaoImpl.getRoleByUserId(UserId);
		for(Role role: roleList){
			roleDaoImpl.deleteUser(role, user);
		}
	}

}

 
