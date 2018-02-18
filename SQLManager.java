import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLManager{
	public static void createDB(){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Justin/Desktop/uOttaHack.db");

			Statement statement = connection.createStatement();
			statement.executeUpdate("create table utable (name VARCHAR(20) PRIMARY KEY);");
			statement.executeUpdate("create table links (url VARCHAR(2000) NOT NULL, uid VARCHAR(20), FOREIGN KEY(uid) REFERENCES uTable(name));");
			connection.close();
		} catch (SQLException e){}
	}

	public static void addUser(String name){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Justin/Desktop/uOttaHack.db");
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO utable(name) VALUES ('" + name + "');");
			connection.close();
		} catch (SQLException e){}
	}

	public static void addLink(String name, String URL){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Justin/Desktop/uOttaHack.db");
			
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO links(url, uid) VALUES ('" + url + "', '" + name + "');");

			connection.close();
		} catch (SQLException e) {}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String> findSavedLinks(String name){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/alan1/Desktop/uOttaHack.db");
			
			Statement statement2 = connection.createStatement();
			ResultSet result = statement2.executeQuery("SELECT * FROM links WHERE uid = '"+name+"';");
			System.out.println(name);
			

			ArrayList<String> lst = new ArrayList<String>();
			
			while(result.next()){
				lst.add( result.getString(1));
			}
			connection.close();

			return lst;
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("CHILL WITH THAT");
			return new ArrayList();
		}
	}

	public static void deleteSavedLink(String name, String URL){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Justin/Desktop/uOttaHack.db");

			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM links WHERE (uid='" + name + "' AND url='" + url + "');");
			
			connection.close();
		} catch (SQLException e){}
	}
}