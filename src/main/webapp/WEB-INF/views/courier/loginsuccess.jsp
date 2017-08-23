<%--
  Created by IntelliJ IDEA.
  User: THINK
  Date: 2017/8/17
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择充值金额</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui1.9/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui1.9/css/news.css" media="all"/>
</head>
<body>
<form action="/pickupd/kuai" class="alipayform" method="POST" target="_blank">
    <div class="container black">
        <div class="qrcode">
            <div class="littlecode">
                <img width="16px" src="img/little_qrcode.jpg" id="licode">
                <div class="showqrs" id="showqrs">
                    <div class="shtoparrow"></div>
                </div>
            </div>
        </div>
    </div>
    <div><center><a style="font-size: 30px;">欢迎你：${msg}</a></center></div>
<fieldset class="layui-elem-field site-demo-button">
    <legend style="font-size: 50px;">请选择你要查看的信息</legend>
    <div class="element">
        <button class="layui-btn" style="width: 200px;height: 100px;" onclick="shi()"><a style="font-size: 40px;">取件记录</a></button>
        <button class="layui-btn layui-btn-normal" style="width: 200px;height: 100px;" onclick="er()"><a style="font-size: 40px;">存件记录</a></button>
        <button class="layui-btn layui-btn-warm" style="width: 230px;height: 100px;" onclick="wei()"><a style="font-size: 40px;">未领取记录</a></button>
        <button class="layui-btn layui-btn-danger" style="width: 230px;height: 100px;" onclick="wu()"><a style="font-size: 40px;">支付宝充值</a></button>
    </div>
</fieldset>
</form>
</body>
<script>
    function er(){
        document.forms[0].action="/pickupd/qu";
        document.forms[0].method="post";
        document.forms[0].submit();
    }
    function wu(){
        document.forms[0].action="/account/toPrepaid";
        document.forms[0].method="post";
        document.forms[0].submit();
    }
    function wei(){
        document.forms[0].action="/account/loginwei";
        document.forms[0].method="post";
        document.forms[0].submit();
    }
</script>
</html>
