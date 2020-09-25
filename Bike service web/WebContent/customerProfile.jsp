<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Profile</title>
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
background-image: url('bike8.jpg');
}
#htable
{
   margin-left:auto; 
   margin-right:auto;
}
</style>
<script type="text/javascript">
function check()
{
	var a= document.getElementById("date");
	if(a=="")
	{
	alert("Please enter date of service");
	return false;
	}
	else
		return true;
}
</script>
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
ResultSet rs=sp.executeQuery(" select * from customer_details where e_mail='"+email+"' and password='"+pass+"'");
if(rs.next())
	
{
%>
	<h1><font color="white" >
		Hello <%=rs.getString(4) %> Welcome To Bike Service Center
		</font>
	</h1>
	<form method="get" action="UpdateCustomerServlet">
	<input type='hidden' id='id' name='id' value='<%=rs.getString(1) %>'>
		<table style="border: 1px solid black; border-collapse: collapse;">
			<tr>
				<th>Bike Company</th>
				<th>Bike number</th>
				<th>Name</th>
				<th>Phone Number</th>
				<th>E-Mail</th>
				<th>Password</th>
			</tr>
			<tr>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getString(3)%></td>
				<td><%=rs.getString(4)%></td>
				<td><%=rs.getString(5)%></td>
				<td><%=rs.getString(6)%></td>
				<td><%=rs.getString(7)%></td>
			</tr>
		</table>
		<br>
		<div>
		<input type="submit" name="update" value="Edit">
		</div>
	</form>
	<br>
<br>
<br>
<table id="form1">
<tr>
	<td>
		<form method="get" action="CurrentServlet">
				<input type='hidden' id='id' name='id' value='<%=rs.getString(1) %>'>
				<input type='hidden' id='e_mail' name='e_mail' value='<%=rs.getString(6) %>'>
				<input type='hidden' id='password' name='password' value='<%=rs.getString(7) %>'>
				<input type="submit" name="Bookservice" value="Currently Booked">
		</form>
	</td>
	<td>
		<form method="get" action="PreviousServlet">
				<input type='hidden' id='id' name='id' value='<%=rs.getString(1) %>'>
				<input type='hidden' id='e_mail' name='e_mail' value='<%=rs.getString(6) %>'>
				<input type='hidden' id='password' name='password' value='<%=rs.getString(7) %>'>
				<input type="submit" name="Bookservice" value="Previous Booked">
		</form>
	</td>
</tr>
</table>
	<br>
	<h1 style="text-align:center;" ><font color="red" >SERVICE CENTERS LIST - BOOK NOW</font></h1>
	<% 
	int h=1;
Statement sp1=con.createStatement();
ResultSet es=sp1.executeQuery("select * from  center_owners");
String id2=rs.getString(1);
while(es.next())
{
	Statement sp2=con.createStatement();
	ResultSet ds=sp2.executeQuery("select * from customer_service where customer_id="+id2);
%>
<form method="get" onsubmit="return check()" action="AddServlet"  id="form1">
<input type='hidden' id='id' name='id' value='<%=es.getString(1) %>'>
		<input type='hidden' id='centername' name='centername' value='<%=es.getString(2) %>'>
		<input type='hidden' id='customer_id' name='customer_id' value='<%=rs.getString(1) %>'>
		<input type='hidden' id='e_mail' name='e_mail' value='<%=rs.getString(6) %>'>
		<input type='hidden' id='password' name='password' value='<%=rs.getString(7) %>'>
		<input type='hidden' id='oe_mail' name='oe_mail' value='<%=es.getString(5) %>'>
		<input type='hidden' id='cname' name='cname' value='<%=rs.getString(4) %>'>
		<table style="border: 1px solid black; border-collapse: collapse;">
			<tr>
				<th>Center Name</th>
				<td><%=es.getString(2)%></td>
			</tr>
			<tr>
				<th>Name</th>
				<td><%=es.getString(3)%></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><%=es.getString(4)%></td>
			</tr>
			<tr>
				<th>E-Mail</th>
				<td><%=es.getString(5)%></td>
			</tr>
			<tr>
				<th>Phone Number</th>
				<td><%=es.getString(6)%></td>
			</tr>
			<tr>
			<th>Book a date</th>
			<td><input type="date" id="date" name="date" ></td>
			</tr>
		</table>
		
		<div><input type="submit" name="Bookservice" value="Book Now"></div>

	</form>
<br>
<br>
<br>
	<%
}
}
		con.close(); %>


</body>
</html>