package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Apustua;
import domain.Aukera;
import domain.Erregistratua;
import domain.Question;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class BetGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private final JLabel jLabelAukera = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Aukerak")); 

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JScrollPane scrollPaneAukerak = new JScrollPane();


	private JFrame nirePantaila;
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private BLFacade negozioLogika;
	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private JTable tableAukera = new JTable();
	
	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelAukerak;
	
	
	private String log;
	
	private domain.Event event;
	private domain.Question question;
	private domain.Apustua apostua;
	private domain.Aukera hautatutakoAukera;
	private boolean diruaEditatu = true;
	private int kont;
	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	
	private String[] columnNamesAukerak = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("AukeraN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Kuota"), 
			ResourceBundle.getBundle("Etiquetas").getString("Aukera")

	};
	private final JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JTextField textField = new JTextField();
	private final JButton btnApostuaAmaitu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApostuaAmaitu")); //$NON-NLS-1$ //$NON-NLS-2$

	private JButton btnAukeraGehitu;

	public BetGUI(String log)
	{
		this.log=log;
		this.negozioLogika=MainGUI.getBusinessLogic();
		textField.setEditable(false);
		textField.setBounds(191, 516, 191, 19);
		textField.setColumns(10);
		textField.setText("0");
		diruaEditatu=true;
		kont=0;
		btnApostuaAmaitu.setVisible(false);
		btnApostuaAmaitu.setEnabled(false);
		try
		{
			nirePantaila=this;
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 669));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Bet"));

		jLabelEventDate.setBounds(new Rectangle(83, 25, 140, 25));
		jLabelQueries.setBounds(138, 210, 406, 14);
		jLabelEvents.setBounds(295, 29, 259, 16);
		jLabelAukera.setBounds(137, 367, 407, 13);
		
		getContentPane().add(textField);
		
		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);
		this.getContentPane().add(jLabelAukera);

		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		datesWithEventsCurrentMonth=negozioLogika.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						datesWithEventsCurrentMonth=negozioLogika.getEventsMonth(jCalendar1.getDate());
					}



					CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects
                       
						Vector<domain.Event> events=negozioLogika.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
						tableEvents.setDefaultEditor(Object.class, null);
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(138, 234, 406, 116));
		scrollPaneAukerak.setBounds(138, 390, 406, 116);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("ApostuaText")); //$NON-NLS-1$ //$NON-NLS-2$
		textPane.setBounds(83, 555, 430, 19);
		getContentPane().add(textPane);
		
		JLabel lblOrainDuzunDirua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("duzunDiruaText")); //$NON-NLS-1$ //$NON-NLS-2$
		lblOrainDuzunDirua.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblOrainDuzunDirua.setBounds(new Rectangle(83, 25, 140, 25));
		lblOrainDuzunDirua.setBounds(523, 547, 140, 25);
		getContentPane().add(lblOrainDuzunDirua);
		
		JTextPane textPane_DiruKopurua = new JTextPane();
		textPane_DiruKopurua.setText(" ");
		textPane_DiruKopurua.setBackground(Color.WHITE);
		textPane_DiruKopurua.setBounds(523, 570, 102, 19);
		getContentPane().add(textPane_DiruKopurua);
		textPane_DiruKopurua.setText(String.valueOf(negozioLogika.erregistratuaItzuli(log).getKontuDirua()) + "�");
		textPane_DiruKopurua.setEditable(false);
		
		
		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				event=ev;
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.setDefaultEditor(Object.class, null);
			}
		});

		
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int j=tableQueries.getSelectedRow();
				String q=(String)tableModelQueries.getValueAt(j, 1);
				Question qu=event.getConcreteQuestion(q);
				question=qu;
				Vector<Aukera> aukerak=negozioLogika.getAukerak(qu);

				tableModelAukerak.setDataVector(null, columnNamesAukerak);
				tableModelAukerak.setColumnCount(4); // another column added to allocate aukera objects
				
				if(aukerak.isEmpty()) {
			        textPane.setText("Galdera honek ez du aukerarik");
		         }
				
				for (domain.Aukera a:aukerak){
					Vector<Object> row = new Vector<Object>();
					row.add(a.getAukeraID());
					row.add(a.getKuota());
					row.add(a.getAukeraIzena());
					row.add(a);// aukera object added in order to obtain it with tableModelEvents.getValueAt(i,2)
					tableModelAukerak.addRow(row);	
				}
				
				tableAukera.getColumnModel().getColumn(0).setPreferredWidth(20);
				tableAukera.getColumnModel().getColumn(1).setPreferredWidth(20);
				tableAukera.getColumnModel().getColumn(2).setPreferredWidth(268);
				tableAukera.getColumnModel().removeColumn(tableAukera.getColumnModel().getColumn(3)); // not shown in JTable
				tableAukera.setDefaultEditor(Object.class, null);
			}
		});
   
		
		tableAukera.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableAukera.getSelectedRow();
				String aukera = (String) tableModelAukerak.getValueAt(i,2);
				if((aukera.equals("")== false) && (diruaEditatu==true)) {
					textField.setEditable(true);
				}
			}
		});
   	
		
		
		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		scrollPaneAukerak.setViewportView(tableAukera);
		tableModelAukerak = new DefaultTableModel(null, columnNamesAukerak);

		tableAukera.setModel(tableModelAukerak);
		tableAukera.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableAukera.getColumnModel().getColumn(1).setPreferredWidth(20);
		tableAukera.getColumnModel().getColumn(2).setPreferredWidth(268);
		
		
		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		this.getContentPane().add(scrollPaneAukerak,null);
		
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame e10 = new ErregistratuaGUI(log);
				nirePantaila.setVisible(false);
				e10.setVisible(true);
			}
		});
		btnAtzera.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtzera.setBounds(10, 10, 63, 21);
		
		getContentPane().add(btnAtzera);

		JButton btnApostatu =new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApustuEginGUI.btnApostatu.text"));
		btnApostatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableAukera.getSelectedRow();
				hautatutakoAukera=(domain.Aukera)tableModelAukerak.getValueAt(i,3);
				
				Float zenbatekoa=Float.parseFloat(textField.getText());
				
				Erregistratua erreg = negozioLogika.erregistratuaItzuli(log);
				if(zenbatekoa<question.getBetMinimum()) {
					textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("ApostuMinimoaEzText")+question.getBetMinimum());
				}
				if(zenbatekoa>question.getBetMinimum() && erreg.getKontuDirua() >= zenbatekoa) {
					apostua= new Apustua(log,zenbatekoa);
					apostua.addAukera(hautatutakoAukera);
					negozioLogika.addBet(hautatutakoAukera,apostua);
					textPane.setText("Apostua ondo burutu da!!");
					textPane_DiruKopurua.setText(String.valueOf(negozioLogika.erregistratuaItzuli(log).getKontuDirua()) + "�");
					textField.setText("");
					System.out.println(question.getBetMinimum());
				}
				if(erreg.getKontuDirua()<zenbatekoa) {
					textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("DiruNahikoaEzText"));
				}
			}
		});
		btnApostatu.setBounds(392, 516, 152, 25);
		getContentPane().add(btnApostatu);
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(103, 594, 481, 38);
		getContentPane().add(panel_1);
		
		
		
		JLabel jLabelIdatziApostua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IdatziApostuaText")); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelIdatziApostua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLabelIdatziApostua.setBounds(new Rectangle(83, 25, 140, 25));
		jLabelIdatziApostua.setBounds(10, 511, 177, 25);
		getContentPane().add(jLabelIdatziApostua);
		
		
		
		
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
				btnApostatu.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuEginGUI.btnApostatu.text"));	
				btnAukeraGehitu.setText(ResourceBundle.getBundle("Etiquetas").getString("ButtonAukeraGehitu"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
				jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
				jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events"));
				jLabelIdatziApostua.setText(ResourceBundle.getBundle("Etiquetas").getString("IdatziApostuaText"));
				lblOrainDuzunDirua.setText(ResourceBundle.getBundle("Etiquetas").getString("duzunDiruaText"));
				
				tableEvents.getColumnModel().getColumn(0).setHeaderValue("#Gertaera");
				tableEvents.getColumnModel().getColumn(1).setHeaderValue("Gertaera");
				tableEvents.getTableHeader().resizeAndRepaint();
				
				tableQueries.getColumnModel().getColumn(0).setHeaderValue("#Galdera");
				tableQueries.getColumnModel().getColumn(1).setHeaderValue("Galdera");
				tableQueries.getTableHeader().resizeAndRepaint();
				
				tableAukera.getColumnModel().getColumn(0).setHeaderValue("#Aukera");
				tableAukera.getColumnModel().getColumn(1).setHeaderValue("Kuota");
				tableAukera.getColumnModel().getColumn(2).setHeaderValue("Aukera");
				tableAukera.getTableHeader().resizeAndRepaint();
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
				btnApostatu.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuEginGUI.btnApostatu.text"));
				btnAukeraGehitu.setText(ResourceBundle.getBundle("Etiquetas").getString("ButtonAukeraGehitu"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
				jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
				jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events"));
				jLabelIdatziApostua.setText(ResourceBundle.getBundle("Etiquetas").getString("IdatziApostuaText"));
				lblOrainDuzunDirua.setText(ResourceBundle.getBundle("Etiquetas").getString("duzunDiruaText"));
				
				tableEvents.getColumnModel().getColumn(0).setHeaderValue("#Evento");
				tableEvents.getColumnModel().getColumn(1).setHeaderValue("Evento");
				tableEvents.getTableHeader().resizeAndRepaint();
				
				tableQueries.getColumnModel().getColumn(0).setHeaderValue("#Pregunta");
				tableQueries.getColumnModel().getColumn(1).setHeaderValue("Pregunta");
				tableQueries.getTableHeader().resizeAndRepaint();
				
				tableAukera.getColumnModel().getColumn(0).setHeaderValue("#Option");
				tableAukera.getColumnModel().getColumn(1).setHeaderValue("Cuota");
				tableAukera.getColumnModel().getColumn(2).setHeaderValue("Opcion");
				tableAukera.getTableHeader().resizeAndRepaint();
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
				btnApostatu.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuEginGUI.btnApostatu.text"));
				btnAukeraGehitu.setText(ResourceBundle.getBundle("Etiquetas").getString("ButtonAukeraGehitu"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
				jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
				jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events"));
				jLabelIdatziApostua.setText(ResourceBundle.getBundle("Etiquetas").getString("IdatziApostuaText"));
				lblOrainDuzunDirua.setText(ResourceBundle.getBundle("Etiquetas").getString("duzunDiruaText"));
				
				tableEvents.getColumnModel().getColumn(0).setHeaderValue("#Event");
				tableEvents.getColumnModel().getColumn(1).setHeaderValue("Event");
				tableEvents.getTableHeader().resizeAndRepaint();
				
				tableQueries.getColumnModel().getColumn(0).setHeaderValue("#Question");
				tableQueries.getColumnModel().getColumn(1).setHeaderValue("Question");
				tableQueries.getTableHeader().resizeAndRepaint();
				
				tableAukera.getColumnModel().getColumn(0).setHeaderValue("#Option");
				tableAukera.getColumnModel().getColumn(1).setHeaderValue("Quote");
				tableAukera.getColumnModel().getColumn(2).setHeaderValue("Option");
				tableAukera.getTableHeader().resizeAndRepaint();
			}
		});
		EnglishRadioButton.setBounds(261, 236, 80, 21);
		panel_1.add(EnglishRadioButton);
		
		btnAukeraGehitu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ButtonAukeraGehitu")); //$NON-NLS-1$ //$NON-NLS-2$
		btnAukeraGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnApostatu.setVisible(false);
				btnApostatu.setEnabled(false);
				btnApostuaAmaitu.setVisible(true);
				btnApostuaAmaitu.setEnabled(true);
				textField.setEditable(false);
				
				int i=tableAukera.getSelectedRow();
				hautatutakoAukera=(domain.Aukera)tableModelAukerak.getValueAt(i,3);
			
				Float zenbatekoa=Float.parseFloat(textField.getText());
				
				Erregistratua erreg = negozioLogika.erregistratuaItzuli(log);
				if(erreg.getKontuDirua() >= zenbatekoa) {
					if(diruaEditatu) {
						apostua= new Apustua(log,zenbatekoa);
					}   
						apostua.addAukera(hautatutakoAukera);
						kont++;
						textPane.setText("Apostu anitzera aukera berria gehitu da!!");
						diruaEditatu=false;
					
				}else if(zenbatekoa < question.getBetMinimum()) {
					textPane.setText("ApostuMinimoaEzText");
				}else {
					textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("DiruNahikoaEzText"));
				}
			}
		});
		btnAukeraGehitu.setBounds(546, 515, 140, 25);
		getContentPane().add(btnAukeraGehitu);
		btnApostuaAmaitu.setBounds(392, 515, 152, 25);
		
		getContentPane().add(btnApostuaAmaitu);
		
		btnApostuaAmaitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				    if(kont>1) {
				    	hautatutakoAukera.setAzkenaDa(true);
				    	negozioLogika.addBet(hautatutakoAukera,apostua);
						textPane_DiruKopurua.setText(String.valueOf(negozioLogika.erregistratuaItzuli(log).getKontuDirua()) + "�");
						textField.setText("");
						textPane.setText("Apostu anitza ondo burutu da!!");

						btnApostatu.setVisible(true);
						btnApostatu.setEnabled(true);
						btnApostuaAmaitu.setVisible(false);
						btnApostuaAmaitu.setEnabled(false);
				    }else {
				    	textPane.setText("Apostu anitzak sortzeko gutxienez 2 aukera gehitu behar dira!!");
				    }
					
			}
		});

		
		}
}
