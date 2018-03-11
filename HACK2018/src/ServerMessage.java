import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class ServerMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public enum ROUTE {AUTHENTICATION_RESPONSE, SEARCH_RESULTS,SHARE_RESPONSE,SHARED_LINKS,SAVED_LINKS,SAVE_RESULTS};
	private ROUTE route = null;
	private ArrayList<String> user_list=null;
	private ArrayList<URL> url_list=null;
	String[] message=null;
	
	public ServerMessage(ROUTE route) {
		this.route = route;
	}
	public ROUTE getType() {
		return route;
	}
	
	public void setMessage(String[] message){
		this.message=message;
	}
	public String[] getMessage(){
		return message;
	}
	
	public void setUser(String user) {
		(this.user_list=new ArrayList<String>()).set(0,user);
	}
	
	public void setUser(ArrayList<String> users) {
		this.user_list=users;
	}
	
	public String getUser() {
		return user_list.get(0);
	}
	
	public ArrayList<String> getUserList() {
		return user_list;
	}
	
	public void setList(ArrayList<URL> list) {
		this.url_list=list;
	}
	public ArrayList<URL> getList() {
		return url_list;
	}
}