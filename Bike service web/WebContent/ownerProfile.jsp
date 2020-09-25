<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Owner Profile</title>
<style type="text/css">
#form1 {
	margin: 0 auto;
	width: 250px;
}
table {
	border-collapse: separate;
	border: 3px solid black;
	background-color: #FDB24C ;
	border-spacing: 2rem;
}

td, th {
  border: 1px solid #999;
  padding: 0.5rem;
  text-align: left;
}
div {
margin: 0 auto;
	width: 250px;
}
body{
background-image: url('bike9.jpg');
}
#htable
{
   margin-left:auto; 
   margin-right:auto;
}
</style>
</head>
<body>
<table id="htable" >
		<tr>
			<td><a href="home.html" ><img src="slogan.jpg" width="200" height="100"></a></td>
		</tr>
</table>
	<h1 align=center style="background-color: steelblue;" >
		<font size="7" ><font color="black"><strong> BIKE SERVICE CENTERS</strong></font></font>
	</h1>
	<%@page import="java.sql.*"%>
	<%@page import="java.sql.DriverManager"%>
	<%
String email=request.getParameter("e_mail");
String pass=request.getParameter("password");
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
Statement sp=con.createStatement();
ResultSet rs=sp.executeQuery("select * from center_owners where e_mail='"+email+"' and password='"+pass+"'");
if(rs.next())
{
%>
	<h1><font color="white" >
		Hi
		<%=rs.getString(3) %>
		Welcome To Your
		<%= rs.getString(2) %>
		Details
		</font>
	</h1>
	<form method="get" action="UpdateServlet">
	<input type='hidden' id='id' name='id' value='<%=rs.getString(1) %>'>
		<table style="border: 1px solid black; border-collapse: collapse;">
			<tr>
				<th>Center Name</th>
				<td><%=rs.getString(2)%></td>
			</tr>
			<tr>
				<th>Name</th>
				<td><%=rs.getString(3)%></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><%=rs.getString(4)%></td>
			</tr>
			<tr>
				<th>E-Mail</th>
				<td><%=rs.getString(5)%></td>
			</tr>
			<tr>
				<th>Phone Number</th>
				<td><%=rs.getString(6)%></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><%=rs.getString(7)%></td>
			</tr>
		</table>
		<input type="submit" name="update" value="Edit">
	</form>
	<br>
	<br>
	<table id="form1">
	<tr>
	<td>
		<form method="get" action="BookingListServlet">
				<input type='hidden' id='id' name='id' value='<%=rs.getString(1) %>'>
				<input type='hidden' id='centername' name='centername' value='<%=rs.getString(2) %>'>
				<input type='hidden' id='e_mail' name='e_mail' value='<%=rs.getString(5) %>'>
				<input type='hidden' id='password' name='password' value='<%=rs.getString(7) %>'>
				<input type="submit" name="Bookservice" value="Booking List">
		</form>
	</td>
	</tr>
	</table>
	<br>
	<br>
	<% 
	int h=1;
Statement sp1=con.createStatement();
ResultSet es=sp1.executeQuery("select * from  service_providers where service_providersid='"+rs.getString(1)+"'");
while(es.next())
{
%>
	<form method="post" action="UpdateServiceServlet">
		<input type='hidden' id='idd' name='idd' value='<%=rs.getString(1) %>'>
		<input type='hidden' id='serviced' name='serviced' value='<%=es.getString(2) %>'>
		<input type='hidden' id='e_mail' name='e_mail' value='<%=rs.getString(5) %>'>
		<input type='hidden' id='password' name='password' value='<%=rs.getString(7) %>'>
	<br>
		<table style="border: 1px solid black; border-collapse: collapse;">
			<tr>
				<th>Service <%out.print((h++)+":-   \n"); %></th>
				<td><%=es.getString(2)%></td>
			</tr>
		</table>
		<input type="submit" name="update" value="delete">
	</form>
	<%} 
%>
<br>
<br>
	<form action="Servlet" method="post">
		<input type='hidden' id='id' name='id' value='<%=rs.getString(1) %>'>
		<input type='hidden' id='e_mail' name='e_mail' value='<%=rs.getString(5) %>'>
		<input type='hidden' id='password' name='password' value='<%=rs.getString(7) %>'>
		<h4><font color="white" >SERVICE NAME:</font></h4>
		<input type="text" id="Service" name="service"> 
		<input type="submit" name="submit" value="ADD">
	</form>
		<% 
}
		con.close(); 
%>
	
</body>
</html>