package com.cjd.ssm.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cjd.ssm.pojo.*;
import com.cjd.ssm.mapper.SysAccountMapper;
import com.cjd.ssm.mapper.SysRoleMapper;
import com.cjd.ssm.base.*;




@Service
@Transactional(readOnly = true)
public class SysRoleService extends CrudService<SysRoleMapper, SysRole>
{
	@Autowired
	SysRoleMapper sysRoleMapper;
	
	
}
