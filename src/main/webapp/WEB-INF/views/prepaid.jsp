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
<form action="/alipay/alipayapi" class="alipayform" method="POST" target="_blank">
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
<fieldset class="layui-elem-field site-demo-button">
    <legend style="font-size: 50px;">请选择你要充值的金额</legend>
    <div class="element">
        <button class="layui-btn" style="width: 200px;height: 100px;" onclick="shi()"><a style="font-size: 50px;">10元</a></button>
        <button class="layui-btn layui-btn-normal" style="width: 200px;height: 100px;" onclick="er()"><a style="font-size: 50px;">20元</a></button>
        <button class="layui-btn layui-btn-warm" style="width: 200px;height: 100px;" onclick="wu()"><a style="font-size: 50px;">50元</a></button>
        <button class="layui-btn layui-btn-danger" style="width: 200px;height: 100px;" onclick="bai()"><a style="font-size: 50px;">100元</a></button>
        <div class="element">
            <div class="einput"><input type="hidden" name="WIDout_trade_no" id="out_trade_no"></div>
        </div>

        <div class="element">
            <div class="einput"><input type="hidden" name="WIDsubject" value="test商品123"></div>
        </div>
        <div class="element">
            <div class="einput"><input type="hidden" id="bb" name="WIDtotal_fee" value="8.00"></div>
        </div>
        <div class="element">
            <div class="einput"><input type="hidden" name="WIDbody" value="即时到账测试"></div>
        </div>
    </div>
</fieldset>
</form>
</body>
<script>

    var even = document.getElementById("licode");
    var showqrs = document.getElementById("showqrs");
    even.onmouseover = function(){
        showqrs.style.display = "block";
    }
    even.onmouseleave = function(){
        showqrs.style.display = "none";
    }

    var out_trade_no = document.getElementById("out_trade_no");

    //设定时间格式化函数
    Date.prototype.format = function (format) {
        var args = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
        };
        if (/(y+)/.test(format))
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var i in args) {
            var n = args[i];
            if (new RegExp("(" + i + ")").test(format))
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
        }
        return format;
    };

    out_trade_no.value = 'test'+ new Date().format("yyyyMMddhhmmss");
    function shi(){
        var oTextbox1=document.getElementById("bb");
        oTextbox1.value=10;
    }
    function er(){
        var oTextbox1=document.getElementById("bb");
        oTextbox1.value=20;
    }
    function wu(){
        var oTextbox1=document.getElementById("bb");
        oTextbox1.value=50;
    }
    function bai(){
        var oTextbox1=document.getElementById("bb");
        oTextbox1.value=100;
    }
    function ba(){
        var oTextbox1=document.getElementById("bb");
        oTextbox1.value=0.01;
    }
</script>
</html>
