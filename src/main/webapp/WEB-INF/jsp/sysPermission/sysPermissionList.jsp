<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<table id="sysPermissionList_list" class="easyui-datagrid" title="账号管理"
	style="height: 400px"
	data-options="toolbar:'#sysPermissionList_toolbar',singleSelect:true,
	loadMsg: '数据正在加载,请耐心的等待...',pagination:true,pageSize: 15,url:'/ssm/sys/permission/list'
	,method:'post',pageList:[5,10,15,20,50],fit:true,striped:true,fitColumns:true,onLoadSuccess:function (data) {
                       
                        $('.sysPermissionList_change').linkbutton({text:'修改',plain:true,iconCls:'fi-pencil'});
                      $('.sysPermissionList_fenpei').linkbutton({text:'分配角色',plain:true,iconCls:'fi-results-demographics'});
                    
                    
                    }">
	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true"></th>
			<th data-options="field:'name',width:'14%',align:'center'">权限名称</th>
			<th data-options="field:'url',width:'42%',align:'center'">URL</th>
			<th data-options="field:'created',sortable:true,width:'18%',align:'center'">创建时间</th>
			<!-- 		<th data-options="field:'updater',width:'14%',align:'center'">修改人</th>
			<th data-options="field:'updated',sortable:true,width:'14%',align:'center'">修改时间</th> -->
			<th data-options="field:'a',width:'24%',align:'center',formatter:formatOper">操作</th>
		</tr>
	</thead>
</table>
<div id="sysPermissionList_toolbar">
	<a onclick="sysPermissionList_add('0');" href="javascript:void(0);"
		class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus'">新增</a>
	<a onclick="sysPermissionList_del();" href="javascript:void(0);"
		class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-x '">删除</a>
</div>
<div id="sysPermissionList_dialog"></div>
<script>
	$(function()
	{
		//dialog还没生成，后面会出bug，所以我设置了初始化然后关闭
		$('#sysPermissionList_dialog').dialog(
		{
			closed : true
		});

	});
	function formatOper(val, row, index)
	{
		var operation = '';
		//因为有多行 所以要用class
		operation += '<a href="javascript:void(0);" href="javascript:void(0);" class="sysPermissionList_change" onClick="sysPermissionList_add(\''
			+ row.id + '\')">修改</a>';
/* 		operation += '<a href="javascript:void(0);" href="javascript:void(0);" class="sysPermissionList_fenpei" onClick="sysPermissionList_fenpei(\''
			+ row.id + '\')">分配权限</a>';
 */
		return operation;
	}
	function sysPermissionList_add(id)
	{
		var title="新增";
		var iconCls = 'fi-plus';
		if(id!="0")
		{
			title="修改";
			iconCls = 'fi-pencil';
		}
		$('#sysPermissionList_dialog').dialog(
		{
			title : title,
			iconCls : iconCls,
			width : 400,
			height : 200,
			resizable : true,
			closed : false,
			cache : false,
			href : '/ssm/sys/permission/addlist?id='+id,
			buttons :
			[
			{
				text : '保存',
				iconCls : 'icon-save',
				handler : function()
				{
					$('#sysPermissionAdd_form').submit();
				}
			},
			{
				text : '关闭',
				iconCls : 'fi-x ',
				handler : function()
				{

					$('#sysPermissionList_dialog').dialog('close');
				}
			} ]
		});

		$('#sysPermissionList_dialog').dialog('center');
	}

	function sysPermissionList_del()
	{
		var row = $('#sysPermissionList_list').datagrid('getSelected');
		if (row == null || row.username == 'admin')
		{
			$.messager.alert('(⊙o⊙)…', '凑傻逼，想删我！', 'info');
		} else
		{
			$.messager.confirm('删除', '请确保没有账号拥有该角色？', function(r)
			{
				if (r)
				{
					$.ajax(
					{
						type : 'POST',
						data :
						{
							id : row.id
						},
						url : '/ssm/sys/permission/del',
						success : function(data)
						{
							$('#sysPermissionList_list').datagrid('reload');
							$.messager.show(
							{
								title : '提示',
								msg : '删除成功',
							});
						}
					})

				}
			});
		}

	}

</script>