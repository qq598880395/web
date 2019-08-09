<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/18
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>会员表格</title>
</head>
<link rel="stylesheet" type="text/css" href="js/layui/css/layui.css"/>
<body>
<br>
<div class="demoTable" style="margin-left: 20px">
    搜索：
    <div class="layui-inline">
        <input class="layui-input" name="keyWord" id="keyWord" autocomplete="off">
    </div>
    <span class="input-group-btn">
                            <select name="keyType" id="key_type" class="layui-btn">
                                <option value="vip_tel" selected="selected">手机号</option>
                                 <option value="vip_name"  >姓名</option>
                            </select>
        </span>
    <button class="layui-btn" data-type="reload">搜索</button>

</div>
<br>
<table class="layui-hide" id="demo" lay-filter="test"></table>



</body>
<script src="js/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

    layui.use('table', function() {
        var table = layui.table;//表格

        //向世界问个好
        layer.msg('Welcome');

        //执行一个 table 实例
        table.render({
            elem: '#demo',
            height: 580,
            url: 'findalllist',//数据接口
            title: '用户表',
            toolbar: '' ,//开启工具栏，此处显示默认图标，可以自定义模板，详见文档,
            totalRow: true ,//开启合计行\
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                ,groups: 5 //只显示 1 个连续页码
                ,first: "首页" //不显示首页
                ,last: "尾页" //不显示尾页
              },
            id:'rclist',
            limit:5,//十数据一页
            limits:[5,10,20,50],
            response:{
              statusName:'code',
              statusCode:0,
              msgName:'msg',
              countName:'count',
              dataName:'data',
            },
            cols: [//表头
                [
                    { type: 'checkbox', fixed: 'left' },
                    { field: 'rc_id', title: '充值单号', width: 150},
                    { field: 'vip_id', title: '用户号', width: 150 },
                    { field: 'vip_tel', title: '电话', width: 130, sort: true },
                    { field: 'vip_name', title: '用户名', width: 100 , sort: true },
                    { field: 'hotel_id', title: '酒店ID', width: 80, sort: true },
                    { field: 'rc_cost_1st', title: '实际充值', width: 110},
                    { field: 'rc_cost', title: '总计', width: 110},
                    { field: 'rc_time', title: '下单时间', width: 130, sort: true },
                ]
            ],
            page: true //开启分页
        });


        var $ = layui.jquery, active = {

            reload:function () {
                var keyWord=$("#keyWord").val();
                var keyType=$("#key_type option:selected").val();
                table.reload('rclist',{
                    method:'post',
                    where:{keyWord:keyWord,keyType:keyType}
                });
            }
        };
        $('i').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('.layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });


</script>


</html>