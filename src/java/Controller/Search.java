/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.ProductDAO;
import Models.Paging;
import java.io.IOException;
import Models.Product;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        String nameProduct = request.getParameter("search-product");
        HttpSession ses = request.getSession();
        ArrayList<Product> listProduct = dao.searchProuctsByName(nameProduct);
        ArrayList<Paging> pages = dao.getDataForPage(listProduct);
        int pageCurrent = 1;
        if (!pages.isEmpty()) {
            ses.setAttribute("product", pages.get(pageCurrent - 1).getCurrentPageItems());
        } else {
            ses.setAttribute("product", new ArrayList<>());
        }
        ses.setAttribute("page", pages);
        ses.setAttribute("pageCurrent", pageCurrent); 
        request.setAttribute("productname", nameProduct);
        ses.setAttribute("searchProductKeyword", nameProduct);
        request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
    }

}
