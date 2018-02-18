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

public class Login extends JFrame {

	private JPanel contentPane;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		JPanel appName = new JPanel();
		contentPane.add(appName, BorderLayout.NORTH);
		appName.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblAppName = new JLabel("App Name");
		appName.add(lblAppName);
		
		JPanel loginLayout = new JPanel();
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
		
		
		JLabel lblUserName = new JLabel("Alan");
		lblUserName.setAlignmentX(CENTER_ALIGNMENT);
		loginLayout.add(lblUserName);
		
		JLabel lblNewLabel_6 = new JLabel(" ");
		loginLayout.add(lblNewLabel_6);
		lblNewLabel_6.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel lblNewLabel_7 = new JLabel(" ");
		loginLayout.add(lblNewLabel_7);
		lblNewLabel_7.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setAlignmentX(CENTER_ALIGNMENT);
		loginLayout.add(btnLogin);
		
		
		
	}

}
