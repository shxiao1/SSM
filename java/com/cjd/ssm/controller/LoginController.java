package com.cjd.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.cjd.ssm.base.Result;

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
	@ResponseBody
	public String login2(String username, String password,HttpServletRequest request)
	{
		logger.info("-------------shiro验证登录-------------");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject user = SecurityUtils.getSubject();
        try {
        	user.login(token);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        HttpSession session = request.getSession();
        session.setAttribute("user", username);
		return "index";
	}
//登出
	@RequestMapping(value = "/logout")
	@ResponseBody
	public Result logout() {  
		Result result=new Result();
	    Subject subject = SecurityUtils.getSubject();  
	    if (subject.isAuthenticated()) {  
	        subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存  
	        logger.debug("用户退出登录");  
	        result.setMsg("退出成功");
	        return result;
	    }
	    result.setMsg("退出失败");
        return result;
	}  

	@RequestMapping(value = "/home")
	public String home()
	{
		return "home";
	}
	
	@RequestMapping(value = "/index")
	public String index()
	{
		System.out.println("asd");
		return "index";
	}
}
