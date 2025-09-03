package vn.iostar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.iostar.model.User;
import vn.iostar.utils.DBConnection;

public class UserDaoImpl implements UserDao {

    private static final String UPDATE_PASSWORD_SQL = "UPDATE [User] SET password = ? WHERE email = ?";
    private static final String SELECT_USER_SQL = "SELECT * FROM [User] WHERE username = ?";
    private static final String INSERT_USER_SQL = "INSERT INTO [User](email, username, fullname, password, avatar, roleId, phone, createDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CHECK_EXIST_EMAIL_SQL = "SELECT * FROM [User] WHERE email = ?";
    private static final String CHECK_EXIST_USERNAME_SQL = "SELECT * FROM [User] WHERE username = ?";
    private static final String CHECK_EXIST_PHONE_SQL = "SELECT * FROM [User] WHERE phone = ?";
    
    
    @Override
    public void updatePassword(String email, String newPassword) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_PASSWORD_SQL)) {
            
            ps.setString(1, newPassword);
            ps.setString(2, email);
            
            ps.executeUpdate();
            int rows = ps.executeUpdate();
            System.out.println("✅ Rows updated: " + rows);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(String username) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_USER_SQL)) {
            
            ps.setString(1, username);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUserName(rs.getString("username"));
                    user.setFullName(rs.getString("fullname"));
                    user.setPassWord(rs.getString("password"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setRoleid(rs.getInt("roleId"));
                    user.setPhone(rs.getString("phone"));
                    user.setCreatedDate(rs.getDate("createDate"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(User user) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_USER_SQL)) {
            
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassWord());
            ps.setString(5, user.getAvatar());
            ps.setInt(6, user.getRoleid());
            ps.setString(7, user.getPhone());
            ps.setDate(8, user.getCreateDate());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        boolean duplicate = false;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(CHECK_EXIST_EMAIL_SQL)) {
            
            ps.setString(1, email);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    duplicate = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return duplicate;
    }

    @Override
    public boolean checkExistUsername(String username) {
        boolean duplicate = false;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(CHECK_EXIST_USERNAME_SQL)) {
            
            ps.setString(1, username);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    duplicate = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return duplicate;
    }
    
    @Override
    public boolean checkExistPhone(String phone) {
        boolean duplicate = false;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(CHECK_EXIST_PHONE_SQL)) {
            
            ps.setString(1, phone);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    duplicate = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return duplicate;
    }
}