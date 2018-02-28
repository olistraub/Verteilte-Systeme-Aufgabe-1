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
import dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.ejb.ValidationBean;
import dhbwka.wwi.vertsys.javaee.aufgabeverteiltesysteme.jpa.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
    
    @EJB 
    ValidationBean validationBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         
            User user = userBean.getCurrentUser();
            
            request.setAttribute("name", user.getName());
            request.setAttribute("anschrift", user.getAnschrift());
            request.setAttribute("plz", user.getPlz());
            request.setAttribute("ort", user.getOrt());
            request.setAttribute("telefon", user.getTel());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("username", user.getUsername());

        
        
          request.getRequestDispatcher("/WEB-INF/app/user.jsp").forward(request, response);
          
          HttpSession session = request.getSession();
          session.removeAttribute("errors");
     
        
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
        String oldpwd = currUser.getHashPassword();
        User newUser = new User(username, name, anschrift, plz, ort, tel, email);
        newUser.setHashPassword(oldpwd);
        
        List<String> errors = validationBean.validate(newUser);
        
        if(!errors.isEmpty()){

            HttpSession session = request.getSession();
            session.setAttribute("errors", errors);
            response.sendRedirect(request.getRequestURI());

        }else{    
        userBean.update(newUser);
       
        response.sendRedirect(WebUtils.appUrl(request, "/app/tasks/"));
        }
    }


}
