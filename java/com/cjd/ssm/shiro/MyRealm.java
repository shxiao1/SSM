package com.cjd.ssm.shiro;

import java.util.List;
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
import org.springframework.beans.factory.annotation.Autowired;

import com.cjd.ssm.pojo.SysAccount;
import com.cjd.ssm.service.SysAccountService;
import com.cjd.ssm.service.SysPermissionService;
import com.cjd.ssm.service.SysRoleService;

public class MyRealm extends AuthorizingRealm
{

	@Autowired
	SysAccountService sysAccountService;

	@Autowired
	SysRoleService sysRoleService;

	@Autowired
	SysPermissionService sysPermissionService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{

		String username = principals.getPrimaryPrincipal().toString();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		String roleid = sysAccountService.findRoles(username);
		if (roleid != null && roleid.length() > 0)
		{

			String roles[] = roleid.split(",");
			for (int i = 0; i < roles.length; i++)
			{
				String role = sysAccountService.findRole(roles[i]);
				info.addRole(role);
				String permissionid = sysAccountService.findPermissions(role);
				if (permissionid != null && permissionid.length() > 0)
				{
					String permissions[] = permissionid.split(",");
					for (int j = 0; j < permissions.length; j++)
					{
						String permission = sysAccountService.findPermission(permissions[j]);
						info.addStringPermission(permission);
					}
				}
			}
		}
		/* info.setStringPermissions(permissions); */
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		SysAccount sysAccount = sysAccountService.findByName(token.getUsername());

		if (token.getUsername().equals(sysAccount.getUsername()))
		{
			return new SimpleAuthenticationInfo(sysAccount.getUsername(), sysAccount.getPassword(), getName());
		} else
		{
			throw new AuthenticationException();
		}
	}
}