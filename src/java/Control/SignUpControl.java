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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "SignUpControl", urlPatterns = {"/signup"})
public class SignUpControl extends HttpServlet {
    private UserDAO userDao;
    private EntityManager em;
    private EntityManagerFactory emf;
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String name = request.getParameter("name");
        String username = request.getParameter("username");
	String email = request.getParameter("email");
	String address = request.getParameter("address");
	String password_1 = request.getParameter("password_1");
	String password_2 = request.getParameter("password_2");
        Date registerDate = new Date();
        
        
        emf=Persistence.createEntityManagerFactory("Phone_webPU");
        em = emf.createEntityManager();
        userDao = new UserDAO(emf);
        
        List<User> listCustomer = new ArrayList<User>();
        listCustomer=userDao.searchByEmailandUsername(email,username);
        if(!listCustomer.isEmpty()){
            request.setAttribute("msg", "This email or Username has been use!");
            request.setAttribute("name", name);
            request.setAttribute("address", address);
            getServletContext()
                    .getRequestDispatcher("/it_signup.jsp")
                    .forward(request, response);
        }
        else if(!userDao.isValid(password_1)){
            request.setAttribute("msg", "Password must contain at least one digits and have at least eight characters!");
            request.setAttribute("username", username);
            request.setAttribute("name", name);
            request.setAttribute("address", address);
            request.setAttribute("email", email);
            getServletContext()
                    .getRequestDispatcher("/it_signup.jsp")
                    .forward(request, response);
        }
        else if(password_1.equals(password_2)) {
            User user = new User();
            user.setUsername(username);
            user.setName(name);
            user.setAddress(address);
            user.setEmail(email);
            user.setPassword(password_1);
            user.setPermission(0);
            user.setRegisterdate(registerDate);
            
            userDao.addUser(user);
            request.setAttribute("username", username);
            request.setAttribute("msg", "Account created successfully, Please Login!");
            getServletContext()
                    .getRequestDispatcher("/it_login.jsp")
                    .forward(request, response);
        }
        else{
            request.setAttribute("msg", "The two passwords do not match");
            request.setAttribute("username", username);
            request.setAttribute("name", name);
            request.setAttribute("address", address);
            request.setAttribute("email", email);
            getServletContext()
                    .getRequestDispatcher("/it_signup.jsp")
                    .forward(request, response);
        }
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
