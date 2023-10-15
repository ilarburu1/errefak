package gui;

import javax.persistence.EntityManager;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;
import domain.Admin;
import domain.Mezua;
import domain.MezuaInfo;
import businessLogic.BLFacade;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MezuakAdminGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JFrame nirePantaila;
	private JLabel jLabelJasotakoMezuak;
	private JLabel jLabelErantzunMezuak;
  
	private JTextPane textPane;
	
    private BLFacade negozioLogika;
	private JRadioButton rdbtnEuskera;
	private JRadioButton rdbtnCastellano;
	private JRadioButton rdbtnEnglish;
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	JList<String> listMezuak = new JList<String>();
	private DefaultListModel<String> listaModelMezuak = new DefaultListModel<String>();

	protected static EntityManager  db;
	private JTextField textField;
	
	private JScrollPane scrollPaneMezuak;
	private String bidaltzaileIzena;
	
	private JButton btnBidali;
	
	private JButton btnBanned;
	
	/**
	 * This is the default constructor
	 */
	public MezuakAdminGUI() {
		super();
		nirePantaila=this;
		negozioLogika = MainGUI.getBusinessLogic();
		initialize();
	
		textPane = new JTextPane();
		textPane.setBounds(53, 301, 233, 21);
		textPane.setEditable(false);
		jContentPane.add(textPane);
		
		Admin a=negozioLogika.adminaItzuli();
		ArrayList<Mezua> jasotakoMezuak=a.getJasotakoMezuak();
		if(jasotakoMezuak.isEmpty())
			textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("noMessages"));
		else {
			for (Mezua m: a.getJasotakoMezuak()) {
				listaModelMezuak.addElement(m.toString());
			}
			listMezuak= new JList<String>(listaModelMezuak);
		}
		getScrollPaneMezuak().setViewportView(listMezuak);
		listMezuak.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mezua=listMezuak.getSelectedValue();
				System.out.println(mezua);
				String[] mezuaZatitu=mezua.split(",");
				String[] bidaltzaileaZatitu=mezuaZatitu[1].split("=");
				System.out.println(bidaltzaileaZatitu[1]);
				bidaltzaileIzena=bidaltzaileaZatitu[1];
				System.out.println(bidaltzaileIzena);
			}
		});
		jContentPane.add(scrollPaneMezuak);
		
		JButton atzera = new JButton();
		atzera.setBounds(10, 17, 85, 21);
		atzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame e1 = new AdminGUI();
				nirePantaila.setVisible(false);
				e1.setVisible(true);
			}
		});
		atzera.setFont(new Font("Tahoma", Font.PLAIN, 16));
		atzera.setText(ResourceBundle.getBundle("Etiquetas").getString("ConsultMovementsGUI.atzera.text"));
		jContentPane.add(atzera);
				
		JPanel buttonGroup = new JPanel();
		buttonGroup.setBounds(0, 332, 686, 38);
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
		this.setSize(699, 405);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MessagesTittle"));
	}
	
	private JRadioButton getRdbtnEuskera() {
		if (rdbtnEuskera == null) {
			rdbtnEuskera = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("MezuakAdminGUI.rdbtnEuskera.text")); //$NON-NLS-1$ //$NON-NLS-2$
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
			rdbtnCastellano = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("MezuakAdminGUI.rdbtnCastellano.text")); //$NON-NLS-1$ //$NON-NLS-2$
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
			rdbtnEnglish = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("MezuakAdminGUI.rdbtnEnglish.text")); //$NON-NLS-1$ //$NON-NLS-2$
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
			jLabelJasotakoMezuak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeeMessages"));
			jLabelJasotakoMezuak.setBounds(53, 37, 197, 33);
			jLabelJasotakoMezuak.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelJasotakoMezuak.setForeground(Color.BLACK);
			jLabelJasotakoMezuak.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelJasotakoMezuak;
	}
	
	private JLabel getLblErantzunMezuak() {
		if (jLabelErantzunMezuak == null) {
			jLabelErantzunMezuak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("answerMessages"));
			jLabelErantzunMezuak.setBounds(421, 37, 197, 33);
			jLabelErantzunMezuak.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelErantzunMezuak.setForeground(Color.BLACK);
			jLabelErantzunMezuak.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelErantzunMezuak;
	}
	
	
	
	private JScrollPane getScrollPaneMezuak() {
		if (scrollPaneMezuak == null) {
			scrollPaneMezuak = new JScrollPane();
			scrollPaneMezuak.setBounds(25, 70, 281, 209);
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

			jContentPane.add(jLabelErantzunMezuak);
			
			btnBanned = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Ban")); //$NON-NLS-1$ //$NON-NLS-2$
			btnBanned.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame e1 = new BanGUI(bidaltzaileIzena);
					nirePantaila.setVisible(false);
					e1.setLocationRelativeTo(null); 
					e1.setVisible(true);
				}
			});
			btnBanned.setBounds(369, 301, 169, 21);
			jContentPane.add(btnBanned);

		}
		return jContentPane;
		
		
	}
	
	private JButton getButtonBidali() {
		if (btnBidali == null) {
			btnBidali = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Send"));
			btnBidali.setBounds(547, 301, 115, 21);
			btnBidali.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String mezua=textField.getText();
					Date data= new Date();
					Admin a=negozioLogika.adminaItzuli();
					MezuaInfo m = new MezuaInfo(data, a, bidaltzaileIzena, mezua);
					negozioLogika.mezuaErantzunErregistratuari(m);
					redibujar();
				}
			});
		}
		return btnBidali;
	}
	
	private void redibujar() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MessagesTittle"));
		jLabelJasotakoMezuak.setText(ResourceBundle.getBundle("Etiquetas").getString("SeeMessages"));
		jLabelErantzunMezuak.setText(ResourceBundle.getBundle("Etiquetas").getString("answerMessages"));
		btnBidali.setText(ResourceBundle.getBundle("Etiquetas").getString("Send"));
		btnBanned.setText(ResourceBundle.getBundle("Etiquetas").getString("Ban"));
		Admin a=negozioLogika.adminaItzuli();
		if(a.getJasotakoMezuak().isEmpty()) {
			textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("noMessages"));
		}
	}
} // @jve:decl-index=0:visual-constraint="0,0"

