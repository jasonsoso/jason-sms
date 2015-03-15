package com.jason.sms.repository.security;

import java.util.List;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.security.User;

public interface UserRepository {

	User lazyGet(Long id);

	User queryUniqueByUsername(String username);

	Page<User> queryPage(Page<User> page);
	
	List<User> queryAll();

	void save(User entity);

	void delete(User entity);

	void update(User entity);

	void markLocked(String[] ids);

	void markNotLocked(String[] ids);

	void evictCache(String[] ids);
}
