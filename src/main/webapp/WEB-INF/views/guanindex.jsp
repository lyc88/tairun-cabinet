<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/common.jsp" %>
<html>
<head>
    <title></title>
    <script type="text/javascript">
        $(function () {
            $("a[title]").click(function () {
                var text = this.href;
                //alert(text);
                //判断是否存在
                if ($('#tt').tabs("exists", this.title)) {
                    //存在则选中
                    alert("存在了");
                    $('#tt').tabs("select", this.title);
                } else {
                    $('#tt').tabs('add', {
                        title: this.title,
                        //面板有关闭按键
                        closable: true,
                        //href对远程页面js的支持不好
                        //href: text
                        //可以加载内容填充到选项卡，页面有Js时，也可加载
                        content: "<iframe src='" + text + "' title='" + this.title + "' height='100%' width='100%' frameborder='0px' ></iframe>"
                    });
                }
                return false;

            });
        });


    </script>
</head>
<body class="easyui-layout">
<!-- 头部 -->
<<%--div data-options="region:'north',title:'自提柜后台管理系统',split:true"
     style="height:130px;">欢迎您 ：${user.username}</div>--%>
<!-- 左边导航 -->
<div data-options="region:'west',title:'导航菜单',split:true"
     style="width:200px;background:#0088CC; color:#00c0ef">

    <div id="aa" class="easyui-accordion">

        <div title="快递员信息">


            <!-- list-style: none去左边的点；text-decoration: none：去超链接下划线,title用来区分后继定位这里的超链接 -->
            <ul style="list-style: none;padding: 0px;margin:0px;height:710px;background:#0088CC; color:#00c0ef">
                <li style="padding: 6px;"><a href="${proPath}/pickupd/kuai" title='取件记录'
                                             style="text-decoration: none;display: block;font-weight:bold;font-size: 20px;color:white
                    ;">取件记录</a>
                </li>
                <li style="padding: 6px;"><a href="${proPath}/pickupd/qu" title='存件记录'
                                             style="text-decoration: none;display: block;font-weight:bold;font-size: 20px;color:white;">存件记录</a>
                </li>
                <li style="padding: 6px;"><a href="${proPath}/account/loginwei" title='未领取记录'
                                             style="text-decoration: none;display: block;font-weight:bold; font-size: 20px;color:white;">未领取记录</a>
                </li>
                <li style="padding: 6px;"><a href="${proPath}/account/toPrepaid" title='支付宝充值'
                                             style="text-decoration: none;display: block;font-weight:bold;font-size: 20px;color:white;">支付宝充值</a>
                </li>
            </ul>
        </div>
</div>


</div>

<!-- 主体内容 -->
<div data-options="region:'center',title:'主要信息'"
     style="padding:5px;background:#eee;">
    <div id="tt" class="easyui-tabs" data-options="fit:true"
         style="width:500px;height:250px;">
        <div title="主页" style="padding:20px;"> <img style="height: 100%; width:100%;" src="${ctx}/static/layui1.9/images/timg2.jpg"></div>
    </div>


</div>
<div id="win"></div>

</body>

</html>

