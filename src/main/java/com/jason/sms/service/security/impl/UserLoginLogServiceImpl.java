package com.jason.sms.service.security.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jason.framework.orm.Page;
import com.jason.framework.util.DateHelper;
import com.jason.framework.util.RequestUtil;
import com.jason.sms.domain.security.UserLoginLog;
import com.jason.sms.repository.security.UserLoginLogRepository;
import com.jason.sms.service.security.UserLoginLogService;
import com.jason.sms.util.shiro.ShiroUserUtils;

@Transactional
@Service
public class UserLoginLogServiceImpl  implements UserLoginLogService {

	@Autowired
	private UserLoginLogRepository userLoginLogRepository;

	@Override
	public UserLoginLog get(Long id) {
		return userLoginLogRepository.get(id);
	}

	@Override
	public List<UserLoginLog> query(Object object) {
		return userLoginLogRepository.query(object);
	}

	@Override
	public Page<UserLoginLog> queryPage(Page<UserLoginLog> page) {
		return userLoginLogRepository.queryPage(page);
	}

	@Override
	public void update(UserLoginLog entity) {
		userLoginLogRepository.update(entity);
		
	}

	@Override
	public void delete(UserLoginLog entity) {
		userLoginLogRepository.delete(entity);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void save(UserLoginLog entity) {
		userLoginLogRepository.save(entity);
		
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void save(HttpServletRequest request) {
		String ip = RequestUtil.getUserIp(request);
		save(ip);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void save(String ip) {
		Long userId = ShiroUserUtils.getCurrentUserId();
		
		UserLoginLog entity = new UserLoginLog();
		entity.setIp(ip);
		entity.setUserId(userId);
		entity.setLoginTime(DateHelper.getCurrentDate());
		save(entity);
	}

}
