package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import businessLogic.BLFacade;
import configuration.UtilDate;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JRadioButton;

public class CreateEventGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField eventNumber;
	private JFrame nirePantaila;
	private JTextField YearField;
	private JTextField MonthField;
	private JTextField DayField;
	private JTextField eventDescription;
	private BLFacade negozioLogika;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEventGUI frame = new CreateEventGUI();
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
	public CreateEventGUI() {
		setTitle("Crate Event");
		nirePantaila=this;
		negozioLogika=MainGUI.getBusinessLogic();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel eveNum = new JLabel("Event number");
		eveNum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		eveNum.setBounds(18, 31, 208, 25);
		contentPane.add(eveNum);
		
		eventNumber = new JTextField();
		eventNumber.setBounds(232, 36, 194, 19);
		contentPane.add(eventNumber);
		eventNumber.setColumns(10);
		
		JLabel eveDes = new JLabel("Event description");
		eveDes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		eveDes.setBounds(18, 82, 129, 19);
		contentPane.add(eveDes);
		
		
		JLabel data = new JLabel("Date");
		data.setFont(new Font("Tahoma", Font.PLAIN, 16));
		data.setBounds(186, 111, 61, 25);
		contentPane.add(data);
		
		YearField = new JTextField();
		YearField.setColumns(10);
		YearField.setBounds(65, 154, 68, 19);
		contentPane.add(YearField);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setBounds(18, 174, 411, 19);
		contentPane.add(textArea);
		
		JButton atzeraBotoia = new JButton("<--");
		atzeraBotoia.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFrame e8 = new AdminGUI();
				nirePantaila.setVisible(false);
				e8.setVisible(true);
			}
		});
		atzeraBotoia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		atzeraBotoia.setBounds(10, 4, 61, 21);
		contentPane.add(atzeraBotoia);
		
		
		JButton RegisterButton = new JButton("CREATE EVENT");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
				String eventNum=eventNumber.getText();
				String eventDes=eventDescription.getText();
				String[] zatiak = eventDes.split("-");
				int year = Integer.parseInt(YearField.getText());
				int month = Integer.parseInt(MonthField.getText());
				int day = Integer.parseInt(DayField.getText());

				
				if(eventNum.equals("") || eventNum.matches("\\d+")==false) {
					textArea.setText("Gertaerako zenbakia ez da zuzena");
				}
				else if(eventDes.equals("") || zatiak[0].equals("") || zatiak[1].equals("") ) {
					textArea.setText("Deskripzio hori ez da zuzena");
				}
				else if((year>2030 || year<0) ) {
					textArea.setText("Urte hori ez da zuzena");
			    }
				else if((month>12 || month<0) ) {
					textArea.setText("Hilabete hori ez da zuzena");
			    }
				else if((day>31 || year<0) ) {
					textArea.setText("Egun hori ez da zuzena");
			    }
				else {	
				        int EventNum = Integer.parseInt(eventNum);
						negozioLogika.createEvent(EventNum, eventDes, UtilDate.newDate(year,month-1,day));
						textArea.setText("Gertaera ondo sortu da!!");	
						eventNumber.setText("");
						eventDescription.setText("");
						YearField.setText("");
						MonthField.setText("");
						DayField.setText("");
				}
			}catch(NumberFormatException errorea) {
				textArea.setText("Gertaeraren data hori ez da zuzena");
			}
		}
	});
		
		RegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RegisterButton.setBounds(145, 196, 144, 30);
		contentPane.add(RegisterButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(-23, 225, 481, 38);
		contentPane.add(panel_1);
		
		JLabel yearT = new JLabel("Year");
		yearT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yearT.setBounds(65, 133, 61, 25);
		contentPane.add(yearT);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMonth.setBounds(186, 133, 61, 25);
		contentPane.add(lblMonth);
		
		JLabel lblDay = new JLabel("Day");
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDay.setBounds(315, 133, 61, 25);
		contentPane.add(lblDay);
		
		MonthField = new JTextField();
		MonthField.setColumns(10);
		MonthField.setBounds(186, 154, 68, 19);
		contentPane.add(MonthField);
		
		DayField = new JTextField();
		DayField.setColumns(10);
		DayField.setBounds(315, 154, 68, 19);
		contentPane.add(DayField);
		
		eventDescription = new JTextField();
		eventDescription.setColumns(10);
		eventDescription.setBounds(232, 84, 194, 19);
		contentPane.add(eventDescription);
		
		
		
		
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
				RegisterButton.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));	
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
				eveNum.setText("Gertaera zenbakia");
				eveDes.setText("Gertaera azalpena");
				data.setText("Data");
				yearT.setText("Urtea");
				lblMonth.setText("Hilabetea");
				lblDay.setText("Eguna");
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
				RegisterButton.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
				eveNum.setText("Numero de evento");
				eveDes.setText("Descripcion de evento");
				data.setText("Fecha");
				yearT.setText("Año");
				lblMonth.setText("Mes");
				lblDay.setText("Día");
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
				RegisterButton.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
				eveNum.setText("Event number");
				eveDes.setText("Event description");
				data.setText("Date");
				yearT.setText("Year");
				lblMonth.setText("Month");
				lblDay.setText("Day");
			}
		});
		EnglishRadioButton.setBounds(261, 236, 80, 21);
		panel_1.add(EnglishRadioButton);
		
		
		
		}
}
