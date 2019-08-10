<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>沃斯酒店</title>
    <link rel="stylesheet" href="js/layui/css/layui.css">
</head>
<body>
<br>
<div class="layui-form-item">

    <div class="layui-inline">
            <i class="layui-icon">&#xe66e;</i> <span > -A到C数额由小到大！</span>
        <br> <br>
        <label class="layui-form-label">优惠A:充</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="price_min" placeholder="￥" autocomplete="off" class="layui-input" id="rc_a">
        </div>
        <div class="layui-form-mid">送</div>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="price_max" placeholder="￥" autocomplete="off" class="layui-input" id="rc_a_regiv">
        </div>
    </div>

</div>
<div class="layui-form-item">

    <div class="layui-inline">
        <label class="layui-form-label">优惠B:充</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="price_min" placeholder="￥" autocomplete="off" class="layui-input" id="rc_b">
        </div>
        <div class="layui-form-mid">送</div>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="price_max" placeholder="￥" autocomplete="off" class="layui-input" id="rc_b_regiv">
        </div>
    </div>

</div>
<div class="layui-form-item">

    <div class="layui-inline">
        <label class="layui-form-label">优惠C:充</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="price_min" placeholder="￥" autocomplete="off" class="layui-input" id="rc_c">
        </div>
        <div class="layui-form-mid">送</div>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="price_max" placeholder="￥" autocomplete="off" class="layui-input" id="rc_c_regiv">
        </div>
    </div>

</div>

<i class="layui-icon">&#xe62e;</i> <a id="sure" type="button"  class="layui-btn layui-btn-normal">确定</a>

<%--<jsp:forward page="background.jsp"></jsp:forward>--%>
</body>
<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/layui/layui.js"></script>
<script type="text/javascript">

    var rc_a,rc_b,rc_c,rc_a_regiv,rc_b_regiv,rc_c_regiv;
    // var jsonstr=JSON.stringify(vip_id);
    $("#sure").click(function() {
        rc_a=$("#rc_a").val();
        rc_b=$("#rc_b").val();
        rc_c=$("#rc_c").val();
        rc_a_regiv=$("#rc_a_regiv").val();
        rc_b_regiv=$("#rc_b_regiv").val();
        rc_c_regiv=$("#rc_c_regiv").val();

        if(((rc_a<rc_b<rc_c)&&rc_a!=0&&rc_b!=0&&rc_c!=0)|| ((rc_a==0&&rc_b!=0&&rc_c!=0)&&(rc_b<rc_c))||(rc_a!=0&&rc_b==0&&rc_c!=0&&(rc_a<rc_c) || (rc_a!=0&&rc_b!=0&&rc_c ==0)&&(rc_a<rc_b))||((rc_a!=0&&rc_b==0&&rc_c==0)||(rc_a==0&&rc_b!=0&&rc_c==0)||(rc_a==0&&rc_b==0&&rc_c!=0)) ){
            if(rc_a==0)
            {
                rc_a=0;
            }
            if(rc_b==0)
            {
                rc_b=0;
            }
            if(rc_c==0)
            {
                rc_c=0;
            }
            if(rc_a_regiv==0)
            {
                rc_a_regiv=0;
            }
            if(rc_b_regiv==0)
            {
                rc_b_regiv=0;
            }
            if(rc_c_regiv==0)
            {
                rc_c_regiv=0;
            }
            alert(rc_a_regiv);
            $.ajax({
            url : "rcCase",
            type : "get",
            data : {"rc_a":rc_a,"rc_b":rc_b,"rc_c":rc_c,"rc_a_regiv":rc_a_regiv,"rc_b_regiv":rc_b_regiv,"rc_c_regiv":rc_c_regiv},
            datatype:"json",
            contentType:"application/json;charset=UTF-8",
            success : function(data) {
                if(data==1)
                {
                    alert("修改成功");
                }
                else{
                    alert("修改失败");
                }
            }
        });

        }
        else
        {
            alert ("参数错误,注意是否从小到大");
        }




    });
    // $("#btn_login").click(function() {
    //     var code=$("#code").val();//输入的验证码
    //     if(code==code1){
    //         if(status==1) {
    //             alert("welcome");
    //             window.location.href="background.jsp";
    //         }
    //         else if(status==0){
    //             alert("注册成功，请先完善会员信息")
    //             window.location.href="updateVipMsg.jsp";
    //         }
    //     }
    //     else{
    //         alert("验证码错误");
    //     }
    //
    // });
</script>
</html>
