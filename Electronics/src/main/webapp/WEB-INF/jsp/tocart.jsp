<%@include file="top.jsp" %>

	  <div class="center_content">
	  <div class="center_title_bar">Latest Products</div>
                
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
          <form method="post" action="addtocart">
              <input type="hidden" name="id" value="${product.id}" />
              <input type="number" min="1" name="quantity" /><br /><br />
                    <input type="submit" value="Add to cart" />
                </form>
        
      </div>
      
      
      </div>
          <%@include file="bottom.jsp" %>
