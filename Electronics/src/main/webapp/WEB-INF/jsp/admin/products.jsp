<%-- 
    Document   : products
    Created on : Sep 5, 2017, 2:30:20 PM
    Author     : Dusan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../resources/style.css" />
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <table border="1" class="products-margin">
            <tr><td>id</td><td>name</td><td>price</td><td>photo</td><td>category</td><td></td><td></td></tr>
            <c:forEach items="${products}" var="product">
                <tr><td>${product.id}</td><td>${product.name}</td><td>${product.price}</td><td><img class="products" src="../resources/images/${product.photo}"/></td><td>${product.category}</td><td><a href="updateproduct?id=${product.id}">Edit</a></td><td><a href="removeproduct?id=${product.id}">Remove</a></td></tr>
            </c:forEach>
                <tr><td colspan="7">
                        <c:forEach begin="1" end="${totalpages}" varStatus="counter">
                            <a href="products?page=${counter.count}">${counter.count}</a>
                        </c:forEach>
                 </td></tr>
        </table><br /><br />
        <div class="products-margin">
            <a href="addproduct">Add Product</a>
        </div>
        
        
    </body>
</html>
