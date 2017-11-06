package com.cjd.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cjd.ssm.base.MyUtils;
import com.cjd.ssm.base.Result;
import com.cjd.ssm.pojo.SysPermission;
import com.cjd.ssm.pojo.SysRole;
import com.cjd.ssm.service.SysPermissionService;
import com.cjd.ssm.utils.IdGen;

@Controller
@RequestMapping(value = "/sys/permission")
public class SysPermissionController
{
	@Autowired
	SysPermissionService sysPermissionService;

	@ModelAttribute("sysPermission")
	public SysPermission get(@RequestParam(required = false) String id)
	{
		if (id != null && id != "")
		{
			return sysPermissionService.findById(id);
		} else
		{
			return new SysPermission();
		}
	}

	@RequestMapping(value = "/list")
	public String forList()
	{
		return "/sysPermission/sysPermissionList";
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public List<SysPermission> list(SysPermission sysPermission)
	{

		List<SysPermission> list = new ArrayList<SysPermission>();
		list = sysPermissionService.findAll(sysPermission);

		return list;
	}

	@RequestMapping(value = "/addlist")
	public String addlist(SysPermission sysPermission, Model model)
	{

		return "/sysPermission/sysPermissionAdd";
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public Result save(SysPermission sysPermission, Model model, String type)
	{
		Result result = new Result();
		
		//新增、修改account
		if (type.equals("1"))
		{
			sysPermission.setId(IdGen.uuid());
			sysPermission.setCreater(MyUtils.getSysAccount());
			sysPermission.setCreated(new Date());
			result.setMsg("新增成功");
			sysPermissionService.insert(sysPermission);
		} else if(type.equals("2"))
		{
			sysPermission.setUpdater(MyUtils.getSysAccount());
			sysPermission.setUpdated(new Date());
			result.setMsg("修改成功");
			sysPermissionService.updateByIdSelective((sysPermission));
		}
		return result;
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public String del(SysPermission sysPermission)
	{
		sysPermission.setDelFlag("1");
		sysPermissionService.updateByIdSelective(sysPermission);
		return "删除成功";
	}

}
