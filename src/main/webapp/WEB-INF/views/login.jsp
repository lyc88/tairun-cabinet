<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lyc
  Date: 2017/7/27
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>泰润自提柜后台</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
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
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .hide{
            display: none;
        }
    </style>
</head>
<body class="hold-transition login-page">
<div class="login-box" id="rrapp">
    <div class="login-logo">
        <b>泰润自提柜后台</b>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">管理员登录</p>
        <form id="login" method="post" action="/admin/login">
            <div id="error" class='alert alert-danger alert-dismissible  <c:if test="${empty (msg)}">hide</c:if>' >
                <h4 id="msg" style="margin-bottom: 0px;">${msg}</h4>
            </div>
            <div class="form-group has-feedback">
                <input type="text" class="form-control" name="username"  placeholder="账号">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" name="password" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                    </div>
                </div>
                <div class="col-xs-4">
                    <button type="button" class="btn btn-primary btn-block btn-flat" onclick="login();">登录</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- /.login-box -->
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.jqGrid.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js"></script>

<script type="text/javascript">
    //登入
    function login(){
        var usename = $("input[name=username]").val();
        var password = $("input[name=password]").val();
        if(!usename || !password){
            $("#error").removeClass("hide");
            $("#msg").text("用户名或者密码不能为空");
            return false;
        }
        $("#login").submit();
    }
    //按键事件
    document.onkeydown = function (e) {
        e = e || event;
        if (e.keyCode == 13) {  //判断是否单击的enter按键(回车键)
            $("#login").submit();
        }
    }

</script>
<%--<script type="text/javascript">
    var vm = new Vue({
        el:'#rrapp',
        data:{
            username: '',
            password: '',
            captcha: '',
            error: false,
            errorMsg: '',
            src: 'captcha.jpg'
        },
        beforeCreate: function(){
            if(self != top){
                top.location.href = self.location.href;
            }
        },
        methods: {
            refreshCode: function(){
                this.src = "captcha.jpg?t=" + $.now();
            },
            login: function () {
                var data = "username="+vm.username+"&password="+vm.password+"&captcha="+vm.captcha;
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/login",
                    data: data,
                    dataType: "json",
                    success: function(r){
                        if(r.code == 0){//登录成功
                            localStorage.setItem("token", r.token);
                            parent.location.href ='index.html';
                        }else{
                            vm.error = true;
                            vm.errorMsg = r.msg;

                            vm.refreshCode();
                        }
                    }
                });
            }
        }
    });
</script>--%>
</body>
</html>

