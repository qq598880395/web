<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>沃斯酒店</title>
    <link rel="stylesheet" href="js/layui/css/layui.css">
</head>
<body onload="load()">
<div ><span id="name"></span>,您好,您要修改的个人信息:</div>
<div>
    *name：<input type="text" id="vip_name1">
    <br><br>
    *IDcard:<input type="text" id = "vip_IDcard1">
</div>
<div id="tel">
    *tel:  <input type="text" id="vip_tel1">
    *验证码：<input type="text" id="code">
    <button id="btn_sendcode"  type="button" style="margin-left: 20px;" class="layui-btn layui-btn-normal">send</button>
</div>


<br>
<span style="font-size: 10px;" >(*不能为空)</span>
<br>
<button id="btn_update" type="button" style="margin-left: 20px;" class="layui-btn layui-btn-normal">update</button>
<a href="background.jsp">Hello World!</a>
<%--<jsp:forward page="background.jsp"></jsp:forward>--%>
</body>
<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/layui/layui.js"></script>
<script type="text/javascript">
    var vip_id,vip_name,vip_tel,vip_openid,hotel_id;
    var tel = document.getElementById("tel");
    var code1;//正确的验证码

     function load() {
         $.ajax({
             async: true,
             dataType: "json",
             type: "POST",
             url: "checkVip",
             success: function (data) {
                 console.log(data);
                 alert(data);
                 vip_id = data.vip_id;
                 if(vip_id==null){
                     alert("请先登录");
                     window.location.href="login.jsp";
                 }
                 vip_tel = data.vip_tel;
                 vip_openid = data.vip_openid;
                 hotel_id=data.hotel_id;
                 alert(vip_id);
                 if (vip_openid == null) {
                     tel.style.display = "none";

                 }
                 document.getElementById("name").innerHTML = data.vip_name;
             }
         })
     }
    $("#btn_sendcode").click(function() {
        vip_tel = $("#vip_tel").val();

        alert(vip_tel);

        $.ajax({
            url: "findbytel",
            type: "get",
            data: {"vip_tel": vip_tel},
            datatype: "json",
            contentType: "application/json;charset=UTF-8",
            success: function (data) {
                alert(data);
                if (data == 1&&hotel_id==1001) {
                    alert("电话已存在");
                } else {
                    $.ajax({
                        url: "sendCode",
                        type: "get",
                        data: {"vip_tel": vip_tel},
                        datatype: "json",
                        contentType: "application/json;charset=UTF-8",
                        success: function (data) {
                            var jsondata = JSON.parse(data);
                            code1 = jsondata.code;
                            alert(code1);

                        }
                    });
                }
            }
        })
    })

    // var jsonstr=JSON.stringify(vip_id);


    // var jsonstr=JSON.stringify(vip_id);
    $("#btn_update").click(function() {
        var vip_name1 = $("#vip_name1").val();
        var vip_IDcard1 = $("#vip_IDcard1").val();
        var code =$("#code").val();
        var vip_tel1;
        if (vip_openid == null) {
            vip_tel1 = vip_tel;
        } else {
            vip_tel1 = $("#vip_tel1").val();
        }
        alert(vip_id);
        if (vip_name1 == null || vip_tel1 == null || vip_IDcard1 == null) {
            alert("请务必填写完整");
        }
        else if (code == code1||vip_tel==vip_tel1) {
            alert(vip_name1);
            // vip_name1 = encodeURI(vip_name1);
            $.ajax({
                url: "updateMsg",
                type: "post",
                data: {"vip_id": vip_id, "vip_name": vip_name1, "vip_tel": vip_tel1, "vip_IDcard": vip_IDcard1},
                datatype: "json",
                // contentType: "application/json;charset=UTF-8",
                contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                success: function (data) {

                    if (data == null) {
                        alert("nobody");
                    } else {
                        if (data == 1) {
                            alert("success");
                            //此处要用ajax改session
                            $.ajax({

                                url : "dosession",
                                type : "get",
                                data : {"vip_tel":vip_tel},
                                datatype:"json",
                                contentType:"application/json;charset=UTF-8",
                                success : function(data) {
                                    alert("success");
                                }
                                });


                            window.location.href = "updateVipMsg.jsp";
                        } else {
                            alert("fauls");

                        }
                    }
                }
            });
        }

    });

</script>
</html>
