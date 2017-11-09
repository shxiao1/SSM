package com.cjd.ssm.service;




import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cjd.ssm.pojo.*;
import com.cjd.ssm.utils.MyUtils;
import com.cjd.ssm.utils.MyUtils;
import com.cjd.ssm.mapper.SysAccountMapper;
import com.cjd.ssm.mapper.SysAccountRoleMapper;
import com.cjd.ssm.base.*;




@Service
@Transactional(readOnly = true)
public class SysAccountService extends CrudService<SysAccountMapper, SysAccount>
{
	@Autowired
	SysAccountMapper sysAccountMapper;

	@Autowired
	SysAccountRoleMapper sysAccountRoleMapper;
	
	public SysAccount findByName(String name)
	{
		return sysAccountMapper.findByName(name);
	}
	
	public void insertAccount(SysAccount sysAccount,String type)
	{
		
		//新增、修改account
		if (type.equals("1"))
		{
			sysAccount.setId(MyUtils.uuid());
			sysAccount.setCreater(MyUtils.getSysAccount());
			sysAccount.setCreated(new Date());
			sysAccountMapper.insert(sysAccount);
		} else if(type.equals("2"))
		{
			sysAccount.setUpdater(MyUtils.getSysAccount());
			sysAccount.setUpdated(new Date());
			sysAccountMapper.updateByPrimaryKeySelective(sysAccount);
		}
		
		//新增修改account_role
		SysAccountRole sysAccountRole=new SysAccountRole();
		sysAccountRole.setAccountid(sysAccount.getId());
		if(sysAccountRoleMapper.selectAll(sysAccountRole).size()==0)
		{
			
			sysAccountRole.setId(MyUtils.uuid());
			sysAccountRole.setDelFlag("0");
			
			sysAccountRole.setroleid(sysAccount.getRoleid());
			sysAccountRole.setCreater(MyUtils.getSysAccount());
			sysAccountRole.setCreated(new Date());
			sysAccountRoleMapper.insert(sysAccountRole);
		}
		else
		{
			sysAccountRole.setroleid(sysAccount.getRoleid());
			sysAccountRole.setUpdater(sysAccount.getSysAccount());
			sysAccountRole.setUpdated(new Date());
			sysAccountRoleMapper.updateByPrimaryKeySelective(sysAccountRole);
		}
	
	}

	public String findRoles(String username)
	{
		// TODO 自动生成的方法存根
		return sysAccountMapper.findRoles(username);
	}



	public String findRole(String roleid)
	{
		// TODO 自动生成的方法存根
		return sysAccountMapper.findRole(roleid);
	}

	public String findPermissions(String roleid)
	{
		// TODO 自动生成的方法存根
		return sysAccountMapper.findPermissions(roleid);
	}
	public String findPermission(String permissionid)
	{
		// TODO 自动生成的方法存根
		return sysAccountMapper.findPermission(permissionid);
	}
	
}
