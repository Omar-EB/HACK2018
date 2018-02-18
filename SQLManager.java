import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLManager{
	public static void createDB() throws SQLException{
		//try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Justin/Desktop/uOttaHack.db");
			Statement statement = connection.createStatement();
			statement.executeUpdate("create table utable (name VARCHAR(20) PRIMARY KEY);");
			statement.executeUpdate("create table links (URL VARCHAR(2000) NOT NULL, UID VARCHAR(20) REFERENCES uTable(name) NOT NULL);");
			connection.close();
		//} catch (SQLException e){}
	}

	public static void addUser(String name)throws SQLException{
		//try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Justin/Desktop/uOttaHack.db");
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO utable(name) VALUES ('" + name + "');");
			connection.close();
		//} catch (SQLException e){}
	}

	public static void addLink(String name, String URL)throws SQLException{
		//try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Justin/Desktop/uOttaHack.db");
			
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO links(URL, UID) VALUES ('" + URL + "', '" + name + "');");

			connection.close();
		//} catch (SQLException e) {}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String> findSavedLinks(String name){
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Justin/Desktop/uOttaHack.db");
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT URL FROM links WHERE (UID='" + name + "');");
			System.out.println(name);
			connection.close();

			ArrayList<String> lst = new ArrayList<String>();

			result.first();
			while(result.next()){
				lst.add(result.getString(0));
			}

			return lst;
		} catch (SQLException e){
			return new ArrayList();
		}
	}

	public static void deleteSavedLink(String name, String URL)throws SQLException{
		//try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Justin/Desktop/uOttaHack.db");

			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM links WHERE (UID='" + name + "' AND URL='" + URL + "');");
			
			connection.close();
		//} catch (SQLException e){}
	}
}