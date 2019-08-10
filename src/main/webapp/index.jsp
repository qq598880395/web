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

<div >您好，请先<a href="Login.jsp"> 登录</a>
    <br><br>


<br>
<button id="sure" type="button" style="margin-left: 20px;" class="layui-btn layui-btn-normal">确定</button>
<a href="background.jsp" class="layui-btn">Hello World!</a>
<a href="rcCase.jsp" class="layui-btn">rccase</a>
<a href="recharge.jsp" class="layui-btn">recharge</a>


<%--<jsp:forward page="background.jsp"></jsp:forward>--%>
</body>
<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/layui/layui.js"></script>
<script type="text/javascript">


        function load() {
            $.ajax({
                async: true,
                dataType: "json",
                type: "POST",
                url: "checkVip",
                success: function (data) {
                    console.log(data);
                    var vip_id = data.vip_id;
                    if (vip_id!=null)
                    {
                        window.location.href="recharge.jsp";
                    }


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






</script>
</html>
