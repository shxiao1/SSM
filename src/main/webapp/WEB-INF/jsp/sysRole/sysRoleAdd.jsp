<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="sysRoleAdd_form">
	<table id="sysRoleAdd_table" style="margin: 10px">
		<tr>
			<td>角色：</td>
			<td><input name="rolename" 
				class="easyui-textbox easyui-validatebox"
				data-options="required:true,validType:['length[0,20]'],delay:'0'"
				style="height: 23px; border-radius: 7px"
				value="${sysRole.rolename}" /></td>
		</tr>
		<tr>
			<td>权限：</td>
			<td><input id="sysRoleAdd_combobox" name="url"  data-options="value:'${sysRole.url}'"></td>
		</tr>
	</table>

</form>
<script>
	$(function()
	{
		$('#sysRoleAdd_combobox').combobox({  
			panelHeight:'auto',
		    required:true,    
		    multiple:true,
		    editable:false,
		    url:'/ssm/sys/role/combobox',
		    valueField:'id',
		    textField:'name',
		});  
		
		
		
	})

	var type = 1;
	var id = '${sysRole.id}';

	if (id != '')
	{
		type = 2;
	}
	$('#sysRoleAdd_form').form(
	{
		url : '/ssm/sys/role/save',
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
			$('#sysRoleList_dialog').dialog('close');
			$('#sysRoleList_list').datagrid('reload');
			$.messager.show(
			{
				title : '提示',
				msg : JSON.parse(data).msg,
			});
		}
	});
</script>