package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import businessLogic.BLFacade;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JRadioButton;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField izena;
	private JPasswordField pasahitza;
	private JFrame nirePantaila;
	private BLFacade negozioLogika;
	private JTextField NANzenbakia;
	private JTextField kontuDirua;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle("REGISTER");
		nirePantaila=this;
		negozioLogika=MainGUI.getBusinessLogic();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel username = new JLabel("Enter username");
		username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		username.setBounds(18, 26, 208, 25);
		contentPane.add(username);
		
		izena = new JTextField();
		izena.setBounds(232, 31, 194, 19);
		contentPane.add(izena);
		izena.setColumns(10);
		
		JLabel password = new JLabel("Create password");
		password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		password.setBounds(18, 68, 129, 19);
		contentPane.add(password);
		
		pasahitza = new JPasswordField();
		pasahitza.setBounds(232, 70, 194, 19);
		contentPane.add(pasahitza);
		
		
		JLabel NAN = new JLabel("Enter ID");
		NAN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NAN.setBounds(18, 104, 155, 25);
		contentPane.add(NAN);
		
		NANzenbakia = new JTextField();
		NANzenbakia.setColumns(10);
		NANzenbakia.setBounds(232, 109, 194, 19);
		contentPane.add(NANzenbakia);
		
		JLabel Money = new JLabel("Money");
		Money.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Money.setBounds(18, 142, 155, 25);
		contentPane.add(Money);
		
		kontuDirua = new JTextField();
		kontuDirua.setColumns(10);
		kontuDirua.setBounds(232, 147, 194, 19);
		contentPane.add(kontuDirua);
		kontuDirua.setText("0");
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setBounds(18, 168, 411, 25);
		contentPane.add(textArea);
		
		JButton atzeraBotoia = new JButton("<--");
		atzeraBotoia.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFrame e8 = new MainGUI();
				nirePantaila.setVisible(false);
				e8.setVisible(true);
			}
		});
		atzeraBotoia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		atzeraBotoia.setBounds(10, 4, 61, 21);
		contentPane.add(atzeraBotoia);
		
		
		JButton RegisterButton = new JButton("REGISTER");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String log=izena.getText();
				String pass=pasahitza.getText();
				String NAN=NANzenbakia.getText();
				try {
				Integer diru_kop=Integer.parseInt(kontuDirua.getText());
				
				if(log.equals("") || log.length()<4) {
					textArea.setText("Erabiltzaile izen hori ez da egokia, gutxienez 4 karaktere "
							+ "izan behar ditu");
				}
				else if(pass.equals("") || pass.length() <6) {
					textArea.setText("Pasahitz hori ez da egokia, gutxienez 6 karaktere"
							+ "izan behar ditu");
				}
				else if(NAN.equals("") || NAN.length()<9   ){
					
					textArea.setText("NAN zenbaki horrek ez du errespetatzen benetazko NAN zenbaki baten patroia");
				}
				else if(NAN.substring(0,8).matches("\\d+")==false || NAN.substring(8,9).matches("[A-Z]*")==false ) {
				
					textArea.setText("NAN zenbaki horrek ez du errespetatzen benetazko NAN zenbaki baten patroia");
				}
				else if(diru_kop<0) {
						textArea.setText("Diru kantitatea ezin da negatiboa izan");
				}
			    else {
				

					if(negozioLogika.UserExistitzenDa(log)==true) {
						textArea.setText("Erabiltzaile hori existitxen da");
					}else {
						negozioLogika.Register(log, pass,NAN,diru_kop);
						textArea.setText("Erabiltzailea ondo sortu da!!");	
						izena.setText("");
						pasahitza.setText("");
						NANzenbakia.setText("");
						kontuDirua.setText("");
					}
				}
				}catch(NumberFormatException errorea) {
					textArea.setText("Benetako diru kantitatea izan behar da");
				}
			}
		});
		RegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RegisterButton.setBounds(160, 196, 129, 30);
		contentPane.add(RegisterButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(-23, 225, 481, 38);
		contentPane.add(panel_1);
		
		
		
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
				RegisterButton.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Register"));	
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Register"));
				username.setText("Erabiltzaile izena sartu");
				password.setText("Pasahitza sortu");
				NAN.setText("NAN zenbakia sartu");
				Money.setText("Dirua");
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
				RegisterButton.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Register"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Register"));
				username.setText("Introduce un nombre de usuario");
				password.setText("Crea una contraseña");
				NAN.setText("Introduce tu DNI");
				Money.setText("Dinero");
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
				RegisterButton.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Register"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Register"));
				username.setText("Enter username");
				password.setText("Create password");
				NAN.setText("Enter ID");
				Money.setText("Money");
			}
		});
		EnglishRadioButton.setBounds(261, 236, 80, 21);
		panel_1.add(EnglishRadioButton);
		
		
		
		
		}
	
	
}
