import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

@WebServlet("/Preferences")

public class Preferences extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HttpSession session=request.getSession(true);//false
		String username =session.getAttribute("username").toString(); 
		System.out.println(username); 
		
		String allprefevents = MySqlDataStoreUtilities.getprefEvents(username);

		System.out.println(allprefevents);
		HashMap<String,Events> allmyevents = new HashMap<String,Events> ();
		allmyevents = MySqlDataStoreUtilities.getmyEvents(allprefevents);
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("header.html");
		utility.printHtml("loginheader.html");
		utility.printHtml("recommend.html");
		utility.printHtml("halfheader.html");
		utility.printHtml("cards.html");
		pw.print("<br><br><br><br><br><br><br><div ><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Recommendations Based on Preferences</a>");
		pw.print("</h2><div class='entry'><table  id='bestseller'>");
		int i = 1; int size= allmyevents.size();
		for(Map.Entry<String, Events> entry : allmyevents.entrySet())
		{
			Events events = entry.getValue();
			if(i%2==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<a class='card'>");
			pw.print("<div class='header'>");
      pw.print("<div class='header-image' style='background-image: url('https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Page_31_illustration_to_Three_hundred_Aesop%27s_fables_%28Townshend%29.png/724px-Page_31_illustration_to_Three_hundred_Aesop%27s_fables_%28Townshend%29.png')'>");
      pw.print("</div></div>");
    pw.print("<div class='card-content'>");
      pw.print("<div class='card-body-title'>");
        pw.print("<h2>"+events.getName()+"</h2>");
      pw.print("</div>");
      pw.print("<div class='card-body'>");
        pw.print("<p>"+events.getEventDate());
        pw.print("<br/>");
			pw.print(events.getVenue());
			pw.print("<br/>");
			pw.print(events.getCity());
			pw.print("<br/>");
			pw.print(events.getState());
        pw.print("</p></div><div class='card-body-footer'></div></div></a></div>");

			
			pw.print("</div></td>");
			if(i%2==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("footer.html");
		
		
}
}		
		
	