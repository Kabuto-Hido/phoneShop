package AdminControl;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import DAO.*;
import Entity.*;

@WebServlet(name = "ManageBrandControl", urlPatterns = {"/managebrand"})
public class ManageBrandControl extends HttpServlet {
    private BrandDAO brandDao;
    private ProductDAO productDao;

    private EntityManager em;
    private EntityManagerFactory emf;
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String id = request.getParameter("brandId");
        String name = request.getParameter("BrandName");
        String image = request.getParameter("BrandImage");
        String description = request.getParameter("BrandDescription");
        String url="";
        
        emf=Persistence.createEntityManagerFactory("Phone_webPU");
        em = emf.createEntityManager();
        brandDao = new BrandDAO(emf);
        productDao = new ProductDAO(emf);
        if(action.equals("")){
            action="show";
        }
        
        if(action.equals("show")){       
            List<Brand> listBrand = new ArrayList<Brand>();
            listBrand = brandDao.getAllBrand();
            request.setAttribute("listBrand", listBrand);
            url="/admin/brand.jsp";
        }
        else if(action.equals("add")){
            if (name.isEmpty()) {
                request.setAttribute("Message", "Please enter brand name!");
                url="/managebrand?action=show";
            }
            
            else if(brandDao.checkExistBrand(name)){
                request.setAttribute("Message", name +" Already Exsist");
                url="/managebrand?action=show";
            }
            else{
                Brand brand = new Brand();
                brand.setName(name);
                brand.setImage(image);
                brand.setDescription(description);

                brandDao.addNewBrand(brand);

                request.setAttribute("Message", "Added New Brand Successed");
                url="/managebrand?action=show";
            }
        }
        else if(action.equals("delete")){
            if(productDao.checkBrandExistInProduct(id)){
                request.setAttribute("Message", "You Can't Delete This Brand");         
            }
            else{
              brandDao.deleteBrand(id);

              request.setAttribute("Message", "Delete Brand Successed");
            }
            url="/managebrand?action=show";
        }
        else if(action.equals("showupdate")){
            Brand brand = brandDao.searchById(id);
            
            request.setAttribute("brandInfo", brand);
            url="/admin/updatebrand.jsp";
        }
        else if(action.equals("confirmupdate")){
            Brand brand = brandDao.searchById(id);
            
            brand.setName(name);
            brand.setImage(image);
            brand.setDescription(description);
            
            brandDao.updateBrand(brand);
            
            request.setAttribute("Message", "Update Brand Successed");
            url="/managebrand?action=show";
        }
        else if(action.equals("cancel")){
            url="/managebrand?action=show";
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
    }// </editor-fold>

}
