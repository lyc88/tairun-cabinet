<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/common/common.jsp"%>
<html>
<head>


    <title></title>
    <style type="text/css">
        .searchbox{
            margin:-3
        }
    </style>

    <script type="text/javascript">
        $(function(){

            $('#dg').datagrid({
                url:'json',
                columns:[[
            {field:'productid',title:'productid',width:100},
            {field:'productname',title:'productname',width:100}
            ]]
            });
        });
    </script>
</head>

<body>

<table id="dg"></table>
</body>
</html>