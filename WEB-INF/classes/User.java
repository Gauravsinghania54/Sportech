import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;



/* 
	Users class contains class variables id,username,password,usertype.

	Users class has a constructor with Arguments username, String password, String usertype.
	  
	Users  class contains getters and setters for id,username,password,usertype.

*/

public class User implements Serializable{
	private String username;
	private String email;
	private String password;
	private String[] preferences;
	
	public User(String username, String password, String email,String[] preferences) {
		this.username=username;
		this.password=password;
		this.email=email;
		for(int i=0;i<preferences.length;i++)
	{
			this.preferences[i]=preferences[i];
	}
	}


	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void setPreferences(String[] preferences)
	{
		for(int i=0;i<preferences.length;i++)
	{
			this.preferences[i]=preferences[i];
	}
		
	}
	public String[] getPreferences(String[] preferences)// it should return the courseIDEnrolled array
	{
		for(int i=0;i<preferences.length;i++)
	{
			System.out.println(this.preferences[i]);
			
	}
	return preferences;
	
}
}
