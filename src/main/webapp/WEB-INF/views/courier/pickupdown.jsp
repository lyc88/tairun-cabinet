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
    <title>快递员存件列表</title>
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
</blockquote>
<div class="layui-form news_list">
    <table class="layui-table">
        <colgroup>
            <col width="15%" >
            <col  width="15%">
            <col  width="15%">
            <col  width="15%">
            <col  width="15%">
            <col  width="15%">
        </colgroup>
        <thead>
        <tr>
            <%--<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>--%>
            <th>账户</th>
            <th>自提柜号</th>
            <th>注册时间</th>
            <th>联系号码</th>
            <th>柜子号</th>
            <th>订单类型</th>
        </tr>
        </thead>
        <tbody class="content"></tbody>
    </table>
</div>
<div id="page"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui1.9/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<%--<script type="text/javascript" src="${ctx}/static/layui1.9/js/courierList.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/template-web.js"> </script>

<script  id="content" type="text/html">
    {{each list obj}}
    <tr>
        <td>{{obj.acount}}</td>
        <td>{{obj.code}}</td>
        <td>{{obj.createdate}}</td>
        <td>{{obj.waybillnumbrer}}</td>
        <td>{{obj.boxnumber}}</td>
        <td>{{obj.ordertype}}</td>
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

        function paging(curr,tel){
            var nums = 10;
            var pageLoading = layer.load(2);

            $.ajax({
                url : '/pickupd/pickupdown',
                type : 'post',
                data :{
                    page: curr || 1 ,   //当前页
                    rows: nums,         //每页显示四条数据
                    telephone:tel,
                },
                success : function(data) {
                    // layer.close(index);
                    console.log(data);
                    $.each(data.rows, function (index, item) {
                        var createdate =new Date(item.createdate);
                        item.createdate = formatDate(createdate);
                    });
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
    });
    function formatDate(now) {
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var date = now.getDate();
        return year + "-" + fixZero(month, 2) + "-" + fixZero(date, 2);
    }

    //时间如果为单位数补0
    function fixZero(num, length) {
        var str = "" + num;
        var len = str.length;
        var s = "";
        for (var i = length; i-- > len;) {
            s += "0";
        }
        return s + str;
    }
</script>


</body>
</html>
