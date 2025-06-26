/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = "";

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    username = c.getValue();
                }

            }
        }

        request.setAttribute("username", username);

        request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        if(type.equals("login")){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO d = new UserDAO();
        HttpSession session = request.getSession();
        if (d.getAccount(username, password) != null) {
            session.setAttribute("account", d.getAccount(username, password));
            response.sendRedirect("Home");
        } else {
            request.setAttribute("error", "Username or password incorrect");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
        }
        }else{
            doLogout(request, response);
        }
    }


    protected void doLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession ses = request.getSession();
//       ses.invalidate();
       ses.removeAttribute("account");
       response.sendRedirect("Views/Login.jsp");
    }
}
