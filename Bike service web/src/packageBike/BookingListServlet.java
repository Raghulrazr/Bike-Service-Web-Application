package packageBike;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookingListServlet")
public class BookingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Update Page");
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
		String s1="select customer_id,service,date,status,centername from customer_service where serviceid='"+ id+"'";
		PreparedStatement ps=con.prepareStatement(s1);
		ResultSet rs= ps.executeQuery();
		while(rs.next())
		{
			String cid=rs.getString(1);
			String s2="select customer_id,name from customer_details where customer_id='"+ cid+"'";
			PreparedStatement gs=con.prepareStatement(s2);
			ResultSet ds= gs.executeQuery();
			while(ds.next())
			{
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>name</th>");
		out.println("<th>Service</th>");
		out.println("<th>Date</th>");
		out.println("<th>Status</th>");
	    out.println("</tr>");
	    out.println("<tr>");
		out.println("<td>");
		out.println("<input type='text' id='centername' name='centernam' value='" +ds.getString(2)+"'/ >");
		out.println("</td>");
		out.println("<td>");
		out.println("<input type='text' id='service' name='service' value='" +rs.getString(2)+"'/ >");
		out.println("</td>");
		out.println("<td>");
		out.println("<input type='text' id='date' name='date' value='" +rs.getString(3)+"'/ >");
		out.println("</td>");
		out.println("<td>");
		String s6="select e_mail,bike_number,name from customer_details where customer_id='"+ cid+"'";
		PreparedStatement ps1=con.prepareStatement(s6);
		ResultSet bs= ps1.executeQuery();
		while(bs.next())
		{
		String cemail=bs.getString(1);
		String bikeno=bs.getString(2);
		out.println("<form action='BookingListServlet' method='post'>");
		out.println("<input type='hidden' name='id' value='" + id +"' / >");
		out.println("<input type='hidden' name='cid' value='" + cid +"' / >");
		out.println("<input type='hidden' id='date' name='date' value='" +rs.getString(3)+"'/ >");
		out.println("<input type='hidden' id='e_mail' name='e_mail' value='"+email +"'>");
		out.println("<input type='hidden' id='password' name='password' value='"+password +"'>");
		out.println("<input type='hidden' name='recipient' value='"+cemail+ "'/>");
		out.println("<input type='hidden' name='subject' value='Bike Ready for Delivery' >");
		out.println("<input type='hidden' name='content' value='Hi "+bs.getString(3)+" your bike no "+bikeno
				+" has been ready for delivery from the "+rs.getString(5)+"'>");
		out.print("<input type='text' id='status' name='status' value='" +rs.getString(4)+"'/ >");
		out.print("<select name='status1'>");
		out.println("<option value='Change'>Change</option>");
		out.println("<option value='Ready For Deliver'>Ready For Deliver</option>");
		out.println("<option value='Completed'>Completed</option>");
		out.print("</select>");
		out.println("<input type='submit' id='submit' name='submit' value='Change Status' >");
		out.println("</form>");
		}
		out.println("</td>");
		out.println("<td>");
		out.println("<form action='BookingListServlet' method='post'>");
		out.println("<input type='hidden' name='id' value='" + id +"' / >");
		out.println("<input type='hidden' name='cid' value='" + cid +"' / >");
		out.println("<input type='hidden' id='e_mail' name='e_mail' value='"+email +"'>");
		out.println("<input type='hidden' id='password' name='password' value='"+password +"'>");
		out.println("<input type='submit' id='submit' name='submit' value='view details' >");
		out.println("</form>");
		out.println("</td>");
		out.println("</tr>");
			}
		}
		out.println("<tr>");
		out.println("<td>");
		out.println("<form action='BookingListServlet' method='post'>");
		out.println("<input type='hidden' name='id' value='" + id +"' / >");
		out.println("<input type='hidden' id='e_mail' name='e_mail' value='"+email +"'>");
		out.println("<input type='hidden' id='password' name='password' value='"+password +"'>");
		out.println("<input type='submit' id='submit' name='submit' value='Back'>");
		out.println("</td>");
		out.println("</tr>");
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
		String submit=req.getParameter("submit");
		PrintWriter out = res.getWriter();
		if(submit.equals("Back"))
		{
			req.getRequestDispatcher("ownerProfile.jsp").forward(req, res);
		}
		else if(submit.equals("Change Status"))
		{
			try
			{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
			String status=req.getParameter("status1");
			String date=req.getParameter("date");
			String cid=req.getParameter("cid");
			String id=req.getParameter("id");
			String s1="update customer_service set status='"+ status+"' where date='"+date+"' and customer_id='"+
			cid+"' and serviceid='"+id+"'";
			Statement ps=con.createStatement();
			ps.executeUpdate(s1);
			if(status.equals("Ready For Deliver"))
			{
				String recipient = req.getParameter("recipient");
		        String subject = req.getParameter("subject");
		        String content = req.getParameter("content");
			String resultMessage = "";
			 
	        try {
	            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
	                    content);
	            resultMessage = "The e-mail was sent successfully";
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            resultMessage = "There were an error: " + ex.getMessage();
	        } finally {
	            req.setAttribute("Message", resultMessage);
	        }
			}
			req.getRequestDispatcher("ownerProfile.jsp").forward(req, res);
			}
			catch(SQLException se)
			{
			out.println("Error " + se);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
		res.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Update Page");
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
		String id=req.getParameter("cid");
		String oid=req.getParameter("id");
		String email=req.getParameter("e_mail");
		String password=req.getParameter("password");
		String s1="select  bike_company,bike_number, name, phone_number,e_mail from customer_details where customer_id='"+ id+"'";
		PreparedStatement ps=con.prepareStatement(s1);
		ResultSet rs= ps.executeQuery();
		out.println("<form action='BookingListServlet' method='get'>");
		while(rs.next())
		{
		out.println("<input type='hidden' name='id' value='" + oid +"' / >");
		out.println("<input type='hidden' id='e_mail' name='e_mail' value='"+email +"'>");
		out.println("<input type='hidden' id='password' name='password' value='"+password +"'>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Bike Company</th>");
		out.println("<th>Bike Number</th>");
		out.println("<th>Name</th>");
		out.println("<th>Phone Number</th>");
		out.println("<th>E-Mail ID</th>");
	    out.println("</tr>");
	    out.println("<tr>");
		out.println("<td>");
		out.println("<input type='text' id='bike_company' name='bike_company' value='" +rs.getString(1)+"'/ >");
		out.println("</td>");
		out.println("<td>");
		out.println("<input type='text' id='bike_number' name='bike_number' value='" +rs.getString(2)+"'/ >");
		out.println("</td>");
		out.println("<td>");
		out.println("<input type='text' id='name' name='name' value='" +rs.getString(3)+"'/ >");
		out.println("</td>");
		out.println("<td>");
		out.println("<input type='text' id='phone_number' name='phone_number' value='" +rs.getString(4)+"'/ >");
		out.println("</td>");
		out.println("<td>");
		out.println("<input type='text' id='cemail' name='cemail' value='" +rs.getString(5)+"'/ >");
		out.println("</td>");
		out.println("</tr>");
		}
		out.println("<tr>");
		out.println("<td>");
		out.println("<input type='submit' id='submit' name='submit' value='Back'>");
		out.println("</td>");
		out.println("</tr>");
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
	}

}
