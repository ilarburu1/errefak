package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;

import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class BanGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;
	private JFrame nirePantaila;
	private BLFacade negozioLogika;
	private String erabiltzailea;
	private final JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Launch the application.
	 */
	public static void main(String e) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BanGUI frame = new BanGUI(e);
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
	public BanGUI(String erabil) {
		nirePantaila=this;
		nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Ban"));
		this.negozioLogika=MainGUI.getBusinessLogic();
		this.erabiltzailea=erabil;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPaneErabilIzena = new JTextPane();
		textPaneErabilIzena.setEditable(false);
		textPaneErabilIzena.setBounds(20, 48, 250, 19);
		textPaneErabilIzena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPaneErabilIzena.setText("Name of the banned user:");
		textPaneErabilIzena.setBackground(SystemColor.menu);
		contentPane.add(textPaneErabilIzena);
		
		JTextPane textPaneErabilIzena_1 = new JTextPane();
		textPaneErabilIzena_1.setText(erabiltzailea);
		textPaneErabilIzena_1.setEditable(false);
		textPaneErabilIzena_1.setBounds(300, 48, 126, 19);
		textPaneErabilIzena_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPaneErabilIzena_1.setBackground(SystemColor.menu);
		contentPane.add(textPaneErabilIzena_1);
		
		JTextPane textPaneErabilData = new JTextPane();
		textPaneErabilData.setBounds(46, 89, 244, 19);
		textPaneErabilData.setText("Ban termination date:");
		textPaneErabilData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPaneErabilData.setBackground(SystemColor.menu);
		contentPane.add(textPaneErabilData);
		
		
		this.setSize(new Dimension(604, 370));
		jCalendar.setBounds(300, 80, 225, 150);
		contentPane.add(jCalendar);
	
		
		// Code for JCalendar
				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent propertychangeevent) {
//						this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//							public void propertyChange(PropertyChangeEvent propertychangeevent) {
						if (propertychangeevent.getPropertyName().equals("locale")) {
							jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
						} else if (propertychangeevent.getPropertyName().equals("calendar")) {
							calendarAnt = (Calendar) propertychangeevent.getOldValue();
							calendarAct = (Calendar) propertychangeevent.getNewValue();
							System.out.println("calendarAnt: "+calendarAnt.getTime());
							System.out.println("calendarAct: "+calendarAct.getTime());
							DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
							
							int monthAnt = calendarAnt.get(Calendar.MONTH);
							int monthAct = calendarAct.get(Calendar.MONTH);
							if (monthAct!=monthAnt) {
								if (monthAct==monthAnt+2) { 
									// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolverá 2 de marzo (se toma como equivalente a 30 de febrero)
									// Con este código se dejará como 1 de febrero en el JCalendar
									calendarAct.set(Calendar.MONTH, monthAnt+1);
									calendarAct.set(Calendar.DAY_OF_MONTH, 1);
								}
								
								jCalendar.setCalendar(calendarAct);

								
							}
						}
					}
				});
				JTextPane textPanelTextua = new JTextPane();
				textPanelTextua.setEditable(false);
				textPanelTextua.setBounds(100, 229, 308, 19);
				textPanelTextua.setFont(new Font("Tahoma", Font.PLAIN, 14));
				textPanelTextua.setBackground(SystemColor.menu);
				contentPane.add(textPanelTextua);
				
				JButton btnBan = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnBan"));
				btnBan.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Date blokeoBukaeraData = jCalendar.getDate();
						negozioLogika.banUser(erabiltzailea,blokeoBukaeraData);
						textPanelTextua.setText("'" + erabiltzailea+ "'" + " Erabiltzailea blokeatua izan da");
					}
				});
				btnBan.setBounds(158, 258, 238, 27);
				contentPane.add(btnBan);
				
				
				btnAtzera.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFrame e10 = new AdminGUI();
						nirePantaila.setVisible(false);
						e10.setVisible(true);
					}
				});
				btnAtzera.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnAtzera.setBounds(10, 10, 63, 21);
				
				getContentPane().add(btnAtzera);
				
				JPanel buttonGroup = new JPanel();
				buttonGroup.setBounds(0, 295, 590, 38);
				contentPane.add(buttonGroup);
				
				JRadioButton rdbtnEuskera = new JRadioButton("Euskera");
				buttonGroup.add(rdbtnEuskera);
				
				JRadioButton rdbtnCastellano = new JRadioButton("Castellano");
				buttonGroup.add(rdbtnCastellano);
				
				JRadioButton rdbtnEnglish = new JRadioButton("English");
				rdbtnEnglish.setSelected(true);
				buttonGroup.add(rdbtnEnglish);
				
				
				
				rdbtnEuskera.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Locale.setDefault(new Locale("eus"));
						System.out.println("Locale: "+Locale.getDefault());
						rdbtnCastellano.setSelected(false);
						rdbtnEnglish.setSelected(false);
						nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Ban"));
						textPaneErabilIzena.setText(ResourceBundle.getBundle("Etiquetas").getString("textErabilIzena"));
						textPaneErabilData.setText(ResourceBundle.getBundle("Etiquetas").getString("textData"));
						btnBan.setText(ResourceBundle.getBundle("Etiquetas").getString("btnBan"));
					}
				});
				
				
				
				rdbtnCastellano.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Locale.setDefault(new Locale("es"));
						System.out.println("Locale: "+Locale.getDefault());
						rdbtnEuskera.setSelected(false);
						rdbtnEnglish.setSelected(false);
						nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Ban"));
						textPaneErabilIzena.setText(ResourceBundle.getBundle("Etiquetas").getString("textErabilIzena"));
						textPaneErabilData.setText(ResourceBundle.getBundle("Etiquetas").getString("textData"));
						btnBan.setText(ResourceBundle.getBundle("Etiquetas").getString("btnBan"));
					}
				});
				
				
				
				
				
				rdbtnEnglish.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Locale.setDefault(new Locale("en"));
						System.out.println("Locale: "+Locale.getDefault());
						rdbtnEuskera.setSelected(false);
						rdbtnCastellano.setSelected(false);
						nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Ban"));
						textPaneErabilIzena.setText(ResourceBundle.getBundle("Etiquetas").getString("textErabilIzena"));
						textPaneErabilData.setText(ResourceBundle.getBundle("Etiquetas").getString("textData"));
						btnBan.setText(ResourceBundle.getBundle("Etiquetas").getString("btnBan"));
					}
				});
				
			}
	
	}

