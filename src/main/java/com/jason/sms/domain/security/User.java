package com.jason.sms.domain.security;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;

import com.jason.framework.domain.MyBatisDomainObject;
import com.jason.framework.util.EncryptUtils;

public class User extends MyBatisDomainObject {

	private static final long serialVersionUID = 1L;

	@Size(min = 3, max = 16)
	private String username;	//用户名
	private String password;	//密码
	private boolean accountNonLocked = true;//是否锁定

	private Long roleId;	//用户角色ID
	private Role role;		//用户角色

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public boolean hasPassword() {
		return StringUtils.isNotBlank(getPassword());
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public User setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
		return this;
	}

	public User lock() {
		return setAccountNonLocked(false);
	}

	public User notLock() {
		return setAccountNonLocked(true);
	}

	public Role getRole() {
		return role;
	}

	public String getRoleNamesAsString() {
		if (getRole()==null) {
			return "";
		}
		return getRole().getName();
	}

	public boolean hasRole() {
		return getRole()==null;
	}


	public User encodePassword() {
		String md5 =EncryptUtils.md5(getPassword() + getUsername());
		return setPassword(md5);
	}

	public Long getRoleId() {
		return roleId;
	}

	public User setRoleId(Long roleId) {
		this.roleId = roleId;
		return this;
	}

	public User setRole(Role role) {
		this.role = role;
		return this;
	}
	
	public Set<String> getRoleNames() {
		if (getRole() == null) {
			return Collections.emptySet();
		}
		List<String> namesList = new LinkedList<String>();
		namesList.add(getRole().getName());
		return new HashSet<String>(namesList);
	}
	
	public Set<String> getPermissions() {
		if (getRole() == null) {
			return Collections.emptySet();
		}
		Set<String> permissions = new HashSet<String>();
		Role role = getRole();
		permissions.addAll(role.getAuthPermissions());
		return permissions;
	}

}
