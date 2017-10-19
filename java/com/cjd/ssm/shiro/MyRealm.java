package com.cjd.ssm.shiro;

import java.util.HashSet;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MyRealm extends AuthorizingRealm {
	//开启日志输出 
	private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);

    private static final String USER_NAME = "";  
    private static final String PASSWORD = "";  
    /* 
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { 
    	LOGGER.info("Shiro开始登录认证");
    	
    	String username = principals.getPrimaryPrincipal().toString() ;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
/*        Set<String> roleName = t_userService.findRoles(username) ;
        Set<String> permissions = t_userService.findPermissions(username) ;
        info.setRoles(roleName);
        info.setStringPermissions(permissions);*/
        return info;
    }
    /* 
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        if(token.getUsername().equals(USER_NAME)){
            return new SimpleAuthenticationInfo(USER_NAME, PASSWORD, getName());  
        }else{
            throw new AuthenticationException();  
        }
    }
}