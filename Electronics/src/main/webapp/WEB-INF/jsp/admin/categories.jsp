
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
        
        <form class="products-margin" action="updatecategory" method="post">
            
            <select onchange="if(this.value != -1) window.location = './categories?id=' + this.value" name="id">
                <option value="-1">Select category: </option><br />
                <c:forEach items="${categories}" var="category">
                    <option <c:if test="${category.id == selectedCategory.id}">selected</c:if>value="${category.id}">${category.name}</option>
                </c:forEach>
            </select><br /><br />
            Name: <input type="text" name="name" value="${selectedCategory.name}"/><br /><br />
            Description: <input type="text" name="description" value="${selectedCategory.description}"/><br /><br />
            <input type="submit" name="update" value="Update" />
        </form>
    </body>
</html>
