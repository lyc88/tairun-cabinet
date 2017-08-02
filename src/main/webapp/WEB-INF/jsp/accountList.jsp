<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title></title>
    <style type="text/css">
        .searchbox{
            margin:-3;
        }
    </style>

    <script type="text/javascript">
        $(function(){

            $('#dg').datagrid({
                //url:'${proPath}/supplier/selectPage.action', //通过关键字查询
                //支持多条件查询
                url:'${proPath}/account/list',
                fitColumns:true,
                nowrapL:true,
                idField:'id',
                rownumbers:true,
                pagination:true,
                pageSize:10,
                pageList:[10,15,20],
                queryParams: {
                    name: '%%',
                    telephone:'%%'
                },
                toolbar: [{
                    text:"账号：<input type='text' id='name' name='name'/>",
                }
                    ,'-',{
                        text:"电话：<input type='text' id='telephone' name='telephone'/>",
                    }],

                columns : [ [{
                    checkbox:true,
                }, {
                    field : 'id',
                    title : '快递员编号'
                }, {
                    field : 'telephone',
                    title : '快递员手机',
                    width : 100
                }, {
                    field : 'name',
                    title : '账号',
                    width : 100
                }, {
                    field : 'account',
                    title : '账户',
                    width : 100
                }, {
                    field : 'createDateStr',
                    title : '注册时间',
                    width : 100
                }
                ] ]
            });

            $('#telephone').searchbox({
                searcher:function(value,name){
                    $('#dg').datagrid('load',{
                        name: '%'+$('#name').val()+'%',
                        telephone:'%'+value+'%'
                    });
                },
                prompt:''
            });


        });

    </script>

</head>

<body>

<table id="dg"></table>
</body>
</html>