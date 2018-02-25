import java.net.*;
import java.io.*;

//-just to test
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//-just to test

public class Client implements ActionListener {
	ObjectOutputStream out;
	ObjectInputStream in;
	Socket sock = null;
	JFrame jf;
	JButton jb;
	JPanel jp;
	public Client (String host,String name ) {
		try{
			int serverPort = 6432;
			System.out.println("starting a new client socket");
			sock = new Socket(InetAddress.getByName(host), serverPort);
			out =new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream( sock.getInputStream());
			System.out.println("subscribing as: " + name);
			jf = new JFrame(); 
			jp= new JPanel();
 			jb = new JButton("press me");
			jp.setLayout(new GridLayout(0,1));
			jp.add(jb);
			jf.add(jp);
			jb.addActionListener(this);
			jf.setVisible(true);
		}catch (UnknownHostException e){System.out.println("Socket issue:"+e.getMessage());
		}catch (EOFException e){System.out.println("File ended:"+e.getMessage());
		}catch (IOException e){System.out.println("IO exception:"+e.getMessage());
		}
	}
	public static void main (String args[]) {   
		new Client(args[1],args[0]).start();
	}
	public void actionPerformed(ActionEvent e) {
		try {
			System.out.println("We wirte");
			out.writeObject(new ClientMessage(ClientMessage.ROUTE.SHARE));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void start() {
		try {
			while(true) {
				Object sm = in.readObject();
				if(sm!=null) {
					ServerMessage newm=(ServerMessage)sm;
					if( newm.getType()==ServerMessage.ROUTE.SHARE_RESPONSE) {
						System.out.println("client gets back");
					}
				}
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (EOFException e){
			System.out.println("File ended:"+e.getMessage());
		}catch (IOException e){
			System.out.println("readline IO:"+e.getMessage());
		}
	}
}