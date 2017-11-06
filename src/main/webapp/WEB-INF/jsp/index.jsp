<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 -->
<%@include file="/WEB-INF/head/head.jsp"%>

<title>首页</title>

<style type="text/css">
/* 
设置所有fi开头的icon样式
 */
[class*="fi-"] {
	color: #5f9ea0;
	font-size: 18px;
	background-image: none !important;
	overflow: visible !important;
}
</style>
</head>

<body class="easyui-layout">
	<div data-options="region:'north'" style="height:100px;">
		<div class="logo">
			<a><img style="width: 100%; height: 100%;"
				src="/ssm/images/upback.png"></a>
		</div>
	</div>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width: 250px;">
		<div class="easyui-accordion" style="border: left"
			data-options="fit:true,border:false">
			<!-- 		<div title="首页" iconCls="fi-home">
				<ul class="easyui-tree tree" style="border: left"
					data-options="fit:true,border:false">
					<li iconCls="icon-man">测试1</li>
					<li iconCls="icon-man">测试2</li>
					<li iconCls="icon-man">测试3</li>
				</ul>
			</div> -->

			<div title="系统管理" iconCls="fi-layout">
				<ul class="easyui-tree tree" style="border: left"
					data-options="fit:true,border:false,onClick:index_addtab">
					<li iconCls="fi-torso-business" data-options="url:'/ssm/sys/account/list'">账号管理</li>
					<li iconCls="fi-database" data-options="url:'/ssm/sys/role/list'" >角色管理</li>
					<li iconCls="fi-results-demographics" data-options="url:'/ssm/sys/permission/list'" >权限管理</li>
				</ul>
			</div>

		</div>

	</div>
	<div data-options="region:'center'" style="overflow: hidden;">
		<div id="index_tabs" style="overflow: hidden; fit: true">
			<div title="首页"
				data-options="iconCls:'fi-home',border:false,fit:true"
				style="overflow: hidden;"></div>
				
		</div>
	</div>


	<div data-options="region:'south'" style="height: 30px;">
		<div
			style="line-height: 30px; overflow: hidden; text-align: center; background-color: rgb(238, 238, 238); width: 100%; height: 28px;">池剑迪
			联系方式:15858508283</div>
	</div>

</body>

<script type="text/javascript">
	//点击菜单项添加选项卡
	function index_addtab(node)
	{
		//获取选项卡，如果已经存在就选择并刷新，如果没存在就添加
		var t = $('#index_tabs');
		if (t.tabs('exists', node.text))
		{
			t.tabs('select', node.text);
			refreshTab();
		} else
		{
			t.tabs('add',
			{
				title : node.text,
				border : false,
				closable : true,
				fit : true,
				href:node.url,   
				iconCls : node.iconCls
			});
		}

	}

	//选项卡初始化
	var index_tabs = $('#index_tabs').tabs(
		{
			fit : true,
			border : false,
			tools :
			[
				{
					//工具条-主页 索引为0的
					iconCls : 'fi-home ',
					handler : function()
					{
						index_tabs.tabs('select', 0);
					}
				},
				{
					//获取工具条选择的对象，刷新
					iconCls : 'fi-loop',
					handler : function()
					{
						refreshTab();
					}
				},
				{
					//选项卡-关闭
					iconCls : 'fi-x',
					handler : function()
					{
						var index = index_tabs.tabs('getTabIndex', index_tabs
							.tabs('getSelected'));
						var tab = index_tabs.tabs('getTab', index);
						console.log(tab);
						if (tab.panel('options').closable)
						{
							index_tabs.tabs('close', index);
						}
					}
				} ],
			onSelect : function(title)
			{
			}
		});
	
	//首页初始化，获取索引为0的创建页面
	var tab = index_tabs.tabs('getTab', 0);
	$('#index_tabs')
			.tabs(
					'update',
					{
						tab : tab,
						options : {
							content : '<iframe name="indextab" scrolling="auto" src="/ssm/sys/home" frameborder="0" style="width:100%;height:100%;"></iframe>',
							closable : false,
							selected : true
						}
					});
	
	
	//刷新选项卡
	function refreshTab()
	{
		var index = index_tabs.tabs('getTabIndex', index_tabs
			.tabs('getSelected'));
		var tab = index_tabs.tabs('getTab', index);
		var options = tab.panel('options');
		if (options.content)
		{
			index_tabs.tabs('update',
			{
				tab : tab,
				options :
				{
					content : options.content
				}
			});
		} else
		{
			tab.panel('refresh', options.href);
		}
	}
</script>
</html>
