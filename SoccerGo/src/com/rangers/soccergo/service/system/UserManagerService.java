package com.rangers.soccergo.service.system;

import java.util.List;
import com.rangers.soccergo.vo.system.UserVo;

public interface UserManagerService {
	public List<UserVo> listService(int page,int pageSize);
	public int countService();
}
