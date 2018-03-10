
<%@include file="top.jsp" %>

	  <div class="center_content">
	  <div class="center_title_bar">Latest Products</div>
                
<c:forEach items="${products}" var="product">
    <div class="prod_box">
        <div class="top_prod_box"></div>
        <div class="center_prod_box">
          <div class="product_title">
          	<a href="">${product.name}</a>
          </div>
          <div class="product_img">
          	<a href="">
          		<img src="resources/images/${product.photo}" alt="" border="0" /></a>
          </div>
          <div class="prod_price">
          	<span class="price">${product.price} $</span>
          </div>
        </div>
        <div class="bottom_prod_box"></div>
        <div class="prod_details_tab"> 
        	
        	<a href="tocart/${product.id}" class="prod_details">BUY NOW</a> 
        </div>
      </div>
</c:forEach>
      
      
      </div>
          <%@include file="bottom.jsp" %>
	  

