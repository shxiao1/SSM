/*
* SysPerson.java
* Copyright(C) 2009-2016 北京盘古世纪科技发展有限公司
* All right Reserved
* 2017-10-18 created
*/
package com.cjd.ssm.pojo;

import java.io.Serializable;

public class SysPerson implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String username;

	private String password;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username == null ? null : username.trim();
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password == null ? null : password.trim();
	}
}