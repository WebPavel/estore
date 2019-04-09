<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/9
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>添加商品</title>
    <script type="text/javascript">
        function checkForm() {
            var f1 = checkNotNull("name");
            var f2 = checkNotNull("price");
            var f3 = checkNotNull("stock");
            var f4 = checkNotNull("description");
            return f1 && f2 && f3 && f4;
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
</head>
<body>
<form action="${pageContext.request.contextPath}/product?method=add" method="post" onsubmit="return checkForm();" enctype="multipart/form-data">
    <table width="60%" border="1" align="center">
        <tr>
            <td>商品名称</td>
            <td><input type="text" name="name" id="name"><span id="name_message"></span></td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td><input type="text" name="price" id="price"><span id="price_message"></span></td>
        </tr>
        <tr>
            <td>商品类别</td>
            <td>
                <select name="category">
                    <option>请选择类别</option>
                    <option value="图书文娱">图书文娱</option>
                    <option value="家用电器">家用电器</option>
                    <option value="男装女装">男装女装</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>库存</td>
            <td><input type="text" name="stock" id="stock"><span id="stock_message"></span></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td><input type="file" name="f"></td>
        </tr>
        <tr>
            <td>商品描述</td>
            <td><textarea rows="10" cols="30" name="description" id="description"></textarea><span id="description_message"></span></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="添加">&nbsp;&nbsp;
                <input type="reset" value="取消">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
