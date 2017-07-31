<%--
  Created by IntelliJ IDEA.
  User: cyt
  Date: 2017/7/19
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/common.jsp" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        $(function () {
            $('#firstdatagrid').datagrid({
                url: '${proPath}/selfCabinet/showSelfcabinetList',
                pagination: true,
                pageSize: 5,
                pageList: [5, 10, 15],
                fitColumns: true,
                nowrapL: true,
                onSelect:showCabinet,
                columns: [[
                    {field: 'code', title: '自提柜编号', width: 100},
                    {field: 'info', title: '自提柜信息', width: 100},
                    {field: 'update', title: '是否更新', width: 100},
                    {field: 'imgId', title: '图片编号', width: 100},
                    {field: 'imgUpdate', title: '图片是否更新', width: 100},
                    {field: 'createDateStr', title: '创建时间', width: 100},
                    {field: 'updateDateStr', title: '更新时间', width: 100},
                    {field: 'name', title: '名字', width: 100}
                ]]
        });
        });
        function showCabinet(index, rows) {
            var row = $(this).datagrid("getRows")[index].code;
            $('#windatagrid').datagrid({
                url: '${proPath}/cabinet/showListAll?code=' + row,
                pageSize: 5,
                pagination: true,
                pageList: [5, 10, 15],
                fitColumns: true,
                nowrapL: true,
                toolbar: [{
                    text: "自提柜状态：<select name='status' id='zstatus'>" +
                    "<option value='-1'>全部</option>" +
                    "<option value='0'>未使用</option>" +
                    "<option value='1'>已使用</option>" +
                    "</select>"
                }, {
                    text: "<input type='button' value='查询' onclick='doSearch()'/>"
                }],
                columns: [[
                    {field: 'code', title: '编号', width: 100},
                    {field: 'number', title: '自提柜编号', width: 100},
                    {field: 'status', title: '自提柜状态', width: 100},
                    {field: 'courierTelephone', title: '快递员电话', width: 100},
                    {field: 'createDateStr', title: '创建时间', width: 100},
                    {field: 'beginDateStr', title: '存货时间', width: 100},
                    {field: 'endDateStr', title: '取货时间', width: 100},
                    {field: 'contactsTelephone', title: '联系人电话', width: 100},
                    {field: '', title: '操作', width: 100}
                ]]
            });
            $('#win').window();
        }

        function doSearch() {
            $('#windatagrid').datagrid('load', {
                status: $('#zstatus').val()
            });
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
</div>
</body>
</html>
