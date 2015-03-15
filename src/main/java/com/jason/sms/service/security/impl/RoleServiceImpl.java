package com.jason.sms.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.security.Role;
import com.jason.sms.repository.security.RoleRepository;
import com.jason.sms.service.security.RoleService;

@Transactional
@Service
public class RoleServiceImpl  implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role get(Long id) {
		return roleRepository.get(id);
	}

	@Override
	public List<Role> query(Object object) {
		return roleRepository.query(object);
	}

	@Override
	public Page<Role> queryPage(Page<Role> page) {
		return roleRepository.queryPage(page);
	}

	@Override
	public void update(Role entity) {
		roleRepository.update(entity);
		
	}

	@Override
	public void delete(Role entity) {
		roleRepository.delete(entity);		
	}

	@Override
	public void save(Role entity) {
		roleRepository.save(entity);
	}


}
