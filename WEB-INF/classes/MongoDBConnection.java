import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;
                	
public class MongoDBConnection
{
public static DBCollection users;
public static DBCollection getConnection()
{
MongoClient mongo;
mongo = new MongoClient("localhost", 27017);

DB db = mongo.getDB("recommendproject");
 users= db.getCollection("users");	
return users; 
}


public static String setFavoriteItems(String username,String eventname,String id)
{
	try
		{		
			getConnection();
			BasicDBObject doc = new BasicDBObject("title", "users").
				append("name", username).
			append("event", eventname).
			append("id",id);
			users.insert(doc);
			System.out.println("Successfull");
			return "Successfull";
		}
		catch(Exception e)
		{
		return "UnSuccessfull";
		}	
		
}
}
