import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class ClientMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public enum ROUTE {REGISTER,LOGIN,SEARCH,SHARE,SAVE,TERMINATE};
	private ROUTE route = null;
	
	private String destination=null;
	private String source=null;
	private String password=null;
	private ArrayList<URL> list=null;
	private String choice;
	
	public ClientMessage(ClientMessage.ROUTE route) {
		this.route=route;
	}
	public ROUTE getType() {
		return route;
	}
	
	public void setdestination(String destination) {
		this.destination=destination;
	}
	public String getdestination() {
		return destination;
	}
	
	public void setPassWord(String password) {
		this.password=password;
	}
	public String getPassWord() {
		return password;
	}
	
	public void setSource(String source) {
		this.source=source;
	}
	public String getSource() {
		return source;
	}
	
	public void setList(ArrayList<URL> list) {
		this.list=list;
	}
	public ArrayList<URL> getList() {
		return list;
	}
	
	public void setChoice(String choice) {
		this.choice=choice;
	}
	public String getChoice() {
		return choice;
	}
	
}
