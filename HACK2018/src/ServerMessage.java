import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class ServerMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public enum ROUTE {AUTHENTICATION_RESPONSE, SEARCH_RESULTS,SHARE_RESPONSE,SHARED_LINKS,SAVED_LINKS,SHARE_RESULT,SAVE_RESULTS};
	private ROUTE route = null;
	private String source=null;
	private ArrayList<URL> list=null;
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
	public String getMessage(){
		return message;
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
}