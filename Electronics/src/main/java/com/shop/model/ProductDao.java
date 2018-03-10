
package com.shop.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class ProductDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public Product getById(int id) {
        return (Product)sessionFactory.getCurrentSession().get(Product.class, id);
    }
    
    public void update(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }
    
    public void add(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }
    
    public void remove(Product product) {
        
        sessionFactory.getCurrentSession().delete(product);
    }
    
    public Long pages() {
        return ((Double)Math.ceil((Long)sessionFactory.getCurrentSession().createQuery("select count(id)from Product").uniqueResult()/3)).longValue();
    }
    
    public List<Product> findByPage(int page) {
        int perpage = 4;
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = session.createQuery("from Product").setFirstResult(page*perpage).setMaxResults(perpage).list();
        return result;
    }
    
    public List<Product> findByCategory(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = session.getNamedQuery("Product.findByCategory").setInteger("category", id).list();
        return result;
    }
    
    public List<Product> find() {
        
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = session.createCriteria(Product.class).list();
        return result;
    }
    
}
