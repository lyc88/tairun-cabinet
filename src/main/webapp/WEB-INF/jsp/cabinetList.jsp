<%--
Created by IntelliJ IDEA.
User: cyt
Date: 2017/7/31
Time: 10:33
To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/common.jsp" %>
<html>
<head>
            <title>Title</title>
            <script type="text/javascript">
                $(function (index, rows) {
                    $('#firstdatagrid').datagrid({
                        url: '${proPath}/selfCabinet/showSelfcabinetList',
                        pagination: true,
                        pageSize: 5,
                        pageList: [5, 10, 15],
                        fitColumns: true,
                        nowrapL: true,
                        onClickCell: showCabinet,
                        columns: [[
                            {field: 'code', title: '自提柜编号', width: 100},
                            {field: 'info', title: '自提柜信息', width: 100},
                            {field: 'updateStr', title: '是否更新', width: 100},
                            {field: 'imgId', title: '图片编号', width: 100},
                            {field: 'imgUpdateStr', title: '图片是否更新', width: 100},
                            {field: 'createDateStr', title: '创建时间', width: 100},
                            {field: 'updateDateStr', title: '更新时间', width: 100},
                            {field: 'name', title: '名字', width: 100},
                            {field: 'find', title: '查看', width: 100},
                            {field: 'upImg', title: '上传', width: 100}
                        ]]
        });
        });
        function showCabinet(index, filed,value) {
            if(filed =="find" ){
                var row = $(this).datagrid("getRows")[index].code;
                $('#windatagrid').datagrid({
                    url: '${proPath}/cabinet/showListAll?code='+row+'&status='+1,
                    pageSize: 5,
                    pagination: true,
                    pageList: [5, 10, 15],
                    fitColumns: true,
                    nowrapL: true,
                    toolbar: [{
                        text: "自提柜状态：<select name='status' id='zstatus'>" +
                        "<option value='0'>未使用</option>" +
                        "<option value='1'>已使用</option>" +
                        "</select>"
                    }, {
                    text: "<input type='submit' value='查询' onclick='doSearch()'/>"
                }],
                    columns: [[
                        {field: 'code', title: '编号', width: 100},
                        {field: 'number', title: '自提柜编号', width: 100},
                        {field: 'statusStr', title: '自提柜状态', width: 100},
                        {field: 'courierTelephone', title: '快递员电话', width: 100},
                        {field: 'createDateStr', title: '创建时间', width: 100},
                        {field: 'beginDateStr', title: '存货时间', width: 100},
                        {field: 'endDateStr', title: '取货时间', width: 100},
                        {field: 'contactsTelephone', title: '联系人电话', width: 100},
                        {field: 'operation', title: '操作', width: 100,
                            formatter: function (value, rec, index) {
                                var operate = '<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-remove\'" >清空</a>||<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-remove\'" >打开</a>';
                                return operate;
                            }}
                    ]]
                });
                $('#win').window();
            }
        }

        function doSearch() {
            $('#windatagrid').datagrid('load', {
                status:$('#zstatus').val()
            });
        }

    function find() {
    showCabinet();
    }

    function upImg() {
    $('#upload').window();
    }
    </script>
</head>
<body>


<div>
    <table id="firstdatagrid">
    </table>
    <div id="win" style="width: 1000px;height:400px">
        <table id="windatagrid"></table>
    </div>
    <div id="upload" style="width: 1000px;height:600px;display: none">
        <jsp:include page="fileUp.jsp"></jsp:include>
    </div>
</div>
</body>
</html>
