package packageBike;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServiceServlet")
public class UpdateServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       int id;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("idd");
		String service = req.getParameter("serviced");
		PrintWriter out = res.getWriter();
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
		Statement ps=con.createStatement();
		ps.executeUpdate(" delete from service_providers where service_providersid='" + id + 
				"' and service='"+service+"'");
		req.getRequestDispatcher("ownerProfile.jsp").forward(req, res);
		con.close();
		}
		catch(ClassNotFoundException ce)
		{
		out.println("Error " + ce);
		}
		catch(SQLException se)
		{
		out.println("Error " + se);
		}
	}

}
