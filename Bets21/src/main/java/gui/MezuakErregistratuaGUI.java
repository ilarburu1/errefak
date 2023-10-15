package gui;

import javax.persistence.EntityManager;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;
import domain.Erregistratua;
import domain.Mezua;
import businessLogic.BLFacade;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MezuakErregistratuaGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JFrame nirePantaila;
	private JLabel jLabelJasotakoMezuak;
	private JLabel jLabelErantzunMezuak;
  
    private BLFacade negozioLogika;
	private JRadioButton rdbtnEuskera;
	private JRadioButton rdbtnCastellano;
	private JRadioButton rdbtnEnglish;
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	JList<String> listMezuak = new JList<String>();
	private DefaultListModel<String> listaModelMezuak = new DefaultListModel<String>();

	private String log;
	
	protected static EntityManager  db;
	private JTextField textField;

	private JScrollPane scrollPaneMezuak;
	
	private JButton btnBidali;
	
	private JTextPane textPane;
	/**
	 * This is the default constructor
	 */
	public MezuakErregistratuaGUI(String log) {
		super();
		nirePantaila=this;
		negozioLogika = MainGUI.getBusinessLogic();
		this.log=log;
	
	
		textPane = new JTextPane();
		textPane.setBounds(63, 289, 233, 21);
		textPane.setEditable(false);
		initialize();
		jContentPane.add(textPane);
		
		Erregistratua erreg=negozioLogika.erregistratuaItzuli(log);
		ArrayList<Mezua> jasotakoMezuak=erreg.getJasotakoMezuak();
		if(jasotakoMezuak.isEmpty())
			textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("noMessages"));
		else {
			for (Mezua m: erreg.getJasotakoMezuak()) {
				listaModelMezuak.addElement(m.toString());
			}
			listMezuak=new JList<String>(listaModelMezuak);
		}
		jContentPane.add(scrollPaneMezuak);
		
		
		JButton atzera = new JButton();
		atzera.setBounds(10, 17, 85, 21);
		atzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame e1 = new ErregistratuaGUI(log);
				nirePantaila.setVisible(false);
				e1.setVisible(true);
			}
		});
		atzera.setFont(new Font("Tahoma", Font.PLAIN, 16));
		atzera.setText(ResourceBundle.getBundle("Etiquetas").getString("ConsultMovementsGUI.atzera.text"));
		jContentPane.add(atzera);
				
		JPanel buttonGroup = new JPanel();
		buttonGroup.setBounds(0, 329, 686, 38);
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
		this.setSize(700, 404);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("LaguntzaTittle"));
		btnBidali.setText("SEND");
	}
	
	private JRadioButton getRdbtnEuskera() {
		if (rdbtnEuskera == null) {
			rdbtnEuskera = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("MezuakErregistratuaGUI.rdbtnEuskera.text")); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtnEuskera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					textPane.setText("");
					redibujar();				}
			});
			buttonGroup.add(rdbtnEuskera);
		}
		return rdbtnEuskera;
	}
	private JRadioButton getRdbtnCastellano() {
		if (rdbtnCastellano == null) {
			rdbtnCastellano = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("MezuakErregistratuaGUI.rdbtnCastellano.text")); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtnCastellano.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					textPane.setText("");
					redibujar();				}
			});
			buttonGroup.add(rdbtnCastellano);
		}
		return rdbtnCastellano;
	}
	private JRadioButton getRdbtnEnglish() {
		if (rdbtnEnglish == null) {
			rdbtnEnglish = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("MezuakErregistratuaGUI.rdbtnEnglish.text")); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtnEnglish.setSelected(true);
			rdbtnEnglish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					textPane.setText("");
					redibujar();
				}
			});
			buttonGroup.add(rdbtnEnglish);
		}
		return rdbtnEnglish;
	}

	
	
	

	private JLabel getLblJasotakoMezuak() {
		if (jLabelJasotakoMezuak == null) {
			jLabelJasotakoMezuak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ConsultMovements"));
			jLabelJasotakoMezuak.setBounds(77, 37, 197, 33);
			jLabelJasotakoMezuak.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelJasotakoMezuak.setForeground(Color.BLACK);
			jLabelJasotakoMezuak.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelJasotakoMezuak;
	}
	
	private JLabel getLblErantzunMezuak() {
		if (jLabelErantzunMezuak == null) {
			jLabelErantzunMezuak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LaguntzaEskatu"));
			jLabelErantzunMezuak.setBounds(417, 37, 197, 33);
			jLabelErantzunMezuak.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelErantzunMezuak.setForeground(Color.BLACK);
			jLabelErantzunMezuak.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelErantzunMezuak;
	}
	

	
	private JScrollPane getScrollPaneMezuak() {
		if (scrollPaneMezuak == null) {
			scrollPaneMezuak = new JScrollPane();
			scrollPaneMezuak.setBounds(25, 70, 300, 209);
			listMezuak= new JList<String>(listaModelMezuak);
			scrollPaneMezuak.setViewportView(listMezuak);
		}
		return scrollPaneMezuak;
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
			jContentPane.add(getLblJasotakoMezuak());
			jContentPane.add(getLblErantzunMezuak());
			
			textField = new JTextField();
			textField.setBounds(362, 70, 300, 209);
			jContentPane.add(textField);
			textField.setColumns(10);
			jContentPane.add(getButtonBidali());
			jContentPane.add(getScrollPaneMezuak());
			

		}
		return jContentPane;
	}
		
		private JButton getButtonBidali() {
			if (btnBidali == null) {
				btnBidali = new JButton();
				btnBidali.setBounds(465, 289, 85, 21);
				btnBidali.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String mezua=textField.getText();
						Date data= new Date();
						Erregistratua erreg=negozioLogika.erregistratuaItzuli(log);
						negozioLogika.mezuaBidaliAdminari(data, erreg, mezua);
						redibujar();
					}
				});
			}
			return btnBidali;
		}
		
		private void redibujar() {
			this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("LaguntzaTittle"));
			jLabelJasotakoMezuak.setText(ResourceBundle.getBundle("Etiquetas").getString("SeeMessages"));
			jLabelErantzunMezuak.setText(ResourceBundle.getBundle("Etiquetas").getString("LaguntzaEskatu"));
			btnBidali.setText(ResourceBundle.getBundle("Etiquetas").getString("Send"));
			Erregistratua a=negozioLogika.erregistratuaItzuli(log);
			if(a.getJasotakoMezuak().isEmpty()) {
				textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("noMessages"));
			}
		}

} // @jve:decl-index=0:visual-constraint="0,0"

