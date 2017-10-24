package com.cjd.ssm.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;


@Controller


@RequestMapping(value = "/sys/account")
public class SysAccountController  
{
	
	
	@RequestMapping(value = "/list")
	public String list()
	{
		return "/account/accountlist";
	}
	
	
}
