package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Font;
public class Hasierako_pantailaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JLabel jLabelSelectOption;
	private JFrame nirePantaila;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hasierako_pantailaGUI frame = new Hasierako_pantailaGUI(); 
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
	public Hasierako_pantailaGUI() {
		setTitle("MyBets");
		nirePantaila=this;
		nirePantaila.setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon ico = new ImageIcon(getClass().getResource("B-Bets-Casino.png"));
		
		JButton btnNewButton = new JButton("ENTER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI e12 = new MainGUI();
				nirePantaila.setVisible(false);
				e12.setLocationRelativeTo(null); 
				e12.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(234, 191, 98, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		
		lblNewLabel.setBounds(10, 10, 426, 253);
		ImageIcon img = new ImageIcon(ico.getImage().getScaledInstance(lblNewLabel.getWidth(),lblNewLabel.getHeight(),Image.SCALE_SMOOTH));
		
		lblNewLabel.setIcon(img);
		contentPane.add(lblNewLabel);
	}
}
