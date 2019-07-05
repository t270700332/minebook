package com.book.tzl.common.shiro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book.tzl.common.utils.PasswordHelper;
import com.book.tzl.system.user.domain.UserPojo;
import com.book.tzl.system.user.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 授权
	 * 
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 在数据库中查询用户拥有的角色/权限
		/*
		 * authorizationInfo.setRoles(userService.findRoles(username));
		 * authorizationInfo.setStringPermissions(userService.findPermissions(username))
		 * ;
		 */
		return authorizationInfo;
	}

	/**
	 * 验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal();
		UserPojo user = userService.findByUsername(username);
		if (user == null) {
			throw new UnknownAccountException(); // 没找到账号
		}
		String Md5Pwd = PasswordHelper.encrypt(username, new String((char[]) token.getCredentials()), user.getSalt());
		if (!Md5Pwd.equals(user.getPassword())) {
			throw new UnknownAccountException(); // 没找到账号
		}
		/*
		 * if (Boolean.TRUE.equals(user.getLocked())) { throw new
		 * LockedAccountException(); // 账号被锁定 }
		 */

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
				user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), // salt = username+salt
				getName());

		return authenticationInfo;
	}

	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;

		private Integer uid; // 编号
		private String loginName; // 登录名
		private String name; // 姓名
		// private boolean mobileLogin; // 是否手机登录

		private Map<String, Object> cacheMap;

		public Principal(UserPojo user, boolean mobileLogin) {
			this.uid = user.getUid();
			this.loginName = user.getUsername();
			// this.name = user.getName();
			// this.mobileLogin = mobileLogin;
		}

		public Integer getUid() {
			return uid;
		}

		public String getLoginName() {
			return loginName;
		}

		public String getName() {
			return name;
		}

		@JsonIgnore
		public Map<String, Object> getCacheMap() {
			if (cacheMap == null) {
				cacheMap = new HashMap<String, Object>();
			}
			return cacheMap;
		}

		/**
		 * 获取SESSIONID
		 */
		/*
		 * public String getSessionid() { try { return (String)
		 * UserUtils.getSession().getId(); } catch (Exception e) { return ""; } }
		 */

	}

}
