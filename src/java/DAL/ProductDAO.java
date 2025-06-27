package DAL;

import Models.Category;
import Models.Paging;
import Models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object for Product and Category.
 */
public class ProductDAO extends DBContext {

    private Connection connection;
    private String status = "ok";

    public ProductDAO() {
        try {
            connection = new DBContext().getConnection();
        } catch (Exception e) {
            status = "Connection failed: " + e.getMessage();
        }
    }

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [products]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category category = getCategoryByID(rs.getInt("categoryID"));
                if (category != null) {
                    Product p = new Product(
                        rs.getInt("productID"),
                        category,
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getInt("sold"),
                        rs.getFloat("rating"),
                        rs.getString("image"),
                        rs.getInt("quantity")
                    );
                    products.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [categories]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category c = new Category(
                    rs.getInt("categoryID"),
                    rs.getString("name")
                );
                categories.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categories;
    }

    public Category getCategoryByID(int id) {
        for (Category c : getAllCategory()) {
            if (c.getCategoryID() == id) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Product> getProductByCategory(int categoryID) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : getAllProduct()) {
            if (p.getCategory().getCategoryID() == categoryID) {
                result.add(p);
            }
        }
        return result;
    }

    public ArrayList<Product> getTop4BestSeller() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT TOP (4) * FROM [products] ORDER BY sold DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category category = getCategoryByID(rs.getInt("categoryID"));
                if (category != null) {
                    Product p = new Product(
                        rs.getInt("productID"),
                        category,
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getInt("sold"),
                        rs.getFloat("rating"),
                        rs.getString("image"),
                        rs.getInt("quantity")
                    );
                    products.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public ArrayList<Product> searchProuctsByName(String name) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [products] WHERE name LIKE N'%" + name + "%'";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category category = getCategoryByID(rs.getInt("categoryID"));
                if (category != null) {
                    Product p = new Product(
                        rs.getInt("productID"),
                        category,
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getInt("sold"),
                        rs.getFloat("rating"),
                        rs.getString("image"),
                        rs.getInt("quantity")
                    );
                    products.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public ArrayList<Paging> getDataForPage(ArrayList<Product> productList) {
        ArrayList<Paging> pages = new ArrayList<>();
        int itemsPerPage = 8;
        int totalItems = productList.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        for (int i = 0; i < totalPages; i++) {
            int start = i * itemsPerPage;
            int end = Math.min(start + itemsPerPage, totalItems);
            ArrayList<Product> subList = new ArrayList<>(productList.subList(start, end));

            Paging paging = new Paging();
            paging.setCurrentPageItems(subList);
            paging.setCurrentPage(i + 1);
            paging.setTotalPages(totalPages);

            pages.add(paging);
        }

        return pages;
    }

    public static void main(String[] args) {
        
    }
}
