/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.User;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class UserDAO extends DBContext {

    private Connection connection;
    private String status = "ok";

    public UserDAO() {
        try {
            connection = new DBContext().getConnection();
        } catch (Exception e) {
            status = "Connection failed: " + e.getMessage();
        }
    }

    public User getAccountByID(String username, String pass) {
        try {
            String sql = "select * from [users] where username = ? and [password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User account = new User(rs.getString("username"), rs.getString("password"));
                return account;
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> Users = new ArrayList<>();
        try {

            String sql = "SELECT * FROM [Users]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User a = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                Users.add(a);
            }
        } catch (SQLException ex) {
        }
        return Users;
    }

    public boolean isUsernameDuplicate(String name) {
        try {
            String sql = "select * from [users] where username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                    return true;
            }
        } catch (SQLException ex) {
        }
        return false;
    }

    public void insertUser(User user) {

        try {
            String sql = "INSERT INTO [dbo].[Users]\n"
                    + "           ([username]\n"
                    + "           ,[email]\n"
                    + "           ,[password]\n"
                    + "           ,[role]\n"
                    + "           ,[phone]\n"
                    + "           ,[points])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPassword());
            stm.setString(4, "user");
            stm.setString(5, user.getPhone());
            stm.setInt(6, 0);
            stm.executeUpdate();

        } catch (Exception ex) {
        }

    }

    public boolean isEmailDuplicate(String email) {
        try {
            String sql = "select * from [users] where email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                    return true;
            }
        } catch (SQLException ex) {
        }
        return false;
    }

     public boolean isPhoneDuplicate(String phone) {
          try {
            String sql = "select * from [users] where phone = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, phone);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                    return true;
            }
        } catch (SQLException ex) {
        }
        return false;
    }

    public static void main(String[] args) {
        UserDAO d = new UserDAO();

        System.out.println(d.isUsernameDuplicate("ab"));
        System.out.println(d.isEmailDuplicate("nq061205@gmail.com"));
        System.out.println(d.isPhoneDuplicate("03373643311"));
        
    }

   
}
