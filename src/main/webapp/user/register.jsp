<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/6
  Time: 4:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>注册</title>
</head>
<script type="text/javascript">
    function change() {
        var captchaImg = document.getElementById("captchaId");
        captchaImg.src = "${pageContext.request.contextPath}/captcha?time=" + new Date().getTime();
    };
    function checkForm() {
        var f1 = checkNotNull("username");
        var f2 = checkNotNull("password");
        var f3 = checkNotNull("confirm_password");
        var f4 = checkNotNull("nickname");
        var f5 = checkNotNull("email");
        var f6 = checkNotNull("captcha");
        var f7 = document.getElementById("password").value == document.getElementById("confirm_password").value;
        if (!f7) {
            document.getElementById("confirm_password_message").innerHTML = "<font color='red'>两次密码不一致</font>";
        }
        return f1 && f2 && f3 && f4 && f5 && f6 && f7;
    };
    function checkNotNull(fieldName) {
        var value = document.getElementById(fieldName).value;
        var reg = /^\s*$/;
        if (reg.test(value)) {
            document.getElementById(fieldName+"_message").innerHTML = "<font color='red'>"+fieldName+"不能为空</font>";
            return false;
        } else {
            return true;
        }
    }
</script>
<body>
<center>${requestScope["register.message"]}<br>
<%--<c:forEach var="entry" items="${requestScope.map}">--%>
    <%--${entry.value}<br>--%>
<%--</c:forEach>--%>
</center>
<form name="f" action="${pageContext.request.contextPath}/user/register" method="post" onsubmit="return checkForm();">
    <table width="60%" align="center" border="1">
        <caption>用户注册</caption>
        <tr>
            <td>用户名：</td>
            <td>
                <input type="text" name="username" id="username"><span id="username_message">${map["register.username.error"]}</span>
            </td>
        </tr>
        <tr>
            <td>密码：</td>
            <td>
                <input type="password" name="password" id="password"><span id="password_message">${map["register.password.error"]}</span>
            </td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td>
                <input type="password" name="confirm_password" id="confirm_password"><span id="confirm_password_message"></span>
            </td>
        </tr>
        <tr>
            <td>昵称：</td>
            <td>
                <input type="text" name="nickname" id="nickname"><span id="nickname_message">${map["register.nickname.error"]}</span>
            </td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td>
                <input type="text" name="email" id="email"><span id="email_message">${map["register.email.error"]}</span>
            </td>
        </tr>
        <tr>
            <td>验证码：<img src="${pageContext.request.contextPath}/captcha" onclick="change();" id="captchaId"></td>
            <td>
                <input type="text" name="captcha" id="captcha"><span id="captcha_message"></span>
            </td>
        </tr>
        <tr align="center">
            <td colspan="2">
                <input type="submit" value="注册">&nbsp;&nbsp;
                <input type="reset" value="取消">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
