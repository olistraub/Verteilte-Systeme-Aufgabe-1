/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.web;

import dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.ejb.UserBean;
import dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.jpa.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Raphael
 */
@WebServlet(name = "UserEditServlet", urlPatterns = {"/app/user/*"})
public class UserEditServlet extends HttpServlet {

    @EJB
    UserBean userBean;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            HttpSession session = request.getSession();
               
            User user = userBean.getCurrentUser();
            
            session.setAttribute("name", user.getName());
            session.setAttribute("anschrift", user.getAnschrift());
            session.setAttribute("plz", user.getPlz());
            session.setAttribute("ort", user.getOrt());
            session.setAttribute("telefon", user.getTel());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("username", user.getUsername());

        
        
          request.getRequestDispatcher("/WEB-INF/app/user.jsp").forward(request, response);
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("utf-8");
        
        String username = request.getParameter("signup_username");
        String name = request.getParameter("signup_name");
        String anschrift = request.getParameter("signup_anschrift");
        String plz = request.getParameter("signup_plz");
        String ort = request.getParameter("signup_ort");
        String tel = request.getParameter("signup_tel");
        String email = request.getParameter("signup_email");
        
        User currUser = userBean.getCurrentUser();
        String oldpwd = currUser.getPassword().password;
        User newUser = new User(currUser.getUsername(), oldpwd, name, anschrift, plz, ort, tel, email);
        userBean.update(newUser);
       
        response.sendRedirect(WebUtils.appUrl(request, "/app/tasks/"));
        
    }


}
