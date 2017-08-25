<%--
  Created by IntelliJ IDEA.
  User: THINK
  Date: 2017/8/13
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>日志管理</title>
    <link rel="stylesheet" href="${ctx}/static/layui1.9/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
    <link rel="stylesheet" href="${ctx}/static/layui1.9/css/news.css" media="all"/>
</head>
<body class="childrenBody">
<div class="layui-form news_list">
    <table class="layui-table">
        <colgroup>
            <col width="15%">
            <col width="10%">
            <col width="10%">
            <col width="15%">
        </colgroup>
        <thead>
        <tr>
            <%--<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>--%>
            <th>充值账号</th>
            <th>充值金额</th>
            <th>充值状态</th>
            <th>充值订单号</th>
        </tr>
        </thead>
        <tbody class="content"></tbody>
    </table>
</div>
<div id="page"></div>
<script type="text/javascript" src="${ctx}/static/layui1.9/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<%--<script type="text/javascript" src="${ctx}/static/layui1.9/js/courierList.js"></script>--%>
<script type="text/javascript" src="${ctx}/static/js/template-web.js"></script>

<script id="content" type="text/html">
    {{each list obj}}
    <tr>
        <td>{{obj.name}}</td>
        <td>{{obj.num}}</td>
        <td>{{obj.status}}</td>
        <td>{{obj.orderid}}</td>
    </tr>
    {{/each}}
</script>

<script type="text/javascript">
    layui.use(['form', 'laypage'], function () {

        var $ = layui.jquery,
            form = layui.form(),
            laypage = layui.laypage;
        function paging(curr) {
            var nums = 10;

            $.ajax({
                url: '/prepaid/list',
                type: 'post',
                data: {
                    page: curr || 1,   //当前页
                    rows: nums     //每页显示四条数据
                },
                success: function (data) {
                    console.log(data);
                    var pdata = data.rows;
                    var d = {
                        list: pdata
                    };
                    var html = template('content', d);

                    $(".content").html(html);
                    form.render();
                    //分页layui
                    laypage({
                        cont: 'page',
                        pages: Math.ceil(data.total / nums),
                        curr: curr || 1, //当前页
                        skip: true,
                        jump: function (obj, first) { //触发分页后的回调
                            if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                $(".content").text('');//先清空原先内容
                                var tel = $("#code").val();
                                var name = $("#name").val();
                                paging(obj.curr);
                            }
                        }
                    });
                }
            });
        }
        paging(1);
     })
</script>

</body>
</html>
