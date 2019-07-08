package com.book.tzl.common.utils;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.book.tzl.system.user.domain.UserPojo;

/**
 * @author 作者:tianzhili
 * @version 创建时间:2019年7月5日下午3:58:58 类说明:minebook
 */
public class UserUtils {
	@Autowired
	private static SessionDAO sessionDAO;

	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}

	public static UserPojo getUser() {
		Object object = getSubjct().getPrincipal();
		return (UserPojo) object;
	}

	public static Long getUserId() {
		return getUser().getUid();
	}

	public static void logout() {
		getSubjct().logout();
	}

	public static List<Principal> getPrinciples() {
		List<Principal> principals = null;
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		return principals;
	}
}
