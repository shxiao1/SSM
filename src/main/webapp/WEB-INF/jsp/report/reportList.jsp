<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/../WEB-INF/head/head2.jsp"%>
<table id="reportlist_list" class="easyui-datagrid" title="业务提交"
	style="height: 400px"
	data-options="toolbar:'#reportlist_toolbar',singleSelect:true,
	loadMsg: '数据正在加载,请耐心的等待...',url:'/ssm/sys/account/list'
	,method:'post',fit:true,striped:true,fitColumns:true,onLoadSuccess:function (data) {
                        $('.reportlist_change').linkbutton({text:'修改',plain:true,iconCls:'fi-pencil'});
                    }">
	<thead>
		<tr>
	
			<th data-options="field:'id',checkbox:true"></th>
			<th data-options="field:'a',width:'14%',align:'center'">借款人</th>
			<th data-options="field:'a',width:'14%',align:'center'">逾期期数</th>
			<th data-options="field:'a',width:'14%',align:'center'">应催收总额</th>
			<th
				data-options="field:'a',width:'14%',align:'center'">本次摧毁</th>
			<th
				data-options="field:'a',width:'14%',align:'center'">所属公司</th>
			<th
				data-options="field:'a',width:'14%',align:'center'">催收员</th>
			<th
				data-options="field:'a',width:'14%',align:'center',formatter:formatOper">操作</th>
		</tr>
	</thead>
</table>
<div id="reportlist_toolbar">
	<a onclick="reportlist_add('0');" href="javascript:void(0);"
		class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus'">新增</a>
	<a onclick="reportlist_del();" href="javascript:void(0);"
		class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-x '">删除</a>
</div>
<div id="reportlist_dialog"></div>
<script>
	$(function() {
		//dialog还没生成，后面会出bug，所以我设置了初始化然后关闭
		$('#reportlist_dialog').dialog({
			closed : true
		});

	});
	function formatOper(val, row, index) {
		console.log(row);
		var operation = '';
		//因为有多行 所以要用class
		operation += '<a href="javascript:void(0);" href="javascript:void(0);" class="reportlist_change" onClick="reportlist_add(\''
				+ row.id + '\')">修改</a>';
		return operation;
	}
	function reportlist_add(id) {
		var title = "新增";
		var iconCls = 'fi-plus';
		if (id != "0") {
			title = "修改";
			iconCls = 'fi-pencil';
		}
		$('#reportlist_dialog').dialog({
			title : title,
			iconCls : iconCls,
			width : 400,
			height : 200,
			resizable : true,
			closed : false,
			cache : false,
			href : '/ssm/sys/account/addlist?id=' + id,
			buttons : [ {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					$('#reportAdd_form').submit();
				}
			}, {
				text : '关闭',
				iconCls : 'fi-x ',
				handler : function() {

					$('#reportlist_dialog').dialog('close');
				}
			} ]
		});

		$('#reportlist_dialog').dialog('center');
	}

	function reportlist_del() {
		var row = $('#reportlist_list').datagrid('getSelected');
		console.log(row);
		if (row == null ) {
			$.messager.alert('提示', '请选择删除的行！', 'info');
		} else {
			$.messager.confirm('删除', '确认要删除吗？', function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						data : {
							id : row.id
						},
						url : '/ssm/sys/account/del',
						success : function(data) {
							$('#reportlist_list').datagrid('reload');
							$.messager.show({
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