/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.UserDAO;
import java.io.IOException;
import Models.User;

import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
public class userUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        User user = (User) request.getSession().getAttribute("account");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String image = request.getParameter("image");
        dao.updateUser(user.getUserID(), image, phone, email);
         user.setEmail(email);
        user.setPhone(phone);
        user.setImage(image);
        request.getSession().setAttribute("account", user);
        request.getRequestDispatcher("Views/Profile.jsp").forward(request, response);
    }

}
