<%-- 
    Document   : orders
    Created on : Sep 5, 2017, 4:50:57 PM
    Author     : Dusan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="menu.jsp" %>
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../resources/style.css" />
    </head>
    <body>
        <table border="1" class="products-margin">
            <tr><td class="products-margin">id</td><td class="products-margin">userdata</td><td class="products-margin">ordertime</td><td class="products-margin">products</td></tr>
            <c:forEach items="${orders}" var="orders">
                <tr><td>${orders.id}</td><td>${orders.userdata}</td><td>${orders.ordertime}</td><td>${orders.products}</td></tr>
            </c:forEach>
        </table>
    </body>
</html>
