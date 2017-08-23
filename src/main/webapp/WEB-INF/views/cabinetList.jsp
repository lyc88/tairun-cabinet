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
    <title>自提柜管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui1.9/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui1.9/css/news.css" media="all"/>
</head>
<body class="childrenBody">
<blockquote class="layui-elem-quote news_search">
    <div class="layui-inline">
        <div class="layui-input-inline">
            <input type="text" id="code" value="" placeholder="柜子编号" class="layui-input search_input">
        </div>

        <div class="layui-input-inline">
            <input type="text" id="name" value="" placeholder="柜子名称" class="layui-input search_input">
        </div>
        <a class="layui-btn search_btn">查询</a>
    </div>
</blockquote>
<div class="layui-form news_list">
    <table class="layui-table">
        <colgroup>
            <col width="10%">
            <col width="20%">
            <col width="20%">
            <col width="35%">
            <col width="10%">
        </colgroup>
        <thead>
        <tr>
            <%--<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>--%>
            <th style="text-align:left;">柜子编号</th>
            <th>柜子名称</th>
            <th>温度等信息</th>
            <th>轮播图</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody class="content"></tbody>
    </table>
</div>
<div id="page"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui1.9/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<%--<script type="text/javascript" src="${ctx}/static/layui1.9/js/courierList.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/template-web.js"></script>

<script id="content" type="text/html">
    {{each list obj}}
    <tr>
        <td>{{obj.code}}</td>
        <td>{{obj.name}}</td>
        <td>{{obj.info}}</td>
        <td id="{{obj.code}}">
            {{each obj.files file}}
                <img class="imgDiv" style="width: 70px;height:70px;" src="${pageContext.request.contextPath}/{{file.filePath}}\{{file.fileName}}" data-imageId = "{{file.id}}">
            {{/each}}
        </td>
        <td><a class="layui-btn layui-btn-mini upload_file"><i class="layui-icon"></i> 上传固件</a>
            <a class="layui-btn layui-btn-mini news_edit"><i class="layui-icon"></i> 上传图片</a><a
                class="layui-btn layui-btn-normal layui-btn-mini news_collect"><i class="iconfont icon-edit"></i> 查看信息</a></td>
    </tr>
    {{/each}}
</script>

