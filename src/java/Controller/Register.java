/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.UserDAO;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Views/Register.jsp");
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPassword");
        UserDAO d = new UserDAO();
        //phone
        //password
        //Username
        if (!d.isUsernameDuplicate(username)) {
            //email
            if (!d.isEmailDuplicate(email)) {
                if (!d.isPhoneDuplicate(phone)) {
                    if (!password.equals(confirmPass)) {
                        request.setAttribute("username", username);
                        request.setAttribute("email", email);
                        request.setAttribute("phone", phone);
                        request.setAttribute("password", password);
                        request.setAttribute("error", "confirm password incorrect");
                        request.getRequestDispatcher("Views/Register.jsp").forward(request, response);
                    } else {
                        User u = new User(0, username, email, password, "user", phone, 0);
                        d.insertUser(u);
                        response.sendRedirect("Views/Login.jsp");
                    }
                } else {
                    request.setAttribute("username", username);
                    request.setAttribute("email", email);
                    request.setAttribute("phone", phone);
                    request.setAttribute("password", password);
                    request.setAttribute("error", "Phone is already used");
                    request.getRequestDispatcher("Views/Register.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("username", username);
                request.setAttribute("phone", phone);
                request.setAttribute("password", password);
                request.setAttribute("error", "Email is already used");
                request.getRequestDispatcher("Views/Register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("password", password);
            request.setAttribute("error", "Username is already used");
            request.getRequestDispatcher("Views/Register.jsp").forward(request, response);
        }

    }
}
