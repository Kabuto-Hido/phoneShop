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

@WebServlet(name = "ManageOrderControl", urlPatterns = {"/manageorder"})
public class ManageOrderControl extends HttpServlet {
    private OrdersDAO ordersDao;
    private OrderDetailDAO orderDetailDao;

    private EntityManager em;
    private EntityManagerFactory emf;
    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url="/admin/index.jsp";
        String action = request.getParameter("action");
        String id = request.getParameter("orderId");
  
        emf=Persistence.createEntityManagerFactory("Phone_webPU");
        em = emf.createEntityManager();
        ordersDao = new OrdersDAO(emf);
        orderDetailDao = new OrderDetailDAO(emf);
        
        session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username == null || username.isEmpty()){
            url="/it_login.jsp";
        }
        if(action.equals("")){
            action="show";
        }
        
        if(action.equals("show")){
            List<Orders> listOrders = new ArrayList<Orders>();
            listOrders = ordersDao.getAllOrder();
            request.setAttribute("listOrders", listOrders);
        }
        else if(action.equals("delete")){

            orderDetailDao.deleteOrderDetail(Integer.parseInt(id));
            ordersDao.deleteOrders(Integer.parseInt(id));
            request.setAttribute("Message", "Delete Order Successed");
            url="/manageorder?action=show";
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
