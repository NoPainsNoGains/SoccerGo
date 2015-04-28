package com.rangers.soccergo.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import com.rangers.soccergo.common.util.Constant;
import com.rangers.soccergo.common.util.DateUtil;
import com.rangers.soccergo.dao.UserDao;
import com.rangers.soccergo.model.User;
import com.rangers.soccergo.service.system.UserManagerService;
import com.rangers.soccergo.vo.system.UserVo;

@Component("userManagerServiceImpl")
public class UserManagerServiceImpl implements UserManagerService{
	private UserDao userDaoImpl;
	
	public UserDao getUserDaoImpl() {
		return userDaoImpl;
	}
	@Resource(name="userDaoImpl")
	public void setUserDaoImpl(UserDao userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
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
	  * 分页列出用户信息
	  * @return List<UserVo> 返回用户列表
	 */
	public List<UserVo> listService(int page,int pageSize) {
		List<User> userList = userDaoImpl.findByPage(page, pageSize);
		List<UserVo> userVoList = this.modelToVoBylist(userList); 
		return userVoList;
	}
	/** 
	  * 计算用户总数
	  * @return int 总数
	 */
	public int countService() {	
		return userDaoImpl.count();
	}
	
}
