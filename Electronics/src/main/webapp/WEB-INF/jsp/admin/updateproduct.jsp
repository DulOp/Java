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
        <form class="products-margin" method="post" action="updateproduct" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${product.id}" />
            Name: <input type="text" name="name" value="${product.name}"/><br /><br />
            Price: <input type="text" name="price" value="${product.price}"/><br /><br />
            <select name="category"><br />
                <c:forEach items="${categories}" var="category">
                    <option <c:if test="${category.id == product.category}">selected</c:if>value="${category.id}">${category.name}</option>
                </c:forEach>
            </select><br /><br />
            <input type="file" name="photo" /><br /><br />
            <input type="submit" name="Update" value="Update Product" />
        </form>
    </body>
</html>
