package Control;

//import javax.mail.MessagingException;
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
import java.util.Date;
import javax.swing.JOptionPane;

@WebServlet(name = "OrderControl", urlPatterns = {"/order"})
public class OrderControl extends HttpServlet {
    private ProductDAO productDao;
    private CartDAO cartDao;
    private UserDAO userDao;
    private OrdersDAO ordersDao;
    private OrderDetailDAO orderDetailDao;
    //private MailDAO mailDao;
    
    private EntityManager em;
    private EntityManagerFactory emf;
    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url="/it_checkout.jsp";
        
        //get session
        session = request.getSession();
        //check login 
        String username = (String) session.getAttribute("username");
        if(username == null || username.isEmpty()){   //hadn't login
            url="/it_login.jsp";
        }
        else{
            String action = request.getParameter("action");
            String coupon = request.getParameter("coupon_code");
        
            emf=Persistence.createEntityManagerFactory("Phone_webPU");
            em = emf.createEntityManager();
            productDao = new ProductDAO(emf);
            cartDao = new CartDAO(emf);
            userDao = new UserDAO(emf);
            ordersDao = new OrdersDAO(emf);
            orderDetailDao = new OrderDetailDAO(emf);
            //mailDao = new MailDAO();
            
            //get customer id
            List<User> listuser = new ArrayList<User>();
            listuser = userDao.searchByUsername(username);
            User user = listuser.get(0);
            int userid= user.getId();
            
            //check action
            if(action.equals("checkout")){          //action = checkout
                //get all product from cart by customer id
                List<Cart> listcart = new ArrayList<Cart>();
                listcart= cartDao.getFromCart(userid);
                
                //get totalmoney in cart
                Double sum=0.0;
                for(Cart c : listcart){
                    sum+=c.getUnitPrice();
                }
                
                //check if exist invoice in status "Chua thanh toan" and perform update
                if(ordersDao.checkUserExistOrders(userid)){
                    //find invoice exsist and set totalmoney have change
                    List<Orders> listOrder = new ArrayList<Orders>();
                    listOrder = ordersDao.getFromOrders(userid);
                    Orders orders = listOrder.get(0);
                    orders.setTotalPrice(sum);
                    
                    //update invoice again
                    ordersDao.updateOrders(orders);
                }
                else{           //haven't invoice in status "Chua thanh toan"
                    //create a new invoice and add a detail
                    Date createDate = new Date();

                    Orders orders = new Orders();
                    orders.setUserId(user);
                    orders.setTotalPrice(sum);
                    orders.setStatus("Chua thanh toan");
                    orders.setVoucher(1.0);
                    orders.setCreateDate(createDate);

                    ordersDao.addToOrders(orders);
                }
                //get invoice again
                List<Orders> listOrderData = new ArrayList<Orders>();
                listOrderData=ordersDao.getFromOrders(userid);
                Orders orderData = listOrderData.get(0);
                //get totalmoney
                Double total = orderData.getTotalPrice();
                Double voucher = orderData.getVoucher();
                if(voucher!=1){
                    request.setAttribute("sale", "10%");
                }
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                
                request.setAttribute("subtotal", formatter.format((total)));
                request.setAttribute("total", formatter.format((total*voucher)));
            }
            else if(action.equals("show")){     //action = show invoice
                //get invoice
                List<Orders> listOrders = new ArrayList<Orders>();
                listOrders = ordersDao.getFromOrders(userid);
                //check invoice have exsist
                if(listOrders.isEmpty()){      //doesn't exsist
                    String message="YOU DONT HAVE ANY ORDER";
                    String btn="Continue Shopping";
                    request.setAttribute("notfoundtxt",message);
                    request.setAttribute("btn",btn);
                    
                    getServletContext()
                        .getRequestDispatcher("/notfound.jsp")
                        .forward(request, response);
                }
                //exsist invoice and get totalmoney
                Orders order = listOrders.get(0);
                Double total = order.getTotalPrice();
                Double voucher = order.getVoucher();
                if(voucher!=1){
                    request.setAttribute("sale", "10%");
                }
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                request.setAttribute("subtotal", formatter.format((total)));
                request.setAttribute("total", formatter.format((total*voucher)));
            }
            else if(action.equals("voucher")){     //action = show invoice
                //get invoice
                List<Orders> listOrders = new ArrayList<Orders>();
                listOrders = ordersDao.getFromOrders(userid);
                Orders order = listOrders.get(0);
                Double sale = order.getVoucher();
                Double subtotal = order.getTotalPrice();
                Double total = subtotal;
                //exsist invoice and get totalmoney
                if(sale!=1){
                    JOptionPane.showMessageDialog(null, "You just can enter coupon one time!!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(coupon.equals("SALE10")){
                    total = subtotal*0.9;
                    order.setVoucher(0.9);
                }
                else if(coupon.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the coupon", "Info", JOptionPane.ERROR_MESSAGE);
                }
                //update
                ordersDao.updateOrders(order);
                
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                request.setAttribute("subtotal", formatter.format((subtotal)));
                request.setAttribute("total", formatter.format((total)));
                request.setAttribute("sale", "10%");
            }
            else{           //action = pay
                //get invoice and change status
                List<Orders> listOrder = new ArrayList<Orders>();
                listOrder = ordersDao.getFromOrders(userid);
                Orders order = listOrder.get(0);
                order.setStatus("Da thanh toan");
                ordersDao.updateOrders(order);
                
                //get invoice id
                //int orderid = order.getId();
                
                //get all product by user id 
                List<Cart> list = new ArrayList<Cart>();
                list= cartDao.getFromCart(userid);
                
                //information email need send
//                String to = email;
//                String from = "badshop@gmail.com";
//                String subject = "Thank you for buying in our shop";
//                String body="<h1>Here is your invoice detail</h1>"
//                        +"<table><tr><th>Product</th>" 
//                        +"<th>Quantity</th>" 
//                        +"<th>Price</th>" 
//                        +"<th>Total</th></tr>";
//                boolean ishtml=true;
                
                for(Cart c : list){
                    //get product from cart and insert into the invoice detail                  
                    
                    Orrderdetail orderDetail = new Orrderdetail();
                    orderDetail.setProductId(c.getProductId());
                    orderDetail.setQuantity(c.getQuantity());
                    orderDetail.setUnitPrice(c.getUnitPrice());
                    orderDetail.setOrderId(order);
                    
                    //get infor to send mail for customer by email has register
                    //String b = "<tr><td><h4>"+c.getProname()+"</h4></td>" +
                    //            "<td>"+c.getAmount().toString()+"</td>" +
                    //            "<td>"+c.getPriceCurrencyFormat()+"</td>" +
                    //            "<td>"+c.getTotalCurrencyFormat()+"</td></tr>";
                    //body=body.concat(b);
                    
                    //insert order detail 
                    orderDetailDao.addToOrderDetail(orderDetail);
                }
                
                //body=body.concat("<tr><td><h4>TotalMoney</h4></td>"
                //        + "<td></td>"
                //        + "<td></td>"
                //        + "<td>"+invoice.getPriceCurrencyFormat()+"</td>"
                //        + "</tr>"+"</table>");
                //delete item cart
                cartDao.deleteCart(userid);
                
                //check if mail have sent
                //boolean check=true;
                //try{
                //    mailDao.sendEmail(to,from ,subject, body, ishtml);
                //}catch(MessagingException e){
                //    check=false;
                //};
                
                String message="YOU HAVE COMPLETED THE PAYMENT";
                String btn="Back Home";
                request.setAttribute("notfoundtxt",message);
                request.setAttribute("btn",btn);

                url="/notfound.jsp";
                    
                
                //if(check){      //have sent
                //    String message="YOU HAVE COMPLETED THE PAYMENT";
                //    String btn="Back Home";
                //    request.setAttribute("notfoundtxt",message);
                //    request.setAttribute("btn",btn);

                //    url="/notfound.jsp";
                //}
                //else{           //not sent
                //    String message="YOU HAVE ERROR TO SEND MAIL";
                //    String btn="Back Home";
                //    request.setAttribute("notfoundtxt",message);
                //    request.setAttribute("btn",btn);

                //    url="/notfound.jsp";
                //}
            }
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
