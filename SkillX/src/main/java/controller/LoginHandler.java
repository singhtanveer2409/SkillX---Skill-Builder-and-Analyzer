/*package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.UserDAO;

@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public LoginHandler() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("Inside get()\n");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        UserDAO ud = new UserDAO();
        boolean auth = ud.validateUser(email, password);
        //ud.getData();
        
        if(auth) {
            response.sendRedirect("dashboard.html");
        } else {
            response.getWriter().print("Invalid Email or Password");
            // or response.sendRedirect("login.html?error=1");
        }
    }
}*/
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.UserDAO;

@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public LoginHandler() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.getWriter().print("Inside get()\n");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        UserDAO ud = new UserDAO();
        String role = ud.validateUser(email, password);
        
        if (role != null) {
            // Redirect based on role
            if (role.equalsIgnoreCase("admin")) {
                System.out.println("🔑 Admin login - redirecting to admin dashboard");
                response.sendRedirect("admin.html");
            } else if (role.equalsIgnoreCase("student")) {
                System.out.println("👨‍🎓 Student login - redirecting to student dashboard");
                response.sendRedirect("dashboard.html");
            } else {
                response.getWriter().print("Unknown user role");
            }
        } else {
            response.getWriter().print("Invalid Email or Password");
        }
    }
}