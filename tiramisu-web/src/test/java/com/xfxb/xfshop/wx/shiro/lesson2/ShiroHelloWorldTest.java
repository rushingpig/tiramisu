package com.xfxb.xfshop.wx.shiro.lesson2;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.SimpleByteSource;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author pigo.can
 * @email  rushingpig@163.com
 * @homepage http://www.pigo.top
 * @date   2015年11月16日 下午7:17:17
 * @ver    V1.0
 */
public class ShiroHelloWorldTest {
	public void login(String filePath,String... tokens){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(filePath);
		SecurityManager manager = factory.getInstance();
		SecurityUtils.setSecurityManager(manager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(tokens[0],tokens[1]);
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			System.out.println("#########            认证失败。。。");
			System.exit(-1);
		}
	}
	
	public Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	@Test
	/**
	 *  通过默认的realm进行认证
	 */
	public void testHelloWorld(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager manager = factory.getInstance();
		SecurityUtils.setSecurityManager(manager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			System.out.println("#########            认证失败。。。");
			System.exit(-1);
		}
		
		Assert.assertEquals(true, subject.isAuthenticated());
		subject.logout();
	}
	
	@Test
	/**
	 * 通过自定义的realm来认证
	 */
	public void testRealm(){
		login("classpath:shiro-realm.ini", "zhu","zhenglin");
	}
	/**
	 * 验证是否具有某种权限
	 * 粗粒度
	 */
	@Test
	public void testHasRole(){
		login("classpath:shiro-hasRole.ini","zhang","123");
		Assert.assertTrue(getSubject().hasRole("role1"));
		Assert.assertTrue(getSubject().hasAllRoles(Arrays.asList("role1","role2")));
		boolean[] reuslts = getSubject().hasRoles(Arrays.asList("role1","role2","role3"));
		Assert.assertTrue(reuslts[0]);
		Assert.assertTrue(reuslts[1]);
		Assert.assertTrue(reuslts[2]);
	}
	/**
	 * 验证是否具有某种权限
	 * 细粒度（基于资源的访问控制）
	 */
	@Test
	public void testHasRole2(){
		login("classpath:shiro-hasRole.ini", "zhang","123");
		Assert.assertTrue(getSubject().isPermitted("user:create"));
		Assert.assertTrue(getSubject().isPermitted("user:delete"));
	}
	
	@Test
	public void encrypto(){
		/**
		 * 通用的散列支持
		 */
		String simpleHash = new SimpleHash("SHA-1","hello","123").toString();
		System.out.println(simpleHash);
		
		/**
		 * DefaultHashService
		 */
		
		DefaultHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName("SHA-512");
		hashService.setPrivateSalt(new SimpleByteSource("123"));
		hashService.setGeneratePublicSalt(true);
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
		hashService.setHashIterations(1);
		/**
		 * 查看源码发现，在computeHash时，优先选取HashRequest里面的设置属性。如果为空，则取hashService的
		 */
		HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
				.setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
		String hex = hashService.computeHash(request).toHex();
		System.out.println(hex);
	}
	

}
