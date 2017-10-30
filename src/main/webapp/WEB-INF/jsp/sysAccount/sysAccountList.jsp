<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<table id="accountlist_list" class="easyui-datagrid" title="账号管理"
	style="height: 400px"
	data-options="toolbar:'#accountlist_toolbar',singleSelect:true,
	loadMsg: '数据正在加载,请耐心的等待...',pagination:true,pageSize: 15,url:'/ssm/sys/account/list'
	,method:'post',pageList:[5,10,15,20,50],fit:true,striped:true,fitColumns:true,onLoadSuccess:function (data) {
                       
                        $('.accountlist_change').linkbutton({text:'修改',plain:true,iconCls:'fi-pencil'});
                    }">
	<thead>
		<tr>
			<th data-options="field:'id',checkbox:true"></th>
			<th data-options="field:'username',width:'14%',align:'center'">账号</th>
			<th data-options="field:'password',width:'14%',align:'center'">密码</th>
			<th data-options="field:'roleid',width:'28%',align:'center'">拥有角色</th>
			<!-- <th data-options="field:'creater',width:'14%',align:'center'">创建人</th> -->
			<th
				data-options="field:'created',sortable:true,width:'18%',align:'center'">创建时间</th>
			<!-- 		<th data-options="field:'updater',width:'14%',align:'center'">修改人</th>
			<th data-options="field:'updated',sortable:true,width:'14%',align:'center'">修改时间</th> -->
			<th
				data-options="field:'a',width:'24%',align:'center',formatter:formatOper">操作</th>
		</tr>
	</thead>
</table>
<div id="accountlist_toolbar">
	<a onclick="accountlist_add();" href="javascript:void(0);"
		class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus'">新增</a>
	<a onclick="accountlist_del();" href="javascript:void(0);"
		class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-x '">删除</a>
</div>
<div id="accountlist_dialog"></div>
<script>
	$(function()
	{
		//dialog还没生成，后面会出bug，所以我设置了初始化然后关闭
		$('#accountlist_dialog').dialog(
		{
			closed : true
		});

	});
	function formatOper(val, row, index)
	{
		var operation = '';
		//因为有多行 所以要用class
		operation += '<a href="javascript:void(0);" href="javascript:void(0);" class="accountlist_change" onClick="accountlist_change(\''
			+ row.id + '\')">修改</a>';
	/* 	operation += '<a href="javascript:void(0);" href="javascript:void(0);" class="accountlist_fenpei" onClick="accountlist_fenpei(\''
			+ row.id + '\')">分配角色</a>'; 
			  $('.accountlist_fenpei').linkbutton({text:'分配角色',plain:true,iconCls:'fi-results-demographics'});
			*/

		return operation;
	}
	function accountlist_add()
	{
		$('#accountlist_dialog').dialog(
		{
			title : '新增',
			iconCls : 'fi-plus',
			width : 400,
			height : 200,
			resizable : true,
			closed : false,
			cache : false,
			href : '/ssm/sys/account/addlist',
			buttons :
			[
			{
				text : '保存',
				iconCls : 'icon-save',
				handler : function()
				{
					$('#accountAdd_form').submit();
				}
			},
			{
				text : '关闭',
				iconCls : 'fi-x ',
				handler : function()
				{

					$('#accountlist_dialog').dialog('close');
				}
			} ]
		});

		$('#accountlist_dialog').dialog('center');
	}

	function accountlist_del()
	{
		var row = $('#accountlist_list').datagrid('getSelected');
		console.log(row);
		if (row == null || row.username == 'admin')
		{
			$.messager.alert('(⊙o⊙)…', '凑傻逼，想删我！', 'info');
		} else
		{
			$.messager.confirm('删除', '确认要删除吗？', function(r)
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
						url : '/ssm/sys/account/del',
						success : function(data)
						{
							$('#accountlist_list').datagrid('reload');
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

	function accountlist_change(id)
	{
		/* 
		不用这个方法的原因是有个先后顺序，所以要传参才不会出BUG
		var row = $('#accountlist_list').datagrid('getSelected');
		 */
		$('#accountlist_dialog').dialog(
		{
			title : '修改',
			iconCls : 'fi-pencil',
			width : 400,
			height : 200,
			resizable : true,
			closed : false,
			cache : false,
			href : '/ssm/sys/account/addlist?id=' + id,
			buttons :
			[
			{
				text : '保存',
				iconCls : 'icon-save',
				handler : function()
				{
					$('#accountAdd_form').submit();
					$('#accountlist_dialog').dialog('close');
				}
			},
			{
				text : '关闭',
				iconCls : 'fi-x ',
				handler : function()
				{
					$('#accountlist_dialog').dialog('close');
				}
			} ]
		});

		$('#accountlist_dialog').dialog('center');
	}

	function accountlist_fenpei(id)
	{
		$('#accountlist_dialog').dialog(
			{
				title : '分配角色',
				iconCls : 'fi-results-demographics',
				width : 400,
				height : 200,
				resizable : true,
				closed : false,
				cache : false,
				href : '/ssm/sys/account/fenpeilist?id=' + id,
				buttons :
				[
				{
					text : '保存',
					iconCls : 'icon-save',
					handler : function()
					{
						$('#accountFenpei_form').submit();
						$('#accountlist_dialog').dialog('close');
					}
				},
				{
					text : '关闭',
					iconCls : 'fi-x ',
					handler : function()
					{
						$('#accountlist_dialog').dialog('close');
					}
				} ]
			});

			$('#accountlist_dialog').dialog('center');
	}
</script>