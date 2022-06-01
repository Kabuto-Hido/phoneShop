package Control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

import DAO.*;
import Entity.*;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ProfileControl", urlPatterns = {"/Profile"})
public class ProfileControl extends HttpServlet {
    private EntityManager em;
    private EntityManagerFactory emf;
    HttpSession session;

    
    private UserDAO userDao;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        request.setCharacterEncoding("UTF-8");
        
        emf=Persistence.createEntityManagerFactory("Phone_webPU");
        em = emf.createEntityManager();
        userDao = new UserDAO(emf); 
        
        session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        String new_password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");
        String new_name = request.getParameter("name");
        String new_email = request.getParameter("email");
        String new_phone = request.getParameter("phone");
        String new_address = request.getParameter("address");
        
        if(username == null || username.isEmpty()){
            url="/it_login.jsp";
        }
         
        List<User> listUser = new ArrayList<User>();
        listUser = userDao.searchByUsername(username);
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "show";
        }
        else switch (action) {
            case "show":
                listUser = new ArrayList<User>();
                listUser = userDao.searchByUsername(username);
                if (!listUser.isEmpty()){
                    url="/it_profile.jsp";
                    request.setAttribute("userInfo", listUser.get(0));
                }   break;
            case "update":
                if (!listUser.isEmpty()) {
                    if(!userDao.isValid(new_password)){
                        request.setAttribute("msg", "Password must contain at least one digits and have at least eight characters!");
                    }
                    else if(!new_password.equals(confirm_password)){
                        request.setAttribute("msg", "The two passwords do not match");
                    }
                    else{
                        User user = listUser.get(0);
                        
                        user.setPassword(new_password);
                        user.setName(new_name);
                        user.setEmail(new_email);
                        user.setPhone(new_phone);
                        user.setAddress(new_address);
                        
                        userDao.updateUser(user);
                        request.setAttribute("msg", "Update success!");
                    }
                    
                    url="/Profile?action=show";
                    
                }
                else {
                    url="/it_login.jsp";
                    request.setAttribute("msg", "Update Failed!");
                }   break;
            case "cancel":
                url="/Home";
                break;
            default:
                break;
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
