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
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</head>
<body class="childrenBody">
<div class="layui-form news_list">
    <table class="layui-table">
        <colgroup>
            <col width="10%">
            <col width="20%">
            <col width="5%">
        </colgroup>
        <thead>
        <tr>
            <%--<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>--%>
            <th>创建日志时间</th>
            <th>日志内容</th>
            <th>操作</th>
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
        <td>{{obj.createDate}}</td>
        <td>{{obj.info}}</td>
        <td><a class="layui-btn layui-btn-normal layui-btn-mini news_collect" data-log-id="{{obj.id}}"><i class="iconfont icon-edit"></i>删除</a></td>
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
                url: '/logs/list',
                type: 'post',
                data: {
                    page: curr || 1,   //当前页
                    rows: nums     //每页显示四条数据
                },
                success: function (data) {
                    console.log(data);
                    console.log(data.rows);
                    $.each(data.rows, function (index, item) {
                        var createDate =new Date(item.createDate);
                        item.createDate = formatDate(createDate);
                    });
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
                            debugger;
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
        $("body").on("click", ".news_collect", function () {
            var logid = $(this).attr("data-log-id");
            $.ajax({
                url:"/logs/deletebyid",
                type:"POST",
                data:{"logId":logid},
                success:function(data){
                    alert("删除成功");
                    location.replace(location);
                },

            })
        })
     })

    //时间转换
    function formatDate(now) {
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var date = now.getDate();
        var hour = now.getHours();
        var minute = now.getMinutes();
        var second = now.getSeconds();
        return year + "-" + fixZero(month, 2) + "-" + fixZero(date, 2) + "    " + fixZero(hour, 2) + ":" + fixZero(minute, 2) + ":" + fixZero(second, 2);
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
