<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<table id="sysAccountlist_list" class="easyui-datagrid" title="账号管理"
	style="height: 400px"
	data-options="toolbar:'#sysAccountlist_toolbar',singleSelect:true,
	loadMsg: '数据正在加载,请耐心的等待...',pagination:true,pageSize: 15,url:'/ssm/sys/account/list'
	,method:'post',pageList:[5,10,15,20,50],fit:true,striped:true,fitColumns:true,onLoadSuccess:function (data) {
                       
                        $('.sysAccountlist_change').linkbutton({text:'修改',plain:true,iconCls:'fi-pencil'});
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
<div id="sysAccountlist_toolbar">
	<a onclick="sysAccountlist_add('0');" href="javascript:void(0);"
		class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus'">新增</a>
	<a onclick="sysAccountlist_del();" href="javascript:void(0);"
		class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-x '">删除</a>
</div>
<div id="sysAccountlist_dialog"></div>
<script>
	$(function()
	{
		//dialog还没生成，后面会出bug，所以我设置了初始化然后关闭
		$('#sysAccountlist_dialog').dialog(
		{
			closed : true
		});

	});
	function formatOper(val, row, index)
	{
		var operation = '';
		//因为有多行 所以要用class
		operation += '<a href="javascript:void(0);" href="javascript:void(0);" class="sysAccountlist_change" onClick="sysAccountlist_add(\''
			+ row.id + '\')">修改</a>';
	/* 	operation += '<a href="javascript:void(0);" href="javascript:void(0);" class="sysAccountlist_fenpei" onClick="sysAccountlist_fenpei(\''
			+ row.id + '\')">分配角色</a>'; 
			  $('.sysAccountlist_fenpei').linkbutton({text:'分配角色',plain:true,iconCls:'fi-results-demographics'});
			*/

		return operation;
	}
	function sysAccountlist_add(id)
	{
		var title="新增";
		var iconCls = 'fi-plus';
		if(id!="0")
		{
			title="修改";
			iconCls = 'fi-pencil';
		}
		$('#sysAccountlist_dialog').dialog(
		{
			title : title,
			iconCls :iconCls,
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
					$('#sysAccountAdd_form').submit();
				}
			},
			{
				text : '关闭',
				iconCls : 'fi-x ',
				handler : function()
				{

					$('#sysAccountlist_dialog').dialog('close');
				}
			} ]
		});

		$('#sysAccountlist_dialog').dialog('center');
	}

	function sysAccountlist_del()
	{
		var row = $('#sysAccountlist_list').datagrid('getSelected');
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
							$('#sysAccountlist_list').datagrid('reload');
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

/* 	function sysAccountlist_change(id)
	{
		$('#sysAccountlist_dialog').dialog(
		{
			title : '修改',
			iconCls : 'fi-pencil',
			width : 400,
			height : 200,
			resizable : true,
			closed : false,
			cache : false,
			href : '/ssm/sys/sysAccount/addlist?id=' + id,
			buttons :
			[
			{
				text : '保存',
				iconCls : 'icon-save',
				handler : function()
				{
					$('#sysAccountAdd_form').submit();
					$('#sysAccountlist_dialog').dialog('close');
				}
			},
			{
				text : '关闭',
				iconCls : 'fi-x ',
				handler : function()
				{
					$('#sysAccountlist_dialog').dialog('close');
				}
			} ]
		});

		$('#sysAccountlist_dialog').dialog('center');
	}
 */
	function sysAccountlist_fenpei(id)
	{
		$('#sysAccountlist_dialog').dialog(
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
						$('#sysAccountFenpei_form').submit();
						$('#sysAccountlist_dialog').dialog('close');
					}
				},
				{
					text : '关闭',
					iconCls : 'fi-x ',
					handler : function()
					{
						$('#sysAccountlist_dialog').dialog('close');
					}
				} ]
			});

			$('#sysAccountlist_dialog').dialog('center');
	}
</script>