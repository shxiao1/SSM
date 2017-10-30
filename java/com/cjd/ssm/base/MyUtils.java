package com.cjd.ssm.base;

import org.apache.shiro.SecurityUtils;

public class MyUtils
{
	
	
	
	public static String getSysAccount()
	{
		return (String) SecurityUtils.getSubject().getPrincipal();
	}
}
