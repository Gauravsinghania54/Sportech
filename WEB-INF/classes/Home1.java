import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

@WebServlet("/Home1")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class Home1 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HttpSession session=request.getSession(true);
		Utilities utility = new Utilities(request,pw);
		if (session.getAttribute("username")!=null){
			String city = session.getAttribute("city").toString();
			System.out.println(city);
		utility.printHtml("header.html");
		utility.printHtml("loginheader.html");
		utility.printHtml("recommend.html");
		utility.printHtml("halfheader.html");
		pw.print("<br><br><br><br><br><br><br>");
		utility.printHtml("news.html");
		
		//utility.printHtml("content.html");
		utility.printHtml("footer.html");
	}
	}

}
