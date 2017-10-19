<%-- <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

标签
<%@ page language="java" pageEncoding="UTF-8"%>
 --%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
<script type="text/javascript" src="/ssm/js/jquery.js" charset="utf-8"></script>


  <link id="easyuiTheme" rel="stylesheet" type="text/css" href="/ssm/js/easyui/themes/bootstrap/easyui.css" />
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="/ssm/js/easyui/themes/icon.css" />
<script type="text/javascript" src="/ssm/js/easyui/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/ssm/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/ssm/js/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<script type="text/javascript" src="/ssm/js/extJs.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="/ssm/js/style/css/dreamlu.css?v=10" />
<link rel="stylesheet" type="text/css" href="/ssm/js/style/css/default.css" />

<base href="<%=basePath%>">
