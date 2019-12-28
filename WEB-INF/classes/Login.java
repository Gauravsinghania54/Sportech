import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.*;
import java.sql.Array;
import org.apache.commons.lang3.ArrayUtils;



@WebServlet("/Login")

public class Login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayLogin(request, response, pw, false);
	}
	static Connection conn = null;
	static String message;
	public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sportech?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");							
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

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        String username = request.getParameter("username");
		String password = request.getParameter("password");
		String city=request.getParameter("city");
		//String[] preferences=request.getParameterValues("preferences");
		
		/*for(int i=0;i<preferences.length;i++)
	{
			this.preferences[i]=preferences[i];
	}*/
		
		try
		{	
			getConnection();

		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Register";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{
		if(username.equals(rs.getString("username")))
		{
		 String user_password = rs.getString("password");
		 if (password.equals(user_password)) 
			{
			HttpSession session = request.getSession(true);
			session.setAttribute("username", rs.getString("username"));
			session.setAttribute("city",city);
			response.sendRedirect("Home1");
			return;
			}
		}
	}
		}
		catch(Exception e)
		{
				displayLogin(request, response, pw, true);
		}
		//User user = hm.get(username);
		
		
	}



	protected void displayLogin(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("header.html");
		pw.print("<br><br><br><br><br><br><br><div class='post' style='float: none; width: 100%;margin:25px; margin-left: auto;margin-right: auto;margin-top:auto;'>");
		pw.print("<h2 class='title meta'style='margin:25px; margin-left: auto;margin-right: auto;margin-top:auto;'><a style='font-size: 24px;margin:25px; margin-left: 400px;margin-right: auto;margin-top:auto;'>Login</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;margin-top:auto;'>");
		if (error)
			pw.print("<h4 style='color:red'>Please check your username, password </h4>");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("login_msg")!=null){			
			pw.print("<h4 style='color:red'>"+session.getAttribute("login_msg")+"</h4>");
			session.removeAttribute("login_msg");
		}
		pw.print("<form method='post' action='Login'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Enter Your City</h3></td><td><input type='text' name='city' value='' class='input'></input>"
				+ "</td></tr>"
				+ "<tr><td></td><td>"
				+ "<input type='submit' class='btnbuy' value='Login' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</td></tr><tr><td></td><td>"
				+ "<strong><a class='' href='Register' style='float: right;height: 20px margin: 20px;'>New User? Register here!</a></strong>"
				+ "</td></tr></table>"
				+ "</form>" + "</div></div></div>");
		utility.printHtml("footer.html");
	}
/*public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Register";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{
            Array array=rs.getArray("preferences");
			String[] arr=(String[])array.getArray();
	
			User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("email"),arr);
				hm.put(rs.getString("username"), user);

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}*/
			}
			
