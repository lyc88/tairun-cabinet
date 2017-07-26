<%--
  Created by IntelliJ IDEA.
  User: Administrator
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
            $('#datagrid').datagrid({
                url: '/show',
                pagination: true,
                pageNumber: 1,
                pageSize: 15,
                pageList: [10, 20, 30],
                columns: [[
                    {field: 'id', title: '自提柜编号', width: 100},
                    {field: 'code', title: '编号', width: 100},
                    {field: 'number', title: '自提柜编号', width: 100},
                    {field: 'status', title: '状态', width: 100},
                    {field: 'courierTelephone', title: '快递员账号', width: 100},
                    {field: 'createDate', title: '创建时间', width: 100},
                    {field: 'beginDate', title: '存货时间', width: 100},
                    {field: 'endDate', title: '取货时间', width: 100},
                    {field: 'contactsTelephone', title: '收货人电话', width: 100}
                ]]
             });
        });
    </script>
</head>
<body>

<div>
    <form action="show" method="get">
        <p>自提柜状态：<select name="status">
            <option value="-1" selected="selected">全部</option>
            <option value="0">未使用</option>
            <option value="1">已使用</option>
        </select>
        <input type="submit" value="查询"/></p>
    </form>
</div>

<div>
    <table id="datagrid">
    </table>
</div>
</body>
</html>
