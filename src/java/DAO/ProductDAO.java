
package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import Entity.*;
import javax.persistence.EntityTransaction;
public class ProductDAO {
    private final EntityManager em;

    public ProductDAO(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }
    public Product searchById(String pid) {
        int id=Integer.parseInt(pid);
        return em.find(Product.class, id);
    }
    
    public void addNewProduct(Product product){
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.persist(product);
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }
    }
    
    public void updateProduct(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    public void deleteProduct(String id){
        int pid = Integer.parseInt(id);
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            Query query = em.createQuery("delete from Product p where p.id = ?1");
            query.setParameter(1,pid);
            query.executeUpdate();
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }
    }

    public List<Product> getAll() {
        Query query = em.createQuery("SELECT p FROM Product p");
        List<Product> list = query.getResultList();
        return list;
    }
    
    public List<Product> getProductByTypeId(String brandId) {
        int bid=Integer.parseInt(brandId);
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.idBrand.id = :id");
        query.setParameter("id",bid);
        List<Product> list = query.getResultList();
        return list;
    }
    
    public List<Product> get_3Product(String pid,int brandId) {
        int ppid=Integer.parseInt(pid);
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.idBrand.id = ?1 and p.id != ?2");
        query.setParameter(1,brandId);
        query.setParameter(2,ppid);
        List<Product> list = query.setMaxResults(3).getResultList();
        return list;
    }
    
    public List<Product> searchByNameDetail(String nameSearch) {
        String q="SELECT p FROM Product p WHERE p.name like ?1 or p.description like ?2 or p.idBrand.name like ?3";
        Query query = em.createQuery(q);
        query.setParameter(1,"%"+nameSearch+"%");
        query.setParameter(2,"%"+nameSearch+"%");
        query.setParameter(3,"%"+nameSearch+"%");
        List<Product> list = query.getResultList();
        return list;
    }
        
    
    public List<Product> searchByPrice(String priceSearch) {
        Double price=Double.parseDouble(priceSearch);
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.price like :price");
        query.setParameter("price","%"+priceSearch+"%");
        List<Product> list = query.getResultList();
        return list;
    }
    public int getNumPage(String tid){
        int ttid=Integer.parseInt(tid);
        Query query = em.createQuery("SELECT p FROM Product p where p.idBrand.id = ?1");
        query.setParameter(1,ttid);
        List list = query.getResultList();
        if(!list.isEmpty()){
            int total = list.size();
            int countPage=0;
            countPage=total/6;
            if(total%6!=0){
                countPage++;
            }
            return countPage;
        }
        else {
            return 0;
        }
    }
    public List<Product> getPaging(String tid,int index) {
        int ttid=Integer.parseInt(tid);
        Query query = em.createQuery("SELECT p FROM Product p where p.idBrand.id = ?1");
        query.setParameter(1,ttid);
        List<Product> list = query.setFirstResult((index-1)*6).setMaxResults(6).getResultList();
        return list;
    }
    
    public int getNumPageForSearch(String nameSearch){
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.name like ?1 or p.description like ?2 or p.idBrand.name like ?3");
        query.setParameter(1,"%"+nameSearch+"%");
        query.setParameter(2,"%"+nameSearch+"%");
        query.setParameter(3,"%"+nameSearch+"%");
        List list = query.getResultList();
        if(!list.isEmpty()){
            int total = list.size();
            int countPage=0;
            countPage=total/6;
            if(total%6!=0){
                countPage++;
            }
            return countPage;
        }
        else {
            return 0;
        }
    }
    public List<Product> getPagingForSearch(String nameSearch,int index) {
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.name like ?1 or p.description like ?2 or p.idBrand.name like ?3");
        query.setParameter(1,"%"+nameSearch+"%");
        query.setParameter(2,"%"+nameSearch+"%");
        query.setParameter(3,"%"+nameSearch+"%");
        List<Product> list = query.setFirstResult((index-1)*6).setMaxResults(6).getResultList();
        return list;
    }
    
    public boolean checkBrandExistInProduct(String id){
        int idBrand=Integer.parseInt(id);
        Query query = em.createQuery("select p from Product p where p.idBrand.id = ?1");
        query.setParameter(1,idBrand);
        List<Product> list = query.getResultList();
        return !list.isEmpty();
    }
    
    public boolean checkExistProduct(String name){
        Query query = em.createQuery("select p from Product p where p.name = ?1");
        query.setParameter(1,name);
        List<Product> list = query.getResultList();
        return !list.isEmpty();
    }
    
    public void close() {
        em.close();
    }
}
