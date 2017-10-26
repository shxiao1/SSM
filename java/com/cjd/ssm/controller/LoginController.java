package com.cjd.ssm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@RequestMapping(value = "/sys")
public class LoginController
{
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login()
	{
		//登录成功后还是访问的这个
		  if (SecurityUtils.getSubject().isAuthenticated()) {
	            return "index";
	        }
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login2(String username, String password)
	{
		logger.info("-------------shiro验证登录-------------");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject user = SecurityUtils.getSubject();
        try {
        	user.login(token);
        } catch (Exception e) {
            e.printStackTrace();
        } 
			
		return "index";
	}

	
	
	@RequestMapping(value = "/home")
	public String home()
	{
		return "home";
	}
	
	@RequestMapping(value = "/index")
	public String index()
	{
		return "index";
	}
}