<script type="text/javascript">
    layui.use(['form', 'laypage', 'layer', 'upload'], function () {

        // var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        var $ = layui.jquery,
            form = layui.form(),
            laypage = layui.laypage,
            layer = layui.layer,
            upload = layui.upload;

        function paging(curr, tel, username) {
            var nums = 10;
            var pageLoading = layer.load(2);

            $.ajax({
                url: '/selfCabinet/showSelfcabinetList',
                type: 'post',
                data: {
                    page: curr || 1,   //当前页
                    rows: nums,         //每页显示四条数据
                    code: tel,
                    name: username
                },
                success: function (data) {
                    $.each(data.rows, function (index, item) {
                        $.each(item.files, function (index1, item1) {
                            var url =item1.filePath;
                            item1.filePath = format(url);
                        });
                    });
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
                        pages: Math.ceil(data.total / nums),
                        curr: curr || 1, //当前页
                        skip: true,
                        jump: function (obj, first) { //触发分页后的回调

                            if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                $(".content").text('');//先清空原先内容
                                var tel = $("#code").val();
                                var name = $("#name").val();
                                paging(obj.curr, tel, name);
                            }
                        }
                    });
                }
            });
        }
        paging(1, '', '');
        function format(aaa) {
            var fileurl=aaa.substr(aaa.length-6,aaa.length);
            return fileurl;
        }
        //查询
        $(".news_collect").click(function () {
            var tel = $("#code").val();
            var name = $("#name").val();
            if (tel != '' || name != '') {
                paging(1, tel, name);
                // layer.close(index);
            } else {
                layer.msg("请输入需要查询的内容");
            }
        });
        //操作
        var index;
        $("body").on("click", ".news_edit", function () {
            var cabinet_id = $(this).parent().parent().children("td:first-child").text();
            index='';
            //编辑
            //layer.alert('您点击了友情链接编辑按钮，由于是纯静态页面，所以暂时不存在编辑内容，后期会添加，敬请谅解。。。',{icon:6, title:'友链编辑'});
            index = layer.open({
                type: 1,
                content: $('#file') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            });
            //上传
            layui.upload({
                url: '/fileUp',
                before:function (input) {
                    var data = {"cabinetId":cabinet_id,"type":0}; //type:0 图片
                    extra_data(input,data);
                }
                , success: function (res) {
                    //console.log(res); //上传成功返回值，必须为json格式
                    //var html = "<div><img src='file:///"+res.src+"' /></div>" res.src,/static/layui1.9/images/face.jpg'
                    var html = "<img class='imgDiv' src='"+res.src+"' width='70' height='70' data-imageId='"+res.imageId+"' />"
                    $(html).appendTo("#"+cabinet_id+"");
                    layer.close(index)
                }
            });
        });
        /**
         * 上传文件
         */
        var bbb;
        $("body").on("click", ".upload_file", function () {
            var cabinet_id = $(this).parent().parent().children("td:first-child").text();
            $("#cabinetId").val(cabinet_id);
            bbb = '';
            //编辑
            //layer.alert('您点击了友情链接编辑按钮，由于是纯静态页面，所以暂时不存在编辑内容，后期会添加，敬请谅解。。。',{icon:6, title:'友链编辑'});
            bbb = layer.open({
                type: 1,
                content: $('#file_gujian') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            });
        });

        $("#upload_gujian").on('click',function(){
            var fileName = $("#gujianFile").val();
            if(fileName == "" || fileName == undefined) {
                alert("请选择要上传的文件！");
                return;
            }
            layer.close(bbb);
            var formData = new FormData($("#form_gujian")[0]);
            $.ajax({
                url : "/fileUp",
                type : 'POST',
                data : formData,
             // 告诉jQuery不要去处理发送的数据
                processData : false,
                // 告诉jQuery不要去设置Content-Type请求头
                contentType : false,
                success : function(data) {
                    alert(data.msg);
                },
                error : function(data) {
                    alert(data.msg);
                }
            });
        })

        function extra_data(input,data){
            var item=[];
            $.each(data,function(k,v){
                item.push('<input type="hidden" name="'+k+'" value="'+v+'">');
            })
            $(input).after(item.join(''));
        }
        $("body").on("click", ".news_collect", function () {
            var cabinet_id = $(this).parent().parent().children("td:first-child").text();
            $.ajax({
                url:"/selectcabinet_id",
                type:"POST",
                data:{"code":cabinet_id},
                success:function(data){
                    $("#selectcatinetid tr:not(:first)").remove();
                    $.each(data, function(idx, obj) {
                        if(obj.status == 1){
                            var status = "使用中";
                        }else if(obj.status == 0){
                            var status = "空闲中";
                        }
                        var inner = "<tr>" +
                            "<td width='50%'>"+status+"</td><td width='50%'>"+obj.number+"</td></tr>";
                        $(inner).appendTo("#selectcatinetid");
                    });
                    layer.open({
                        type: 1,
                        content: $('#selectcatinet') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                    });
                },

            })
        })
        var imgDiv;
        var imgId_this;
        $("body").on('click','.imgDiv',function(){
            imgId_this= $(this);
            var imgSrc = $(this).attr("src");
            var imgId = $(this).attr("data-imageId");
            $("#imgId_delete").attr("src",imgSrc);
            $("#imgId_delete").attr("data-imageId",imgId);
            imgDiv = layer.open({
                type: 1,
                content: $('#deleteImage') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            });
        })

        $("#DelImg_btn").click(function(){
            //拿到要删除的图片的id
            var imgId = $("#imgId_delete").attr("data-imageId");
//            alert(imgId);
            $.ajax({
                url:"/deleteImage",
                type:"POST",
                data:{"imageId":imgId},
                success:function(data){
                    alert(data.msg);
                    //删除图片，关闭框
                    layer.close(imgDiv);
                    $(imgId_this).remove();
                },
                error:function(data){

                }
            })
        })
    });

</script>

<div id="file" style="display: none">
    <div class="layui-box layui-upload-button">
        <form target="layui-upload-iframe" method="post" key="set-mine" enctype="multipart/form-data" action="/fileUp">
            <input type="hidden" name="'+k+'" value="'+v+'">
            <input type="file" name="file" class="layui-upload-file">
        </form>
        <span class="layui-upload-icon"><i class="layui-icon"></i>上传图片</span>
    </div>
</div>
<div id="file_gujian" style="display: none">
    <div class="layui-box layui-upload-button">
        <form id="form_gujian" methtod="post" key="set-mine" enctype="multipart/form-data" action="/fileUp">
            <input type="hidden" name="'+k+'" value="'+v+'">
            <input type="hidden" name="cabinetId" id="cabinetId" />
            <input type="hidden" name="type" value="1" />
            <input type="file" name="file" id="gujianFile" />
        </form>
        <span class="layui-upload-button"><i class="layui-upload-file"></i>选择文件</span>
    </div>
    <span class="layui-upload-button" id="upload_gujian"><i class="layui-upload-file"></i>上传</span>
</div>

<div id="deleteImage" style="display: none">
    <div class="layui-box layui-upload-button" style="height:260px;">
        <img id="imgId_delete" src="" width="200px" height="200px" /><br />
        <button id="DelImg_btn" class="layui-upload-button" data-image="" style="float:right;mrgin-top:10px;margin-right:10px;">
            确定删除该图片？
        </button>
    </div>
</div>
<div id="selectcatinet" style="display: none" >
    <div class="layui-box layui-upload-button" style="height:300px;width:360px;overflow-y: auto;">
        <div class="placeholder">
            <table id="selectcatinetid" class="layui-table">
                <th width="50%">状态</th>
                <th width="50%">柜子号</th>
            </table>
        </div>
    </div>
</div>
</body>

</html>
