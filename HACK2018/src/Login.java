import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtNameBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		JPanel appName = new JPanel();
		contentPane.add(appName, BorderLayout.NORTH);
		appName.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblAppName = new JLabel("UOttaNotUseThisApp");
		lblAppName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		appName.add(lblAppName);
		
		JPanel loginLayout = new JPanel();
		loginLayout.setBackground(Color.decode("#B2EBF2"));
		contentPane.add(loginLayout, BorderLayout.CENTER);
		loginLayout.setLayout(new BoxLayout(loginLayout, BoxLayout.PAGE_AXIS));
		
		JLabel lblNewLabel = new JLabel(" ");
		loginLayout.add(lblNewLabel);
		lblNewLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel lblNewLabel_1 = new JLabel(" ");
		loginLayout.add(lblNewLabel_1);
		lblNewLabel_1.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel lblNewLabel_2 = new JLabel(" ");
		loginLayout.add(lblNewLabel_2);
		lblNewLabel_2.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel lblNewLabel_3 = new JLabel(" ");
		loginLayout.add(lblNewLabel_3);
		lblNewLabel_3.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel lblNewLabel_4 = new JLabel(" ");
		loginLayout.add(lblNewLabel_4);
		lblNewLabel_4.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel lblNewLabel_5 = new JLabel(" ");
		loginLayout.add(lblNewLabel_5);
		lblNewLabel_5.setAlignmentX(CENTER_ALIGNMENT);
		
		
		JLabel lblUserName = new JLabel("Please Enter Your Name");
		lblUserName.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblUserName.setAlignmentX(CENTER_ALIGNMENT);
		loginLayout.add(lblUserName);
		
		JLabel lblNewLabel_6 = new JLabel(" ");
		loginLayout.add(lblNewLabel_6);
		lblNewLabel_6.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel lblNewLabel_7 = new JLabel(" ");
		loginLayout.add(lblNewLabel_7);
		lblNewLabel_7.setAlignmentX(CENTER_ALIGNMENT);
		
		txtNameBox = new JTextField();
		txtNameBox.setMaximumSize(new Dimension(250, 25));
		txtNameBox.setHorizontalAlignment(SwingConstants.CENTER);
		txtNameBox.setText("HERE!");
		txtNameBox.setPreferredSize(new Dimension(50, 50)) ; 
		loginLayout.add(txtNameBox);
		txtNameBox.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setMaximumSize(new Dimension(80, 35));
		btnLogin.setAlignmentX(CENTER_ALIGNMENT);
		btnLogin.setPreferredSize(new Dimension(700, 50));	
		loginLayout.add(btnLogin);
		
		
		
	}

}
