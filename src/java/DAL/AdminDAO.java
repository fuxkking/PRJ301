/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Category;
import Models.Product;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Do Quang Huy_HE191197
 */
public class AdminDAO extends DBContext {

    private Connection connection;
    private String status = "ok";

    public AdminDAO() {
        try {
            connection = new DBContext().getConnection();
        } catch (Exception e) {
            status = "Connection failed: " + e.getMessage();
        }
    }

    public void addProduct(Product p) {
        try {
            String sql = "INSERT INTO products (categoryID, name, price, description, sold, rating, image, quantity) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, p.getCategory().getCategoryID());
            ps.setString(2, p.getName());
            ps.setFloat(3, p.getPrice());
            ps.setString(4, p.getDescription());
            ps.setInt(5, p.getSold());
            ps.setFloat(6, p.getRating());
            ps.setString(7, p.getImage());
            ps.setInt(8, p.getQuantity());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void UpdateProduct(Product p) {
        try {
            String sql = "UPDATE products SET categoryID = ?, name = ?, price = ?, description = ?, sold=?, rating=? , image=?, quantity=? "
                    + "WHERE productID=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, p.getCategory().getCategoryID());
            ps.setString(2, p.getName());
            ps.setFloat(3, p.getPrice());
            ps.setString(4, p.getDescription());
            ps.setInt(5, p.getSold());
            ps.setFloat(6, p.getRating());
            ps.setString(7, p.getImage());
            ps.setInt(8, p.getQuantity());
            ps.setInt(9, p.getProductID());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void DeleteProduct(Product p) {
        try {
            String sql = "INSERT INTO HiddenProducts (productID,categoryID, name, price, description, sold, rating, image, quantity) "
                    + "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, p.getProductID());
            ps.setInt(2, p.getCategory().getCategoryID());
            ps.setString(3, p.getName());
            ps.setFloat(4, p.getPrice());
            ps.setString(5, p.getDescription());
            ps.setInt(6, p.getSold());
            ps.setFloat(7, p.getRating());
            ps.setString(8, p.getImage());
            ps.setInt(9, p.getQuantity());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Product> getAllHiddenProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [HiddenProducts]";
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

    public Category getCategoryByID(int id) {
        ArrayList<Category> category = getAllCategory();
        for (Category category1 : category) {
            if (category1.getCategoryID() == id) {
                return category1;
            }
        }
        return null;
    }

    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> category = new ArrayList<>();
        try {

            String sql = "SELECT * FROM [Categories]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category a = new Category(
                        rs.getInt("categoryID"),
                        rs.getString("name"));

                category.add(a);
            }
        } catch (SQLException ex) {
        }
        return category;

    }

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Products\n"
                    + "WHERE productID NOT IN (SELECT productID FROM HiddenProducts);";
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

    public Product getProductById(int id) {
        Product product = null;
        try {
            String sql = "SELECT * FROM [products] WHERE productID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("productID"),
                        getCategoryByID(rs.getInt("categoryID")),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getInt("sold"),
                        rs.getFloat("rating"),
                        rs.getString("image"),
                        rs.getInt("quantity")
                );
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }
}
