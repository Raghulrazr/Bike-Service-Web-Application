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

@WebServlet("/Servlet")
public class Servlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public String name;
	public String password;
	public String email;
	public String phone_no;
	public String bike_company;
	public String bike_no;
	public String address;
	public String center;
	public String id;
	public String service;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String submit=req.getParameter("submit");
		PrintWriter out= res.getWriter();
		if(submit.equals("CustomerRegister"))
		{
			name=req.getParameter("name");
			password=req.getParameter("password");
			email=req.getParameter("e_mail");
			phone_no=req.getParameter("phone_number");
			bike_company=req.getParameter("bike_company");
			bike_no=req.getParameter("bike_number");
			try {
				insert_customer_details();
				req.getRequestDispatcher("customer_login.html").forward(req, res);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		if(submit.equals("OwnerRegister"))
		{
			name=req.getParameter("name");
			password=req.getParameter("password");
			email=req.getParameter("e_mail");
			phone_no=req.getParameter("phone_number");
			address=req.getParameter("address");
			center=req.getParameter("center");
			try {
				insert_owner_details();
				req.getRequestDispatcher("owner_login.html").forward(req, res);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		if(submit.equals("OwnerLogin") || submit.equals("ADD"))
		{
			password=req.getParameter("password");
			email=req.getParameter("e_mail");
			if(submit.equals("ADD"))
			{
			id=req.getParameter("id");
			service=req.getParameter("service");
			}
			try {
				if(submit.equals("ADD"))
				{
				addService();
				}
				if(owner_check()!=0)
				{
					req.getRequestDispatcher("ownerProfile.jsp").forward(req, res);
				}
				else
				{
					out.print("Login Failed Retry!!!");
					req.getRequestDispatcher("oerror.html").forward(req, res);
					
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		if(submit.equals("CustomerLogin"))
		{
			
			password=req.getParameter("password");
			email=req.getParameter("e_mail");
			try {
				if(customer_check()!=0)
				{
					req.getRequestDispatcher("customerProfile.jsp").forward(req, res);
				}
				else
				{
					out.print("Login Failed Retry!!!");
					req.getRequestDispatcher("cerror.html").forward(req, res);
					
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private String addService() throws ClassNotFoundException, SQLException {
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
		String s1="insert into service_providers value(?,?)";
		PreparedStatement ps=con.prepareStatement(s1);
		ps.setString(1, id);
		ps.setString(2, service);
		ps.executeUpdate();
		con.close();
		return "success";
		}
		 catch(Exception ex){
	      	 return ex.toString();
	      }
	}
	private String insert_customer_details() throws ClassNotFoundException, SQLException {
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
		String s1="insert into customer_details(bike_company,bike_number ,name ,phone_number,e_mail,password) value(?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(s1);
		ps.setString(1, bike_company);
		ps.setString(2, bike_no);
		ps.setString(3, name);
		ps.setString(4, phone_no);
		ps.setString(5, email);
		ps.setString(6, password);
		ps.executeUpdate();
		con.close();
		return "Success";
		}
	  catch(Exception ex){
      	 return ex.toString();
      }
	}
	private String insert_owner_details() throws ClassNotFoundException, SQLException {
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
		String s1="insert into center_owners(center ,name,address ,phone_number,e_mail,password) value(?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(s1);
		ps.setString(1, center);
		ps.setString(2, name);
		ps.setString(3, address);
		ps.setString(4, phone_no);
		ps.setString(5, email);
		ps.setString(6, password);
		ps.executeUpdate();
		con.close();
		return "Success";
		}
	  catch(Exception ex){
      	 return ex.toString();
      }
	}
	private int owner_check() throws ClassNotFoundException, SQLException {
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
		String s1="select owner_id from center_owners where e_mail= ? and password= ?";
		int m=0;
		PreparedStatement ps=con.prepareStatement(s1);
		ps.setString(2, password);
		ps.setString(1, email);
		ResultSet rs= ps.executeQuery();
		while(rs.next())
		{
			m=rs.getInt(1);
		}
		con.close();
		return m;
		}
	  catch(Exception ex){
      	 return 0;
      }
	}
	private int customer_check() throws ClassNotFoundException, SQLException {
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_service","root","9985@raghuls");
		String s1="select customer_id from customer_details where e_mail= ? and password= ?";
		int m=0;
		PreparedStatement ps=con.prepareStatement(s1);
		ps.setString(2, password);
		ps.setString(1, email);
		ResultSet rs= ps.executeQuery();
		while(rs.next())
		{
			m=rs.getInt(1);
		}
		con.close();
		return m;
		}
	  catch(Exception ex){
      	 return 0;
      }
	}
}
