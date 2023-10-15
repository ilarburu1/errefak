package gui;

import javax.persistence.EntityManager;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;
import domain.Erregistratua;
import domain.Mugimendua;
import businessLogic.BLFacade;
import java.awt.Color;
import java.awt.Font;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;


public class ConsultMovementsGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JFrame nirePantaila;
	protected JLabel jLabelConsultMovements;
    private static BLFacade appFacadeInterface;
  
    private BLFacade negozioLogika;
	private JRadioButton rdbtnEuskera;
	private JRadioButton rdbtnCastellano;
	private JRadioButton rdbtnEnglish;
	private ButtonGroup buttonGroup = new ButtonGroup();
	JScrollPane scrollPane = new JScrollPane();
	JList<String> listMugimenduak = new JList<String>();
	private DefaultListModel<String> listaModelMugimenduak = new DefaultListModel<String>();
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	private String log;
	
	protected static EntityManager  db;
	private JLabel lblOrainDuzunDirua;
	private JTextPane textPane_DiruKopurua;
	
	
	/**
	 * This is the default constructor
	 */
	public ConsultMovementsGUI(String log) {
		super();
		nirePantaila=this;
		negozioLogika = MainGUI.getBusinessLogic();
		this.log=log;
		Erregistratua erreg=negozioLogika.erregistratuaItzuli(log);
		for (Mugimendua m: erreg.getMugimenduak()) {
			listaModelMugimenduak.addElement(m.toString());
		}
		listMugimenduak= new JList<String>(listaModelMugimenduak);
		
		initialize();
		
		scrollPane.setBounds(20, 48, 439, 127);
		scrollPane.setViewportView(listMugimenduak);
		jContentPane.add(scrollPane);

		JButton atzera = new JButton();
		atzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame e1 = new ErregistratuaGUI(log);
				nirePantaila.setVisible(false);
				e1.setVisible(true);
			}
		});
		atzera.setFont(new Font("Tahoma", Font.PLAIN, 16));
		atzera.setText(ResourceBundle.getBundle("Etiquetas").getString("ConsultMovementsGUI.atzera.text")); //$NON-NLS-1$ //$NON-NLS-2$
		atzera.setBounds(10, 17, 85, 21);
		jContentPane.add(atzera);
				
		JPanel buttonGroup = new JPanel();
		buttonGroup.setBounds(0, 215, 481, 38);
		jContentPane.add(buttonGroup);
		buttonGroup.add(getRdbtnEuskera());
		buttonGroup.add(getRdbtnCastellano());
		buttonGroup.add(getRdbtnEnglish());
	
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MovementsTittle"));
	}
	
	String lokala="Locale: ";
	private JRadioButton getRdbtnEuskera() {
		if (rdbtnEuskera == null) {
			rdbtnEuskera = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("ConsultMovementsGUI.rdbtnEuskera.text")); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtnEuskera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("eus"));
					System.out.println(lokala+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnEuskera);
		}
		return rdbtnEuskera;
	}
	private JRadioButton getRdbtnCastellano() {
		if (rdbtnCastellano == null) {
			rdbtnCastellano = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("ConsultMovementsGUI.rdbtnCastellano.text")); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtnCastellano.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("es"));
					System.out.println(lokala+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnCastellano);
		}
		return rdbtnCastellano;
	}
	private JRadioButton getRdbtnEnglish() {
		if (rdbtnEnglish == null) {
			rdbtnEnglish = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("ConsultMovementsGUI.rdbtnEnglish.text")); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtnEnglish.setSelected(true);
			rdbtnEnglish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println(lokala+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnEnglish);
		}
		return rdbtnEnglish;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getLblOrainDuzunDirua());
			jContentPane.add(getTextPane_DiruKopurua());
		}
		return jContentPane;
		
		
	}
	
	

	private JLabel getLblNewLabel() {
		if (jLabelConsultMovements == null) {
			jLabelConsultMovements = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ConsultMovements"));
			jLabelConsultMovements.setBounds(138, 10, 197, 33);
			jLabelConsultMovements.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelConsultMovements.setForeground(Color.BLACK);
			jLabelConsultMovements.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelConsultMovements;
	}
	
	private void redibujar() {
		jLabelConsultMovements.setText(ResourceBundle.getBundle("Etiquetas").getString("ConsultMovements"));
		lblOrainDuzunDirua.setText(ResourceBundle.getBundle("Etiquetas").getString("duzunDiruaText"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MovementsTittle"));
	}
	private JLabel getLblOrainDuzunDirua() {
		if (lblOrainDuzunDirua == null) {
			lblOrainDuzunDirua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("duzunDiruaText"));
			lblOrainDuzunDirua.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblOrainDuzunDirua.setBounds(new Rectangle(83, 25, 140, 25));
			lblOrainDuzunDirua.setBounds(341, 180, 140, 25);
		}
		return lblOrainDuzunDirua;
	}
	private JTextPane getTextPane_DiruKopurua() {
		if (textPane_DiruKopurua == null) {
			textPane_DiruKopurua = new JTextPane();
			textPane_DiruKopurua.setText(String.valueOf(negozioLogika.erregistratuaItzuli(log).getKontuDirua()) + "�");
			textPane_DiruKopurua.setEditable(false);
			textPane_DiruKopurua.setBackground(Color.WHITE);
			textPane_DiruKopurua.setBounds(341, 203, 102, 19);
		}
		return textPane_DiruKopurua;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

