package gui;

import java.awt.Rectangle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class KuotaEzarriGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BLFacade negozioLogika;
	//private ArrayList<Aukera> aukeraList;

	private JFrame nirePantaila;

	private DefaultTableModel tableModelOptions;
	private String[] columnNamesOptions = new String[] { "Option", "Quote" };
	private JTable tablaAukerak = new JTable();
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JTextField kuotaAukera;
	private JLabel kuotaKonfirmatu = new JLabel("");
	private JButton btnKuotakJarri = new JButton("Kuota ezarri");
	private JTextField kuotaZenbatekoa;

	/**
	 * Create the frame.
	 */
	public KuotaEzarriGUI(Question q) {
		setTitle("CREATE QUOTE");
		nirePantaila=this;
		negozioLogika=  MainGUI.getBusinessLogic();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGaldera = new JLabel("Question: ");
		lblGaldera.setBounds(22, 10, 71, 14);
		contentPane.add(lblGaldera);

		scrollPaneEvents.setBounds(new Rectangle(45, 34, 346, 119));
		
		kuotaKonfirmatu.setForeground(Color.BLUE);
		kuotaKonfirmatu.setBounds(45, 199, 346, 14);
		contentPane.add(kuotaKonfirmatu);

		tableModelOptions = new DefaultTableModel(null, columnNamesOptions);
		tableModelOptions.setDataVector(null, columnNamesOptions);
		tableModelOptions.setColumnCount(3);
		tablaAukerak.setModel(tableModelOptions);
		Vector<Aukera> aukerak = negozioLogika.getAukerak(q);
		if(aukerak.size()==0) {
			kuotaKonfirmatu.setText("Galdera honek ez du aukerarik");
		}
		for (domain.Aukera o : aukerak) {
			Vector<Object> row = new Vector<Object>();
			row.add(o.getAukeraIzena());
			row.add(o.getKuota());
			row.add(o);
			tableModelOptions.addRow(row);
		}
		scrollPaneEvents.setViewportView(tablaAukerak);

		tablaAukerak.getColumnModel().getColumn(0).setPreferredWidth(268);
		tablaAukerak.getColumnModel().getColumn(1).setPreferredWidth(25);
		tablaAukerak.getColumnModel().removeColumn(tablaAukerak.getColumnModel().getColumn(2));
		tablaAukerak.setDefaultEditor(Object.class, null);
		
		this.getContentPane().add(scrollPaneEvents, null);

		JLabel label = new JLabel(q.getQuestion());
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setForeground(Color.BLUE);
		label.setBounds(103, 9, 278, 14);
		contentPane.add(label);

		
		JLabel lblZenbatekoa_2 = new JLabel("Quantity");
		lblZenbatekoa_2.setBounds(333, 163, 86, 14);
		contentPane.add(lblZenbatekoa_2);
		
		kuotaZenbatekoa = new JTextField();
		kuotaZenbatekoa.setColumns(10);
		kuotaZenbatekoa.setBounds(333, 175, 58, 19);
		contentPane.add(kuotaZenbatekoa);
		
		

		kuotaAukera = new JTextField();
		kuotaAukera.setBounds(45, 175, 278, 19);
		contentPane.add(kuotaAukera);
		kuotaAukera.setColumns(10);

		JLabel lblAukera = new JLabel("New Quote");
		lblAukera.setBounds(45, 163, 86, 14);
		contentPane.add(lblAukera);
		
		JButton EzarriKuotaButton = new JButton("CREATE QUOTE");
		EzarriKuotaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Float sartutakoKuotaZenbat = Float.parseFloat(kuotaZenbatekoa.getText());
				String aukeraIzena = kuotaAukera.getText();
		
				negozioLogika.setKuota(q, aukeraIzena, sartutakoKuotaZenbat);
				tableModelOptions.addRow(new Vector<Object>());
				tablaAukerak.setModel(tableModelOptions);
				tablaAukerak.setValueAt(sartutakoKuotaZenbat, tablaAukerak.getRowCount()-1, 1);
				tablaAukerak.setValueAt(aukeraIzena, tablaAukerak.getRowCount()-1, 0);
				kuotaAukera.setText("");
				kuotaZenbatekoa.setText("");
				kuotaKonfirmatu.setText("Kuota berria ongi gehitu da!!");	
				}catch(NumberFormatException error) {
					kuotaKonfirmatu.setText("Zenbatekoa zenbaki bat izan behar da");	

				}
			}
		});
		EzarriKuotaButton.setBounds(142, 212, 142, 29);
		contentPane.add(EzarriKuotaButton);
	

		

		tablaAukerak.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnKuotakJarri.setEnabled(true);
				kuotaAukera.setEditable(true);
			}
		});
	


	JPanel panel_1 = new JPanel();
	panel_1.setBounds(-21, 235, 481, 28);
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
			EzarriKuotaButton.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuote"));
			nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuote"));
			tablaAukerak.getColumnModel().getColumn(0).setHeaderValue("Aukera");
			tablaAukerak.getColumnModel().getColumn(1).setHeaderValue("Kuota");
			tablaAukerak.getTableHeader().resizeAndRepaint();
			lblGaldera.setText("Galdera: ");
			lblZenbatekoa_2.setText("Zenbatekoa");
			lblAukera.setText("Kuota Berria");
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
			EzarriKuotaButton.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuote"));
			nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuote"));
			tablaAukerak.getColumnModel().getColumn(0).setHeaderValue("Opcion");
			tablaAukerak.getColumnModel().getColumn(1).setHeaderValue("Cuota");
			tablaAukerak.getTableHeader().resizeAndRepaint();
			lblGaldera.setText("Pregunta: ");
			lblZenbatekoa_2.setText("Cantidad");
			lblAukera.setText("Nueva Cuota");
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
			EzarriKuotaButton.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuote"));
			nirePantaila.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuote"));
			tablaAukerak.getColumnModel().getColumn(0).setHeaderValue("Option");
			tablaAukerak.getColumnModel().getColumn(1).setHeaderValue("Quote");
			tablaAukerak.getTableHeader().resizeAndRepaint();
			lblGaldera.setText("Question: ");
			lblZenbatekoa_2.setText("Quantity");
			lblAukera.setText("New Quote");
		}
	});
	EnglishRadioButton.setBounds(261, 236, 80, 21);
	panel_1.add(EnglishRadioButton);
			
}


	
	
	
}
