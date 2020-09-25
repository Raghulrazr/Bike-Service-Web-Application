package packageBike;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CurrentServlet")
public class CurrentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Currently Booked");
		out.println("</title>");
		out.println("<style>");
		out.println("body{ background-image: url('bike10.jpeg');}");
		out.println("table {border-collapse: separate;border: 3px solid black;background-color: #FDB24C ;border-spacing: 2rem; }");
		out.println("td, th { border: 1px solid #999; padding: 0.5rem; text-align: left;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table align='center' width='150' height='75'>");
		out.println("<tr> <td><a href='home.html' ><img src='slogan.jpg' width='200' height='100'></a></td> </tr>");
		out.println("</table>");
		out.println("<h1 align=center style='background-color: steelblue;' >");
		out.println("<font size='7' ><font color='black'><strong> BIKE SERVICE CENTERS</strong></font></font>");
		out.println("</h1>");
		out.println("<table>");
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
		String id=req.getParameter("id");
		String email=req.getParameter("e_mail");
		String password=req.getParameter("password");
		String s1="select  centername, service,date,status from customer_service where customer_id='"+ id+"' and status<>'Completed'";
		PreparedStatement ps=con.prepareStatement(s1);
		ResultSet rs= ps.executeQuery();
		out.println("<form action='PreviousServlet' method='post'>");
		int f=0;
		while(rs.next())
		{
			f=1;
		out.println("<input type='hidden' name='id' value='" + rs.getString(1) +"' / >");
		out.println("<input type='hidden' id='e_mail' name='e_mail' value='"+email +"'>");
		out.println("<input type='hidden' id='password' name='password' value='"+password +"'>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Service Center</th>");
		out.println("<th>Service</th>");
		out.println("<th>Date</th>");
		out.println("<th>Status</th>");
	    out.println("</tr>");
	    out.println("<tr>");
		out.println("<td>");
		out.println("<input type='text' id='centername' name='centernam' value='" +rs.getString(1)+"'/ >");
		out.println("</td>");
		out.println("<td>");
		out.println("<input type='text' id='service' name='service' value='" +rs.getString(2)+"'/ >");
		out.println("</td>");
		out.println("<td>");
		out.println("<input type='text' id='date' name='date' value='" +rs.getString(3)+"'/ >");
		out.println("</td>");
		out.println("<td>");
		out.println("<input type='text' id='status' name='status' value='" +rs.getString(4)+"'/ >");
		out.println("</td>");
		out.println("</tr>");
		}
		if(f==0)
		out.println("No Booking Available");
		else
		{
		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='submit' id='submit' name='submit' value='Back'>");
		out.println("</td>");
		out.println("</tr>");
		}
		out.println("</table>");
		out.println("</form>");
		
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
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("customerProfile.jsp").forward(req, res);
	}

}
