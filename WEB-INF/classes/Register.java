import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.*;
import java.util.*;

@WebServlet("/Register")

public class Register extends HttpServlet {
	private String error_msg;
	static Connection conn = null;
	static String message;
	
	
public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportech?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");							
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayRegistration(request, response, pw, false);
	}
	protected void displayRegistration(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("header.html");
		pw.print("<br><br><br><br><br><br><br><div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;margin-left: 400px;'>Registration</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;margin-top:auto;'>");
		if (error)
			pw.print("<h4 style='color:red'>"+error_msg+"</h4>");
		pw.print("<form method='post' action='Register'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Email</h3></td><td><input type='text' name='email' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<label for='preferences'><h3><b>Sport Preferences</b></h3></label>"
				+ "</td></tr><tr><td>"
				+"<input type='checkbox' name='preferences' value='hockey'> Hockey</input>"
                +"</td><td><input type='checkbox' name='preferences' value='basketball'> Basketball</input>"
                +"</td><td><input type='checkbox' name='preferences' value='baseball'> Baseball</input>"
                +"</td><td><input type='checkbox' name='preferences' value='Soccer'> Soccer </input>" 
                +"</td><td><input type='checkbox' name='preferences' value='handball'> Handball</input>"
                +"</td><td><input type='checkbox' name='preferences' value='tennis'> Tennis</input>"
				+ "</td></tr></table>"
				+ "<input type='submit' class='btnbuy' name='ByUser' value='Create User' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</form>" 
				+ "</div></div></div>");
		utility.printHtml("footer.html");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String preferences[]=request.getParameterValues("preferences");
		
		

		//if password and repassword does not match show error message

		
		getConnection();

		
		if(message.equals("Successfull"))
		{
			
				/*create a user object and store details into hashmap
				store the user hashmap into file  */

				
			
			    try{	
						String insertIntoCustomerRegisterQuery = "INSERT INTO Register(username,password,email,preferences) "
						+ "VALUES (?,?,?,?);";
						PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
						pst.setString(1,username);
						pst.setString(2,password);
						pst.setString(3,email);
						for(String pf : preferences)
						{
						pst.setString(4,pf);
						}
						pst.execute();
				}
			
				catch(Exception e)
				{
	
				}						

				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Your account has been created. Please login");
				if(!utility.isLoggedin()){
					response.sendRedirect("Login"); return;
				} else {
					response.sendRedirect("Login"); return;
				}
			
				
			
			
		}
		else 
		{
			error_msg="MySql server is not up and running";
		}
		
	}
	
}
