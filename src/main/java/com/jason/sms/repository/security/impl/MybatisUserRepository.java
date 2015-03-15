package com.jason.sms.repository.security.impl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jason.framework.orm.mybatis.MybatisRepositorySupport;
import com.jason.sms.domain.security.User;
import com.jason.sms.repository.security.UserRepository;

@Repository
public class MybatisUserRepository extends MybatisRepositorySupport<Long, User> implements UserRepository {
	private static final Logger logger = LoggerFactory.getLogger(MybatisUserRepository.class);
	private final Cache<String, User> cacheOnUsername = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).maximumSize(16).build();

	@Override
	public List<User> queryAll() {
		return getSqlSession().selectList(getNamespace() + ".query");
	}
	
	@Override
	public User lazyGet(Long id) {
		return (User) getSqlSession().selectOne(getNamespace().concat(".lazyGet"), id);
	}

	@Override
	public User queryUniqueByUsername(final String username) {
		try {
			return cacheOnUsername.get(username, new Callable<User>() {
				@Override
				public User call() throws Exception {
					return (User) getSqlSession().selectOne(getNamespace().concat(".queryUniqueByUsername"), username);
				}
			});
		} catch (Exception ignore) {
			logger.error("some error occur when access to the cacher", ignore);
			return null;
		}
	}
	
	@Override
	public void markLocked(String[] ids) {
		if (isAvaliableIds(ids)) {
			evictCache(ids);
			getSqlSession().update(getNamespace().concat(".markLocked"), ids);
		}
	}

	@Override
	public void markNotLocked(String[] ids) {
		if (isAvaliableIds(ids)) {
			evictCache(ids);
			getSqlSession().update(getNamespace().concat(".markNotLocked"), ids);
		}
	}

	public void evictCache(String[] ids) {
		List<User> users = getSqlSession().selectList(getNamespace().concat(".idsAsUsers"), ids);
		for (User user : users) {
			cacheOnUsername.invalidate(user.getUsername());
		}
	}

	private boolean isAvaliableIds(String[] ids) {
		return null != ids && ids.length > 0;
	}

	@Override
	public void save(User entity) {
		super.save(entity);
	}

	@Override
	public void update(User entity) {
		cacheOnUsername.invalidate(entity.getUsername());
		super.update(entity);
	}

	@Override
	public void delete(User entity) {
		cacheOnUsername.invalidate(entity.getUsername());
		super.delete(entity);
	}

	@Override
	protected String getNamespace() {
		return "com.jason.sms.domain.security.User";
	}

	

}
