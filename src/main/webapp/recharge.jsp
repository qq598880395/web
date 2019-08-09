<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>沃斯酒店</title>
    <link rel="stylesheet" href="js/layui/css/layui.css">

</head>
<body onload="load()">

<br>
<br>
<div class="layui-form-item">



</div>

<div ><span id="name"></span>,你好,您的余额：<span id="money">      </span>
    <br>
    您现在拥有的积分为<span id="level_num"></span>,您目前是我酒店：<span id="level_name"></span> 享受所有消费折扣：<span id="level_cost"></span>
    <br>
    <div id="font1" style="display: block">您尚需要:<span id="need_num"></span>点积分升级</div>
    <div id="font2" style="display: none">您已达到最高会员等级，感谢您！</div>
    <br><br>
    <button id="rc_btn" class="layui-btn layui-btn-normal" style="margin-left: 20px;" onclick="rc_click()">充值</button></div>

<%--<div id="rc_area"></div>--%>
<br>
<button id="sure" type="button" style="margin-left: 20px;" class="layui-btn layui-btn-normal">确定</button>
<a href="background.jsp" class="layui-btn">Hello World!</a>
<a href="rcCase.jsp" class="layui-btn">rccase</a>
<a href="updateVipMsg.jsp" class="layui-btn">update</a>
<%--弹出层--%>
<div id="recharge" style="width:100%;display: none;" class="layui-container">
    <div class="layui-row" >
        <div class="layui-col-md4 layui-col-xs2" style="height: 30%;"></div>
        <div class="layui-col-md4 layui-col-xs8" style="height: 30%; border: #F8F8F8 2px solid;background-color: #F8F8F8">
            <div style="width: 100%;height: 30px;text-align: center; border-bottom: #c0c4cc 2px solid;font-size: 20px;">充值</div>
            <br>
            <input type="text" name="title" required lay-verify="required" placeholder="请输入充值金额" autocomplete="off" class="layui-input"id = "rc_cost">
            <%--//有优惠就显示没有就隐藏--%>
            <br><div  id="rcfont" style="margin-left: 20px;display:none;">*现在有优惠，多充多送喔！</div>
            <br>

            <button id="rc_sure"  type="button"  class="layui-btn layui-btn-normal" style="margin-left: 40%;">确定</button>
            <a id="rc_close"  href=" " type="button"  class="layui-btn layui-btn-normal" >关闭</a></div>

        <div class="layui-col-md4 layui-col-xs2" style="height: 30%;"></div>
    </div>
</div>

<%--<jsp:forward page="background.jsp"></jsp:forward>--%>
</body>
<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/layui/layui.js"></script>
<script type="text/javascript">
    var vip_id,vip_name,_vip_money,level_id,level_num,level_cost,level_name,need_num;
    var rc_a,rc_b,rc_c,rc_a_regiv,rc_b_regiv,rc_c_regiv;
    // http://how2j.cn/k/ssm/ssm-json/1575.html#nowhere
    var font1 = document.getElementById("font1");
    var font2 = document.getElementById("font2");

    // var jsonstr=JSON.stringify(vip_id);
    function load(){
        $.ajax({
            async: true,
            dataType: "json",
            type: "POST",
            url: "checkVip",
            success: function (data) {
                console.log(data);
                vip_id = data.vip_id;
                if(vip_id==null){
                    alert("请先登录");
                    window.location.href="login.jsp";
                }

                $.ajax({
                    url: "findMsg",
                    type: "get",
                    data: {"vip_id": vip_id},
                    datatype: "json",
                    contentType: "application/json;charset=UTF-8",
                    success: function (data) {

                        var jsondata = JSON.parse(data);
                        level_id = jsondata.level_id;
                        level_num = jsondata.level_num;
                        vip_money = jsondata.vip_money;
                        vip_name = jsondata.vip_name;
                        if (vip_name == 0) {
                            vip_name = jsondata.vip_tel;
                            if (vip_name == 0) {
                                vip_name = "尊贵的会员";
                            }
                        }
                        document.getElementById("name").innerHTML = vip_name;
                        document.getElementById("money").innerHTML = vip_money;
                        $.ajax({
                            url: "findlevelMsg",
                            type: "get",
                            data: {"level_id": level_id},
                            datatype: "json",
                            contentType: "application/json;charset=UTF-8",
                            success: function (data) {

                                var jsondata = JSON.parse(data);
                                level_cost = jsondata.level_cost;
                                level_name = jsondata.level_name;
                                need_num = jsondata.next_need - level_num;

                                document.getElementById("level_num").innerHTML = level_num;
                                document.getElementById("level_name").innerHTML = level_name;
                                document.getElementById("level_cost").innerHTML = level_cost;
                                document.getElementById("need_num").innerHTML = need_num;
                                if (level_id == 7) {
                                    font1.style.display = "none";
                                    font2.style.display = "block";
                                }
                            }
                        })
                    }
                })
                // vip_id="6b17e570ee124e9d8fb24afd8ab1446c";
            }
    })
    };
    function rc_click(){
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

    };

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
                    window.location.href="recharge.jsp";
                }
                else{
                    alert("充值失败");
                    window.location.href="recharge.jsp";
                }

            }
        });



    });

</script>
</html>
