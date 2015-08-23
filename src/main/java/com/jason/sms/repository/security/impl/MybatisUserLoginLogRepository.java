package com.jason.sms.repository.security.impl;

import org.springframework.stereotype.Repository;

import com.jason.framework.orm.mybatis.MybatisRepositorySupport;
import com.jason.sms.domain.security.UserLoginLog;
import com.jason.sms.repository.security.UserLoginLogRepository;

@Repository
public class MybatisUserLoginLogRepository extends MybatisRepositorySupport<Long, UserLoginLog> implements UserLoginLogRepository {

	@Override
	protected String getNamespace() {
		return "com.jason.sms.domain.security.UserLoginLog";
	}}
