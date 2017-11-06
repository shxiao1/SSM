
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
<!-- <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> -->


<link id="easyuiTheme" rel="stylesheet" type="text/css" href="/ssm/js/easyui/themes/bootstrap/easyui.css" />
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="/ssm/js/easyui/themes/icon.css" />
<script type="text/javascript" src="/ssm/js/easyui/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/ssm/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/ssm/js/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>

<link rel="stylesheet" type="text/css" href="/ssm/css/foundation-icons/foundation-icons.css" />

<base href="<%=basePath%>">
