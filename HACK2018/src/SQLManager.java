import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.net.URL;
import java.sql.SQLException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class SQLManager{
	
	public static void createDB(){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data_base_folder\\uOttaHack.db");
			Statement statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE utable (name VARCHAR(20) PRIMARY KEY, password VARCHAR(8) NOT NULL);");
			statement.executeUpdate("CREATE TABLE links (url VARCHAR(2000) NOT NULL, uid VARCHAR(20), FOREIGN KEY(uid) REFERENCES uTable(name));");
			statement.executeUpdate("CREATE TABLE shared (source VARCHAR(20) REFERENCES uTable(name) NOT NULL, destination VARCHAR(20) REFERENCES uTable(name) NOT NULL, url VARCHAR(2000) NOT NULL);");
			connection.close();
		} catch (SQLException e){}
	}

	public static void addUser(String name, String password){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data_base_folder\\uOttaHack.db");
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO utable(name,password) VALUES ('" + name +"','"+ password +"');");
			connection.close();
		} catch (SQLException e){}
	}
	
	public static ArrayList<String> getUsernames(){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data_base_folder\\uOttaHack.db");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT name FROM utable;");
			ArrayList<String> lst = new ArrayList<String>();
			while(rs.next()){
				lst.add(rs.getString("name"));
			}
			connection.close();
			return lst;
		} catch (SQLException e){
			System.out.println("sql went crazy >.>");
			return null;
		}
	}

	public static void addLink(String name, String URL){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data_base_folder\\uOttaHack.db");
			
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO links(url, uid) VALUES ('" + URL + "', '" + name + "');");

			connection.close();
		} catch (SQLException e) {}
	}
	
	public static void shareLink(String source,String destination,String URL){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data_base_folder\\uOttaHack.db");

			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO shared(source,destination,url) VALUES ('"+source+"','"+destination+"','"+URL+"');");
			
			connection.close();
		} catch (SQLException e){}
	}
	public static void sharedLink(String destination,String URL,boolean choice){
		if(choice){
			addLink(destination, URL);
		}
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data_base_folder\\uOttaHack.db");

			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM shared WHERE (destination='" + destination + "' AND url='" + URL + "');");
			
			connection.close();
		} catch (SQLException e){}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String> findSavedLinks(String name){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data_base_folder\\uOttaHack.db");
			
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery("SELECT * FROM links WHERE uid = '"+name+"';");
			System.out.println(name);
			

			ArrayList<String> lst = new ArrayList<String>();
			
			while(result.next()){
				lst.add(result.getString(1));
			}
			connection.close();
			return lst;
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("CHILL WITH THAT");
			return new ArrayList();
		} //catch (MalformedURLException e){
			//e.printStackTrace();
			//System.out.println("wow there");
		//}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> [] findSharedLinks(String destination){
		//returned array : url's at 0 , sources at 1
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data_base_folder\\uOttaHack.db");
			
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery("SELECT * FROM shared WHERE destination = '"+destination+"';");
			System.out.println(destination);
			

			ArrayList<String> urlLst = new ArrayList<String>();
			ArrayList<String> srcLst = new ArrayList<String>();
			ArrayList<String>[] ret= (ArrayList<String>[]) new Object[2];
			while(result.next()){
				urlLst.add(result.getString("url"));
				srcLst.add(result.getString("source"));
			}
			connection.close();
			ret[0]=urlLst;
			ret[1]=srcLst;
			return ret;
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("CHILL WITH THAT");
			return null;
		} //catch (MalformedURLException e){
			//e.printStackTrace();
			//System.out.println("wow there");
		//}
	}

	public static void deleteSavedLink(String name, String URL){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data_base_folder\\uOttaHack.db");

			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM links WHERE (uid='" + name + "' AND url='" + URL + "');");
			
			connection.close();
		} catch (SQLException e){}
	}
}
