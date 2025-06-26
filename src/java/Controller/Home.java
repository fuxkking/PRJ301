/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.ProductDAO;
import DAL.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author hgduy
 */
public class Home extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO d = new ProductDAO();
        HttpSession session = request.getSession();
        session.setAttribute("category", d.getAllCategory());
        session.setAttribute("product", d.getAllProduct());
        session.setAttribute("bestSellerProducts", d.getTop5BestSeller());
        request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProductDAO d = new ProductDAO();
            HttpSession session = request.getSession();
            String categorySearch = request.getParameter("categorySearch");

            if (categorySearch.equals("all")) {
                session.setAttribute("product", d.getAllProduct());
                request.setAttribute("cateChoice", 0);
            } else {
                int categoryID = Integer.parseInt(request.getParameter("categorySearch"));
                session.setAttribute("product", d.getProductByCategory(categoryID));
                request.setAttribute("cateChoice", categoryID);
            }
            request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
