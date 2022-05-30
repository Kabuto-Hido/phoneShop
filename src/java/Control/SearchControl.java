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


@WebServlet(name = "SearchControl", urlPatterns = {"/search"})
public class SearchControl extends HttpServlet {
    private ProductDAO productDao;
    private BrandDAO brandDao;
    private EntityManager em;
    private EntityManagerFactory emf;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url="/it_shop.jsp";
        
        String txtsearch=request.getParameter("txt");

        emf=Persistence.createEntityManagerFactory("Phone_webPU");
        em = emf.createEntityManager();
        productDao = new ProductDAO(emf);
        brandDao = new BrandDAO(emf);
            
        List<Product> listProductFound = new ArrayList<Product>();
        listProductFound = productDao.getPagingForSearch(txtsearch, 1);
        int countPage = productDao.getNumPageForSearch(txtsearch);
            
        if(listProductFound.isEmpty()){
            String message="NO RESULTS FOUND";
            String btn="Back Home";
            request.setAttribute("notfoundtxt",message);
            request.setAttribute("btn",btn);
            
            url="/notfound.jsp";
        }
        request.setAttribute("listProduct", listProductFound);
        request.setAttribute("txtsearch",txtsearch);
        request.setAttribute("indexPage",1);
        request.setAttribute("pageNum", countPage);
        
        List<Brand> listCategory = new ArrayList<Brand>();
        listCategory = brandDao.getAllBrand();
        request.setAttribute("listCategory", listCategory);
        
        request.setAttribute("tagsearch", txtsearch);
        
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
