<%--
  Created by IntelliJ IDEA.
  User: THINK
  Date: 2017/8/3
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>快递员列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui1.9/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui1.9/css/news.css" media="all" />

</head>
<body class="childrenBody">
<blockquote class="layui-elem-quote news_search">
    <div class="layui-inline">
        <div class="layui-input-inline">
            <input type="text" id="tel" value="" placeholder="请输入电话号码" class="layui-input search_input">
        </div>

        <div class="layui-input-inline">
            <input type="text" id="name" value="" placeholder="请输入姓名" class="layui-input search_input">
        </div>
        <a class="layui-btn search_btn">查询</a>
    </div>
</blockquote>
<div class="layui-form news_list">
    <table class="layui-table">
        <colgroup>
            <col width="25%" >
            <col  width="25%">
            <col  width="25%">
            <col  width="25%">
        </colgroup>
        <thead>
        <tr>
            <%--<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>--%>
            <th style="text-align:left;">姓名</th>
            <th>电话号码</th>
            <th>账户余额</th>
            <th>注册时间</th>
        </tr>
        </thead>
        <tbody class="content"></tbody>
    </table>
</div>
<div id="page"></div>
<script type="text/javascript" src="${ctx}/static/layui1.9/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<%--<script type="text/javascript" src="${ctx}/static/layui1.9/js/courierList.js"></script>--%>
<script type="text/javascript" src="${ctx}/static/js/template-web.js"> </script>

<script  id="content" type="text/html">
    {{each list obj}}
    <tr>
        <td>{{obj.name}}</td>
        <td>{{obj.telephone}}</td>
        <td>{{obj.account==null?0:obj.account}} 元</td>
        <td>{{obj.createDateStr}}</td>
    </tr>
    {{/each}}
</script>

<script type="text/javascript">
    layui.use(['form', 'laypage', 'layer'], function () {

       // var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        var $ = layui.jquery,
            form = layui.form(),
            laypage = layui.laypage,
            layer = layui.layer;

        function paging(curr,tel,username){
            var nums = 10;
            var pageLoading = layer.load(2);

            $.ajax({
                url : '/account/list',
                type : 'post',
                data :{
                    page: curr || 1 ,   //当前页
                    rows: nums,         //每页显示四条数据
                    telephone:tel,
                    name:username
                },
                success : function(data) {
                   // layer.close(index);
                    var pdata = data.rows;
                    var d = {
                        list: pdata
                    };
                    var html = template('content', d);

                    $(".content").html(html);
                    form.render();
                    layer.close(pageLoading);
                    //分页layui
                    laypage({
                        cont: 'page',
                        pages:  Math.ceil(data.total/nums),
                        curr: curr || 1, //当前页
                        skip: true,
                        jump: function(obj, first){ //触发分页后的回调
                            //debugger;
                            if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                                $(".content").text('');//先清空原先内容
                                var tel = $("#tel").val();
                                var name = $("#name").val();
                                paging(obj.curr,tel,name);
                            }
                        }
                    });
                }
            });
        }
        paging(1,'','');
        //查询
        $(".search_btn").click(function(){
            var tel = $("#tel").val();
            var name = $("#name").val();
            if( tel != '' ||  name!= ''){
                paging(1,tel,name);
               // layer.close(index);
            }else{
                layer.msg("请输入需要查询的内容");
            }
        })
    });

</script>


</body>
</html>
