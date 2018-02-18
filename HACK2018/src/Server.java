import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
	
	public static void main (String args[]) {
		int serverPort = 7896; // the server port
    List <Connection> all_connections = new ArrayList<Connection>();
	   	try {
	        InetAddress addr = InetAddress.getLocalHost();
	    
	        // Get IP Address
	        byte[] ipAddr = addr.getAddress();
	    
	        // Get hostname
	        String hostname = addr.getHostName();
	        System.out.println("Server Name: " + hostname + "\nServer Port: " + serverPort);
	    } catch (UnknownHostException e) {
	    }

		try{
			ServerSocket listenSocket = new ServerSocket(serverPort);
			System.out.println("Server is Ready");
			while(true) {
				System.out.println("listening to client sockets");
				Socket clientSocket = listenSocket.accept();
				System.out.println("connection found, creating a new connection thread");
				Connection c = new Connection(clientSocket);
				all_connections.add(c);
				c.all_connections = all_connections;
			}
		} catch(IOException e) {while (true) System.out.println("IOException Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {
	InetAddress addr;
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
		try{
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException x){
			x.getStackTrace();
		}
		//while(true){
			try {
			String st = (String)in.readObject();
			  System.out.println(st);
				//}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
				} catch(IOException e) {System.out.println("readline1:"+e.getMessage());
				} catch(ClassNotFoundException e) {System.out.println("readline1:"+e.getMessage());}
				//}catch (InterruptedException e){System.out.println("readline:"+e.getMessage());
				//}
		//}
		/*finally {
			try {
			clientSocket.close();
			for (int i=0; i< all_connections.size(); i++) {
				if (this == (Connection) all_connections.get(i)) {
					System.out.println("Removing connection from the list, for " + name);
					System.out.println("num connection upon removing " + all_connections.size());
					all_connections.remove(i);
					break;
				}
			}
			}catch (IOException e){}
		} */
		

	}
}
