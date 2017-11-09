package com.cjd.ssm.utils;

import java.util.UUID;

import org.apache.shiro.SecurityUtils;

public class MyUtils
{
	public static String getSysAccount()
	{
		return (String) SecurityUtils.getSubject().getPrincipal();
	}
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
