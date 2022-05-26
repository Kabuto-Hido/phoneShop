/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

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
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

@WebServlet(name = "CartControl", urlPatterns = {"/cart"})
public class CartControl extends HttpServlet {
    private ProductDAO productDao;
    private CartDAO cartDao;
    private UserDAO userDao;
    
    private EntityManager em;
    private EntityManagerFactory emf;
    HttpSession session;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url="/it_cart.jsp";
        
        session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username == null || username.isEmpty()){
            url="/it_login.jsp";
        }
        else{
            String action = request.getParameter("action");
            String proid= request.getParameter("proid");
            String quantity=request.getParameter("quantity");
            
            
            emf=Persistence.createEntityManagerFactory("Phone_webPU");
            em = emf.createEntityManager();
            productDao = new ProductDAO(emf);
            cartDao = new CartDAO(emf);
            userDao = new UserDAO(emf);
            
            List<User> listuser = new ArrayList<User>();
            listuser = userDao.searchByUsername(username);
            User user = listuser.get(0);
            int userid= user.getId();
            
            if(action.equals("add")){ 
                int amount =Integer.parseInt(quantity);
                Product product = productDao.searchById(proid);
                //get infor product to add to cart
                Double sale = product.getSale();
                Double price= product.getPrice();
                if(sale != 1){
                    price = price * (1-sale);
                }
                 
                Double total = price*amount;
                
                if(cartDao.check(proid, userid)==true){
                    url="/productdetail?pid="+proid;
                    JOptionPane.showMessageDialog(null, "Product is already added to Cart", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    
                    Cart cart = new Cart();
                    cart.setUserId(user);
                    cart.setProductId(product);
                    cart.setQuantity(amount);
                    cart.setUnitPrice(total);
                    
                    cartDao.addToCart(cart);
                }
            }
            else if(action.equals("update")){
                int amount =Integer.parseInt(quantity);
                Product product = productDao.searchById(proid);
                Double sale = product.getSale();
                Double price= product.getPrice();
                if(sale != 1){
                    price = price * (1-sale);
                }
  
                //get max mount product
                int maxitem = product.getAmount();
                
                if(amount <= 0){
                    //set mount defalut = 1
                    amount =1;
                    //notification
                    JOptionPane.showMessageDialog(null, "The quantity of the product must be greater than 0 and less than " + maxitem,
                        "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(maxitem < amount){
                    amount = maxitem;
                    JOptionPane.showMessageDialog(null, "The quantity of the product must be greater than 0 and less than " + maxitem,
                        "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                Double total = price*amount;
                
                List<Cart> list = new ArrayList<Cart>();
                list= cartDao.findFromCart(proid, userid);
                Cart cart = list.get(0);
                cart.setQuantity(amount);
                cart.setUnitPrice(total);

                cartDao.updateProductToCart(cart);
                
                request.setAttribute("maxitem", maxitem);
            }
            else if(action.equals("remove")){
                cartDao.deleteProductToCart(proid, userid);
            }
            List<Cart> listcart = new ArrayList<Cart>();
            listcart= cartDao.getFromCart(userid);
                
            Double sum=0.0;
            for(Cart c : listcart){
                sum+=c.getUnitPrice();
            }
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            request.setAttribute("sum", formatter.format((sum)));
            request.setAttribute("listcart", listcart);

            //url="/productdetail?pid="+proid;
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
