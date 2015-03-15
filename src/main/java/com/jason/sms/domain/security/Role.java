package com.jason.sms.domain.security;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.Size;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jason.framework.domain.MyBatisDomainObject;
import com.jason.framework.util.Collections3;
import com.jason.framework.util.ConvertUtils;

public class Role extends MyBatisDomainObject {

	private static final long serialVersionUID = 1L;

	@Size(min = 1, max = 32)
	private String name;
	@Size(min = 1, max = 32)
	private String showName;

	private transient Map<Long, Long> authoritiesTrans = Maps.newHashMap();
	private Set<Authority> authorities = Sets.newHashSet();

	public String getName() {
		return name;
	}

	public Role setName(String name) {
		this.name = name;
		return this;
	}

	public String getShowName() {
		return showName;
	}

	public Role setShowName(String showName) {
		this.showName = showName;
		return this;
	}

	public Map<Long, Long> getAuthoritiesTrans() {
		return authoritiesTrans;
	}

	public void setAuthoritiesTrans(Map<Long, Long> authoritiesTrans) {
		this.authoritiesTrans = authoritiesTrans;
	}

	public Set<Authority> getAuthorities() {
		if (null == authorities) {
			return Collections.emptySet();
		}

		return authorities;
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasAuthority() {
		return !getAuthorities().isEmpty();
	}

	public Role setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
		return this;
	}

	/**
	 * transit current authorities to authTrans map
	 * 
	 * @return
	 */
	public Role ofAuths() {
		@SuppressWarnings("unchecked")
		Map<Long, Long> authTrans = Collections3.extractToMap(getAuthorities(), "id", "id");
		setAuthoritiesTrans(authTrans);
		return this;
	}

	/**
	 * merge authority from authTrans map
	 * 
	 * @return
	 */
	public Role mergeAuths() {
		if (null == getAuthoritiesTrans()) {
			return this;
		}

		// clear current authorities first
		getAuthorities().clear();
		for (Long authId : getAuthoritiesTrans().values()) {
			if (authId == null) {
				continue;
			}

			Authority auth = new Authority();
			auth.setId(authId);
			getAuthorities().add(auth);
		}

		return this;
	}

	public String getAuthNamesAsString() {
		if (getAuthorities().isEmpty()) {
			return "";
		}
		return ConvertUtils.convertPropertyToString(getAuthorities(), "name", ",");
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAuthPermissions() {
		if (getAuthorities().isEmpty()) {
			return Collections.emptyList();
		}
		List<String> permissions = Collections3.extractToList(getAuthorities(), "permission");
		return permissions;

	}

}
