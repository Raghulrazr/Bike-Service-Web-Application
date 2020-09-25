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

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	
	
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
		out.println("Add service");
		out.println("</title>");
		out.println("<style>");
		out.println("body {margin: 20 auto;width: 250px;background-color:#10F3F3;}");
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
		String id=req.getParameter("id");
		String center=req.getParameter("centername");
		String customerid=req.getParameter("customer_id");
		String date=req.getParameter("date");
		String email=req.getParameter("e_mail");
		String oemail=req.getParameter("oe_mail");
		String name=req.getParameter("cname");
		String password=req.getParameter("password");
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
		String s1="select * from  service_providers where service_providersid='"+id+"'";
		PreparedStatement ps=con.prepareStatement(s1);
		ResultSet rs= ps.executeQuery();
		out.println("<h1> Select the Service Needed </h1>");
		out.println("<form action='AddServlet' method='post'>");
		out.println("<input type='hidden' name='id' value='" + id +"' / >");
		out.println("<input type='hidden' id='date' name='date' value='"+date+"'/ >");
		out.println("<input type='hidden' name='centername' value='" + center +"' / >");
		out.println("<input type='hidden' id='customer_id' name='customer_id' value='"+customerid +"'/>");
		out.println("<input type='hidden' id='e_mail' name='e_mail' value='"+email +"'/>");
		out.println("<input type='hidden' id='password' name='password' value='"+password +"'/>");
		out.println("<input type='hidden' name='recipient' value='"+oemail+ "'/>");
		out.println("<input type='hidden' name='subject' value='NewBooking' >");
		out.println("<input type='hidden' name='content' value='U have Recieved new service booking request from "
		+name+ ", who want a service on "+ date+". The services requested are '/>");
		out.println("<select name='service'  multiple>");
		while(rs.next())
		{
		out.println("<option value='"+rs.getString(2)+"'>"+rs.getString(2)+"</option>");
		}
		out.println("</select>");
		out.println("<br>");
		out.println("<input type='submit' name='submit' value='Book' >");
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
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String id = req.getParameter("id");
		String submit = req.getParameter("submit");
		 String value="";
		String centername = req.getParameter("centername");
		 String[] service   =req.getParameterValues("service");
		            for(int p=0;p<service.length;p++)
		            {
		                value += service[p]+",";
		            }
		String customerid=req.getParameter("customer_id");
		String date=req.getParameter("date");
		if(submit.equals("Book"))
		{
			String recipient = req.getParameter("recipient");
	        String subject = req.getParameter("subject");
	        String content1 = req.getParameter("content");
	        String content=content1+value;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
		Statement ps=con.createStatement();
		ps.executeUpdate("insert into customer_service(customer_id ,service,date,serviceid,centername, status)"
				+ " values('"+ customerid+"' ,'"+value+"' ,'"+date+"' ,'"+id+"' ,'"+centername+"','Booked')");
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
            req.getRequestDispatcher("customerProfile.jsp").forward(req, res);
        }
		}
	}

}
