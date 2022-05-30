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

@WebServlet(name = "ProductDetailControl", urlPatterns = {"/productdetail"})
public class ProductDetailControl extends HttpServlet {
    private ProductDAO productDao;
    private BrandDAO brandDao;
    private EntityManager em;
    private EntityManagerFactory emf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url="/it_shop_detail.jsp";
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("pid");
        
        emf=Persistence.createEntityManagerFactory("Phone_webPU");
        em = emf.createEntityManager();
        productDao = new ProductDAO(emf);
        brandDao = new BrandDAO(emf);
        
        Product product = productDao.searchById(id);
        request.setAttribute("detail", product);
        
        Brand brand=product.getIdBrand();
        int brandid=brand.getId();
        
        List<Product> listRelatedProduct = new ArrayList<Product>();
        listRelatedProduct = productDao.get_3Product(id,brandid);
        request.setAttribute("listRelatedProduct", listRelatedProduct);
        
        List<Brand> listCategory = new ArrayList<Brand>();
        listCategory = brandDao.getAllBrand();
        request.setAttribute("tag",brandid);
        request.setAttribute("listCategory", listCategory);
        
        getServletContext()
                .getRequestDispatcher(url)
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
