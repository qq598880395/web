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
<body style="position: relative">
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

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="rc" id="btn_recharge" >充值</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" id="btn_delete" >删除</a>
</script>
<div id="recharge" style="width:100%;display: none;position: absolute;bottom: 55%;left: 30%" class="layui-container">
    <div class="layui-row" >
        <div class="layui-col-md4 layui-col-xs2" style="height: 30%;"></div>
        <div class="layui-col-md4 layui-col-xs8" style="height: 30%; border: #F8F8F8 2px solid;background-color: #F8F8F8">
            <div style="width: 100%;height: 30px;text-align: center; border-bottom: #c0c4cc 2px solid;font-size: 20px;">充值</div>
            <br>
            <input type="text" name="title" required lay-verify="required" placeholder="请输入充值金额" autocomplete="off" class="layui-input"id = "rc_cost">

            <br><div  id="rcfont" style="margin-left: 20px;display:none;">*现在有优惠，多充多送喔！</div>
            <br>

            <button id="rc_sure"  type="button"  class="layui-btn layui-btn-normal" style="margin-left: 40%;">确定</button>
            <a id="rc_close"  href=" " type="button"  class="layui-btn layui-btn-normal" >关闭</a></div>

        <div class="layui-col-md4 layui-col-xs2" style="height: 30%;"></div>
    </div>
</div>

</body>
<script src="js/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var rc_a,rc_b,rc_c,rc_a_regiv,rc_b_regiv,rc_c_regiv,vip_id,level_num;
    layui.use('table', function() {
        var table = layui.table;//表格

        //向世界问个好
        layer.msg('Welcome');

        //执行一个 table 实例
        table.render({
            elem: '#demo',
            height: 580,
            url: 'findallvip',//数据接口
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
            id:'viplist',
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
                    { field: 'vip_id', title: 'ID', width: 110},
                    { field: 'openid', title: 'openid', width: 110 },
                    { field: 'vip_tel', title: '电话', width: 110},
                    { field: 'vip_name', title: '用户名', width: 80 },
                    { field: 'vip_IDcard', title: '身份证号', width: 177 },
                    { field: 'hotel_id', title: '酒店ID', width: 80, sort: true },
                    { field: 'hotel_name', title: '酒店名称', width: 100, sort: true },
                    { field: 'vip_money', title: '余额', width: 80, sort: true },
                    { field: 'level_num', title: '积分', width: 80, sort: true },
                    { field: 'vip_time', title: '加入时间', width: 130, sort: true },
                    { fixed: 'right', width: 180, align: 'center', toolbar: '#barDemo' }
                ]
            ],
            page: true //开启分页
        });

        //监听 头 工具栏事件
        // table.on('toolbar(test)', function(obj) {
        //     var checkStatus = table.checkStatus(obj.config.id),
        //         data = checkStatus.data; //获取选中的数据
        //     switch(obj.event) {
        //         case 'add':
        //             layer.msg('添加');
        //             break;
        //         case 'update':
        //             if(data.length === 0) {
        //                 layer.msg('请选择一行');
        //             } else if(data.length > 1) {
        //                 layer.msg('只能同时编辑一个');
        //             } else {
        //                 layer.alert('编辑 [id]：' + checkStatus.data[0].id);
        //             }
        //             break;
        //         case 'delete':
        //             if(data.length === 0) {
        //                 layer.msg('请选择一行');
        //             } else {
        //                 layer.msg('删除');
        //             }
        //             break;
        //     };
        // });
        //监听 行 工具事件
        table.on('tool(test)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"

            var data = obj.data ,//获得当前行数据
                layEvent = obj.event; //获得 lay-event 对应的值
           // var jsondata=JSON.stringify(data);
            var jsondata = JSON.parse(JSON.stringify(data));
                vip_id=jsondata.vip_id;
                level_num=jsondata.level_num;
            if(layEvent === 'del') {
                if(jsondata.vip_money>=1)
                {
                    alert('无法删除 用户还有余额');
                }

                else {
                    layer.confirm('真的删除此人么', function(index) {
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                        var vip_id = jsondata.vip_id;
                        $.ajax({
                            url : "delete",
                            type : "get",
                            data : {"vip_id":vip_id},
                            datatype:"json",
                            contentType:"application/json;charset=UTF-8",
                            success : function(data) {

                                if (data == null) {
                                    alert("错误 没有找到对象");
                                } else {
                                    if (data == 1) {
                                        alert("删除成功");
                                        // window.location.href="background.jsp";
                                    } else {
                                        alert("删除失败 错误原因未知");

                                    }
                                }
                            }
                        });
                //向服务端发送删除指令
            });
                }
        } else if(layEvent === 'edit') {
            layer.msg('没有权限，只允许用户本人修改');
        }
            else if(layEvent === 'rc') {
                var rcfont = document.getElementById("rcfont");
                var recharge = document.getElementById("recharge");
                var rc_caseid = 1;
                recharge.style.display="block";

                $.ajax({
                    url : "getRc",
                    type : "get",
                    data : {"rc_caseid":rc_caseid},
                    datatype:"json",
                    contentType:"application/json;charset=UTF-8",
                    success : function(data) {
                        var jsondata = JSON.parse(data);
                        rc_a=jsondata.rc_a;
                        rc_b=jsondata.rc_b;
                        rc_c=jsondata.rc_c;
                        rc_a_regiv=jsondata.rc_a_regiv;
                        rc_b_regiv=jsondata.rc_b_regiv;
                        rc_c_regiv=jsondata.rc_c_regiv;
                        if(jsondata.rc_a!=0||jsondata.rc_b!=0||jsondata.rc_c!=0){
                            rcfont.style.display="block";
                        }
                    }
                });


            }
    });
        var $ = layui.jquery, active = {

            reload:function () {
                var keyWord=$("#keyWord").val();
                var keyType=$("#key_type option:selected").val();
                table.reload('viplist',{
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
    $("#rc_sure").click(function() {
        var rc_cost=$("#rc_cost").val();


        $.ajax({
            url : "recharge",
            type : "get",
            data : {"vip_id":vip_id,"rc_cost":rc_cost,"level_num":level_num},
            datatype:"json",
            contentType:"application/json;charset=UTF-8",
            success : function(data) {
                if(data==1)
                {
                    alert("充值成功");

                    location.reload();
                }
                else{
                    alert("充值失败");
                }
            }
        });



    });

</script>


</html>