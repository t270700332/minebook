package com.book.tzl.common.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.book.tzl.system.user.domain.UserPojo;

/**
 * @author 作者:tianzhili
 * @version 创建时间:2019年7月5日下午2:02:04 类说明:minebook 密码加盐
 */
public class PasswordHelper {
	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	// 这些要与Realm中的一致
	private static String algorithmName = "md5";
	private final static int hashIterations = 2;

	static public void encryptPassword(UserPojo user) {
		// 加盐
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getUsername()), hashIterations).toHex();
		user.setPassword(newPassword);
	}
}
