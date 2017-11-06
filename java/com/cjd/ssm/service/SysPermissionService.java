package com.cjd.ssm.service;




import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cjd.ssm.pojo.*;
import com.cjd.ssm.mapper.SysPermissionMapper;
import com.cjd.ssm.base.*;




@Service
@Transactional(readOnly = true)
public class SysPermissionService extends CrudService<SysPermissionMapper, SysPermission>
{
	
	
}
