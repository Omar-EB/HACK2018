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
	
	
	@SuppressWarnings("unchecked")
	public void run(){
		System.out.println("server thread started");
		while(true) {
			try {
				ClientMessage clMessage = (ClientMessage) in.readObject();
				switch(clMessage.getType()) {
					case REGISTER:{ 
						String name = clMessage.getSource();
						String password = clMessage.getPassWord();
						ArrayList<String> users = (SQLManager.getUsernames())[0];
						boolean taken=false;
						for(String user : users){
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
					}
						
					case LOGIN:{
						String name = clMessage.getSource();
						String password = clMessage.getPassWord();
						ArrayList<String>[] users_passwords = SQLManager.getUsernames();
						ArrayList<String> users = users_passwords[0];
						ArrayList<String> passwords = users_passwords[1];
						boolean found=false;
						for (int i=0; i<users.size();i++){
							if(users.get(i).equals(name)){
								found = true;
								if(passwords.get(i).equals(password)){
									ServerMessage sMessage = new ServerMessage(ServerMessage.ROUTE.AUTHENTICATION_RESPONSE);
									String [] message = {"Success","Login successful"};
									sMessage.setMessage(message);
									out.writeObject(sMessage);
									verified=true;
									this.name=name;
									
									sMessage = new ServerMessage(ServerMessage.ROUTE.SAVED_LINKS);
									sMessage.setList(SQLManager.findSavedLinks(name));
									out.writeObject(sMessage);
									
									sMessage = new ServerMessage(ServerMessage.ROUTE.SHARED_LINKS);
									ArrayList<?>[] shared_links = SQLManager.findSharedLinks(name);
									sMessage.setList((ArrayList<URL>) shared_links[0]);
									sMessage.setUser((ArrayList<String>) shared_links[1]);
									out.writeObject(sMessage);
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
					}
						
					case SEARCH:{ 
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
								ServerMessage sMessage = new ServerMessage(ServerMessage.ROUTE.SEARCH_RESULTS);
								sMessage.setList(URLS);
								out.writeObject(sMessage);
							} catch (IOException ioxptn){
								ioxptn.printStackTrace();
							}
						}
						break;
					}
						
					case SHARE:{
						if(verified){
							for (String destination :  clMessage.getDestinationList() ) {
								ServerMessage sMessage = new ServerMessage(ServerMessage.ROUTE.SHARED_LINKS);
								for(Connection connect : all_connections){
									if (connect.name.equals(destination)){ // !!to be completed!!
										
									}
								}
								out.writeObject(sMessage);
							}
							
							/*
							if(!users.contains(destination)){
								ServerMessage sMessage = new ServerMessage(ServerMessage.ROUTE.SHARE_RESULTS);
								String [] message = {"Error","user not found"};
								sMessage.setMessage(message);
								out.writeObject(sMessage);
								break;
							} 
							if(destination.equals(this.name)){
								ServerMessage sMessage = new ServerMessage(ServerMessage.ROUTE.SHARE_RESULTS);
								String [] message = {"Error","cannot share to self"};
								sMessage.setMessage(message);
								out.writeObject(sMessage);
								break;
							} */
							
						}
						break; 
					}
						
					case SAVE:{
						if(verified){
							
						}
						break;
					}
						
					case TERMINATE:{
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