<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/7
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>注册成功</title>
    <meta http-equiv="refresh" content="3; url=${pageContext.request.contextPath}/index.jsp">
</head>
<body>
<h2>注册成功，<span id="second">3</span>秒后跳转到<a href="${pageContext.request.contextPath}/index.jsp">首页</a></h2>
</body>
<script type="text/javascript">
    var interval;
    window.onload = function () {
        // 设置1秒调用一次func函数
        interval = window.setInterval("func()", 1000);
    };
    function func() {
        var time = document.getElementById("second").innerHTML;
        if (time == 0) {
            window.clearInterval(interval);
            return;
        }
        document.getElementById("second").innerHTML = (time - 1);
    }
</script>
</html>
