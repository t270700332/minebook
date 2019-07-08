package com.book.tzl.common.shiro;

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

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()), // salt = username+salt
				getName());

		return authenticationInfo;
	}

}
