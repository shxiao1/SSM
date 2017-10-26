package com.cjd.ssm.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cjd.ssm.pojo.*;
import com.cjd.ssm.mapper.SysAccountMapper;
import com.cjd.ssm.base.*;




@Service
@Transactional(readOnly = true)
public class SysAccountService extends CrudService<SysAccountMapper, SysAccount>
{
	@Autowired
	SysAccountMapper sysAccountMapper;
	
	public SysAccount findByName(String name)
	{
		return sysAccountMapper.findByName(name);
	}
	
}
