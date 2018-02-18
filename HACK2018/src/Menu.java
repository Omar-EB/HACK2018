import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(100, 100, 700, 700);
		contentPane.add(tabbedPane);
		
		JPanel search = new JPanel();
		search.setBackground(Color.decode("#B2EBF2"));
		tabbedPane.addTab("Search", null, search, "To search for photos");
		search.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JRadioButton rdbtnNature = new JRadioButton("Nature");
		rdbtnNature.setBackground(Color.WHITE);
		rdbtnNature.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		rdbtnNature.setPreferredSize(new Dimension(125,40) );
		search.add(rdbtnNature);
		
		JPanel savedPanel = new JPanel();
		savedPanel.setBackground(Color.decode("#B2EBF2"));
		JScrollPane saved = new JScrollPane(savedPanel);
		saved.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		savedPanel.setLayout(new GridLayout(0,2));
		saved.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		saved.setWheelScrollingEnabled(true);
		
				tabbedPane.addTab("Saved", null, saved, "Photos you saved");
		JPanel share = new JPanel();
		tabbedPane.addTab("Friend's Shared", null, share, "Photos shared to you");
		
		JPanel sharedPanel = new JPanel();
		sharedPanel.setBackground(Color.decode("#B2EBF2"));
		savedPanel.setLayout(new GridLayout(0,2));
		share.setLayout(new BoxLayout(share, BoxLayout.X_AXIS));
		share.add(sharedPanel);
		sharedPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JRadioButton citiesButton = new JRadioButton("Cities");
		citiesButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		citiesButton.setBackground(Color.WHITE);
		citiesButton.setPreferredSize(new Dimension(125,40) );
		search.add(citiesButton);
		
		JRadioButton countrySideButton = new JRadioButton ("Country Side");
		countrySideButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		countrySideButton.setBackground(Color.WHITE);
		countrySideButton.setPreferredSize(new Dimension(125,40) );
		search.add(countrySideButton);
		
		JRadioButton peopleButton = new JRadioButton("People");
		peopleButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		peopleButton.setBackground(Color.WHITE);
		peopleButton.setPreferredSize(new Dimension(125,40) );
		search.add(peopleButton);
		
		JRadioButton artButton = new JRadioButton("Art");
		artButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		artButton.setBackground(Color.WHITE);
		artButton.setPreferredSize(new Dimension(125,40) );
		search.add(artButton);
		
		JRadioButton carsButton = new JRadioButton("Cars");
		carsButton.setBackground(Color.WHITE);
		carsButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		carsButton.setPreferredSize(new Dimension(125,40) );
		search.add(carsButton);
		
		JRadioButton otherButton = new JRadioButton("Other");
		otherButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		otherButton.setBackground(Color.WHITE);
		otherButton.setPreferredSize(new Dimension(125, 40) );
		search.add(otherButton);
		
		ButtonGroup typeGroup = new ButtonGroup();
		typeGroup.add(rdbtnNature);
		typeGroup.add(citiesButton);
		typeGroup.add(countrySideButton);
		typeGroup.add(peopleButton);
		typeGroup.add(artButton);
		typeGroup.add(carsButton);
		typeGroup.add(otherButton);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		//btnSearch.setBackground(Color.decode("#B2EBF2"));
		btnSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		//typeGroup.add(btnSearch);
		search.add(btnSearch);
		
		
		
		
	}

}
