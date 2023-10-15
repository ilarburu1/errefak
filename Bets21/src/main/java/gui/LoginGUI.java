package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Admin;
import domain.Editorea;
import domain.Erregistratua;
import domain.User;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class LoginGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField izena;
	private JPasswordField pasahitza;
	private JFrame nirePantaila;
	private BLFacade negozioLogika;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("LOGIN");
		nirePantaila=this;
		negozioLogika=MainGUI.getBusinessLogic();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		izena = new JTextField();
		izena.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(UIManager.getColor("Button.background"));
		
		JLabel username = new JLabel("Enter username");
		username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel password = new JLabel("Enter password");
		password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		pasahitza = new JPasswordField();
		
		JButton btnNewButton = new JButton("ENTER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String log=izena.getText();
				String pass=pasahitza.getText();

				User user = negozioLogika.isLogin(log, pass);
				if(user instanceof Admin) {
					if(user.getPassword().equals(pass)) {
						JFrame e6 = new AdminGUI();
						nirePantaila.setVisible(false);
						e6.setVisible(true);
					}else {
						textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("WrongPassword"));
					}
				}
				else if(user instanceof Erregistratua) {
					if(((Erregistratua)user).isBanned()==false) {
						if(user.getPassword().equals(pass)) {
							JFrame e7 = new ErregistratuaGUI(log);
							nirePantaila.setVisible(false);
							e7.setLocationRelativeTo(null);
							e7.setVisible(true);
						}else {
							textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("WrongPassword"));
						}
					}else {   //erabiltzailea banneatua dago
						SimpleDateFormat a = new SimpleDateFormat("dd/MM/yyyy");
						String data = a.format(((Erregistratua)user).getBanEndDate());
						textArea.setText("Zure kontua blokeatua dago " + data + " arte!!" );
					}
					
				}
				else if(user instanceof Editorea ) {
					if(user.getPassword().equals(pass)) {
						JFrame e13 = new EditoreaGUI();
						nirePantaila.setVisible(false);
						e13.setVisible(true);
					}else {
						textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("WrongPassword"));
					}
				}
				else if(user==null) {
					textArea.setText("Ez zaude erregistratua");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton_1 = new JButton("<--");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame e9 = new MainGUI();
				nirePantaila.setVisible(false);
				e9.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_1 = new JPanel();
		
		JRadioButton euskeraRdioButton = new JRadioButton("Euskera");
		
		JRadioButton CastellanoRadioButton = new JRadioButton("Castellano");
			
		JRadioButton EnglishRadioButton = new JRadioButton("English");
		EnglishRadioButton.setSelected(true);
		
		
		
		euskeraRdioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("eus"));
				System.out.println("Locale: "+Locale.getDefault());
				CastellanoRadioButton.setSelected(false);
				EnglishRadioButton.setSelected(false);
				btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Enter"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Login"));
				username.setText("Sartu erabiltzaile izena");
				password.setText("Sartu pasahitza");
			}
		});
		euskeraRdioButton.setBounds(93, 236, 80, 21);
		panel_1.add(euskeraRdioButton);
		
		
		
		CastellanoRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: "+Locale.getDefault());
				euskeraRdioButton.setSelected(false);
				EnglishRadioButton.setSelected(false);
				btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Enter"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Login"));
				username.setText("Introduce tu nombre de usuario");
				password.setText("Introduce la contraseña");
			}
		});
		CastellanoRadioButton.setBounds(172, 236, 87, 21);
		panel_1.add(CastellanoRadioButton);
		
		
		
		
		EnglishRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: "+Locale.getDefault());
				euskeraRdioButton.setSelected(false);
				CastellanoRadioButton.setSelected(false);
				btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Enter"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Login"));
				username.setText("Enter username");
				password.setText("Enter password");
			}
		});
		EnglishRadioButton.setBounds(261, 236, 80, 21);
		panel_1.add(EnglishRadioButton);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
					.addGap(392))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(209)
							.addComponent(izena, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(username, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
							.addGap(179)))
					.addGap(50))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addComponent(password, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
					.addGap(19)
					.addComponent(pasahitza, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
					.addGap(50))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(71)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
					.addGap(66))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(183)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addGap(195))
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(izena))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(username, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
							.addGap(2)))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(pasahitza)))
					.addGap(20)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
					.addGap(6)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
				
	}
	
	
	
	
	
}
