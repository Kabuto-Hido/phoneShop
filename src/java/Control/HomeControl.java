/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.ProductDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import Entity.*;

@WebServlet(name = "HomeControl", urlPatterns = {"/Home"})
public class HomeControl extends HttpServlet {
    private ProductDAO um;
    private EntityManager em;
    private EntityManagerFactory emf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "/index.jsp";
        String action = request.getParameter("action");
        HttpSession session;
        if (action == null) {
            action = "Home";
        }
        if (action.equals("Home")) {
            emf = Persistence.createEntityManagerFactory("Phone_webPU");
            em = emf.createEntityManager();
            um = new ProductDAO(emf);
            session = request.getSession();
            List<Product> listProduct = new ArrayList<Product>();
            listProduct = um.get_3Product("1", 1);
            session.setAttribute("listProduct", listProduct);
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
