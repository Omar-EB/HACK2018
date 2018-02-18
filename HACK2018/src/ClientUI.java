import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class ClientUI extends JFrame {

	private JScrollPane contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientUI frame = new ClientUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 500);
		JPanel content = new JPanel();
		content.setBorder(new EmptyBorder(25, 25, 25, 25));
		
		
	
		/*
		JScrollPane scrollPane = new JScrollPane(contentPane);
		scrollPane.setToolTipText("");
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);
		*/
		Button natureButton = new Button("Nature");
		natureButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		natureButton.setBackground(Color.WHITE);
		natureButton.setPreferredSize(new Dimension(125,40) );
		natureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		content.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
		content.add(natureButton);
		
		Button citiesButton = new Button("Cities");
		citiesButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		citiesButton.setBackground(Color.WHITE);
		citiesButton.setPreferredSize(new Dimension(125,40) );
		content.add(citiesButton);
		
		Button countrySideButton = new Button("Country Side");
		countrySideButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		countrySideButton.setBackground(Color.WHITE);
		countrySideButton.setPreferredSize(new Dimension(125,40) );
		content.add(countrySideButton);
		
		Button peopleButton = new Button("People");
		peopleButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		peopleButton.setBackground(Color.WHITE);
		peopleButton.setPreferredSize(new Dimension(125,40) );
		content.add(peopleButton);
		
		Button artButton = new Button("Art");
		artButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		artButton.setBackground(Color.WHITE);
		artButton.setPreferredSize(new Dimension(125,40) );
		content.add(artButton);
		
		Button carsButton = new Button("Cars");
		carsButton.setBackground(Color.WHITE);
		carsButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		carsButton.setPreferredSize(new Dimension(125,40) );
		content.add(carsButton);
		
		Button otherButton = new Button("Other");
		otherButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		otherButton.setBackground(Color.WHITE);
		otherButton.setPreferredSize(new Dimension(125, 40) );
		content.add(otherButton);
		
		contentPane = new JScrollPane(content);
		contentPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
		setContentPane(contentPane);
		contentPane.setBackground(Color.decode("#B2EBF2"));
		
	}

}
