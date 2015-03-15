package com.jason.sms.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.security.Authority;
import com.jason.sms.repository.security.AuthorityRepository;
import com.jason.sms.service.security.AuthorityService;

@Transactional
@Service
public class AuthorityServiceImpl  implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public Authority get(Long id) {
		return authorityRepository.get(id);
	}

	@Override
	public List<Authority> query(Object object) {
		return authorityRepository.query(object);
	}

	@Override
	public Page<Authority> queryPage(Page<Authority> page) {
		return authorityRepository.queryPage(page);
	}

	@Override
	public void update(Authority entity) {
		authorityRepository.update(entity);
		
	}

	@Override
	public void delete(Authority entity) {
		authorityRepository.delete(entity);
		
	}

	@Override
	public void save(Authority entity) {
		authorityRepository.save(entity);
		
	}

}
