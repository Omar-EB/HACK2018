import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
	private static int serverPort = 6432;
	private static List <Connection> all_connections= new ArrayList<Connection>();
	
	public static void main (String args[]) {
	   	try {
	        InetAddress addr = InetAddress.getLocalHost();
	        String hostname = addr.getHostName();
	        System.out.println("Server Name: " + hostname + "\nServer Port: " + serverPort);
	    } catch (UnknownHostException e) {
	    }

		try{
			ServerSocket listenSocket = new ServerSocket(serverPort);
			System.out.println("Server is Ready");
			 listenSocket.setSoTimeout(1000);
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
		} catch(IOException e) { System.out.println("IOException Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {
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
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	
	public void run(){
		System.out.println("server thread started");
		while(true) {
			try {
					ClientMessage mg = (ClientMessage) in.readObject();
					switch(mg.getType()) {
						case REGISTER: System.out.println("Reg"); // write to the output stream
						break;
						case SEARCH: System.out.println("Search"); // write to the output stream
							break;
						case SHARE: 
							for (Connection c : all_connections ) {
								c.out.writeObject(new ServerMessage(ServerMessage.ROUTE.SHARE_RESPONSE));
								System.out.println("got back");
							};
							break; 
						case SAVE: System.out.println("save"); // write to the output stream
							break;	
					}
					
				} catch (EOFException e){System.out.println("EOF message:"+e.getMessage());
				} catch(ClassNotFoundException e) {System.out.println("ClsNtFnd message:"+e.getMessage());
				} catch(IOException e) {System.out.println("IO message:"+e.getMessage());}
	} 
	}
}
