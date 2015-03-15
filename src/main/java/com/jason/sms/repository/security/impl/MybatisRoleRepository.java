package com.jason.sms.repository.security.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jason.framework.orm.mybatis.MybatisRepositorySupport;
import com.jason.sms.domain.security.Role;
import com.jason.sms.repository.security.RoleRepository;

@Repository
public class MybatisRoleRepository extends MybatisRepositorySupport<Long, Role> implements RoleRepository {

	private static final Logger logger = LoggerFactory.getLogger(MybatisRoleRepository.class);
	
	@Override
	public void save(Role entity) {
		super.save(entity);
		entity.mergeAuths();
		if (entity.hasAuthority()) {
			getSqlSession().insert(getNamespace().concat(".saveAuthorities"), entity);
		}
	}

	@Override
	public void update(Role entity) {
		super.update(entity);
		getSqlSession().delete(getNamespace().concat(".deleteAuthorities"), entity);
		entity.mergeAuths();
		if (entity.hasAuthority()) {
			getSqlSession().insert(getNamespace().concat(".saveAuthorities"), entity);
		}
		logger.warn("must evict the user cache when update the role");
	}

	@Override
	protected String getNamespace() {
		return "com.jason.sms.domain.security.Role";
	}
}
