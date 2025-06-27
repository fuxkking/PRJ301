package Controller;

import DAL.ProductDAO;
import Models.Paging;
import Models.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProductDAO dao = new ProductDAO();
            HttpSession session = request.getSession();

            String pageNum = request.getParameter("pageNum");
            int page = (pageNum == null) ? 1 : Integer.parseInt(pageNum);

            Object categoryFilter = session.getAttribute("categoryFilter");
            ArrayList<Paging> pages;

            if (categoryFilter == null || "all".equals(categoryFilter)) {
                pages = dao.getDataForPage(dao.getAllProduct());
                session.setAttribute("cateChoice", 0);
            } else {
                int categoryID = (int) categoryFilter;
                pages = dao.getDataForPage(dao.getProductByCategory(categoryID));
                session.setAttribute("cateChoice", categoryID);
            }

            if (page < 1) page = 1;
            if (page > pages.size()) page = pages.size();

            session.setAttribute("page", pages);
            session.setAttribute("pageCurrent", page);
            session.setAttribute("product", pages.get(page - 1).getCurrentPageItems());

            // Các thông tin chung
            session.setAttribute("category", dao.getAllCategory());
            session.setAttribute("bestSellerProducts", dao.getTop4BestSeller());

            request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProductDAO dao = new ProductDAO();
            HttpSession session = request.getSession();

            String categorySearch = request.getParameter("categorySearch");
            int pageCurrent = 1;
            ArrayList<Paging> pages;
            List<Product> currentPageItems;

            if ("all".equals(categorySearch)) {
                pages = dao.getDataForPage(dao.getAllProduct());
                session.setAttribute("categoryFilter", "all");
                session.setAttribute("cateChoice", 0);
            } else {
                int categoryID = Integer.parseInt(categorySearch);
                pages = dao.getDataForPage(dao.getProductByCategory(categoryID));
                session.setAttribute("categoryFilter", categoryID);
                session.setAttribute("cateChoice", categoryID);
            }

            session.setAttribute("page", pages);
            session.setAttribute("pageCurrent", pageCurrent);

            if (!pages.isEmpty()) {
                currentPageItems = pages.get(pageCurrent - 1).getCurrentPageItems();
            } else {
                currentPageItems = new ArrayList<>();
            }

            session.setAttribute("product", currentPageItems);
            session.setAttribute("category", dao.getAllCategory());
            session.setAttribute("bestSellerProducts", dao.getTop4BestSeller());

            request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for home page with filtering and pagination support.";
    }
}
