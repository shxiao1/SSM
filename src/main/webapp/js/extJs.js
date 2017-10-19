var sy = $.extend({}, sy);/* 定义全局对象，类似于命名空间或包的作用 */
/**
 * 
 * @requires jQuery
 * 
 * 当页面加载完毕关闭加载进度
 * **/
$(window).load(function(){
    $("#loading").fadeOut();
});

/**
 * 使panel和datagrid在加载时提示
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.fn.panel.defaults.loadingMessage = '加载中....';
$.fn.datagrid.defaults.loadMsg = '加载中....';

/**
 * @requires jQuery,EasyUI
 * 
 * panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 */
$.fn.panel.defaults.onBeforeDestroy = function() {
    var frame = $('iframe', this);
    try {
        if (frame.length > 0) {
            for ( var i = 0; i < frame.length; i++) {
                frame[i].src = '';
                frame[i].contentWindow.document.write('');
                frame[i].contentWindow.close();
            }
            frame.remove();
            if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
                try {
                    CollectGarbage();
                } catch (e) {
                }
            }
        }
    } catch (e) {
    }
};

/**
 *  
 * 
 * @requires jQuery,EasyUI
 * 
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
    var l = left;
    var t = top;
    if (l < 1) {
        l = 1;
    }
    if (t < 1) {
        t = 1;
    }
    var width = parseInt($(this).parent().css('width')) + 14;
    var height = parseInt($(this).parent().css('height')) + 14;
    var right = l + width;
    var buttom = t + height;
    var browserWidth = $(window).width();
    var browserHeight = $(window).height();
    if (right > browserWidth) {
        l = browserWidth - width;
    }
    if (buttom > browserHeight) {
        t = browserHeight - height;
    }
    $(this).parent().css({/* 修正面板位置 */
        left : l,
        top : t
    });
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;

