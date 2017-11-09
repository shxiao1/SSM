package com.cjd.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cjd.ssm.base.Result;
import com.cjd.ssm.pojo.SysAccount;
import com.cjd.ssm.pojo.SysPermission;
import com.cjd.ssm.pojo.SysRole;
import com.cjd.ssm.service.SysPermissionService;
import com.cjd.ssm.service.SysRoleService;

@Controller

@RequestMapping(value = "/sys/role")
public class SysRoleController
{
	@Autowired
	SysRoleService sysRoleService;

	@Autowired
	SysPermissionService sysPermissionService;

	@ModelAttribute("sysRole")
	public SysRole get(@RequestParam(required = false) String id)
	{
		if (id != null && id != "")
		{
			return sysRoleService.findById(id);
		} else
		{
			return new SysRole();
		}
	}

	@RequestMapping(value = "/list")
	public String forList()
	{
		return "/sysRole/sysRoleList";
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public List<SysRole> list(SysRole sysRole)
	{

		List<SysRole> list = new ArrayList<SysRole>();
		list = sysRoleService.findAll(sysRole);

		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).getUrl() != null && list.get(i).getUrl().length() > 0)
			{
				String url = "";
				String[] urls = list.get(i).getUrl().split(",");
				for (int j = 0; j < urls.length; j++)
				{
					url += sysPermissionService.findById(urls[j]).getName() + ",";
				}
				url = url.substring(0, url.length() - 1);
				list.get(i).setUrl(url);
			}
		}

		return list;
	}

	@RequestMapping(value = "/addlist")
	public String addlist(SysRole sysRole, Model model)
	{

		return "/sysRole/sysRoleAdd";
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public Result save(SysRole sysRole, Model model, String type)
	{
		Result result = new Result();
		SysRole sysRole1 = new SysRole();
		sysRole1.setRolename(sysRole.getRolename());

		if (type.equals("1"))
		{
			if (sysRoleService.findAll(sysRole1) != null && sysRoleService.findAll(sysRole1).size() > 0)
			{
				result.setMsg("已存在该账号");
				return result;
			}
		}

		sysRoleService.insertRole(sysRole, type);
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
	public String del(SysRole sysRole)
	{
		sysRole.setDelFlag("1");
		sysRoleService.updateByIdSelective(sysRole);
		return "删除成功";
	}

	@RequestMapping(value = "/combobox", method = RequestMethod.POST)
	@ResponseBody
	public List<SysPermission> combobox(SysAccount sysAccount, Model model)
	{

		return sysPermissionService.findAll(new SysPermission());
	}

}
