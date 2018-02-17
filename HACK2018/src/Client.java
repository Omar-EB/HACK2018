import java.net.*;
import java.io.*;
public class Client {
	public static void main (String args[]) {

		if (args.length < 2) {
			System.err.println("Usage : ");
			System.err.println("java TCPClient <Name> <server>");
			System.exit (1);
		}    
		Socket s = null;
		try{
			int serverPort = 7896;
      System.out.println("starting a new client socket");
			s = new Socket(args[1], serverPort);    
			ObjectOutputStream out =new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in = new ObjectInputStream( s.getInputStream());
      System.out.println("subscribing as: " + args[0]);
      out.writeObject("hey");
      Thread.sleep (5000);
	  
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		//}catch (ClassNotFoundException e){System.out.println("readline:"+e.getMessage());
		}catch (InterruptedException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
  }
}