/**
 *  
 * 
 * @requires jQuery,EasyUI
 * 
 * 通用错误提示
 * 
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 */
var easyuiErrorFunction = function(XMLHttpRequest) {
    parent.$.messager.alert('错误', XMLHttpRequest.responseText);
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;

/**
 *  
 * 
 * @requires jQuery,EasyUI
 * 
 * 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
 */
var createGridHeaderContextMenu = function(e, field) {
    e.preventDefault();
    var grid = $(this);/* grid本身 */
    var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
    if (!headerContextMenu) {
        var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
        var fields = grid.datagrid('getColumnFields');
        for ( var i = 0; i < fields.length; i++) {
            var fildOption = grid.datagrid('getColumnOption', fields[i]);
            if (!fildOption.hidden) {
                $('<div iconCls="tick" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
            } else {
                $('<div iconCls="bullet_blue" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
            }
        }
        headerContextMenu = this.headerContextMenu = tmenu.menu({
            onClick : function(item) {
                var field = $(item.target).attr('field');
                if (item.iconCls == 'tick') {
                    grid.datagrid('hideColumn', field);
                    $(this).menu('setIcon', {
                        target : item.target,
                        iconCls : 'bullet_blue'
                    });
                } else {
                    grid.datagrid('showColumn', field);
                    $(this).menu('setIcon', {
                        target : item.target,
                        iconCls : 'tick'
                    });
                }
            }
        });
    }
    headerContextMenu.menu('show', {
        left : e.pageX,
        top : e.pageY
    });
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;

/**
 * grid tooltip参数
 * 
 *  
 */
var gridTooltipOptions = {
    tooltip : function(jq, fields) {
        return jq.each(function() {
            var panel = $(this).datagrid('getPanel');
            if (fields && typeof fields == 'object' && fields.sort) {
                $.each(fields, function() {
                    var field = this;
                    bindEvent($('.datagrid-body td[field=' + field + '] .datagrid-cell', panel));
                });
            } else {
                bindEvent($(".datagrid-body .datagrid-cell", panel));
            }
        });

        function bindEvent(jqs) {
            jqs.mouseover(function() {
                var content = $(this).text();
                if (content.replace(/(^\s*)|(\s*$)/g, '').length > 5) {
                    $(this).tooltip({
                        content : content,
                        trackMouse : true,
                        position : 'bottom',
                        onHide : function() {
                            $(this).tooltip('destroy');
                        },
                        onUpdate : function(p) {
                            var tip = $(this).tooltip('tip');
                            if (parseInt(tip.css('width')) > 500) {
                                tip.css('width', 500);
                            }
                        }
                    }).tooltip('show');
                }
            });
        }
    }
};
/**
 * Datagrid扩展方法tooltip 基于Easyui 1.3.3，可用于Easyui1.3.3+
 * 
 * 简单实现，如需高级功能，可以自由修改
 * 
 * 使用说明:
 * 
 * 在easyui.min.js之后导入本js
 * 
 * 代码案例:
 * 
 * $("#dg").datagrid('tooltip'); 所有列
 * 
 * $("#dg").datagrid('tooltip',['productid','listprice']); 指定列
 * 
 *  
 */
$.extend($.fn.datagrid.methods, gridTooltipOptions);

/**
 * Treegrid扩展方法tooltip 基于Easyui 1.3.3，可用于Easyui1.3.3+
 * 
 * 简单实现，如需高级功能，可以自由修改
 * 
 * 使用说明:
 * 
 * 在easyui.min.js之后导入本js
 * 
 * 代码案例:
 * 
 * $("#dg").treegrid('tooltip'); 所有列
 * 
 * $("#dg").treegrid('tooltip',['productid','listprice']); 指定列
 * 
 *  
 */
$.extend($.fn.treegrid.methods, gridTooltipOptions);

/**
 *  
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展validatebox，添加验证两次密码功能
 */
$.extend($.fn.validatebox.defaults.rules, {
    eqPwd : {
        validator : function(value, param) {
            return value == $(param[0]).val();
        },
        message : '密码不一致！'
    },
    telno: {    
        validator: function(value, param){
            return (/^1[3|4|5|8][0-9]\d{8}$/.test(value.trim()));
        },    
        message: '请输入正确的电话号码'   
    },
    positivenumber: {    
        validator: function(value, param){
            return (/^\d+$/.test(value));    
        },    
        message: '请输入正整数'   
    },
    passportNum: {   //G28233515 
        validator: function(value, param){
        	return (/^1[45][0-9]{7}|G[0-9]{8}|P[0-9]{7}|S[0-9]{7,8}|D[0-9]+$/.test(value.trim())); 
        },    
        message: '请输入正确的护照号码'   
    },
   chinaName: {   //姓名验证
        validator: function(value, param){
        	return (/^[\u4e00-\u9fa5 ]{2,20}$/.test(value.trim())); 
        },    
        message: '请输入中文'   
    },
   idcard: { 
        validator: function(value,param){
        	return isCardID(value)==true?true:false;  
        },     
        message: '不是有效的身份证号码' 
    },
   telNum:{ //既验证手机号，又验证座机号
        validator: function(value, param){
            return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\d{3})|(\d{3}\-))?(1[358]\d{9})$)/.test(value);
           },   
           message: '请输入正确的电话号码。'
      },
   tbcard: { //台胞证
      validator: function(value,param){
      	return ( /^[a-zA-Z0-9]{5,21}$/.test(value.trim())); 
      	},     
      	message: '不是有效的台胞证' 
     },
   gatcard: {//港澳台通行证 
         validator: function(value,param){
         	return ( /^[a-zA-Z0-9]{5,21}$/.test(value.trim()));   
         },     
         message: '不是有效的港澳台通行证 ' 
     },
    checkadOrg: {//验证社区矫正机关类别
    	validator: function(value, param){
        	return (/^[\u4e00-\u9fa5 ]{2,20}[\u516C\u5B89|\u6CD5\u9662|\u76D1\u72F1|\u68C0\u5BDF\u9662]$/.test(value.trim())); 
        },   
         message: '不是有效的社区矫正机关 ' 
    }
    
});

//扩展tree，使其可以获取实心节点
$.extend($.fn.tree.methods, {
    getCheckedExt : function(jq) {// 获取checked节点(包括实心)
        var checked = $(jq).tree("getChecked");
        var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
        $.each(checkbox2, function() {
            var node = $.extend({}, $.data(this, "tree-node"), {
                target : this
            });
            checked.push(node);
        });
        return checked;
    },
    getSolidExt : function(jq) {// 获取实心节点
        var checked = [];
        var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
        $.each(checkbox2, function() {
            var node = $.extend({}, $.data(this, "tree-node"), {
                target : this
            });
            checked.push(node);
        });
        return checked;
    }
});

//扩展tree，使其支持平滑数据格式
$.fn.tree.defaults.loadFilter = function(data, parent) {
    var opt = $(this).data().tree.options;
    var idFiled, textFiled, parentField;
    if (opt.parentField) {
        idFiled = opt.idFiled || 'id';
        textFiled = opt.textFiled || 'text';
        parentField = opt.parentField;
        var i, l, treeData = [], tmpMap = [];
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idFiled]] = data[i];
        }
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                data[i]['text'] = data[i][textFiled];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
            } else {
                data[i]['text'] = data[i][textFiled];
                treeData.push(data[i]);
            }
        }
        return treeData;
    }
    return data;
};

