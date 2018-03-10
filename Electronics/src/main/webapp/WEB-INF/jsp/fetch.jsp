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
          ${product.quantity}
        </div>
          <form method="post" action="remove">
              <input type="hidden" name="id" value="${movie.id}" /><br /><br />
                    <input type="submit" value="Remove from cart" />
                </form>
        
      </div>
</c:forEach><br /><br />
          <a href="confirm">Confirm Order</a>
      
      </div>
          <%@include file="bottom.jsp" %>
