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


@WebServlet(name = "ShopControl", urlPatterns = {"/Shop"})
public class ShopControl extends HttpServlet {
    private ProductDAO productDao;
    private BrandDAO brandDao;
    private EntityManager em;
    private EntityManagerFactory emf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
           String url = "/it_shop.jsp";
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "it_shop_controller";
        }
        if (action.equals("it_shop_controller")){
            emf = Persistence.createEntityManagerFactory("Phone_webPU");
            em = emf.createEntityManager();
            productDao = new ProductDAO(emf);
            brandDao = new BrandDAO(emf);
            
            List<Product> listProduct = new ArrayList<Product>();
            listProduct = productDao.getPaging("1",1);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("indexPage",1);
            
            int countPage = productDao.getNumPage("1");
            request.setAttribute("pageNum", countPage);
            
            List<Brand> listCategory = new ArrayList<Brand>();
            listCategory = brandDao.getAllBrand();
            request.setAttribute("tag","1");
            request.setAttribute("listCategory", listCategory);
            
        }
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
    }

}
