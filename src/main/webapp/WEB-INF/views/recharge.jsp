<%--
  Created by IntelliJ IDEA.
  User: Lkh
  Date: 2017/8/8
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
     folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/all-skins.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>-->
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<head>
<title>充值页面</title>
</head>
<body>
<div>

    <div style="margin:0 auto;margin-top:20px;width:40%;padding-top:60px;">
        <div id="weixin" style="width:250px;height:550px;float: left;margin-right: 20px;">
            <div id="weixin_top" style="height:115px;">
                <center><img src="${pageContext.request.contextPath}/static/picture/4.png" height="115px" /></center>
            </div>
            <div id="weixin_center" style="background-color:green;height:435px;padding-top:20px;text-align: center;font-size: 20px;">
                <div style="background-color:white;border-radius: 15px;width: 80%;margin-left:25px;">
                    泰润自提柜
                </div>
                <div style="padding-top:30px;">
                    <img src="${pageContext.request.contextPath}/static/picture/erweima.png" width="200px" />
                </div>
                <div style="padding-top:25px;color:white;">
                    微信扫码，向我付款
                </div>
            </div>
        </div>
        <div id="zhifubao" style="width:250px;height:550px;float: right;">
            <div id="zhifubao_top" style="height:115px;">
                <center><img src="${pageContext.request.contextPath}/static/picture/5.png" height="115px" /></center>
            </div>
            <div id="zhifubao_center" style="background-color:dodgerblue;height:435px;padding-top:20px;text-align: center;font-size: 20px;">
                <div style="background-color:white;border-radius: 15px;width: 80%;margin-left:25px;">
                    泰润自提柜
                </div>
                <div style="padding-top:30px;">
                    <img src="${pageContext.request.contextPath}/static/picture/erweima.png" width="200px" />
                </div>
                <div style="padding-top:25px;color:white;">
                    支付宝扫码，向我付款
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
