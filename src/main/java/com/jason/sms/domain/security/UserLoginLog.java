package com.jason.sms.domain.security;

import java.util.Date;

import com.jason.framework.domain.MyBatisDomainObject;

/**
 * 用户登录日志
 * @author Jason
 * @data 2015-8-22 上午09:53:01
 */
public class UserLoginLog extends MyBatisDomainObject {
	private static final long serialVersionUID = 1L;

	private Long userId;
	private Date loginTime;
	private String ip;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
