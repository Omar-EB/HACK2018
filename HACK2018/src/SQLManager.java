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
			statement.executeUpdate("create table utable (name VARCHAR(20) PRIMARY KEY);");
			statement.executeUpdate("create table links (url VARCHAR(2000) NOT NULL, uid VARCHAR(20), FOREIGN KEY(uid) REFERENCES uTable(name));");
			connection.close();
		} catch (SQLException e){}
	}

	public static void addUser(String name){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data_base_folder\\uOttaHack.db");
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO utable(name) VALUES ('" + name + "');");
			connection.close();
		} catch (SQLException e){}
	}

	public static void addLink(String name, String URL){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data_base_folder\\uOttaHack.db");
			
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO links(url, uid) VALUES ('" + URL + "', '" + name + "');");

			connection.close();
		} catch (SQLException e) {}
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

	public static void deleteSavedLink(String name, String URL){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:data_base_folder\\uOttaHack.db");

			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM links WHERE (uid='" + name + "' AND url='" + URL + "');");
			
			connection.close();
		} catch (SQLException e){}
	}
}
