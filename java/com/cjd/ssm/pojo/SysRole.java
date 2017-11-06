/*
* SysRole.java
* Copyright(C) 2009-2016 北京盘古世纪科技发展有限公司
* All right Reserved
* 2017-10-26 created
*/
package com.cjd.ssm.pojo;

import java.io.Serializable;

import com.cjd.ssm.base.BaseEntity;

public class SysRole extends BaseEntity<SysRole> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String rolename;
    
    private String url;

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getRolename()
	{
		return rolename;
	}

	public void setRolename(String rolename)
	{
		this.rolename = rolename;
	}

}