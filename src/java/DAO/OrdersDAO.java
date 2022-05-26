package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import Entity.*;
import javax.persistence.EntityTransaction;

public class OrdersDAO {
    private EntityManager em;

    public OrdersDAO(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }
    
    public List<Orders> getAllOrder() {
        Query query = em.createQuery("SELECT p FROM Orders p");
        List<Orders> list = query.getResultList();
        return list;
    }
    
    public void addToOrders(Orders orders){
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.persist(orders);
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }
    }
    
    public void updateOrders(Orders orders){
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.merge(orders);
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }
    }
    
    public void deleteOrders(int id){
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            Query query = em.createQuery("delete from Orders p where p.id = ?1");
            query.setParameter(1,id);
            query.executeUpdate();
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }
    }
    
    public boolean checkUserExistOrders(int userid){
        Query query = em.createQuery("select p from Orders p where p.userId.id = ?1 and p.status like ?2");
        query.setParameter(1,userid);
        query.setParameter(2,"%"+"Chua thanh toan"+"%");
        List<Orders> list = query.getResultList();
        return !list.isEmpty();
    }
    
    public boolean checkStatusOrders(int id){
        Query query = em.createQuery("select p from Orders p where p.id = ?1 and p.status like ?2");
        query.setParameter(1,id);
        query.setParameter(2,"%"+"Da thanh toan"+"%");
        List<Orders> list = query.getResultList();
        return !list.isEmpty();
    }
    
    public List<Orders> getFromOrders(int userid){
        Query query = em.createQuery("select p from Orders p where p.userId.id = ?1 and p.status like ?2");
        query.setParameter(1,userid);
        query.setParameter(2,"%"+"Chua thanh toan"+"%");
        List<Orders> list = query.getResultList();
        return list;
    }
    public void close() {
        em.close();
    }
}
