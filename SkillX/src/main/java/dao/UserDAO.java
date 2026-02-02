package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnection;

public class UserDAO {
    
	public String validateUser(String email, String password) {
	    String role = null;
	    
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(
	             "SELECT role FROM users WHERE email = ? AND password = ?")) {
	        
	        pstmt.setString(1, email);
	        pstmt.setString(2, password);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                role = rs.getString("role");
	                System.out.println(" User found with role: " + role);
	            } else {
	                System.out.println("Invalid credentials");
	            }
	        }
	        
	    } catch (SQLException e) {
	        System.out.println(" Database error during validation!");
	        e.printStackTrace();
	    }
	    
	    return role;
	}
    
    public boolean registerUser(String fullName, String email, String password, 
            String role, String department, int year) {

				try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
				"INSERT INTO users (full_name, email, password, role, department, year) VALUES (?, ?, ?, ?, ?, ?)")) {
				
				pstmt.setString(1, fullName);
				pstmt.setString(2, email);
				pstmt.setString(3, password);  
				pstmt.setString(4, role);
				pstmt.setString(5, department);
				pstmt.setInt(6, year);
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
				} catch (SQLException e) {
				System.out.println(" Registration error: " + e.getMessage());
				e.printStackTrace();
				return false;
					}
    		}		
}
