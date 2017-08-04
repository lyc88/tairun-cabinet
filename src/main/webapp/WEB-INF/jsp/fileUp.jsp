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
<link type="text/css"  rel="stylesheet"  href="${proPath}/static/Uploadtify/uploadify.css"/>
<script type="text/javascript" src="${proPath}/static/Uploadtify/jquery.uploadify.js"></script>
<html>
<head>
    <title>Title</title>
    <script>
    function clicked(){
            $("#upload").uploadify(
                {
                    swf: "${proPath}/static/Uploadtify/uploadify.swf",
                    url: "${proPath}/file/fileUp",
                    fileObjName: "uploadFile",  // 控制器中参数名称
                    //auto: true,
                    multi: true,
                    fileSizeLimit: "1024KB",
                    fileTypeExts: "*.jpg;*.gif;*.png;",
                    onUploadSuccess: function (file, result, response) {
                        if (result) {
                           // 设置图片路径
                           $("#img").attr("src", result);
                          }
                    }
                });
               };
    </script>
</head>
<body>
    <div class="container">
        <div id="fileQueue"></div>
        <input type="file" name="uploadify" id="upload"/>
        <div id="fileList">
        </div>
        <button id="btn" onclick="clicked()" value="upload">upload</button>
    </div>
</body>
</html>
