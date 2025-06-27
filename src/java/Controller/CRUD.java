package Controller;

import DAL.AdminDAO;
import DAL.ProductDAO;
import Models.Category;
import Models.Product;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "CRUD", urlPatterns = {"/CRUD"})
public class CRUD extends HttpServlet {

    AdminDAO dao = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Product> products = dao.getAllProduct();
        ArrayList<Category> categories = dao.getAllCategory(); 

        String type = request.getParameter("type");
        String idParam = request.getParameter("id");
        Product productUpdate = null;

        if ("edit".equals(type) && idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                productUpdate = dao.getProductById(id); 
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else if ("delete".equals(type) && idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                Product p = new Product();
                p.setProductID(id);
                dao.DeleteProduct(p);
                response.sendRedirect(request.getContextPath() + "/CRUD");
                return;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        request.getSession().setAttribute("product", products);
        request.getSession().setAttribute("category", categories);
        request.setAttribute("productUpdate", productUpdate);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/Admin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String image = request.getParameter("image");
            float price = Float.parseFloat(request.getParameter("price"));
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));

            Product p = new Product();
            p.setName(name);
            p.setDescription(description);
            p.setImage(image);
            p.setPrice(price);
            p.setCategory(new Category(categoryID, ""));
            p.setSold(0);
            p.setRating(0);
            p.setQuantity(0);

            if (request.getParameter("btnInsert") != null) {
                dao.addProduct(p);
            } else if (request.getParameter("btnUpdate") != null) {
                int productID = Integer.parseInt(request.getParameter("productID"));
                p.setProductID(productID);
                dao.UpdateProduct(p);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi dữ liệu: " + e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/CRUD");
    }
}