// 扩展treegrid，使其支持平滑数据格式
$.fn.treegrid.defaults.loadFilter = function(data, parentId) {
    var opt = $(this).data().treegrid.options;
    var idFiled, textFiled, parentField;
    if (opt.parentField) {
        idFiled = opt.idFiled || 'id';
        textFiled = opt.textFiled || 'text';
        parentField = opt.parentField;
        var i, l, treeData = [], tmpMap = [];
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idFiled]] = data[i];
        }
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                data[i]['text'] = data[i][textFiled];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
            } else {
                data[i]['text'] = data[i][textFiled];
                treeData.push(data[i]);
            }
        }
        return treeData;
    }
    return data;
};

// 扩展combotree，使其支持平滑数据格式
$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;

/**
 * 
 * @requires jQuery,EasyUI
 * 
 * 创建一个模式化的dialog
 * 
 * @returns $.modalDialog.handler 这个handler代表弹出的dialog句柄
 * 
 * @returns $.modalDialog.xxx 这个xxx是可以自己定义名称，主要用在弹窗关闭时，刷新某些对象的操作，可以将xxx这个对象预定义好
 */
$.modalDialog = function(options) {
    if ($.modalDialog.handler == undefined) {// 避免重复弹出
        var opts = $.extend({
            title : '',
            width : 840,
            height : 680,
            modal : true,
            onClose : function() {
                $.modalDialog.handler = undefined;
                $(this).dialog('destroy');
            },
            onOpen : function() {
            }
        }, options);
        opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
        return $.modalDialog.handler = $('<div/>').dialog(opts);
    }
};


$.cookie = function(key, value, options) {
    if (arguments.length > 1 && (value === null || typeof value !== "object")) {
        options = $.extend({}, options);
        if (value === null) {
            options.expires = -1;
        }
        if (typeof options.expires === 'number') {
            var days = options.expires, t = options.expires = new Date();
            t.setDate(t.getDate() + days);
        }
        return (document.cookie = [ encodeURIComponent(key), '=', options.raw ? String(value) : encodeURIComponent(String(value)), options.expires ? '; expires=' + options.expires.toUTCString() : '', options.path ? '; path=' + options.path : '', options.domain ? '; domain=' + options.domain : '', options.secure ? '; secure' : '' ].join(''));
    }
    options = value || {};
    var result, decode = options.raw ? function(s) {
        return s;
    } : decodeURIComponent;
    return (result = new RegExp('(?:^|; )' + encodeURIComponent(key) + '=([^;]*)').exec(document.cookie)) ? decode(result[1]) : null;
};

/**
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
$.serializeObject = function(form) {
    var o = {};
    $.each(form.serializeArray(), function(index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
};

/**
 * 
 * 增加formatString功能
 * 
 * 使用方法：$.formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
$.formatString = function(str) {
    for ( var i = 0; i < arguments.length - 1; i++) {
        str = str.replace("{" + i + "}", arguments[i + 1]);
    }
    return str;
};

/**
 * 
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 * 
 * @returns list
 */
$.stringToList = function(value) {
    if (value != undefined && value != '') {
        var values = [];
        var t = value.split(',');
        for ( var i = 0; i < t.length; i++) {
            values.push('' + t[i]);/* 避免他将ID当成数字 */
        }
        return values;
    } else {
        return [];
    }
};

/**
 * 
 * @requires jQuery
 * 
 * 改变jQuery的AJAX默认属性和方法
 */
