<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" href="<%=basePath %>/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath %>/plugins/bootstraptable/bootstrap-table.css">
    <link rel="stylesheet" href="<%=basePath%>/hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>/hplus/css/animate.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>/hplus/css/style.css?v=4.0.0" rel="stylesheet">
    <link href="<%=basePath %>/hplus/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=basePath %>/plugins/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>/css/custom-style/base-css.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>/hplus/css/base.css" rel="stylesheet">
    <link href="<%=basePath%>/plugins/select2/css/select2.min.css" rel="stylesheet"/>
    <link href="<%=basePath%>/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=basePath%>/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
</head>

<script type="text/javascript">
    var basePath = "<%=basePath%>";
</script>
<script src="<%=basePath%>/js/jQuery-2.1.4.min.js"></script>
<script src="<%=basePath%>/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="<%=basePath%>/plugins/bootstraptable/bootstrap-table.min.js"></script>
<script src="<%=basePath%>/plugins/bootstraptable/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=basePath %>/plugins/layer/layer.js"></script>
<script src="<%=basePath %>/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
<script src="<%=basePath %>/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath%>/plugins/select2/js/select2.min.js"></script>
<script src="<%=basePath %>/js/front-extend.js"></script>
<script src="<%=basePath%>/js/util.js"></script>

