package com.jason.sms.util.shiro;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jason.sms.domain.security.User;
import com.jason.sms.repository.security.UserRepository;

public class MybatisShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserRepository userRepository;

	/*
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		try {
			User user = userRepository.queryUniqueByUsername(shiroUser.getUsername());
			checkUser(user, shiroUser.getUsername());

			Set<String> roleNames = user.getRoleNames();
			Set<String> permissions = user.getPermissions();
			
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
			info.setStringPermissions(permissions);
			return info;
		} catch (Exception e) {
			throw translateAuthorizationException(e);
		}
	}

	private AuthorizationException translateAuthorizationException(Exception e) {
		if (e instanceof AuthorizationException) {
			return (AuthorizationException) e;
		}
		return new AuthorizationException(e);
	}

	private void checkUser(User user, String username) {
		if (null == user) {
			throw new UnknownAccountException("No account found for user [" + username + "]");
		}
		if (!user.isAccountNonLocked()) {
			throw new LockedAccountException("Account found for user [" + username + "] is locked");
		}
	}

	private AuthenticationException translateAuthenticationException(Exception e) {
		if (e instanceof AuthenticationException) {
			return (AuthenticationException) e;
		}
		return new AuthenticationException(e);
	}

	/*
	 * 认证回调函数,登录时调用.
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		String username = upToken.getUsername();
		
		// Null username is invalid
		if (StringUtils.isBlank(username)) {
			throw new AccountException("Null usernames are not allowed by this realm.");
		}
		try {
			User user = userRepository.queryUniqueByUsername(username);
			checkUser(user, username);

			return buildAuthenticationInfo(user.getId(),username, user.getRoleId(),user.getRoleNamesAsString(),user.getPassword().toCharArray());
		} catch (Exception e) {
			throw translateAuthenticationException(e);
		}
	}

	protected AuthenticationInfo buildAuthenticationInfo(long id,String username,long roleId,String roleName, char[] password) {
		return new SimpleAuthenticationInfo(new ShiroUser(id, username, roleId,roleName), password, getName());
	}
	
	
	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 * @author Jason
	 *
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		
		public long id;
		public String username;
		private long roleId;
		private String roleName;

		public ShiroUser(long id,String username,long roleId,String roleName) {
			this.id = id;
			this.username = username;
			this.roleId = roleId;
			this.roleName = roleName;
		}
		public long getId() {
			return id;
		}
		public String getUsername() {
			return username;
		}
		public long getRoleId() {
			return roleId;
		}

		public String getRoleName() {
			return roleName;
		}
		
		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return username;
		}

		/**
		 * 重载hashCode,只计算username;
		 */
		//@Override
		//public int hashCode() {
		//	return Objects.hashCode(username);
		//}

		/**
		 * 重载equals,只计算username;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ShiroUser other = (ShiroUser) obj;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}
	}
	
}
