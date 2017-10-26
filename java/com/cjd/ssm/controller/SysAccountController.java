package com.cjd.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cjd.ssm.pojo.SysAccount;
import com.cjd.ssm.service.SysAccountService;

@Controller

@RequestMapping(value = "/sys/account")
public class SysAccountController
{
	@Autowired
	SysAccountService sysAccountService;

	@ModelAttribute("sysAccount")
	public SysAccount get(@RequestParam(required = false) String id)
	{
		if (id != null && id != "")
		{
			return sysAccountService.findById(id);
		} else
		{
			return new SysAccount();
		}
	}

	@RequestMapping(value = "/list")
	public String forList()
	{
		return "/sysAccount/sysAccountList";
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public List<SysAccount> list(SysAccount sysAccount)
	{
		
		List<SysAccount> list = new ArrayList<SysAccount>();
		list = sysAccountService.findAll(sysAccount);
		return list;
	}

	@RequestMapping(value = "/addlist")
	public String addlist(SysAccount sysAccount, Model model)
	{

		return "/sysAccount/sysAccountAdd";
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(SysAccount sysAccount, Model model, String type)
	{

		model.addAttribute(sysAccount);

		//新增、删除
		if (type.equals("1"))
		{
			//感觉这里应该用静态方法做工具类的，嫌麻烦就直接这样用了
			sysAccount.setCreater(sysAccount.getSysAccount());
			sysAccount.setCreated(new Date());
			
			sysAccountService.insert(sysAccount);
		} else if(type.equals("2"))
		{
			sysAccount.setUpdater(sysAccount.getSysAccount());
			sysAccount.setUpdated(new Date());
			
			sysAccountService.updateById(sysAccount);

		}

		return "新增成功";
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public String del(SysAccount sysAccount)
	{
		sysAccountService.deleteById(sysAccount.getId());
		return "删除成功";
	}
}
