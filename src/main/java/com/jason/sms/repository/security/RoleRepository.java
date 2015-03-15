package com.jason.sms.repository.security;

import java.util.List;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.security.Role;

public interface RoleRepository {

	Role get(Long id);
	
	List<Role> query(Object object);

	Page<Role> queryPage(Page<Role> Page);

	void update(Role entity);

	void delete(Role entity);

	void save(Role entity);
}
