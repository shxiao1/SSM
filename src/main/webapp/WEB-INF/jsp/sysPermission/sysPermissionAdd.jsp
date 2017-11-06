<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="sysPermissionAdd_form">
	<table id="sysPermissionAdd_table" style="margin: 10px">
		<tr>
			<td>名称：</td>
			<td><input name="name" 
				class="easyui-textbox easyui-validatebox"
				data-options="required:true,validType:['length[0,20]'],delay:'0'"
				style="height: 23px; border-radius: 7px"
				value="${sysPermission.name}" /></td>
		</tr>
		<tr>
			<td>URL：</td>
			<td><input id="sysPermissionAdd_combobox" name="url" class="easyui-textbox easyui-validatebox" data-options="required:true,value:'${sysPermission.url}'"></td>
		</tr>
	</table>

</form>
<script>
	$(function()
	{
		
	})

	var type = 1;
	var id = '${sysPermission.id}';

	if (id != '')
	{
		type = 2;
	}
	$('#sysPermissionAdd_form').form(
	{
		url : '/ssm/sys/permission/save',
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
	 		/* console.log(data);
			console.log(JSON.parse(data));  */
			$('#sysPermissionList_dialog').dialog('close');
			$('#sysPermissionList_list').datagrid('reload');
			$.messager.show(
			{
				title : '提示',
				msg : JSON.parse(data).msg,
			});
		}
	});
</script>