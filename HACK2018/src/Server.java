import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
	private static int serverPort = 6432;
	private static List <Connection> all_connections= new ArrayList<Connection>();
	
	public static void main (String args[]) {
		try{
	        InetAddress addr = InetAddress.getLocalHost();
	        String hostname = addr.getHostName();
	        System.out.println("Server Name: " + hostname + "\nServer Port: " + serverPort);
			ServerSocket listenSocket = new ServerSocket(serverPort);
			System.out.println("Server is Ready");
			//listenSocket.setSoTimeout(1000);
			while(true) {
				System.out.println("listening to client sockets");
				try {
					Socket clientSocket = listenSocket.accept();
					System.out.println("-------created connect---------");
					System.out.println("connection found, creating a new connection thread");
					Connection c = new Connection(clientSocket);
					all_connections.add(c);
					c.all_connections = all_connections;
					System.out.println("----------------");
				} catch (SocketTimeoutException ex ) {
					System.out.println("Time out exception");
				}
			}
		} catch (IOException e) { 
			System.out.println("IOException Listen socket:"+e.getMessage());
		} //catch (UnknownHostException e) {
		//} 
	}
}
class Connection extends Thread {
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
	String name="temp";
	
	static int global_ID;
	int conn_ID;
	
	boolean verified;
	List <Connection> all_connections;
	
	final String directory = System.getProperty("user.dir") ;
	
	public Connection (Socket aClientSocket) {
    System.out.println("creating a new connection for client" );
		try {
			clientSocket = aClientSocket;
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			conn_ID=global_ID;
			global_ID++;
			this.start();
		} catch(IOException e) {
			System.out.println("Connection:"+e.getMessage());
		}
	}
	