$.ajaxSetup({
    type : 'POST',
    error : function(XMLHttpRequest, textStatus, errorThrown) {
        try {
            parent.$.messager.progress('close');
            parent.$.messager.alert('错误', XMLHttpRequest.responseText);
        } catch (e) {
            alert(XMLHttpRequest.responseText);
        }
    }
});


/**
 * 
 * @requires jQuery
 * 
 * 去掉空格
 * **/
String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, '');
};
String.prototype.ltrim = function() {
    return this.replace(/(^\s*)/g, '');
};
String.prototype.rtrim = function() {
    return this.replace(/(\s*$)/g, '');
};

/**
 * 
 * @requires jQuery
 * 
 * 页面加载加载进度条启用
 * **/
function progressLoad(){  
    $("<div class=\"datagrid-mask\" style=\"position:absolute;z-index: 9999;\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\" style=\"position:absolute;z-index: 9999;\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});  
}

/**
 * 
 * @requires jQuery
 * 
 * 页面加载加载进度条关闭
 * **/
function progressClose(){
    $(".datagrid-mask").remove();  
    $(".datagrid-mask-msg").remove();
}

/**
 * 
 * @requires jQuery
 * 
 * 防止退格键导致页面回退
 */
$(document).keydown(function (e) { 
    var doPrevent; 
    if (e.keyCode == 8) { 
        var d = e.srcElement || e.target; 
        if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') { 
            doPrevent = d.readOnly || d.disabled; 
        }else{
            doPrevent = true; 
        }
    }else{ 
        doPrevent = false; 
    }
    if (doPrevent) 
    e.preventDefault(); 
});

$(".confirmForm").keydown(function(event){
	 if (event.keyCode == 13){
	        $(".confirmButton").click();  
	    }
});

/**
 * 显示消息
 */
function showMsg(msg) {
    top.window.$.messager.show({
        title: '提示',
        msg:'<div class="light-info"><div class="light-tip icon-tip"></div><div>' + msg||"消息内容！" + '</div></div>',
        timeout: 3000,
        showType: 'slide'
    });
}


/**
 * @author 
 * 
 * 获得项目根路径
 * 
 * 使用方法：sy.bp();
 * 
 * @returns 项目根路径
 */
sy.bp = function() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
};

//js获取项目根路径，如： http://localhost:8083/uimcardprj  
sy.localhostPath = function (){  
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp  
    var curWwwPath=window.document.location.href;  
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp  
    var pathName=window.document.location.pathname;  
    var pos=curWwwPath.indexOf(pathName);  
    //获取主机地址，如： http://localhost:8083  
    var localhostPaht=curWwwPath.substring(0,pos);  
    //获取带"/"的项目名，如：/uimcardprj  
    //var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
    return localhostPaht;  
}  

/**
 * @author 
 * 
 * 使用方法:sy.pn();
 * 
 * @returns 项目名称(/sshe)
 */
sy.pn = function() {
	return window.document.location.pathname.substring(0, window.document.location.pathname.indexOf('\/', 1));
};

/**
 * @author 
 * 
 * 增加formatString功能
 * 
 * 使用方法：sy.fs('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
sy.fs = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

/**
 * @author 
 * 
 * 增加命名空间功能
 * 
 * 使用方法：sy.ns('jQuery.bbb.ccc','jQuery.eee.fff');
 */
sy.ns = function() {
	var o = {}, d;
	for ( var i = 0; i < arguments.length; i++) {
		d = arguments[i].split(".");
		o = window[d[0]] = window[d[0]] || {};
		for ( var k = 0; k < d.slice(1).length; k++) {
			o = o[d[k + 1]] = o[d[k + 1]] || {};
		}
	}
	return o;
};

/**
 * @author 郭华(夏悸)
 * 
 * 生成UUID
 * 
 * @returns UUID字符串
 */
sy.random4 = function() {
	return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
};
sy.UUID = function() {
	return (sy.random4() + sy.random4() + "-" + sy.random4() + "-" + sy.random4() + "-" + sy.random4() + "-" + sy.random4() + sy.random4() + sy.random4());
};

sy.getBrowerWidth = function(){
	return $(window).width();
}

sy.getBrowerHeight = function(){
	return $(window).height();
}

