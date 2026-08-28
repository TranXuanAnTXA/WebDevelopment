package com.baitap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.baitap.connection.DBConnection;
import com.baitap.dao.UserDao;
import com.baitap.model.User;


public class UserDaoImpl implements UserDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public User get(String username) {
		String sql = "SELECT * FROM [Users] WHERE username = ? ";
		try {
			conn = new DBConnection().getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		rs = ps.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setUserName(rs.getString("username"));
			user.setFullName(rs.getString("fullname"));
			user.setPassWord(rs.getString("password"));
			user.setAvatar(rs.getString("avatar"));
			user.setRoleid(Integer.parseInt(rs.getString("roleid")));
			user.setPhone(rs.getString("phone"));
			user.setCreatedDate(rs.getDate("createdDate"));
			return user; }
		} catch (Exception e) {e.printStackTrace(); }
		return null;
	}
	
	// =========================
    // 1. LOGIN
    // =========================
    @Override
    public User login(String username, String password) {

        String sql = "SELECT id, email, username, fullname, password, "
                   + "avatar, roleid, phone, createdDate "
                   + "FROM Users "
                   + "WHERE username = ? AND password = ?";

        try (
            Connection conn = new DBConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    System.out.println(">>> LOGIN THÀNH CÔNG");
                    System.out.println(">>> Tìm thấy user: " + rs.getString("username"));
                    User user = new User();

                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUserName(rs.getString("username"));
                    user.setFullName(rs.getString("fullname"));
                    user.setPassWord(rs.getString("password"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setRoleid(rs.getInt("roleid"));
                    user.setPhone(rs.getString("phone"));
                    user.setCreatedDate(rs.getDate("createdDate"));

                    return user;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


	 // =========================
    // 2. INSERT USER
    // =========================
    @Override
    public void insert(User user) {

        String sql = "INSERT INTO Users "
                   + "(email, username, fullname, password, avatar, roleid, phone, createdDate) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = new DBConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassWord());
            ps.setString(5, user.getAvatar());
            ps.setInt(6, user.getRoleid());
            ps.setString(7, user.getPhone());

            if (user.getCreatedDate() != null) {
                ps.setDate(8, user.getCreatedDate());
            } else {
                ps.setDate(
                    8,
                    new java.sql.Date(System.currentTimeMillis())
                );
            }

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =========================
    // 3. KIỂM TRA EMAIL
    // =========================
    @Override
    public boolean checkExistEmail(String email) {

        String sql = "SELECT 1 FROM Users WHERE email = ?";

        try (
            Connection conn = new DBConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // 4. KIỂM TRA USERNAME
    // =========================
    @Override
    public boolean checkExistUsername(String username) {

        String sql = "SELECT 1 FROM Users WHERE username = ?";

        try (
            Connection conn = new DBConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // 5. KIỂM TRA PHONE
    // =========================
    @Override
    public boolean checkExistPhone(String phone) {

        String sql = "SELECT 1 FROM Users WHERE phone = ?";

        try (
            Connection conn = new DBConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, phone);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}