package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Apustua;
import domain.Aukera;
import domain.Question;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class DuplicateEventGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private JCalendar jCalendar2 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAnt2 = null;
	private Calendar calendarAct = null;
	private Calendar calendarAct2 = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();


	private JLabel jLabelEvents;
	private JFrame nirePantaila;
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private Vector<Date> datesWithEventsCurrentMonth2 = new Vector<Date>();

	private BLFacade negozioLogika;
	private JTable tableEvents= new JTable();
	
	private DefaultTableModel tableModelEvents;

	private domain.Event event1;
	private Date dataBerria;
	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};

	private final JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$


	public DuplicateEventGUI()
	{
		this.negozioLogika=MainGUI.getBusinessLogic();
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
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("DuplicateTitle"));

		jLabelEventDate.setBounds(new Rectangle(83, 25, 338, 25));
		jLabelQueries.setBounds(107, 258, 406, 14);
		
		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);

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
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("ApostuaText")); //$NON-NLS-1$ //$NON-NLS-2$
		textPane.setBounds(119, 469, 394, 19);
		getContentPane().add(textPane);
		
		
		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				event1=ev;
			}
		});
   	
		
		
		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		
		
		this.getContentPane().add(scrollPaneEvents, null);
		
		
		
		
		
		jCalendar2.setBounds(new Rectangle(40, 50, 225, 150));
		jCalendar2.setBounds(214, 294, 225, 150);


		datesWithEventsCurrentMonth2=negozioLogika.getEventsMonth(jCalendar2.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar2,datesWithEventsCurrentMonth2);

		// Code for JCalendar
		this.jCalendar2.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar2.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt2 = (Calendar) propertychangeevent.getOldValue();
					calendarAct2 = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar2.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay2=UtilDate.trim(new Date(jCalendar2.getCalendar().getTime().getTime()));

					 
					
					int monthAnt2 = calendarAnt2.get(Calendar.MONTH);
					int monthAct2 = calendarAct2.get(Calendar.MONTH);
					
					if (monthAct2!=monthAnt2) {
						if (monthAct2==monthAnt2+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct2.set(Calendar.MONTH, monthAnt2+1);
							calendarAct2.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar2.setCalendar(calendarAct2);

						datesWithEventsCurrentMonth2=negozioLogika.getEventsMonth(jCalendar2.getDate());
					}



					CreateQuestionGUI.paintDaysWithEvents(jCalendar2,datesWithEventsCurrentMonth2);
													
					

					try {
						if(firstDay2==null) {
							textPane.setText("Egun bat aukeratu behar duzu!!");
						}else {
							dataBerria=firstDay2;
						}
					}
					catch (Exception e1) {
						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar2, null);
		
		
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame e10 = new EditoreaGUI();
				nirePantaila.setVisible(false);
				e10.setVisible(true);
			}
		});
		btnAtzera.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtzera.setBounds(10, 10, 63, 21);
		
		getContentPane().add(btnAtzera);

		JButton btnBikoiztu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ButtonDuplicate")); //$NON-NLS-1$ //$NON-NLS-2$
		btnBikoiztu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<domain.Event> ebentuGuztiak=negozioLogika.getAllEvents();
				domain.Event ev=ebentuGuztiak.lastElement();
				int zbkia=ev.getEventNumber()+1;
				negozioLogika.duplicateEvent(zbkia, event1, dataBerria);
			}
		});
		btnBikoiztu.setBounds(244, 520, 177, 25);
		getContentPane().add(btnBikoiztu);
		
		
	
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(103, 594, 481, 38);
		getContentPane().add(panel_1);
		
		
		
		
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
				btnBikoiztu.setText(ResourceBundle.getBundle("Etiquetas").getString("ButtonDuplicate"));	
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("DuplicateTitle"));
				jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
				jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Event"));
				
				tableEvents.getColumnModel().getColumn(0).setHeaderValue("#Gertaera");
				tableEvents.getColumnModel().getColumn(1).setHeaderValue("Gertaera");
				tableEvents.getTableHeader().resizeAndRepaint();
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
				btnBikoiztu.setText(ResourceBundle.getBundle("Etiquetas").getString("ButtonDuplicate"));	
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("DuplicateTitle"));
				jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
				jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Event"));
				
				tableEvents.getColumnModel().getColumn(0).setHeaderValue("#Evento");
				tableEvents.getColumnModel().getColumn(1).setHeaderValue("Evento");
				tableEvents.getTableHeader().resizeAndRepaint();
				
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
				btnBikoiztu.setText(ResourceBundle.getBundle("Etiquetas").getString("ButtonDuplicate"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("DuplicateTitle"));
				jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
				jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Event"));
				
				tableEvents.getColumnModel().getColumn(0).setHeaderValue("#Event");
				tableEvents.getColumnModel().getColumn(1).setHeaderValue("Event");
				tableEvents.getTableHeader().resizeAndRepaint();
				
			}
		});
		EnglishRadioButton.setBounds(261, 236, 80, 21);
		panel_1.add(EnglishRadioButton);
		

		
		jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Event"));
		jLabelEvents.setBounds(304, 31, 259, 16);
		getContentPane().add(jLabelEvents);
		

		
		}
}
