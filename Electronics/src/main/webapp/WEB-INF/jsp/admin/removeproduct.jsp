<%-- 
    Document   : updateproduct
    Created on : Sep 5, 2017, 3:29:39 PM
    Author     : Dusan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <form class="products-margin" method="post" action="removeproduct" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${product.id}" />
            Name: <p>${product.name}</p><br /><br />
            Price: <p>${product.price}</p><br /><br />
            Category: <p>${product.category}</p><br /><br />
            <img class="products" src="../resources/images/${product.photo}"/><br /><br />
            <input type="submit" name="Remove" value="Remove Product" />
        </form>
    </body>
</html>
