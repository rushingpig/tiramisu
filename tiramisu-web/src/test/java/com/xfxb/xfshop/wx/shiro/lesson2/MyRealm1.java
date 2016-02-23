package com.xfxb.xfshop.wx.shiro.lesson2;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * 实现Realm接口，定义自己的Realm角色
 * @author pigo.can
 * @email  rushingpig@163.com
 * @homepage http://www.pigo.top
 * @date   2015年11月16日 下午7:25:58
 * @ver    V1.0
 */
public class MyRealm1 implements Realm{

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		if(!"zhu".equals(username)){
			throw new UnknownAccountException();
		}
		if(!"zhenglin".equals(password)){
			throw new IncorrectCredentialsException();
		}
		
		return new SimpleAuthenticationInfo(username,password,getName());
	}

	@Override
	public String getName() {
		
		return "myRealm1";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		
		return token instanceof UsernamePasswordToken;
	}

}
