package com.cjd.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.Json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cjd.ssm.base.Result;
import com.cjd.ssm.pojo.SysAccount;
import com.cjd.ssm.pojo.SysRole;
import com.cjd.ssm.service.SysAccountService;
import com.cjd.ssm.service.SysRoleService;

@Controller

@RequestMapping(value = "/sys/account")
public class SysAccountController
{
	@Autowired
	SysAccountService sysAccountService;

	@Autowired
	SysRoleService sysRoleService;

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
		// 根据rowid获取rowname
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).getRoleid() != null && list.get(i).getRoleid().length() > 0)
			{
				String rolename = "";
				String[] rowid = list.get(i).getRoleid().split(",");
				for (int j = 0; j < rowid.length; j++)
				{
					rolename += sysRoleService.findById(rowid[j]).getRolename() + ",";
				}
				rolename = rolename.substring(0, rolename.length() - 1);
				list.get(i).setRoleid(rolename);
			}
		}

		return list;
	}

	@RequestMapping(value = "/addlist")
	public String addlist(SysAccount sysAccount, Model model)
	{
		/*
		 * String rolename = ""; String[] rowid =
		 * sysAccount.getRoleid().split(","); for (int j = 0; j < rowid.length;
		 * j++) { rolename += sysRoleService.findById(rowid[j]).getRolename() +
		 * ","; } rolename = rolename.substring(0, rolename.length() - 1);
		 * sysAccount.setRoleid(rolename);
		 */

		return "/sysAccount/sysAccountAdd";
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public Result save(SysAccount sysAccount, Model model, String type)
	{
		Result result = new Result();
		/*
		 * //新增、修改 if (type.equals("1")) {
		 * sysAccount.setCreater(MyUtils.getSysAccount());
		 * sysAccount.setCreated(new Date());
		 * 
		 * sysAccountService.insert(sysAccount); } else if(type.equals("2")) {
		 * sysAccount.setUpdater(sysAccount.getSysAccount());
		 * sysAccount.setUpdated(new Date()); }
		 */
		SysAccount sysAccount1 = new SysAccount();
		sysAccount1.setUsername(sysAccount.getUsername());
		if (type.equals("1"))
		{
			if (sysAccountService.findAll(sysAccount1) != null && sysAccountService.findAll(sysAccount1).size() > 0)
			{
				result.setMsg("已存在该账号");
				return result;
			}
		}

		sysAccountService.insertAccount(sysAccount, type);

		if (type.equals("1"))
		{
			result.setMsg("新增成功");
		}else
		{
			result.setMsg("修改成功");
		}
		return result;
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public String del(SysAccount sysAccount)
	{

		sysAccount.setDelFlag("1");
		sysAccountService.updateByIdSelective(sysAccount);
		return "删除成功";
	}

	@RequestMapping(value = "/fenpeilist")
	public String fenpeilist(SysAccount sysAccount, Model model)
	{

		return "/sysAccount/sysAccountFenpei";
	}

	@RequestMapping(value = "/combobox", method = RequestMethod.POST)
	@ResponseBody
	public List<SysRole> combobox(SysAccount sysAccount, Model model)
	{

		return sysRoleService.findAll(new SysRole());
	}
}
