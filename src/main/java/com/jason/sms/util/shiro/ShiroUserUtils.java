package com.jason.sms.util.shiro;

import org.apache.shiro.SecurityUtils;

import com.jason.sms.util.shiro.MybatisShiroRealm.ShiroUser;

public final class ShiroUserUtils {
	
	
	/**
	 * 判断当前是否登陆状态
	 * @return
	 */
	public static boolean isCurrentUser(){
		ShiroUser ShiroUser = getCurrentUser();
		if(null!=ShiroUser){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 查询当前登陆者信息
	 * @return ShiroUser
	 */
	public static ShiroUser getCurrentUser() {
		return  (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}
	/**
	 * 查询当前登陆者 用户名
	 * @return
	 */
	public static String getCurrentUserName() {
		return getCurrentUser().getUsername();
	}
	/**
	 * 查询当前登陆者 用户Id
	 * @return
	 */
	public static long getCurrentUserId() {
		return getCurrentUser().getId();
	}
	
	/**
	 * 查询当前登陆者 用户角色Id
	 * @return
	 */
	public static long getCurrentUserRoleId() {
		return getCurrentUser().getRoleId();
	}
	/**
	 * 查询当前登陆者 用户角色名称
	 * @return
	 */
	public static String getCurrentUserRoleName() {
		return getCurrentUser().getRoleName();
	}
}