/**
 * @author 
 * 
 * 获得URL参数
 * 
 * @returns 对应名称的值
 */
sy.getUrlParam = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};

/**
 * @author 
 * 
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 * 
 * @returns list
 */
sy.getList = function(value) {
	if (value != undefined && value != '') {
		var values = [];
		var t = value.split(',');
		for ( var i = 0; i < t.length; i++) {
			values.push('' + t[i]);/* 避免他将ID当成数字 */
		}
		return values;
	} else {
		return [];
	}
};

/**
 * @author 
 * 
 * @requires jQuery
 * 
 * 判断浏览器是否是IE并且版本小于8
 * 
 * @returns true/false
 */
sy.isLessThanIe8 = function() {
	return ($.browser.msie && $.browser.version < 8);
};

/**
 * @author 
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
sy.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};
/**
 * 
 * 将JSON对象转换成字符串
 * 
 * @param o
 * @returns string
 */
sy.jsonToUrlParam = function(form) {
	var querystring = "";
	$.each(form.serializeArray(), function(index) {
	   if (querystring!="")
		   querystring = querystring+"&"+this['name']+"="+this['value'];
	   else
	       querystring = this['name']+"="+this['value'];
	});
	return querystring;
};
/**
 * 
 * 将JSON对象转换成字符串
 * 
 * @param o
 * @returns string
 */
sy.jsonToString = function(o) {
	var r = [];
	if (typeof o == "string")
		return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
	if (typeof o == "object") {
		if (!o.sort) {
			for ( var i in o)
				r.push(i + ":" + obj2str(o[i]));
			if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {
				r.push("toString:" + o.toString.toString());
			}
			r = "{" + r.join() + "}";
		} else {
			for ( var i = 0; i < o.length; i++)
				r.push(obj2str(o[i]));
			r = "[" + r.join() + "]";
		}
		return r;
	}
	return o.toString();
};

sy.getMonthEndDate = function(){
    var current=new Date();
	     var currentMonth=current.getMonth();
	     var nextMonth=++currentMonth;
	     var nextMonthDayOne =new Date(current.getFullYear(),nextMonth,1);
	     var minusDate=1000*60*60*24;
	     var endDate = new Date(nextMonthDayOne.getTime()-minusDate);
	     return endDate;
}
sy.getMonthBeginDate = function(){
         var current=new Date();
	     var currentMonth=current.getMonth();
	     var begindate =new Date(current.getFullYear(),currentMonth,1);
	     return begindate;
}
/***
 * 获取数据字典value值
 */
sy.getDicValue = function(tabName,columnName,key,defaultValue){
	var str = '';
	$.ajax({
		type : 'post',
		url : sy.pn()+'/sys/sysDic/getDicValue?tabName='+tabName+'&columnName='+columnName+'&key='+key,
		cache : false,
		async : false, //同步请求
		data : {
			id : key,
			defaultValue:defaultValue
		},
		dataType : 'text',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			 /* alert(XMLHttpRequest.status);
			 alert(XMLHttpRequest.readyState);
			 alert(textStatus); */
			   },
		success : function(result) {
		/*	
			if(result!=null&&$.trim(result)!=''&&typeof(result.value) != 'undefined'){
				str = result.value;
				
			}*/
			str=result;
		}
	});
	return str;
};
/***
 * 获取数据字典key值
 */
sy.getDicKey = function(tabName,columnName,value,defaultValue){
	var url = sy.pn()+'/sys/sysDic/getDicKey?tabName='+tabName+'&columnName='+columnName+'&defaultValue='+defaultValue;
	var str = '';
	$.ajax({
		type : 'post',
		url : url,
		cache : false,
		async : false, //同步请求
		data : {
			value : value
		},
		dataType : 'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			 /*alert(XMLHttpRequest.status);
			 alert(XMLHttpRequest.readyState);
			 alert(textStatus);*/
			   },
		success : function(result) {
			
			if(result!=null&&$.trim(result)!=''&&typeof(result.key) != 'undefined'){
				str = result.key;
			}
		}
	});
	return str;
};
/***
 * 获取省市县名称
 */
