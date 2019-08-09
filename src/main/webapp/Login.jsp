<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>沃斯酒店</title>
    <link media="all" type="text/css" rel="stylesheet" href="bootstrap3.1.1/css/bootstrap.min.css">
    <link media="all" type="text/css" rel="stylesheet" href="css/loginstyle.css">

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal">
                <span class="heading">登录</span>
                <img src="img/Usavich.jpg" style="height: 150px;" class="img-circle">
                <br />
                <br />
                <div class="form-group">
                    <input type="text" class="form-control" id="vip_tel" placeholder="请电话号码" />
                    <br />
                    <br />
                    <div class="row">
                        <div class="col-sm-8 pull-left">
                            <input type="text" class="form-control" id="code" placeholder="请输入短信验证码">
                        </div>
                        <button class="btn btn-success col-sm-4 right" id="btn_sendcode" type="button">发送</button>
                    </div>
                </div>

                <div class="form-group">
                    <li class="li4">
                        <input class="submit" type="button" id="btn_login" value="登录">
                    </li>
                    <hr style="filter: alpha(opacity=100,finishopacity=0,style=3)" width="80%" color="#6f5499" size="3" />
                    <br />
                    <a href="" title="使用微信登录"><span class="weixing"><img src="img/wx.png" style="height: 35px;" class="img-circle"></span></a>
                </div>
            </form>
        </div>
    </div>
</div>
<%--<jsp:forward page="background.jsp"></jsp:forward>--%>
</body>
<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.gradientify.min.js"></script>
<script>
    $(document).ready(function() {
        $("body").gradientify({
            gradients: [{
                start: [49, 76, 172],
                stop: [242, 159, 191]
            },
                {
                    start: [255, 103, 69],
                    stop: [240, 154, 241]
                },
                {
                    start: [33, 229, 241],
                    stop: [235, 236, 117]
                }
            ]
        });
    });
</script>
<script type="text/javascript">

    var code1;//正确的验证码
    var status;//用户状态
    var vip_id;
    var vip_tel;
    // var jsonstr=JSON.stringify(vip_id);
    $("#btn_sendcode").click(function() {
        vip_tel=$("#vip_tel").val();


        $.ajax({
            url : "sendCode",
            type : "get",
            data : {"vip_tel":vip_tel},
            datatype:"json",
            contentType:"application/json;charset=UTF-8",
            success : function(data) {
                var jsondata = JSON.parse(data);
                code1 =jsondata.code;
                status=jsondata.status;
                alert(code1);
            }
        });



    });
    $("#btn_login").click(function() {
        var code=$("#code").val();//输入的验证码
        if(code==code1){
            $.ajax({

                url : "login",
                type : "get",
                data : {"vip_tel":vip_tel,"status":status},
                datatype:"json",
                contentType:"application/json;charset=UTF-8",
                success : function(data) {
                    var jsondata = JSON.parse(data);
                    vip_id=jsondata.vip_id;
                    $.ajax({

                        url : "dosession",
                        type : "get",
                        data : {"vip_tel":vip_tel},
                        datatype:"json",
                        contentType:"application/json;charset=UTF-8",
                        success : function(data) {
                            if(status==1) {

                                window.location.href="recharge.jsp";
                            }
                            else if(status==0){
                                alert("注册成功，请先完善会员信息")
                                window.location.href="updateVipMsg.jsp";
                            }
                        }

                    });
                }

            });

        }
        else{
            alert("验证码错误");
        }

    });

</script>
</html>
