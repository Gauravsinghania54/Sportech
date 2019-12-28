/* This code is used to implement carousel feature in Website. Carousels are used to implement slide show feature. This  code is used to display 
all the accessories related to a particular product. This java code is getting called from cart.java. The product which is added to cart, all the 
accessories realated to product will get displayed in the carousel.*/
  

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;	

			
			
@WebServlet("/RecommendHistory")

public class RecommendHistory extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
				
		EventRecommenderUtility prodRecUtility = new EventRecommenderUtility();
		
		
		
	
			
		
			
		
		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
		prodRecmMap = prodRecUtility.readOutputFile();
		
		
		
		
		for(String user: prodRecmMap.keySet())
		{
			if(user.equals(utility.username()))
			{
				String products = prodRecmMap.get(user);
				products=products.replace("[","");
				products=products.replace("]","");
				products=products.replace("\"", " ");
				ArrayList<String> productsList = new ArrayList<String>(Arrays.asList(products.split(",")));
                utility.printHtml("header.html");
		utility.printHtml("loginheader.html");
		utility.printHtml("recommend.html");
		utility.printHtml("halfheader.html"); 
		utility.printHtml("cards.html");
		pw.print("<br><br><br><br><br><br><br><div ><div class='post'><h2 class='title meta'>Recommend Event");
		
		pw.print("</h2><div class='entry'><table  id='bestseller'>");
	       pw.print("<tr>");
		      
					
				

				
						
				
				for(String prod : productsList){
					prod= prod.replace("'", "");
					Events prodObj = new Events();
					prodObj = EventRecommenderUtility.getProduct(prod.trim());
					pw.print("<div id='shop_item'>");
					pw.print("<a class='card'>");
			pw.print("<div class='header'>");
      pw.print("<div class='header-image' style='background-image: url('https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Page_31_illustration_to_Three_hundred_Aesop%27s_fables_%28Townshend%29.png/724px-Page_31_illustration_to_Three_hundred_Aesop%27s_fables_%28Townshend%29.png')'>");
      pw.print("</div></div>");
    pw.print("<div class='card-content'>");
      pw.print("<div class='card-body-title'>");
        pw.print("<h2>Event Name :"+prodObj.getName()+"</h2>");
      pw.print("</div>");
      pw.print("<div class='card-body'>");
        pw.print("<p>"+prodObj.getEventDate());
        pw.print("<br/>");
			pw.print(prodObj.getVenue());
			pw.print("<br/>");
			pw.print(prodObj.getCity());
			pw.print("<br/>");
			pw.print(prodObj.getState());
        pw.print("</p></div><div class='card-body-footer'></div></div></a></div>");

					pw.print("</div></div>");
					pw.print("</div>");
				
					
					
				}
						
				
			
				}
			}
			
		}
	}
	