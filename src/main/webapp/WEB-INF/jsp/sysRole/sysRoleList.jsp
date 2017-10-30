<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<table id="rolelist_list" class="easyui-datagrid" title="账号管理"
	style="height: 400px"
	data-options="toolbar:'#rolelist_toolbar',singleSelect:true,
	loadMsg: '数据正在加载,请耐心的等待...',pagination:true,pageSize: 15,url:'/ssm/sys/role/list'
	,method:'post',pageList:[5,10,15,20,50],fit:true,striped:true,fitColumns:true,onLoadSuccess:function (data) {
                       
                        $('.rolelist_change').linkbutton({text:'修改',plain:true,iconCls:'fi-pencil'});
                      $('.rolelist_fenpei').linkbutton({text:'分配角色',plain:true,iconCls:'fi-results-demographics'});
                    
                    
                    }">
	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true"></th>
			<th data-options="field:'rolename',width:'14%',align:'center'">角色名称</th>
			<th data-options="field:'permissionname',width:'42%',align:'center'">拥有权限</th>
			<th data-options="field:'created',sortable:true,width:'18%',align:'center'">创建时间</th>
			<!-- 		<th data-options="field:'updater',width:'14%',align:'center'">修改人</th>
			<th data-options="field:'updated',sortable:true,width:'14%',align:'center'">修改时间</th> -->
			<th data-options="field:'a',width:'24%',align:'center',formatter:formatOper">操作</th>
		</tr>
	</thead>
</table>
<div id="rolelist_toolbar">
	<a onclick="rolelist_add();" href="javascript:void(0);"
		class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus'">新增</a>
	<a onclick="rolelist_del();" href="javascript:void(0);"
		class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-x '">删除</a>
</div>
<div id="rolelist_dialog"></div>
<script>
	$(function()
	{
		//dialog还没生成，后面会出bug，所以我设置了初始化然后关闭
		$('#rolelist_dialog').dialog(
		{
			closed : true
		});

	});
	function formatOper(val, row, index)
	{
		var operation = '';
		//因为有多行 所以要用class
		operation += '<a href="javascript:void(0);" href="javascript:void(0);" class="rolelist_change" onClick="rolelist_change(\''
			+ row.id + '\')">修改</a>';
/* 		operation += '<a href="javascript:void(0);" href="javascript:void(0);" class="rolelist_fenpei" onClick="rolelist_fenpei(\''
			+ row.id + '\')">分配权限</a>';
 */
		return operation;
	}
	function rolelist_add()
	{
		$('#rolelist_dialog').dialog(
		{
			title : '新增',
			iconCls : 'fi-plus',
			width : 400,
			height : 200,
			resizable : true,
			closed : false,
			cache : false,
			href : '/ssm/sys/role/addlist',
			buttons :
			[
			{
				text : '保存',
				iconCls : 'icon-save',
				handler : function()
				{
					$('#roleAdd_form').submit();
				}
			},
			{
				text : '关闭',
				iconCls : 'fi-x ',
				handler : function()
				{

					$('#rolelist_dialog').dialog('close');
				}
			} ]
		});

		$('#rolelist_dialog').dialog('center');
	}

	function rolelist_del()
	{
		var row = $('#rolelist_list').datagrid('getSelected');
		console.log(row);
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
						url : '/ssm/sys/role/del',
						success : function(data)
						{
							$('#rolelist_list').datagrid('reload');
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

	function rolelist_change(id)
	{
		/* 
		不用这个方法的原因是有个先后顺序，所以要传参才不会出BUG
		var row = $('#rolelist_list').datagrid('getSelected');
		 */$('#rolelist_dialog').dialog(
		{
			title : '修改',
			iconCls : 'fi-pencil',
			width : 400,
			height : 200,
			resizable : true,
			closed : false,
			cache : false,
			href : '/ssm/sys/role/addlist?id=' + id,
			buttons :
			[
			{
				text : '保存',
				iconCls : 'icon-save',
				handler : function()
				{
					$('#roleAdd_form').submit();
					$('#rolelist_dialog').dialog('close');
				}
			},
			{
				text : '关闭',
				iconCls : 'fi-x ',
				handler : function()
				{
					$('#rolelist_dialog').dialog('close');
				}
			} ]
		});

		$('#rolelist_dialog').dialog('center');
	}
</script>