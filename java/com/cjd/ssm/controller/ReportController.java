package com.cjd.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.Json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cjd.ssm.base.MyUtils;
import com.cjd.ssm.base.Result;
import com.cjd.ssm.pojo.SysAccount;
import com.cjd.ssm.pojo.SysRole;
import com.cjd.ssm.service.SysAccountService;
import com.cjd.ssm.service.SysRoleService;

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
