package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Question;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class EmaitzaJarriGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IdatziEmaitza"));
	private JButton jButtonEmaitzaGehitu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaJarri"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JFrame nirePantaila;
	
	
	
	private JRadioButton euskeraRdioButton = new JRadioButton("Euskera");
	private JRadioButton CastellanoRadioButton = new JRadioButton("Castellano");
	private JRadioButton EnglishRadioButton = new JRadioButton("English");
	
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private BLFacade negozioLogika;
	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private JTextField textField;

	public EmaitzaJarriGUI()
	{
		try
		{
			nirePantaila=this;
			this.negozioLogika=MainGUI.getBusinessLogic();
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
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaJarri"));

		jLabelEventDate.setBounds(new Rectangle(83, 25, 140, 25));
		jLabelQueries.setBounds(40, 224, 406, 14);
		jLabelEvents.setBounds(295, 29, 259, 16);


		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(342, 236, 212, 27);
		getContentPane().add(lblNewLabel);
		
		
		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonEmaitzaGehitu.setBounds(new Rectangle(341, 334, 213, 30));

		jButtonEmaitzaGehitu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonEmaitzaGehitu, null);


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
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(40);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 248, 225, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3); // another column added to allocate question objects

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q); // question object added in order to obtain it with tableModelEvents.getValueAt(i,2)
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(50);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2)); // not shown in JTable
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

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame e10 = new EditoreaGUI();
				nirePantaila.setVisible(false);
				e10.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(10, 10, 63, 21);
		
		getContentPane().add(btnNewButton);

		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(98, 425, 481, 38);
		getContentPane().add(panel_1);
		
		

		EnglishRadioButton.setSelected(true);
		
		
		
		euskeraRdioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("eus"));
				System.out.println("Locale: "+Locale.getDefault());
				CastellanoRadioButton.setSelected(false);
				EnglishRadioButton.setSelected(false);
				jButtonEmaitzaGehitu.setText(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaJarri"));	
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaJarri"));
				jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
				jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events"));
				lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("IdatziEmaitza"));
				
				tableEvents.getColumnModel().getColumn(0).setHeaderValue("#Gertaera");
				tableEvents.getColumnModel().getColumn(1).setHeaderValue("Gertaera");
				tableEvents.getTableHeader().resizeAndRepaint();
				
				tableQueries.getColumnModel().getColumn(0).setHeaderValue("#Galdera");
				tableQueries.getColumnModel().getColumn(1).setHeaderValue("Galdera");
				tableQueries.getTableHeader().resizeAndRepaint();
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
				jButtonEmaitzaGehitu.setText(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaJarri"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaJarri"));
				jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
				jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events"));
				lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("IdatziEmaitza"));
				
				tableEvents.getColumnModel().getColumn(0).setHeaderValue("#Evento");
				tableEvents.getColumnModel().getColumn(1).setHeaderValue("Evento");
				tableEvents.getTableHeader().resizeAndRepaint();
				
				tableQueries.getColumnModel().getColumn(0).setHeaderValue("#Pregunta");
				tableQueries.getColumnModel().getColumn(1).setHeaderValue("Pregunta");
				tableQueries.getTableHeader().resizeAndRepaint();
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
				jButtonEmaitzaGehitu.setText(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaJarri"));
				nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaJarri"));
				jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
				jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events"));
				lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("IdatziEmaitza"));
				
				tableEvents.getColumnModel().getColumn(0).setHeaderValue("#Event");
				tableEvents.getColumnModel().getColumn(1).setHeaderValue("Event");
				tableEvents.getTableHeader().resizeAndRepaint();
				
				tableQueries.getColumnModel().getColumn(0).setHeaderValue("#Question");
				tableQueries.getColumnModel().getColumn(1).setHeaderValue("Question");
				tableQueries.getTableHeader().resizeAndRepaint();
			}
		});
		EnglishRadioButton.setBounds(261, 236, 80, 21);
		panel_1.add(EnglishRadioButton);
		
		textField = new JTextField();
		textField.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		textField.setBounds(341, 273, 213, 30);
		getContentPane().add(textField);
		textField.setColumns(10);

		
		
		
		
		}
	
	

	private void jButton2_actionPerformed(ActionEvent e) {
		try {
			int i=tableQueries.getSelectedRow();
			domain.Question question=(domain.Question)tableModelQueries.getValueAt(i,2); // obtain question object
			String emaitza=textField.getText();
			negozioLogika.emaitzaGehitu(question, emaitza);
			textField.setText("");
			
		} catch (ArrayIndexOutOfBoundsException err) {
			jButtonEmaitzaGehitu.setEnabled(false);
			jButtonEmaitzaGehitu.setEnabled(false);
		}
	}
}
