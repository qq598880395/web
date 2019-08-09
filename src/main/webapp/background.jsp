<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/14
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>沃斯酒店会员管理系统</title>
    <link rel="stylesheet" href="js/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">沃斯会员管理后台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href=""></a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">会议管理</a></dd>
                    <dd><a href="">微官网后台</a></dd>
                    <dd><a href="">广告后台</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="">会员统计</a>
                    <dl class="layui-nav-child">
                        <dd><a href="echarts.jsp" target="iframe_main">常规统计</a></dd>
                        <dd><a href="vip_list.jsp" target="iframe_main">会员名单</a></dd>
                        <dd><a href="javascript:;"></a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">会员充值</a>
                    <dl class="layui-nav-child">
                        <dd><a href="rcCase.jsp" target="iframe_main">优惠活动</a></dd>
                        <dd><a href="rc_list.jsp" target="iframe_main">充值记录</a></dd>

                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">联系我们</a>
                    <dl class="layui-nav-child">
                        <dd><a ><i class="layui-icon layui-icon-layer"></i>598880395@qq.com</a></dd>
                        <dd><a ><i class="layui-icon layui-icon-cellphone-fine"></i>13973723157</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body" >
        <iframe name="iframe_main" style="width: 100%; height: 1000px;" src="echarts.jsp"></iframe>



    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="js/layui/layui.js"></script>
<script src="js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });

</script>
</body>
</html>