
package com.shop.controller;

import com.shop.model.Category;
import com.shop.model.CategoryDao;
import com.shop.model.Orders;
import com.shop.model.OrdersDao;
import com.shop.model.Product;
import com.shop.model.ProductDao;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    CategoryDao categoryDao;
    
    @Autowired
    ProductDao productDao;
    
    @Autowired
    OrdersDao ordersDao;
    
    @RequestMapping("/")
    public String index() {
        return "admin/index";
    }
    
    @RequestMapping("/orders")
    public String orders(@RequestParam(defaultValue = "1") Integer page, ModelMap model) {
        
        List<Orders> orders = ordersDao.findByPage(page-1);
        model.addAttribute("orders", orders);
        return "admin/orders";
    }
    
    @RequestMapping("/products")
    public String products(@RequestParam(defaultValue = "1") Integer page, ModelMap model) {
        List<Product> products = productDao.findByPage(page-1);
        model.addAttribute("products", products);
        model.addAttribute("totalpages", productDao.pages());
        return "admin/products";
    }
    
    @RequestMapping(value = "addproduct", method = RequestMethod.POST)
    public String addProductPost(
           @RequestParam String name,
           @RequestParam String price,
           @RequestParam Integer category,
           @RequestParam MultipartFile photo,
           ModelMap model,
           MultipartHttpServletRequest request) throws FileNotFoundException, IOException {
        
        List<Category> categories = categoryDao.find();
        
        model.addAttribute("categories", categories);
        
        
        HttpSession session = request.getSession();
        HashMap<Integer,Product> sessionProduct = (HashMap<Integer,Product>)session.getAttribute("products");
        Product product = new Product();
        product.setName(name);
        
        if (photo != null && !photo.isEmpty()) {
            String filepath = request.getServletContext().getRealPath("resources/images");
            FileOutputStream fos = new FileOutputStream(filepath + "/" + photo.getOriginalFilename());
            product.setPhoto(photo.getOriginalFilename());
            fos.write(photo.getBytes());
            fos.close();
        }
        
        product.setCategory(category);
        product.setPrice(new BigDecimal(price));
        productDao.add(product);
        model.addAttribute("product", product);
        
        return "admin/addproduct";
    }
    
    @RequestMapping("/addproduct")
    public String addProduct(ModelMap model) {
        
        List<Category> categories = categoryDao.find();
        
        model.addAttribute("categories", categories);
        
        return "admin/addproduct";
    }
    
    @RequestMapping("/removeproduct")
    public String removeProductFin(@RequestParam Integer id, ModelMap model) {
        
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        
        Product product = productDao.getById(id);
        model.addAttribute("product", product);
        
        productDao.remove(product);
        
        return "admin/removed";
    }
    
    @RequestMapping(value = "updateproduct", method = RequestMethod.POST)
    public String updateProductPost(
            @RequestParam Integer id,
            @RequestParam String name,
            @RequestParam String price,
            @RequestParam Integer category,
            @RequestParam MultipartFile photo,
            ModelMap model,
            HttpServletRequest request) throws FileNotFoundException, IOException {
        
        List<Category> categories = categoryDao.find();
        
        model.addAttribute("categories", categories);
        
        Product product = productDao.getById(id);
        product.setName(name);
        
        if (photo != null && !photo.isEmpty()) {
            String filepath = request.getServletContext().getRealPath("resources/images");
            FileOutputStream fos = new FileOutputStream(filepath + "/" + photo.getOriginalFilename());
            product.setPhoto(photo.getOriginalFilename());
            fos.write(photo.getBytes());
            fos.close();
        }
        
        
        product.setCategory(category);
        product.setPrice(new BigDecimal(price));
        productDao.update(product);
        model.addAttribute("product", product);
        return "admin/updateproduct";
    }
    
    @RequestMapping(value = "updateproduct", method = RequestMethod.GET)
    public String updateProduct(@RequestParam Integer id, ModelMap model) {
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        
        Product product = productDao.getById(id);
        model.addAttribute("product", product);
        return "admin/updateproduct";
    }
    
    @RequestMapping("/updatecategory")
    public String updateCategory(
            @RequestParam Integer id,
            @RequestParam String name,
            @RequestParam String description,
            ModelMap model) {
        
        Category selectedCategory = categoryDao.getById(id);
        selectedCategory.setName(name);
        selectedCategory.setDescription(description);
        categoryDao.update(selectedCategory);
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", selectedCategory);
        
        return "admin/categories";
    }
    
    @RequestMapping("/categories")
    public String categories(@RequestParam(required = false) Integer id, ModelMap model) {
        
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        
        if (id != null) {
            Category selectedCategory = categoryDao.getById(id);
            model.addAttribute("selectedCategory", selectedCategory);
        }
        return "admin/categories";
    }
}
