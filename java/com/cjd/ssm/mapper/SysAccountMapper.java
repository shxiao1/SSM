/*
* SysAccountMapper.java
* Copyright(C) 2009-2016 北京盘古世纪科技发展有限公司
* All right Reserved
* 2017-10-26 created
*/
package com.cjd.ssm.mapper;

import java.util.Set;

import com.cjd.ssm.base.CrudDao;
import com.cjd.ssm.pojo.SysAccount;

public interface SysAccountMapper extends CrudDao<com.cjd.ssm.pojo.SysAccount> {

	SysAccount findByName(String name);

	String findRoles(String username);

	String findPermissions(String role);

	String findRole(String roleid);

	String findPermission(String permissionid);
}