package com.cjd.ssm.controller;


import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller

@RequestMapping(value = "/report")
public class ReportController
{
	
	@RequestMapping(value = "/list")
	public String forList()
	{
		return "/report/reportList";
	}

	
	
	
}
