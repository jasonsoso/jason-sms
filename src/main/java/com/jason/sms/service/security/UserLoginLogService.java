package com.jason.sms.service.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.security.UserLoginLog;

public interface UserLoginLogService {

	void update(UserLoginLog entity);

	void delete(UserLoginLog entity);

	void save(UserLoginLog entity);
	
	void save(HttpServletRequest request);
	
	void save(String ip);
	
	UserLoginLog get(Long id);

	List<UserLoginLog> query(Object object);

	Page<UserLoginLog> queryPage(Page<UserLoginLog> page);
}
