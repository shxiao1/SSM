package com.cjd.ssm.service;




import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cjd.ssm.pojo.*;
import com.cjd.ssm.utils.IdGen;
import com.cjd.ssm.mapper.SysRoleMapper;
import com.cjd.ssm.mapper.SysRolePermissionMapper;
import com.cjd.ssm.base.*;




@Service
@Transactional(readOnly = true)
public class SysRoleService extends CrudService<SysRoleMapper, SysRole>
{
	@Autowired
	SysRoleMapper sysRoleMapper;
	
	@Autowired
	SysRolePermissionMapper sysRolePermissionMapper;

	public void insertRole(SysRole sysRole, String type)
	{
		//新增、修改account
		if (type.equals("1"))
		{
			sysRole.setId(IdGen.uuid());
			sysRole.setCreater(MyUtils.getSysAccount());
			sysRole.setCreated(new Date());
			sysRoleMapper.insert(sysRole);
		} else if(type.equals("2"))
		{
			sysRole.setUpdater(MyUtils.getSysAccount());
			sysRole.setUpdated(new Date());
			sysRoleMapper.updateByPrimaryKeySelective(sysRole);
		}
		
		//新增修改role_permission
				SysRolePermission sysRolePermission=new SysRolePermission();
				sysRolePermission.setRoleid((sysRole.getId()));
				List<SysRolePermission> list=sysRolePermissionMapper.selectAll(sysRolePermission);
				if(list.size()==0)
				{
					
					sysRolePermission.setId(IdGen.uuid());
					sysRolePermission.setDelFlag("0");
					sysRolePermission.setPermissionid(sysRole.getUrl());
					sysRolePermission.setCreater(MyUtils.getSysAccount());
					sysRolePermission.setCreated(new Date());
					sysRolePermissionMapper.insert(sysRolePermission);
				}
				else
				{
					sysRolePermission.setPermissionid(sysRole.getUrl());
					sysRolePermission.setUpdater(MyUtils.getSysAccount());
					sysRolePermission.setUpdated(new Date());
					sysRolePermissionMapper.updateByPrimaryKeySelective(sysRolePermission);
				}
	}
	
	
}
