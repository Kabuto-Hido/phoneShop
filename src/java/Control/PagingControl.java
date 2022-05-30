/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entity.*;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

@WebServlet(name = "PagingControl", urlPatterns = {"/paging"})
public class PagingControl extends HttpServlet {
    private ProductDAO productDao;
    private BrandDAO brandDao;
    private EntityManager em;
    private EntityManagerFactory emf;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String brandid=request.getParameter("brandid");
        String index=request.getParameter("index");
        
        //check index and category id 
        if(index==null||index.isEmpty()){
            index="1";
        }
        
        if(brandid==null||brandid.isEmpty()){
            brandid="1";
        }
        
        int indexPage=Integer.parseInt(index);
        emf=Persistence.createEntityManagerFactory("Phone_webPU");
        em = emf.createEntityManager();
        productDao = new ProductDAO(emf);
        brandDao = new BrandDAO(emf);
        
        //count num page by cate id
        int countPage = productDao.getNumPage(brandid);
        request.setAttribute("pageNum", countPage);
        
        //get list product by cate id and indexpage
        List<Product> listProduct = new ArrayList<Product>();
        listProduct = productDao.getPaging(brandid, indexPage);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("indexPage",indexPage);
        
        List<Brand> listCategory = new ArrayList<Brand>();
        listCategory = brandDao.getAllBrand();
        request.setAttribute("tag",brandid);
        request.setAttribute("listCategory", listCategory);
        
        
        getServletContext()
                .getRequestDispatcher("/it_shop.jsp")
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
