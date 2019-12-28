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
import java.lang.*; 
import java.io.*; 
import java.util.*; 
import java.sql.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.JSONException;
import javax.servlet.annotation.WebServlet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;


@WebServlet("/BaseballList")

public class BaseballList extends HttpServlet {


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		HashMap<String,Baseball> allbaseballs = new HashMap<String,Baseball> ();
		String command =
  "curl,-X,GET,--header,Authorization: Bearer UGE6FGLedRgWsGhyRSlj21goTGxD,--header,Accept: application/json,https://api.stubhub.com/sellers/search/events/v3?q=Baseball&rows=500,";
ProcessBuilder processBuilder = new ProcessBuilder(command.split(","));


processBuilder.directory(new File("C:\\Illinois tech\\EWA\\Project"));
Process process = processBuilder.start();
try
{
InputStream inputStream = process.getInputStream();
         
          
        // checking the command in list 
        String result = data.convertInputStreamToString(inputStream);
            
            PrintWriter out = new PrintWriter(new FileWriter("C:\\Illinois tech\\EWA\\apache-tomcat-7.0.34\\webapps\\Sportech\\WEB-INF\\classes\\Baseball.json"));
            out.write(result);
}
catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } 
       catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
		String Basketball="Basketball";
      String basket="Basket";
      data.doPost(Basketball,basket);
		try{
		     allbaseballs = MySqlDataStoreUtilities.getBaseballs();
		}
		catch(Exception e)
		{
			
		}


		HashMap<String, Baseball> hm = new HashMap<String, Baseball>();
		
			hm.putAll(allbaseballs);
			name = "";
		

       
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("header.html");
		utility.printHtml("loginheader.html");
		HttpSession session=request.getSession(true);
		if (session.getAttribute("username")!=null){
		utility.printHtml("recommend.html");
		}

		utility.printHtml("halfheader.html");
		utility.printHtml("cards.html");
		pw.print("<br><br><br><br><br><br><br><div ><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Baseball</a>");
		pw.print("</h2><div class='entry'><table  id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Baseball> entry : hm.entrySet())
		{
			Baseball baseball = entry.getValue();
			if(i%2==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<a class='card'>");
			pw.print("<div class='header'>");
      pw.print("<div class='header-image' style='background-image: url('https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Page_31_illustration_to_Three_hundred_Aesop%27s_fables_%28Townshend%29.png/724px-Page_31_illustration_to_Three_hundred_Aesop%27s_fables_%28Townshend%29.png')'>");
      pw.print("</div></div>");
    pw.print("<div class='card-content'>");
      pw.print("<div class='card-body-title'>");
        pw.print("<h2>"+baseball.getName()+"</h2>");
      pw.print("</div>");
      pw.print("<div class='card-body'>");
        pw.print("<p>"+baseball.getEventDate());
        pw.print("<br/>");
			pw.print(baseball.getVenue());
			pw.print("<br/>");
			pw.print(baseball.getCity());
			pw.print("<br/>");
			pw.print(baseball.getState());
        pw.print("</p></div><div class='card-body-footer'></div></div></a></div>");

			
			pw.print("</div></td>");
			if(i%2==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("footer.html");
		
		
}
}