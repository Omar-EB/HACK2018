import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class ServerMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public enum ROUTE {LOGIN_RESPONSE, SEARCH_RESULTS,SHARE_RESPONSE,SAVE_RESULTS};
	private ROUTE route = null;
	private String source=null;
	private ArrayList<URL> list=null;
	
	public ServerMessage(ROUTE route) {
		this.route = route;
	}
	public ROUTE getType() {
		return route;
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