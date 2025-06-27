/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author hgduy
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
                if (getCategoryByID(rs.getInt("categoryID")) != null) {
                    Product a = new Product(
                            rs.getInt("productID"),
                            (getCategoryByID(rs.getInt("categoryID"))),
                            rs.getString("name"),
                            rs.getFloat("price"),
                            rs.getString("description"),
                            rs.getInt("sold"),
                            rs.getFloat("rating"),
                            rs.getString("image"),
                            rs.getInt("quantity")
                    );
                    products.add(a);
                }

            }
        } catch (SQLException ex) {
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

            String sql = "SELECT * FROM [categories]";
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

    public ArrayList<Product> getProductByCategory(int categoryID) {
        ArrayList<Product> p = new ArrayList<>();
        for (Product product : getAllProduct()) {
            if (product.getCategory().getCategoryID() == categoryID) {
                p.add(product);
            }
        }
        return p;
    }

    public ArrayList<Product> getTop5BestSeller() {
        ArrayList<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT top (4) * FROM [products] order by sold desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (getCategoryByID(rs.getInt("categoryID")) != null) {
                    Product a = new Product(
                            rs.getInt("productID"),
                            (getCategoryByID(rs.getInt("categoryID"))),
                            rs.getString("name"),
                            rs.getFloat("price"),
                            rs.getString("description"),
                            rs.getInt("sold"),
                            rs.getFloat("rating"),
                            rs.getString("image"),
                            rs.getInt("quantity")
                    );
                    products.add(a);
                }

            }
        } catch (SQLException ex) {
        }
        return products;
    }

    public ArrayList<Product> searchProuctsByName(String name) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [products] where name like N'%" + name + "%'";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product a = new Product(
                        rs.getInt("productID"),
                        (getCategoryByID(rs.getInt("categoryID"))),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getInt("sold"),
                        rs.getFloat("rating"),
                        rs.getString("image"),
                        rs.getInt("quantity")
                );
                products.add(a);
            }
        } catch (SQLException ex) {
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
        ProductDAO d = new ProductDAO();
        ArrayList<Paging> p = d.getDataForPage(d.getAllProduct());
        for (Paging product : p) {
            System.out.println(product);
        }
    }
}
