/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User.Dao;

import ConnectionDB.mysql;
import User.Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class UserDao implements UserInterface{
    private static Connection connection = null;
    private static UserDao INSTANCE = new UserDao();

    private UserDao() {
    }

    @Override
    public void create(User user) throws Exception {
        connection = mysql.getConnection();
        String query = "INSERT INTO users (username, password, email, role)";
        query += " VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRole());
            
            if (ps.executeUpdate() >= 1) {
                System.out.println("User created successfully");
                connection.commit();
                ps.close();
                connection.close();
            } else {
                System.out.println("User creation failed");
                try {
                    connection.rollback();
                    ps.close();
                    connection.close();
                } catch (Exception e) {
                    throw new Exception("Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        
    }

    @Override
    public void update(User user) throws Exception {
        connection = mysql.getConnection();
        String query = "UPDATE users SET username = ?, password = ?, email = ?, role = ?";
        query += " WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRole());
            ps.setInt(5, user.getId());
            
            if (ps.executeUpdate() >= 1) {
                System.out.println("User updated successfully");
                connection.commit();
                ps.close();
                connection.close();
            } else {
                System.out.println("User update failed");
                try {
                    connection.rollback();
                    ps.close();
                    connection.close();
                } catch (Exception e) {
                    throw new Exception("Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(User user) throws Exception {
        connection = mysql.getConnection();
        String query = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, user.getId());
            
            if (ps.executeUpdate() >= 1) {
                System.out.println("User deleted successfully");
                connection.commit();
                ps.close();
                connection.close();
            } else {
                System.out.println("User deletion failed");
                try {
                    connection.rollback();
                    ps.close();
                    connection.close();
                } catch (Exception e) {
                    throw new Exception("Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAll() throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User.Builder()
                        .setId(rs.getInt("id"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setEmail(rs.getString("email"))
                        .setRole(rs.getInt("role"))
                        .build();
                users.add(user);
            }
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return users;
    }

    @Override
    public User getById(int id) throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User.Builder()
                        .setId(rs.getInt("id"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setEmail(rs.getString("email"))
                        .setRole(rs.getInt("role"))
                        .build();
            }
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return user;
    }

    @Override
    public User getByUsername(String username) throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT * FROM users WHERE username = ?";
        User user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User.Builder()
                        .setId(rs.getInt("id"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setEmail(rs.getString("email"))
                        .setRole(rs.getInt("role"))
                        .build();
            }
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return user;
    }

    @Override
    public User getByEmail(String email) throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT * FROM users WHERE email = ?";
        User user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User.Builder()
                        .setId(rs.getInt("id"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setEmail(rs.getString("email"))
                        .setRole(rs.getInt("role"))
                        .build();
            }
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return user;
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User.Builder()
                        .setId(rs.getInt("id"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setEmail(rs.getString("email"))
                        .setRole(rs.getInt("role"))
                        .build();
            } else {
                throw new Exception("User not found");
            } 
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return user;
    }

    @Override
    public Boolean getRoleById(int id) throws Exception {
        connection = mysql.getConnection();
        String query = "SELECT role FROM users WHERE id = ?";
        Boolean role = false;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                role = rs.getInt("role") == 1;
            }
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return role;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}