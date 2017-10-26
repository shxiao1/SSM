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
import org.springframework.beans.factory.annotation.Autowired;

import com.cjd.ssm.pojo.SysAccount;
import com.cjd.ssm.service.SysAccountService;



public class MyRealm extends AuthorizingRealm {
	
	@Autowired
	SysAccountService sysAccountService;
	

	
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { 
    	
    	/*String username = principals.getPrimaryPrincipal().toString() ;*/
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
        
        SysAccount sysAccount=sysAccountService.findByName(token.getUsername());
        
        if(token.getUsername().equals(sysAccount.getUsername())){
            return new SimpleAuthenticationInfo(sysAccount.getUsername(), sysAccount.getPassword(), getName());  
        }else{
            throw new AuthenticationException();  
        }
    }
}