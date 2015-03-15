package com.jason.sms.service.security;

import java.util.List;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.security.Role;

public interface RoleService {

	void update(Role entity);

	void delete(Role entity);

	void save(Role entity);
	
	Role get(Long id);

	List<Role> query(Object object);

	Page<Role> queryPage(Page<Role> page);
}
