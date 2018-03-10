
package com.shop.controller;

import com.shop.model.Category;
import com.shop.model.CategoryDao;
import com.shop.model.Orders;
import com.shop.model.OrdersDao;
import com.shop.model.Product;
import com.shop.model.ProductDao;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SiteController {
    
    @Autowired
    CategoryDao categoryDao;
    
    @Autowired
    ProductDao productDao;
    
    @Autowired
    OrdersDao ordersDao;
    
    @RequestMapping("/confirm")
    public String confirmOrder(@RequestParam(required = false) String userdata, HttpServletRequest request, ModelMap model) {
        
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        
        
        if (userdata == null) {
            
        } else {
            StringBuilder sb = new StringBuilder();
            
            HttpSession session = request.getSession();
            HashMap<Integer,Product> sessionProducts = (HashMap<Integer,Product>)session.getAttribute("fetch");
            sb.append("[");
            for (Map.Entry<Integer,Product> p : sessionProducts.entrySet()) {
                //products.add(p.getValue());
                sb.append("{\"id\":");
                sb.append(p.getValue().getId());
                sb.append(",\"q\":");
                sb.append(p.getValue().getQuantity());
                sb.append("},");
            }
            String substr = sb.substring(0, sb.length() - 1);
            substr += "]";
            Orders order = new Orders();
            order.setOrdertime(new Date(new java.util.Date().getTime()));
            order.setProducts(substr);
            order.setUserdata(userdata);
            
            ordersDao.save(order);
            
            session.removeAttribute("fetch");
            return "confirmsuccess";
        }
        return "confirm";
    }
    
    @RequestMapping("/remove")
    public String remove(@RequestParam(required = true) int id, HttpServletRequest request, ModelMap model) {
        
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        
        HttpSession session = request.getSession();
        if (session.getAttribute("fetch") != null) {
            HashMap<Integer,Product> products = (HashMap<Integer,Product>)session.getAttribute("fetch");
            if (products.containsKey(id)) {
                products.remove(id);
            }
        }
        
        return "remove";
    }
    
    @RequestMapping("/fetch")
    public String fetch(HttpServletRequest request, ModelMap model) {
        
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        
        List<Product> products = new ArrayList<Product>();
        HttpSession session = request.getSession();
        
        if (session.getAttribute("fetch") != null) {
            HashMap<Integer,Product> sessionProducts = (HashMap<Integer,Product>)session.getAttribute("fetch");
            for (Map.Entry<Integer,Product> p : sessionProducts.entrySet()) {
                products.add(p.getValue());
            }
        }
        
        model.addAttribute("products", products);
        
        return "fetch";
    }
    
    @RequestMapping("/addtocart")
    public String addToCart(ModelMap model, HttpServletRequest request, @RequestParam(required = true) Integer id,@RequestParam(required = true) Integer quantity) {
        
        HttpSession session = request.getSession();
        HashMap<Integer,Product> fetch;
        if (session.getAttribute("fetch") == null) {
            session.setAttribute("fetch", new HashMap<Integer,Product>()); 
        }
        fetch = (HashMap<Integer,Product>)session.getAttribute("fetch");
        
        if (!fetch.containsKey(id)) {
            Product product = productDao.getById(id);
            product.quantity = quantity;
            fetch.put(id, product);
        } else {
            Product productFromCart = fetch.get(id);
            productFromCart.quantity+=quantity;
        }
        
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        
        return "addtocart";
    }
    
    @RequestMapping("/tocart/{id}")
    public String toCart(@PathVariable int id, ModelMap model) {
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        Product product = productDao.getById(id);
        model.addAttribute("product", product);
        return "tocart";
    }
    
    @RequestMapping("/")
    public String index(ModelMap model) {
        
        
        List<Category> categories = categoryDao.find();
        
        model.addAttribute("categories", categories);
        
        return byCategory(1, model);
    }
    
    @RequestMapping("/{id}")
    public String byCategory(@PathVariable int id, ModelMap model) {
        
        List<Category> categories = categoryDao.find();
        List<Product> products = productDao.findByCategory(id);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "index";
    }
}
