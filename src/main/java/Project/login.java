package Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class login extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PrintWriter pw=response.getWriter();
			response.setContentType("text/html");
			String n=request.getParameter("uname");
			String p=request.getParameter("pwd");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bitm","root","tiger");
			PreparedStatement ps=con.prepareStatement("select username from login where username=? and password=?;");
			ps.setString(1, n);
			ps.setString(2, p);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				RequestDispatcher rd=request.getRequestDispatcher("Mypage.html");
				rd.forward(request, response);
			}
			else {
				pw.print("<font color=red>Username or password is incorrect<br>");
				pw.print("<a href=index.html>Try Again</a> ");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
