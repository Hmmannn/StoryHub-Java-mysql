/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User.Dao;

import User.Model.User;
import java.util.List;
import javax.swing.JOptionPane;
import storyhub.utils.Bcrypt;  

/**
 *
 * @author Administrator
 */
public class UserController {

    private static final UserDao userDao = UserDao.getInstance();

    public static void create(User user) throws Exception {
        if (userDao.getByUsername(user.getUsername()) != null) {
            JOptionPane.showMessageDialog(null, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Username already exists");
        } else if (userDao.getByEmail(user.getEmail()) != null) {
            JOptionPane.showMessageDialog(null, "Email already exists", "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Email already exists");
        } else {
            userDao.create(user);
        }
    }

    public static void update(User user) throws Exception {
        if (userDao.getById(user.getId()) == null) {
            JOptionPane.showMessageDialog(null, "User does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("User does not exist");
        }
        
        if (userDao.getByUsername(user.getUsername()) != null) {
            JOptionPane.showMessageDialog(null, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Username already exists");
        } else if (userDao.getByEmail(user.getEmail()) != null) {
            JOptionPane.showMessageDialog(null, "Email already exists", "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Email already exists");
        } else {
            userDao.update(user);
        }
    }

    public static void delete(User user) throws Exception {
        if (userDao.getById(user.getId()) == null) {
            JOptionPane.showMessageDialog(null, "User does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("User does not exist");
        } else {
            userDao.delete(user);
        }
    }

    public static List<User> getAll() throws Exception {
        return userDao.getAll();
    }

    public static User getById(int id) throws Exception {
        return userDao.getById(id);
    }

    public static User getByUsername(String username) throws Exception {
        return userDao.getByUsername(username);
    }

    public static User getByEmail(String email) throws Exception {
        return userDao.getByEmail(email);
    }

    public static Boolean getRole(User user) {
        return user.getRole() == 1;
    }

    public static User getByUsernameAndPassword(String username, String password) throws Exception {
        if (userDao.getByUsernameAndPassword(username, password) == null) {
            JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Invalid username or password");
        }
        if (userDao.getByUsernameAndPassword(username, password).getRole() == 0) {
            JOptionPane.showMessageDialog(null, "Welcome " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
            return userDao.getByUsernameAndPassword(username, password);
        } else {
            JOptionPane.showMessageDialog(null, "Welcome Admin: " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
            return userDao.getByUsernameAndPassword(username, password);
        }
    }
    
}
