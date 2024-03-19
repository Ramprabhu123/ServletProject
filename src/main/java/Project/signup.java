package Project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String n=request.getParameter("uname");
		String p=request.getParameter("pwd");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bitm","root","tiger");
			PreparedStatement ps=con.prepareStatement("insert into login values(?,?);");
			ps.setString(1, n);
			ps.setString(2, p);
			int result=ps.executeUpdate();
			if(result>0) {
				pw.print("<h1>Signed Up</h1>");
			}
			else {
				pw.print("<h2>Not Signed Up</h2>");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