sy.getAreaValue = function(id){
	var str = '';
	$.ajax({
		type : 'post',
		url : sy.pn()+'/culpritinfo/culprit/getAreaValue',
		cache : false,
		async : false, //同步请求
		data : {
			id : id
		},
		dataType : 'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			 /* alert(XMLHttpRequest.status);
			 alert(XMLHttpRequest.readyState);
			 alert(textStatus); */
			   },
		success : function(result) {
				str = result.areaName;
		}
	});
	return str;
};
/**
 * @author 郭华(夏悸)
 * 
 * 格式化日期时间
 * 
 * @param format
 * @returns
 */
Date.prototype.format = function(format) {
	if (isNaN(this.getMonth())) {
		return '';
	}
	if (!format) {
		format = "yyyy-MM-dd hh:mm:ss";
	}
	var o = {
		/* month */
		"M+" : this.getMonth() + 1,
		/* day */
		"d+" : this.getDate(),
		/* hour */
		"h+" : this.getHours(),
		/* minute */
		"m+" : this.getMinutes(),
		/* second */
		"s+" : this.getSeconds(),
		/* quarter */
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		/* millisecond */
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

/**
 * @author 
 * 
 * @requires jQuery
 * 
 * 改变jQuery的AJAX默认属性和方法
 */
$.ajaxSetup({
	type : 'POST',
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		$.messager.progress('close');
		$.messager.alert('错误', XMLHttpRequest.responseText);
	}
});

/*textarea 字数限制*/
function textarealength(obj,maxlength){
	var v = $(obj).val();
	var l = v.length;
	if( l > maxlength){
		v = v.substring(0,maxlength);
	}
	$(obj).parent().find(".textarea-length").text(v.length);
}

var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}   

function isCardID(sId){   
    var iSum=0 ;  
    var info="" ;  
    if(!/^\d{17}(\d|x)$/i.test(sId)) return "你输入的身份证长度或格式错误";   
    sId=sId.replace(/x$/i,"a"); 
    if(aCity[parseInt(sId.substr(0,2))]==null) return "你的身份证地区非法";   
    sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));   
    var d=new Date(sBirthday.replace(/-/g,"/")) ;  
    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期非法";   
    for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;  
    if(iSum%11!=1) return "你输入的身份证号非法";   
    return true;//aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女")   
} 

function setFilePathAndName(uploadname){
	var filename = "";
	var filepath = "";
	var fileurl = "";
	$('input[name=url]').each(function (index){
		if (this.id.indexOf(uploadname) > 0) {
			fileurl += this.value + ',';
			filepath += $('#hiddenInputFileName'+this.id.replace('hiddenInputUrl', '')).val() + ',';
			filename += $('#hiddenInput'+this.id.replace('hiddenInputUrl', '')).val() + ',';
		}
	});
	if (fileurl.length > 0) {
		fileurl = fileurl.substr(0,fileurl.length-1);
		$('#'+uploadname.replace('Upload','Url')).val(fileurl);
	}
	if (filepath.length > 0) {
		filepath = filepath.substr(0,filepath.length-1);
		$('#'+uploadname.replace('Upload','Path')).val(filepath);
	}
	if (filename.length > 0) {
		filename = filename.substr(0,filename.length-1);
		$('#'+uploadname.replace('Upload','PathName')).val(filename);
	}
}

function sysFileDel(id){
	var url = sy.pn()+'/sys/file/delete';
	$.ajax({
		type : 'post',
		url : url,
		dataType : 'json',
		data : {
			id: id
		},
		async : false,
		success : function(result) {
			if (result.status == 200) {
				$('#file_'+id).remove();
			}
		},
		error : function(result) {
			console.log(result.msg);
		}
	});
}


//timestamp转换date
function formatDate(value, str) {
	if (value == null || value == '') {
		return '';
	}
	var dt;
	if (value instanceof Date) {
		dt = value;
	} else {
		dt = new Date(value);
	}
	return dt.format(str); //扩展的Date的format方法(上述插件实现)
}

function getDialogWidth(width, sub){
	if (width >= sy.getBrowerWidth() - sub) {
		return sy.getBrowerWidth() - sub;
	}
	else return width;
}

function getDialogHeight(height, sub){
	if (height >= sy.getBrowerHeight() - sub) {
		return sy.getBrowerHeight() - sub;
	}
	else return height;
}