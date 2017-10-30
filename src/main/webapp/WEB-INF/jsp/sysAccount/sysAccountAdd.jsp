<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="accountAdd_form">
	<table id="accountAdd_table" style="margin: 10px">
		<tr>
			<td>账号：</td>
			<td><input name="username" id="accountAdd_username"
				class="easyui-textbox easyui-validatebox"
				data-options="required:true,validType:['length[0,20]'],delay:'0'"
				style="height: 23px; border-radius: 7px"
				value="${sysAccount.username}" /></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input name="password"
				class="easyui-validatebox easyui-passwordbox" onpaste="return false"
				ondragenter="return false"
				data-options="required:true,delay:'0',validType:['length[0,20]']"
				style="height: 23px; border-radius: 7px"
				value="${sysAccount.password}" /></td>
		</tr>
		<tr>
			<td>角色：</td>
			<td><input id="sysAccountAdd_combobox" name="roleid" ></td>
		</tr>
	</table>

</form>
<script>
	$(function()
	{
		//防止admin被修改
		if ('${sysAccount.username}' == 'admin')
			$('#accountAdd_username').attr('readonly', true);
		
		$('#sysAccountAdd_combobox').combobox({  
			panelHeight:'auto',
		    required:true,    
		    multiple:true,
		    editable:false,
		    url:'/ssm/sys/account/combobox',
		    valueField:'id',
		    textField:'rolename',
		    
		});  
		
		
		
	})

	var type = 1;
	var id = '${sysAccount.id}';

	if (id != '')
	{
		type = 2;
	}

	$('#accountAdd_form').form(
	{
		url : '/ssm/sys/account/save',
		queryParams :
		{
			id : id,
			type : type
		},
		onSubmit : function()
		{
		},
		success : function(data)
		{
		/* 	console.log(data);
			console.log(JSON.parse(data)); */
			$('#accountlist_dialog').dialog('close');
			$('#accountlist_list').datagrid('reload');
			$.messager.show(
			{
				title : '提示',
				msg : JSON.parse(data).msg,
			});
		}
	});
</script>