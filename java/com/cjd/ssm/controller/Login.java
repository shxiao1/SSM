package com.cjd.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sys")
public class Login
{
	
	@RequestMapping(value = "/login")
	public String login()
	{
		return "index";
	}
	
}
