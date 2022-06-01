/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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


@WebServlet(name = "ManageProductControl", urlPatterns = {"/manageproduct"})
public class ManageProductControl extends HttpServlet {
    private ProductDAO productDao;
    private BrandDAO brandDao;
    private CartDAO cartDao;
    private OrderDetailDAO orderDetailDao;
    
    private EntityManager em;
    private EntityManagerFactory emf;
    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        String idProduct = request.getParameter("productId");
        String name = request.getParameter("ProductName");
        String price = request.getParameter("ProductPrice");
        String image = request.getParameter("ProductImage");
        String amount = request.getParameter("ProductAmount");
        String description = request.getParameter("ProductDescription");
        String sale = request.getParameter("ProductSale");
        String idBrand = request.getParameter("brand");
        String url="";
        
        emf=Persistence.createEntityManagerFactory("Phone_webPU");
        em = emf.createEntityManager();
        productDao = new ProductDAO(emf);
        brandDao = new BrandDAO(emf);
        cartDao = new CartDAO(emf);
        orderDetailDao = new OrderDetailDAO(emf);
        
        if(action.equals("")){
            action="show";
        }
        
        if(action.equals("show")){       
            List<Product> listProduct = new ArrayList<Product>();
            listProduct = productDao.getAll();
            request.setAttribute("listP", listProduct);
            url="/admin/products.jsp";
        }
        else if(action.equals("showadd")){
            List<Brand> listBrand = new ArrayList<Brand>();
            listBrand = brandDao.getAllBrand();
            request.setAttribute("listBrand", listBrand);
            url="/admin/insertproduct.jsp";
        }
        else if(action.equals("add")){
            if (name.isEmpty() || amount.isEmpty() || price.isEmpty()) {
                request.setAttribute("Message", "Invalid input");
                url="/manageproduct?action=show";
            }
            else if(productDao.checkExistProduct(name)){
                request.setAttribute("errorMessage", name +" Already Exsist");
                url="/manageproduct?action=showadd";
            }
            else{
                Double saleProduct = 1.0;
                if(!sale.isEmpty() && Double.parseDouble(sale) < 1){       
                    saleProduct = Double.parseDouble(sale);
                }
                
                Brand brand = brandDao.searchById(idBrand);
                
                Product product = new Product();
                product.setName(name);
                product.setImage(image);
                product.setAmount(Integer.parseInt(amount));
                product.setPrice(Double.parseDouble(price));
                product.setDescription(description);
                product.setSale(saleProduct);
                product.setIdBrand(brand);
                
                productDao.addNewProduct(product);

                request.setAttribute("Message", "Added New Product Successed");
                url="/manageproduct?action=show";
            }
        }
        else if(action.equals("delete")){
            if(cartDao.checkProductExistInCart(idProduct) || orderDetailDao.checkProductExistInOrderDetail(idProduct)){
                request.setAttribute("Message", "You Can't Delete This Product");
            }
            else{
                productDao.deleteProduct(idProduct);
                request.setAttribute("Message", "Delete Product Successed");
            }
            url="/manageproduct?action=show";
        }
        else if(action.equals("showupdate")){
            Product product = productDao.searchById(idProduct);
            request.setAttribute("productInfo", product);
            
            List<Brand> listBrand = new ArrayList<Brand>();
            listBrand = brandDao.getAllBrand();
            request.setAttribute("listBrand", listBrand);
            
            url="/admin/updateproduct.jsp";
        }
        else if(action.equals("confirmupdate")){
            Product product = productDao.searchById(idProduct);
            Brand brand = brandDao.searchById(idBrand);
            
            Double saleProduct = 1.0;
            if(!sale.isEmpty() && Double.parseDouble(sale) < 1){       
                saleProduct = Double.parseDouble(sale);
            }
            
            product.setName(name);
            product.setImage(image);
            product.setAmount(Integer.parseInt(amount));
            product.setPrice(Double.parseDouble(price));
            product.setDescription(description);
            product.setSale(saleProduct);
            product.setIdBrand(brand);
            
            productDao.updateProduct(product);
            
            request.setAttribute("Message", "Update Product Successed");
            url="/manageproduct?action=show";
        }
        else if(action.equals("cancel")){
            url="/manageproduct?action=show";
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
