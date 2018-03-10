<%-- 
    Document   : addproduct
    Created on : Sep 5, 2017, 5:42:45 PM
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
        <form class="products-margin" method="post" action="addproduct" enctype="multipart/form-data">
            Name: <input type="text" name="name" value="${product.name}" /><br /><br />
            Price: <input type="text" name="price" value="${product.price}" /><br /><br />
            <select name="category">
                <c:forEach items="${categories}" var="category">
                    <option <c:if test="${category.id == product.category}">selected</c:if>value="${category.id}">${category.name}</option>
                </c:forEach>
            </select><br /><br />
            <input type="file" name="photo" /><br /><br />
            <input type="submit" name="Add" value="Add Product" />
        </form>
    </body>
</html>
