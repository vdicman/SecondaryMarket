<%--
  Created by IntelliJ IDEA.
  User: wangshouli
  Date: 17-5-6
  Time: 下午9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List All the Books</title>
</head>
<body>
<c:if test="${!empty user}">
    <p> 欢迎光临：${user} </p>

</c:if>
<table align="center" border="1" cellspacing="0">
    <tr>
        <td>name</td>
        <td>price</td>
        <td>buy it!!!</td>
    </tr>
        <c:forEach items="${booklist}" var="book" varStatus="vst">
            <tr>
               <td>${book.name}</td>
                <td>${book.price}</td>
                <td>
                    <form action="additem" method="post">
                        <input type="hidden" name="bookid" value="${book.id}">
                        <input type="submit" value="购买">
                    </form>
                </td>
            </tr>
        </c:forEach>
</table>
</body>
</html>
