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
				data-options="validType:'/[a-zA-Z0-9]{6}/',required:true,delay:'0',validType:['length[0,20]'],
				onValidate:function(bl){
				
				if(bl)
				{
					$('#password').validatebox('validate');
				}
				
				}"
				style="height: 23px; border-radius: 7px"
				value="${sysAccount.password}" /></td>
		</tr>
	</table>

</form>
<script>
	$(function()
	{
		if('${sysAccount.username}'=='admin')	
		$('#accountAdd_username').attr('readonly',true);
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
			$('#accountlist_dialog').dialog('close');
			$('#accountlist_list').datagrid('reload');
			$.messager.show(
			{
				title : '提示',
				msg : '保存成功',
			});
		}
	});
</script>