<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/18
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>会员统计</title>
</head>
<link rel="stylesheet" type="text/css" href="js/layui/css/layui.css"/>
<body>
<div class="layui-row">
    <div id="main" class="layui-col-xs6 layui-anim layui-anim-fadein" style="width: 600px;height:450px;"></div>
    <div id="main2" class="layui-col-xs6 layui-anim layui-anim-fadein" style="width: 600px;height:450px;"></div>
</div>
</body>
<script src="js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
    var myChart = echarts.init(document.getElementById('main'));
    var myChart2 = echarts.init(document.getElementById('main2'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '每月新增会员'
        },
        tooltip: {},
        legend: {
            data:['人数']
        },
        xAxis: {
            data: ["2019.6","2019.7","2019.8","2019.9","2019.10","2019.11"]
        },
        yAxis: {},
        series: [{
            name: '人数',
            type: 'bar',
            data: [10, 20, 30, 40, 50, 60]
        }]
    };
    option2 = {
        title: {
            text: '每月充值总额'
        },
        xAxis: {
            type: 'category',
            data: ["2019.6","2019.7","2019.8","2019.9","2019.10","2019.11"]
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line'
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart2.setOption(option2);
</script>
</html>

