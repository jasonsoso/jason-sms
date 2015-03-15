package com.jason.sms.domain.security;

import javax.validation.constraints.Size;

import com.jason.framework.domain.MyBatisDomainObject;

public class Authority extends MyBatisDomainObject {

	private static final long serialVersionUID = 1L;

	@Size(min = 1, max = 32)
	private String name;
	@Size(min = 1, max = 64)
	private String permission;

	public String getName() {
		return name;
	}

	public Authority setName(String name) {
		this.name = name;
		return this;
	}

	public String getPermission() {
		return permission;
	}

	public Authority setPermission(String permission) {
		this.permission = permission;
		return this;
	}
}
