package com.jason.sms.repository.security;

import java.util.List;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.security.UserLoginLog;

public interface UserLoginLogRepository {

	UserLoginLog get(Long id);

	List<UserLoginLog> query(Object object);

	Page<UserLoginLog> queryPage(Page<UserLoginLog> page);

	void update(UserLoginLog entity);
	
	void delete(UserLoginLog entity);

	void save(UserLoginLog entity);
}
