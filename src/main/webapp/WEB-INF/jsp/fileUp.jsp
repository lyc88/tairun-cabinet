<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="showCaption" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: THINK
  Date: 2017/7/31
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/common.jsp" %>
<link type="text/css"  rel="stylesheet"  href="${proPath}/static/css/bootstrap.min.css"/>
<link type="text/css"  rel="stylesheet"  href="${proPath}/static/bootstrap-fileinput-master/css/fileinput.min.css"/>
<script type="text/javascript" src="${proPath}/static/bootstrap-fileinput-master/js/fileinput.js"></script>
<script type="text/javascript" src="${proPath}/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${proPath}/static/bootstrap-fileinput-master/js/locales/zh.js"></script>
<html>
<head>
    <title>Title</title>
    <script>
        $("#upload")
            .fileinput(
                {
                    uploadUrl : "file/fileUp",
                    uploadAsync : true,
                    maxFileCount : 5,
                    initialPreview : [
                    '<img src="" id="img" class="file-preview-image" width="320px" height="180px" />',
                ]
                })
            .on(
                'fileuploaded',
                function(event, data, previewId, index) {
                    var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
                    var url = $("#url").val();
                    if (response.data != null && response.data != "") {
                        url = url+response.data+",";
                        $("#url").val(url);
                        //alert(4638721478);
                    } else {
                        alert("图片服务器异常，请重新上传！");
                    }
                });
    </script>
</head>
<body>
<form enctype="multipart/form-data">
    <label class="control-label">Select File</label>
    <input id="upload" type="file" class="file" multiple class="file-loading" showCaption: false>
    <input type="hidden" id="url" name="helpUrl"/>
</form>
</body>
</html>
