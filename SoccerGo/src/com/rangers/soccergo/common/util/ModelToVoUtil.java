package com.rangers.soccergo.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rangers.soccergo.model.Role;
import com.rangers.soccergo.vo.system.RoleVo;

public class ModelToVoUtil {
	
	public static String dateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateString=sdf.format(date);
		return dateString;
	}
	public static List<RoleVo> roleToVoRole(List<Role> roleList){
		
		List<RoleVo> roleVoList =  new ArrayList<RoleVo>();
		RoleVo roleVo = null;
		for(Role role : roleList){
			roleVo =  new RoleVo();
			roleVo.setObjectId(role.getObjectId());
			roleVo.setName(role.getName());
			roleVo.setCreatedAt(dateToString(role.getCreatedAt()));
			roleVo.setUpdatedAt(dateToString(role.getUpdatedAt()));
			roleVoList.add(roleVo);
		}
		return roleVoList;
	}
}
