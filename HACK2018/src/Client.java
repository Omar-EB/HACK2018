import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Client implements ActionListener {
	ObjectOutputStream out;
	ObjectInputStream in;
	Socket s = null;
	JFrame jf;
	JButton jb;
	JPanel jp;
	public Client (String host,String name ) {
		try{
			int serverPort = 7896;
			System.out.println("starting a new client socket");
			s = new Socket(InetAddress.getByName(host), serverPort);
			out =new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream( s.getInputStream());
			System.out.println("subscribing as: " + name);
			jf = new JFrame(); 
			jp= new JPanel();
 			jb = new JButton("press me");
			jp.setLayout(new GridLayout(0,1));
			jp.add(jb);
			jf.add(jp);
			jb.addActionListener(this);
			jf.setVisible(true);
		} catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline IO:"+e.getMessage());
		}
	}
	public static void main (String args[]) {   
			Client c = new Client(args[1],args[0]);
			c.runner();
	}
	public void actionPerformed(ActionEvent e) {
		try {
			System.out.println("We wirte");
			out.writeObject(new ClientMessage(ClientMessage.ROUTE.SHARE));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void runner() {
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline IO:"+e.getMessage());}
	}
}