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
	boolean registered;
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
	String name;
	List <Connection> all_connections;
	
	public Connection (Socket aClientSocket) {
    System.out.println("creating a new connection for client" );
		try {
			clientSocket = aClientSocket;
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
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
						name = clMessage.getSource();
						String password = clMessage.getPassWord();
						break;
						
					case LOGIN:
						break;
						
					case SEARCH: 
						System.out.println("Search"); // write to the output stream
						break;
						
					case SHARE: 
						for (Connection c : all_connections ) {
							c.out.writeObject(new ServerMessage(ServerMessage.ROUTE.SHARE_RESPONSE));
							System.out.println("got back");
						};
						break; 
						
					case SAVE:
						System.out.println("save"); // write to the output stream
						break;
						
					case TERMINATE:
						clientSocket.close();
						for (int i=0; i< all_connections.size(); i++) {
							if (this == (Connection) all_connections.get(i)) {
								System.out.println("Removing connection from the list, for " + name);
								System.out.println("num connection upon removing " + all_connections.size());
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