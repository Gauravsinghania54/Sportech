	
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.http.HttpSession;


@WebServlet("/EventsData")
public class EventsData extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			HttpSession session=request.getSession(true);
			if (session.getAttribute("username")!=null)
			{
				try
				{

PrintWriter pw= response.getWriter();
response.setContentType("text/html");			
 pw.println("<html>");
 pw.println("<body>");

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("header.html");
        utility.printHtml("loginheader.html");
        
		
		utility.printHtml("recommend.html");
		
        utility.printHtml("halfheader.html");
        utility.printHtml("cards.html");
		pw.print("<br><br><br><br><br><br><br><div ><div class='post'><h2 class='title meta'>");
		
		pw.print("</h2><div class='entry'><table  id='bestseller'>");
	       pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			Events event= (Events)request.getAttribute("data");
			System.out.println(event);
			pw.print("<a class='card'>");
			pw.print("<div class='header'>");
      pw.print("<div class='header-image' style='background-image: url('https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Page_31_illustration_to_Three_hundred_Aesop%27s_fables_%28Townshend%29.png/724px-Page_31_illustration_to_Three_hundred_Aesop%27s_fables_%28Townshend%29.png')'>");
      pw.print("</div></div>");
    pw.print("<div class='card-content'>");
      pw.print("<div class='card-body-title'>");
        pw.print("<h2>Event Name :"+event.getName()+"</h2>");
      pw.print("</div>");
      pw.print("<div class='card-body'>");
        pw.print("<p>"+event.getEventDate());
        pw.print("<br/>");
			pw.print(event.getVenue());
			pw.print("<br/>");
			pw.print(event.getCity());
			pw.print("<br/>");
			pw.print(event.getState());
        pw.print("</p></div><div class='card-body-footer'></div></div></a></div>");

			
			pw.print("</div></td>");
			 pw.print("</tr>");
			
			
		pw.print("</table></div></div></div>");
  utility.printHtml("footer.html");
  String username = session.getAttribute("username").toString();
  String id=event.getId();
  String category=event.getType();
  String eventname=event.getName();
  System.out.println(username);
  
  MongoDBConnection.setFavoriteItems(username,eventname,id);
  System.out.println("Succesfully inserted");
   
		}
		catch(Exception e)
		{

		}
	}
		else
		{
			PrintWriter pw= response.getWriter();
response.setContentType("text/html");			
 pw.println("<html>");
 pw.println("<body>");

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("header.html");
		utility.printHtml("loginheader.html");
		utility.printHtml("halfheader.html");
		utility.printHtml("cards.html");
		pw.print("<br><br><br><br><br><br><br><div ><div class='post'><h2 class='title meta'>");
		
		pw.print("</h2><div class='entry'><table  id='bestseller'>");
	       pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			Events event= (Events)request.getAttribute("data");
			pw.print("<a class='card'>");
			pw.print("<div class='header'>");
      pw.print("<div class='header-image' style='background-image: url('https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Page_31_illustration_to_Three_hundred_Aesop%27s_fables_%28Townshend%29.png/724px-Page_31_illustration_to_Three_hundred_Aesop%27s_fables_%28Townshend%29.png')'>");
      pw.print("</div></div>");
    pw.print("<div class='card-content'>");
      pw.print("<div class='card-body-title'>");
        pw.print("<h2>Event Name :"+event.getName()+"</h2>");
      pw.print("</div>");
      pw.print("<div class='card-body'>");
        pw.print("<p>"+event.getEventDate());
        pw.print("<br/>");
			pw.print(event.getVenue());
			pw.print("<br/>");
			pw.print(event.getCity());
			pw.print("<br/>");
			pw.print(event.getState());
        pw.print("</p></div><div class='card-body-footer'></div></div></a></div>");

			
			pw.print("</div></td>");
			 pw.print("</tr>");
			
			
		pw.print("</table></div></div></div>");
   
		utility.printHtml("footer.html");
		}
	
		
	}
	
	public void destroy()	{
      // do nothing.
	}
	

}