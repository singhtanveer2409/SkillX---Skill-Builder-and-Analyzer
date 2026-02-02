package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.UserDAO;

@WebServlet("/RegisterHandler")
public class RegisterHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public RegisterHandler() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String department = request.getParameter("department");
        String yearStr = request.getParameter("year");
        String role = request.getParameter("role");
        
        
        int year = Integer.parseInt(yearStr);
        
        
        UserDAO ud = new UserDAO();
        boolean isRegistered = ud.registerUser(fullName, email, password, role, department, year);
        
        if (isRegistered) {
            System.out.println(" User registered successfully: " + email);
            response.sendRedirect("index.html?registered=success");
        } else {
            System.out.println(" Registration failed for: " + email);
            response.getWriter().print("Registration failed. Email might already exist.");
        }
    }
}