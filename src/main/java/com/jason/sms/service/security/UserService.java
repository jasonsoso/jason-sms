package com.jason.sms.service.security;

import java.util.List;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.security.User;

public interface UserService {

	void update(User entity);

	void delete(User entity);

	void save(User entity);
	
	User lazyGet(Long id);

	Page<User> queryPage(Page<User> page);
	
	List<User> queryAll();

	void markLocked(String[] ids);

	void markNotLocked(String[] ids);

	void evictCache(String[] ids);
}
