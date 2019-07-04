package com.book.tzl.common.shiro.conf;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.book.tzl.common.handler.ExceptionHandler;
import com.book.tzl.common.shiro.UserRealm;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/user/login");
		shiroFilterFactoryBean.setUnauthorizedUrl("/page/fail.html");// 未授权跳转
		// 登录成功跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/page/main.html");
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
		filterChainDefinitionMap.put("/logout", "logout");
		// 注册
		filterChainDefinitionMap.put("/user/register", "anon");
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		filterChainDefinitionMap.put("/statics/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/**", "authc");

		// authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
		/* filterChainDefinitionMap.put("/test/**", "authc"); */
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 凭证匹配器 由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * 
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}

	@Bean
	public UserRealm myShiroRealm() {
		UserRealm myShiroRealm = new UserRealm();
		// 使用加密
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 注册全局异常处理
	 * 
	 * @return
	 */
	@Bean(name = "exceptionHandler")
	public HandlerExceptionResolver handlerExceptionResolver() {
		return new ExceptionHandler();
	}

}
