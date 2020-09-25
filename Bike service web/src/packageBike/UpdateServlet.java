  package packageBike;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.sql.*;
import java.io.*;
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

public void doGet(HttpServletRequest req, HttpServletResponse res)
throws ServletException,IOException
{
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
String oid=req.getParameter("id");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
String s1="select * from center_owners where owner_id='"+oid+"'";
PreparedStatement ps=con.prepareStatement(s1);
ResultSet rs= ps.executeQuery();
while(rs.next())
{
out.println("<form action='UpdateServlet' method='post'>");
out.println("<table>");
out.println("<input type='hidden' name='id' value='" + rs.getString(1) +"' / >");
out.println("<tr>");
out.println("<th>Center Name</th> <td>");
out.println("<input type='text' id='centername' name='centername' value='" +rs.getString(2)+"'/ >");
out.println("</td><br>");
out.println("</tr>");
out.println("<tr>");
out.println("<th>Name</th> <td>");
out.println("<input type='text' id='name' name='name' value='" +rs.getString(3)+"'/ >");
out.println("</td><br>");
out.println("</tr>");
out.println("<tr>");
out.println("<th>Address</th> <td>");
out.println("<input type='text' id='address' name='address' value='" +rs.getString(4)+"'/ >");
out.println("</td><br>");
out.println("</tr>");
out.println("<tr>");
out.println("<th>E-Mail</th> <td>");
out.println("<input type='text' id='e_mail' name='e_mail' value='" +rs.getString(5)+"'/ >");
out.println("</td><br>");
out.println("</tr>");
out.println("<tr>");
out.println("<th>Phone Number</th> <td>");
out.println("<input type='text' id='phoneno' name='phoneno' value='" +rs.getString(6)+"'/ >");
out.println("<td><br>");
out.println("</tr>");
out.println("<tr>");
out.println("<th>Password</th> <td>");
out.println("<input type='password' id='password' name='password' value='" +rs.getString(7)+"'/ >");
out.println("<td><br>");
out.println("</tr>");
out.println("</table>");
out.println("<input type='submit' name='submit' value='Update' / >");
out.println("</form>");

}
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

public void doPost(HttpServletRequest req, HttpServletResponse res)
throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
out.println("<html>");
out.println("<head>");
out.println("<title>");
out.println("Select Page");
out.println("</title>");
out.println("</head>");
out.println("<body>");
String id = req.getParameter("id");
String center = req.getParameter("centername");
String address = req.getParameter("address");
String name = req.getParameter("name");
String email= req.getParameter("e_mail");
String phoneno = req.getParameter("phoneno");
String password = req.getParameter("password");
try
{
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
Statement ps=con.createStatement();
ps.executeUpdate("update center_owners set center='"+center+"' , address='"+address+  "' , name='"+name+  "' , e_mail='"+email+
		"' ,  phone_number='"+ phoneno+ "' ,password='"+password+  "' where owner_id='" + id + "'");
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
out.println("</body>");
out.println("</html>");
}
}