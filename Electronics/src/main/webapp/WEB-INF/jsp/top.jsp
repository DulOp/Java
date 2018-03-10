<!DOCTYPE html>
<html>
<head>
    <base href="/Electronics/" />
<title>Electronics Shop</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="resources/style.css" />
</head>
<body>
   
    <div id="main_container">
	<div id="header">
	    <div id="logo"> <a href=""><img src="resources/images/logo.png" alt="" border="0" width="237" height="140" /></a> 
	    </div>
	    
	</div>
        
        <div id="menu_tab">
      <div class="left_menu_corner"></div>
      <ul class="menu">
        <li><a href="#" class="nav1"> Home</a></li>
        <li class="divider"></li>
        <li><a href="fetch" class="nav1">Cart</a></li>
        <li class="divider"></li>
        <li><a href="#" class="nav1">Products</a></li>
        <li class="divider"></li>
        <li><a href="#" class="nav1">Sign Up</a></li>
        <li class="divider"></li>
        <li><a href="#" class="nav1">Shipping</a></li>
        <li class="divider"></li>
        <li><a href="contact.html" class="nav1">Contact Us</a></li>
        <li class="divider"></li>
        
      </ul>

	<div class="left_content">
	  <div class="title_box">Categories</div>
	    <ul class="left_menu">
                <c:forEach items="${categories}" var="category">
                    <li class="even"><a href="./${category.id}">${category.name}</a></li>
                </c:forEach>
	      
	      </ul>

	      	<div class="title_box">Special Products</div>
		      <div class="border_box">
		        <div class="product_title">
		        	<a href="details.html">Motorola 156 MX-VL</a>
		        </div>
		        <div class="product_img">
		        	<a href="details.html">
		        	<img src="resources/images/laptop.png" alt="" border="0" />
		        	</a>
		       	</div>
		        <div class="prod_price">
			        <span class="reduce">350$</span> 
			        <span class="price">270$</span>
		        </div>
      		</div>

	  </div>
