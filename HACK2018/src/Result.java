import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import java.awt.image.*;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Result extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Result frame = new Result();
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
	public Result() {
		/*
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JPanel savedPanel = new JPanel();
		JScrollPane saved2 = new JScrollPane(savedPanel);
		savedPanel.setLayout(new GridLayout(0,2));
		
		*/
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 800);
		JPanel content = new JPanel();
		JScrollPane scrollPanel = new JScrollPane(content);
		content.setBorder(new EmptyBorder(25, 25, 25, 25));
		content.setLayout(new GridLayout(0,2));
		

		
		
		JScrollPane contentPane = new JScrollPane(content);
		contentPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
	
		setContentPane(contentPane);
		//"G:\\UOTTAWAHACK\\HACK2018\\HACK2018\\src\\TEST.jpg"
		ImageIcon pic1 = new ImageIcon("TEST.jpg");
		Image pic1_img = getScaledImage(pic1.getImage(), pic1.getIconWidth()/2, pic1.getIconHeight()/2);
		pic1 = new ImageIcon(pic1_img);
		JLabel lblPicture = new JLabel(pic1);
		JRadioButton button1 = new JRadioButton();
		
		//"G:\\UOTTAWAHACK\\HACK2018\\HACK2018\\src\\testerr.jpg"
		ImageIcon pic2 = new ImageIcon("testerr.jpg");
		Image pic2_img = getScaledImage(pic2.getImage(),  pic2.getIconWidth()/2, pic2.getIconHeight()/2);
		pic2 = new ImageIcon(pic2_img);
		JLabel morePicture = new JLabel(pic2);
		JRadioButton button2 = new JRadioButton();
		

		content.add(lblPicture);
		content.add(button1);
		button1.setHorizontalAlignment(AbstractButton.CENTER);
		content.add(morePicture);
		content.add(button2);
		button2.setHorizontalAlignment(AbstractButton.CENTER);
		
		
		JPanel buttonPanel = new JPanel();

		JButton submissionButton = new JButton("SAVE!");
		submissionButton.setPreferredSize(new Dimension(400, 40));
		buttonPanel.add(submissionButton);
		
		content.add(buttonPanel);
		
		
		
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}

}
