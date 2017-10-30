package com.cjd.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cjd.ssm.base.MyUtils;
import com.cjd.ssm.pojo.SysRole;
import com.cjd.ssm.service.SysRoleService;

@Controller

@RequestMapping(value = "/sys/role")
public class SysRoleController
{
	@Autowired
	SysRoleService sysRoleService;

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
		return list;
	}

	@RequestMapping(value = "/addlist")
	public String addlist(SysRole sysRole, Model model)
	{

		return "/sysRole/sysRoleAdd";
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(SysRole sysRole, Model model, String type)
	{

		//新增、删除
		if (type.equals("1"))
		{
			//感觉这里应该用静态方法做工具类的，嫌麻烦就直接这样用了
			sysRole.setCreater(MyUtils.getSysAccount());
			sysRole.setCreated(new Date());
			
			sysRoleService.insert(sysRole);
		} else if(type.equals("2"))
		{
			sysRole.setUpdater(MyUtils.getSysAccount());
			sysRole.setUpdated(new Date());
			
			sysRoleService.updateById(sysRole);

		}

		return "新增成功";
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public String del(SysRole sysRole)
	{
		sysRole.setDelFlag("1");
		sysRoleService.updateByIdSelective(sysRole);
		return "删除成功";
	}
}
