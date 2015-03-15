package com.jason.sms.repository.security;

import java.util.List;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.security.Authority;

public interface AuthorityRepository {

	Authority get(Long id);

	List<Authority> query(Object object);

	Page<Authority> queryPage(Page<Authority> page);

	void update(Authority entity);
	
	void delete(Authority entity);

	void save(Authority entity);
}
