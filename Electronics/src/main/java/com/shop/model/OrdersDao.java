
package com.shop.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class OrdersDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public Orders getById(int id) {
        return (Orders)sessionFactory.getCurrentSession().get(Orders.class, id);
    }
    
    public Long pages() {
        return ((Double)Math.ceil((Long)sessionFactory.getCurrentSession().createQuery("select count(id)from Orders").uniqueResult()/3)).longValue();
    }
    
    public List<Orders> findByCategory(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Orders> result = session.getNamedQuery("Orders.findByCategory").setInteger("orders", id).list();
        return result;
    }
    
    public List<Orders> findByPage(int page) {
        int perpage = 100;
        Session session = sessionFactory.getCurrentSession();
        List<Orders> result = session.createQuery("from Orders").setFirstResult(page*perpage).setMaxResults(perpage).list();
        return result;
    }
    
    public List<Orders> find() {
        
        Session session = sessionFactory.getCurrentSession();
        List<Orders> result = session.createCriteria(Orders.class).list();
        return result;
    }
    
    public void save(Orders order) {
        
        Session session = sessionFactory.getCurrentSession();
        
        session.save(order);
    }
}
