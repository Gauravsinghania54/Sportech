import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;



public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
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
			 message="unsuccessful";
		     return message;
		}
	}
	
	public  StringBuffer readdata(String searchId)
	{	
		HashMap<String,Events> data;
		data=getData();
		
 	    Iterator it = data.entrySet().iterator();	
        while (it.hasNext()) 
	    {
                    Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
				Events p=(Events)pi.getValue();                   
                if (p.getName().toLowerCase().startsWith(searchId))
                {
                        sb.append("<Event>");
                        sb.append("<Id>" + p.getId() + "</Id>");
                        sb.append("<name>" + p.getName() + "</name>");
                        sb.append("</Event>");
                }
			}
       }
       
	   
	   return sb;
	}
	
	public static HashMap<String,Events> getData()
	{
		HashMap<String,Events> hm=new HashMap<String,Events>();
		try
		{
			getConnection();
			
		    String selectevent="select * from  events";
		    PreparedStatement pst = conn.prepareStatement(selectevent);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	Events p = new Events(rs.getString("Id"),rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("eventDateLocal"),rs.getString("VenueName"),rs.getString("VenueCity"),rs.getString("VenueState"),rs.getString("VenuePostalCode"),rs.getString("VenueCountry"),rs.getString("CategoryName"));
				hm.put(rs.getString("Id"), p);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;			
	}
	public static void storeData(HashMap<String,Events> eventsData)
	{
		try
		{
		
			getConnection();
				
			String insertIntoProductQuery = "INSERT INTO events(Id,status,locale,name,eventDateLocal,VenueName,VenueCity,VenueState,VenuePostalCode,VenueCountry,CategoryName) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);";	
			for(Map.Entry<String, Events> entry : eventsData.entrySet())
			{	

				PreparedStatement pstmt = conn.prepareStatement(insertIntoProductQuery);
				//set the parameter for each column and execute the prepared statement
				pstmt.setString(1,entry.getValue().getId());
            pstmt.setString(2,entry.getValue().getStatus());
            pstmt.setString(3,entry.getValue().getLocale());
            pstmt.setString(4,entry.getValue().getName());
            pstmt.setString(5,entry.getValue().getEventDate());
            pstmt.setString(6,entry.getValue().getVenue());
            pstmt.setString(7,entry.getValue().getCity());
            pstmt.setString(8,entry.getValue().getState());
            pstmt.setString(9,entry.getValue().getPostal());
            pstmt.setString(10,entry.getValue().getCountry());
            pstmt.setString(11,entry.getValue().getType());
				/*pst.setLong(1,entry.getValue().getId());
				pst.setString(2,entry.getValue().getName());
				pst.setDouble(3,entry.getValue().getPrice());
				pst.setString(4,entry.getValue().getImage());
				pst.setString(5,entry.getValue().getRetailer());
				pst.setString(6,entry.getValue().getCondition());
				pst.setString(7,entry.getValue().getType());
				pst.setDouble(8,entry.getValue().getDiscount());*/
				pstmt.execute();
			}
		}
		catch(Exception e)
		{	
	
		}		
	}

}
