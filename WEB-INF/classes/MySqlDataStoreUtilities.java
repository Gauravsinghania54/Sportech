import java.sql.*;
import java.util.*;
                	
public class MySqlDataStoreUtilities
{
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

public static HashMap<String,Basketball> getBasketballs()
{	
	HashMap<String,Basketball> hm=new HashMap<String,Basketball>();
	try 
	{
		getConnection();
		
		String selectBasketball="select * from  events where CategoryName=?";
		PreparedStatement pst = conn.prepareStatement(selectBasketball);
		pst.setString(1,"Basketball");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Basketball tab = new Basketball(rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("eventDateLocal"),rs.getString("venueName"),rs.getString("VenueCity"),rs.getString("VenueState"),rs.getString("VenuePostalCode"),rs.getString("VenueCountry"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Baseball> getBaseballs()
{	
	HashMap<String,Baseball> hm=new HashMap<String,Baseball>();
	try 
	{
		getConnection();
		
		String selectBaseball="select * from  events where CategoryName=?";
		PreparedStatement pst = conn.prepareStatement(selectBaseball);
		pst.setString(1,"Baseball");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Baseball tab = new Baseball(rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("eventDateLocal"),rs.getString("venueName"),rs.getString("VenueCity"),rs.getString("VenueState"),rs.getString("VenuePostalCode"),rs.getString("VenueCountry"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Hockey> getHockeys()
{	
	HashMap<String,Hockey> hm=new HashMap<String,Hockey>();
	try 
	{
		getConnection();
		
		String selectHockey="select * from  events where CategoryName=?";
		PreparedStatement pst = conn.prepareStatement(selectHockey);
		pst.setString(1,"Hockey");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Hockey tab = new Hockey(rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("eventDateLocal"),rs.getString("venueName"),rs.getString("VenueCity"),rs.getString("VenueState"),rs.getString("VenuePostalCode"),rs.getString("VenueCountry"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Handball> getHandballs()
{	
	HashMap<String,Handball> hm=new HashMap<String,Handball>();
	try 
	{
		getConnection();
		
		String selectHandball="select * from  events where CategoryName=?";
		PreparedStatement pst = conn.prepareStatement(selectHandball);
		pst.setString(1,"Handball");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Handball tab = new Handball(rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("eventDateLocal"),rs.getString("venueName"),rs.getString("VenueCity"),rs.getString("VenueState"),rs.getString("VenuePostalCode"),rs.getString("VenueCountry"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Soccer> getSoccers()
{	
	HashMap<String,Soccer> hm=new HashMap<String,Soccer>();
	try 
	{
		getConnection();
		
		String selectSoccer="select * from  events where CategoryName=?";
		PreparedStatement pst = conn.prepareStatement(selectSoccer);
		pst.setString(1,"Soccer");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Soccer tab = new Soccer(rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("eventDateLocal"),rs.getString("venueName"),rs.getString("VenueCity"),rs.getString("VenueState"),rs.getString("VenuePostalCode"),rs.getString("VenueCountry"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Tennis> getTenniss()
{	
	HashMap<String,Tennis> hm=new HashMap<String,Tennis>();
	try 
	{
		getConnection();
		
		String selectTennis="select * from  events where CategoryName=?";
		PreparedStatement pst = conn.prepareStatement(selectTennis);
		pst.setString(1,"Tennis");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Tennis tab = new Tennis(rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("eventDateLocal"),rs.getString("venueName"),rs.getString("VenueCity"),rs.getString("VenueState"),rs.getString("VenuePostalCode"),rs.getString("VenueCountry"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

	public static void setFavoriteItems(String userName, String eventId) {
		
		try {
			getConnection();
		if (conn == null) {
			return;
		}
		String query = "INSERT INTO history values(?, ?)";
		
			PreparedStatement statement = conn.prepareStatement(query);
			
				statement.setString(1, userName);
				statement.setString(2, eventId);
				statement.execute();
			
		} catch (Exception e) {
			
		}

	}
	public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  productdetails";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	Product p = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getString("ProductType"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), p);
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return hm;			
	}
	public static HashMap<String,Events> getCityEvents(String city)
{	
	HashMap<String,Events> hm=new HashMap<String,Events>();
	try 
	{
		getConnection();
		
		String selectcity="select * from  events where VenueCity=?";
		PreparedStatement pst = conn.prepareStatement(selectcity);
		pst.setString(1,city);
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Events tab = new Events(rs.getString("id"),rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("eventDateLocal"),rs.getString("venueName"),rs.getString("VenueCity"),rs.getString("VenueState"),rs.getString("VenuePostalCode"),rs.getString("VenueCountry"),rs.getString("CategoryName"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}
public static String getprefEvents(String pref)
{	
	String p=null;
	System.out.println(pref);
	try 
	{
		getConnection();
		
		String setpreference="select preferences from register where username=?";
		PreparedStatement pst = conn.prepareStatement(setpreference);
		pst.setString(1,pref);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{
		 p=rs.getString("preferences");
		}
	

	}
	catch(Exception e)
	{
	}
	System.out.println(p);
	return p;			
}
public static HashMap<String,Events> getmyEvents(String my)
{	
	HashMap<String,Events> hm=new HashMap<String,Events>();
	try 
	{
		getConnection();
		
		String selectcity="select * from  events where CategoryName=?";
		PreparedStatement pst = conn.prepareStatement(selectcity);
		pst.setString(1,my);
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Events tab = new Events(rs.getString("id"),rs.getString("status"),rs.getString("locale"),rs.getString("name"),rs.getString("eventDateLocal"),rs.getString("venueName"),rs.getString("VenueCity"),rs.getString("VenueState"),rs.getString("VenuePostalCode"),rs.getString("VenueCountry"),rs.getString("CategoryName"));
				hm.put(rs.getString("id"), tab);
				tab.setId(rs.getString("id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}




}