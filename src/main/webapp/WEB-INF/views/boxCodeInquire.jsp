<%--
  Created by IntelliJ IDEA.
  User: THINK
  Date: 2017/8/13
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>开箱码查询</title>
    <link rel="stylesheet" href="${ctx}/static/layui1.9/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
    <link rel="stylesheet" href="${ctx}/static/layui1.9/css/news.css" media="all"/>
</head>
<body class="childrenBody">
<blockquote class="layui-elem-quote news_search">
    <div class="layui-inline">
        <div class="layui-input-inline">
            <input type="text" id="tel" value="" placeholder="请输入运单号" class="layui-input search_input">
        </div>

        <a class="layui-btn search_btn">查询</a>
    </div>
</blockquote>
</div>
<div id="page"></div>
<script type="text/javascript" src="${ctx}/static/layui1.9/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<%--<script type="text/javascript" src="${ctx}/static/layui1.9/js/courierList.js"></script>--%>
<script type="text/javascript" src="${ctx}/static/js/template-web.js"> </script>

<script type="text/javascript">
    layui.use(['form', 'laypage', 'layer'], function () {

        // var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        var $ = layui.jquery,
            form = layui.form(),
            laypage = layui.laypage,
            layer = layui.layer;


        //查询
        $(".search_btn").click(function(){
            var tel = $("#tel").val();
            if( tel != ''){
                $.ajax({
                    type: 'POST',
                    url: '/orderchargs/toNumber',
                    data: {"tel":tel},
                    success: function(data){
                        if(null!=data){
                            if(data[0] == null || data[0] == ""){
                                $("#selectcatinetid tr:not(:first)").remove();
                                var inner = "<tr>" +
                                    "<td width='50%'>查不到</td></tr>";
                                $(inner).appendTo("#selectcatinetid");
                            }else{
                                $("#selectcatinetid tr:not(:first)").remove();
                                var inner = "<tr>" +
                                    "<td width='50%'>"+data[0].boxPassword+"</td></tr>";
                                $(inner).appendTo("#selectcatinetid");
                            }

                        }

                    }

                });
                layer.open({
                    type: 1,
                    content: $('#selectcatinet') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                });

                // layer.close(index);
            }else{
                layer.msg("请输入需要查询的内容");
            }
        })
    });

</script>
<div id="selectcatinet" style="display: none" >
    <div class="layui-box layui-upload-button" style="height:110px;width:230px;overflow-y: auto;">
        <div class="placeholder">
            <table id="selectcatinetid" class="layui-table">
                <th>该柜子的开箱码</th>
            </table>
        </div>
    </div>
</div>
</body>
</html>