	public void run(){
		System.out.println("server thread started");
		while(true) {
			try {
				ClientMessage clMessage = (ClientMessage) in.readObject();
				switch(clMessage.getType()) {
					case REGISTER: 
						String name = clMessage.getSource();
						String password = clMessage.getPassWord();
						//ArrayList<String>[] users_passwords = SQLManager.getUserNames();
						ArrayList<String> users_register = (SQLManager.getUsernames())[0];
						boolean taken=false;
						for(String user : users_register){
							if(name.equals(user)){
								ServerMessage sMessage = new ServerMessage(ServerMessage.ROUTE.AUTHENTICATION_RESPONSE);
								String [] message = {"Error","User Name: "+name+" is taken"};
								sMessage.setMessage(message);
								out.writeObject(sMessage);
								taken=true;
								break;
							}
						}
						if(!taken){
							ServerMessage sMessage = new ServerMessage(ServerMessage.ROUTE.AUTHENTICATION_RESPONSE);
							String [] message = {"Success","Registered"};
							sMessage.setMessage(message);
							out.writeObject(sMessage);
							this.name=name;
							SQLManager.addUser(name,password);
						}
						break;
						
					case LOGIN:
						String name_login = clMessage.getSource();
						password = clMessage.getPassWord();
						ArrayList<String>[] users_passwords = SQLManager.getUsernames();
						ArrayList<String> users_login = users_passwords[0];
						ArrayList<String> passwords = users_passwords[1];
						boolean found=false;
						for (int i=0; i<users_Login.size();i++){
							if(users_login.get(i).equals(name_login)){
								found = true;
								if(passwords.get(i).equals(password)){
									ServerMessage sMessage = new ServerMessage(ServerMessage.ROUTE.AUTHENTICATION_RESPONSE);
									String [] message = {"Success","Login successful"};
									sMessage.setMessage(message);
									out.writeObject(sMessage);
									verified=true;
									this.name=name_login;
									
									sMessage = new ServerMessage(ServerMessage.ROUTE.SAVED_LINKS);
									sMessage.setList(SQLManager.findSavedLinks(name));
									out.writeObject(sMessage);
									
									sMessage = new ServerMessage(ServerMessage.ROUTE.SHARED_LINKS);
									ArrayList<?>[] shared_links = SQLManager.findSharedLinks(name);
									ArrayList<URL> urlList = (ArrayList<URL>) shared_links[0];
									ArrayList<String> srcList = (ArrayList<String>) shared_links[1];
									
									
									//sort lists -- used insertion sort
									String temp_usr=null;
									URL temp_url=null;
									for (int i = 1; i <srcList.size(); i++) {
										temp_usr= srcList.get(i);
										temp_url= urlList.get(i):
										
										int j;
										for (j= i-1; j>=0 && (temp_usr.compareTo(srcList.get(j))<0); j--)  {
											//data[j+1]= data[j];
											srcList.set(j+1,srcList.get(j));
											urlList.set(j+1,urlList.get(j));
										}
										//data[j+1]= temp;
										srcList.set(j+1,temp_usr);
										urlList.set(j+1,temp_url);
									}
									
									
									
									
									int traverse = 0;
									String sender=srcList.get(0);
									ArrayList<URL> current =new ArrayList<URL>();
									while(traverse<srcList.size()){
										if(srcList.get(traverse).compareTo(sender)!=0){
											//write message
											sMessage = new ServerMessage(ServerMessage.ROUTE.SHARED_LINKS);
											sMessage.setList(current);
											sMessage.setSource(sender);
											out.writeObject(sMessage);
											
											
											//reassign values
											usr=srcList.get(traverse);
											current = new ArrayList<URL>;
										} else {
											current.add(urlList.get(traverse));
										}
										traverse++;
									}
									
									//sMessage.setList(SQLManager.findSharedLinks(name));
									//out.writeObject(sMessage);
								} else {
									ServerMessage sMessage = new ServerMessage(ServerMessage.ROUTE.AUTHENTICATION_RESPONSE);
									String [] message = {"Error","Incorrect password"};
									sMessage.setMessage(message);
									out.writeObject(sMessage);
								}
								break;
							}
						}
						
						if(!found){
							ServerMessage sMessage = new ServerMessage(ServerMessage.ROUTE.AUTHENTICATION_RESPONSE);
							String [] message = {"Error","user not found"};
							sMessage.setMessage(message);
							out.writeObject(sMessage);
						}
						break;
						
					case SEARCH: 
						if(verified){
							try{
								//Process p = Runtime.getRuntime().exec("python "+directory+"\\WebScraper\\test.py "+cm.getChoice());
								Process p = Runtime.getRuntime().exec("python scrape.py "+clMessage.getChoice());
								BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
								ArrayList<URL> URLS = new ArrayList<URL>();
								String line=null;
								while ((line = stdInput.readLine()) != null) {
									System.out.println(line);
									URLS.add(new URL(line));
								}
								ServerMessage sm = new ServerMessage(ServerMessage.ROUTE.SEARCH_RESULTS);
								sm.setList(URLS);
								out.writeObject(sm);
							} catch (IOException ioxptn){
								ioxptn.printStackTrace();
							}
						}
						break;
						
					case SHARE:
						if(verified){
							String destination = clMessage.getDestination();
							
							ArrayList<String> users_share = (SQLManager.getUsernames())[0];
							if(!users_share.contains(destination)){
								ServerMessage sm = new ServerMessage(ServerMessage.ROUTE.SHARE_RESULTS);
								String [] message = {"Error","user not found"};
								sMessage.setMessage(message);
								out.writeObject(sm);
								break;
							}
							if(destination.equals(this.name)){
								ServerMessage sm = new ServerMessage(ServerMessage.ROUTE.SHARE_RESULTS);
								String [] message = {"Error","cannot share to self"};
								sMessage.setMessage(message);
								out.writeObject(sm);
								break;
							}
							
							ArrayList<URL> urls = clMessage.getList();
							
							for (URL url : urls){ // !!to be completed!!
								SQLManager.shareLink(this.name,destination,url);
							}
							
						}
						break; 
						
					case SAVE:
						if(verified){
							
						}
						break;
						
					case TERMINATE:
						clientSocket.close();
						for (int i=0; i< all_connections.size(); i++) {
							if (this == (Connection) all_connections.get(i)) {
								System.out.println("Removing connection from the list, for " + this.name +"ID:"+conn_ID);
								System.out.println("# of connections after removing " + (all_connections.size()-1));
								all_connections.remove(i);
							}
						} 
						break;
				}
			} catch (EOFException e){
				System.out.println("File Ended:"+e.getMessage());
			} catch(ClassNotFoundException e) {
				System.out.println("ClsNtFnd message:"+e.getMessage());
			} catch(IOException e) {
				System.out.println("IO message:"+e.getMessage());
				try {
					clientSocket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				for (int i=0; i< all_connections.size(); i++) {
					if (this == (Connection) all_connections.get(i)) {
						System.out.println("Removing connection from the list, for " + name);
						System.out.println("num connection upon removing " + all_connections.size());
						all_connections.remove(i);
					}
				} 
				break;
			}
		} 
	}
}