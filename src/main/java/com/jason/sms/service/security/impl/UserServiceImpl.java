package com.jason.sms.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.security.User;
import com.jason.sms.repository.security.UserRepository;
import com.jason.sms.service.security.UserService;

@Transactional
@Service
public class UserServiceImpl  implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User lazyGet(Long id) {
		return userRepository.lazyGet(id);
	}

	@Override
	public Page<User> queryPage(Page<User> page) {
		return userRepository.queryPage(page);
	}

	@Override
	public void markLocked(String[] ids) {
		userRepository.markLocked(ids);
	}

	@Override
	public void markNotLocked(String[] ids) {
		userRepository.markNotLocked(ids);
	}

	@Override
	public void evictCache(String[] ids) {
		userRepository.evictCache(ids);
	}

	@Override
	public void update(User entity) {
		userRepository.update(entity);		
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public void save(User entity) {
		userRepository.save(entity);
	}

	@Override
	public List<User> queryAll() {
		return userRepository.queryAll();
	}


}
