package com.cjd.ssm.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;



public class MyRealm extends AuthorizingRealm {

    private static final String USER_NAME = "";  
    private static final String PASSWORD = "";  
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { 
    	
    	String username = principals.getPrimaryPrincipal().toString() ;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
/*        Set<String> roleName = t_userService.findRoles(username) ;
        Set<String> permissions = t_userService.findPermissions(username) ;
        info.setRoles(roleName);
        info.setStringPermissions(permissions);*/
        return info;
    }
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