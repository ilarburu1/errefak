package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;
import domain.Erregistratua;
import businessLogic.BLFacade;
import java.awt.Color;
import java.awt.Font;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;


public class PerfilaGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JFrame nirePantaila;
	protected JLabel jLabelPerfila;
    private static BLFacade appFacadeInterface;
  
    private BLFacade negozioLogika;
	private JRadioButton rdbtnEuskera;
	private JRadioButton rdbtnCastellano;
	private JRadioButton rdbtnEnglish;
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	private String log;
	private Erregistratua erabiltzailea;
	private Erregistratua bilatutakoa;
	private String bilatua;
	private JLabel lblErabiltzailearenIzena;
	private JTextPane textPaneErabiltzaileIzena;
	private JTextPane textPaneJarraitzaileaGehitu;
	private JButton btnFollow;
	private JButton btnUnfollow;
	
	
	/**
	 * This is the default constructor
	 */
	public PerfilaGUI(String log,String bilatua) {
		super();
		nirePantaila=this;
		negozioLogika = MainGUI.getBusinessLogic();
		this.log=log;
		erabiltzailea=negozioLogika.erregistratuaItzuli(log);
		bilatutakoa=negozioLogika.erregistratuaItzuli(bilatua);
		this.bilatua=bilatua;
		initialize();

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
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("PerfilaTittle"));
		jLabelPerfila.setText(ResourceBundle.getBundle("Etiquetas").getString("Perfila"));
		lblErabiltzailearenIzena.setText(ResourceBundle.getBundle("Etiquetas").getString("erabIzena"));
		btnFollow.setText(ResourceBundle.getBundle("Etiquetas").getString("Follow"));
		btnUnfollow.setText(ResourceBundle.getBundle("Etiquetas").getString("UnFollow"));
		jLabelPerfila.setText(ResourceBundle.getBundle("Etiquetas").getString("Perfila"));
	}
	
	private JRadioButton getRdbtnEuskera() {
		if (rdbtnEuskera == null) {
			rdbtnEuskera = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("PerfilaGUI.rdbtnEuskera.text")); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtnEuskera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					textPaneJarraitzaileaGehitu.setText("");
					redibujar();				}
			});
			buttonGroup.add(rdbtnEuskera);
		}
		return rdbtnEuskera;
	}
	private JRadioButton getRdbtnCastellano() {
		if (rdbtnCastellano == null) {
			rdbtnCastellano = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("PerfilaGUI.rdbtnCastellano.text")); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtnCastellano.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					textPaneJarraitzaileaGehitu.setText("");
					redibujar();				}
			});
			buttonGroup.add(rdbtnCastellano);
		}
		return rdbtnCastellano;
	}
	private JRadioButton getRdbtnEnglish() {
		if (rdbtnEnglish == null) {
			rdbtnEnglish = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("PerfilaGUI.rdbtnEnglish.text")); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtnEnglish.setSelected(true);
			rdbtnEnglish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					textPaneJarraitzaileaGehitu.setText("");
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
			jContentPane.add(getLblErabiltzailearenIzena());
			jContentPane.add(getTextPaneErabiltzaileIzena());
			jContentPane.add(getTextPaneJarraitzaileaGehitu());
			jContentPane.add(getBtnJarraitu());
			jContentPane.add(getBtnJarraitzeazUtzi());
			
			
		}
		return jContentPane;
		
		
	}
	
	

	private JLabel getLblNewLabel() {
		if (jLabelPerfila == null) {
			jLabelPerfila = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ConsultMovements"));
			jLabelPerfila.setBounds(138, 10, 197, 33);
			jLabelPerfila.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelPerfila.setForeground(Color.BLACK);
			jLabelPerfila.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelPerfila;
	}
	

	private JLabel getLblErabiltzailearenIzena() {
		if (lblErabiltzailearenIzena == null) {
			lblErabiltzailearenIzena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("duzunDiruaText"));
			lblErabiltzailearenIzena.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblErabiltzailearenIzena.setBounds(new Rectangle(83, 25, 140, 25));
			lblErabiltzailearenIzena.setBounds(36, 53, 140, 25);
		}
		return lblErabiltzailearenIzena;
	}
	private JTextPane getTextPaneErabiltzaileIzena() {
		if (textPaneErabiltzaileIzena == null) {
			textPaneErabiltzaileIzena = new JTextPane();
			textPaneErabiltzaileIzena.setEditable(false);
			textPaneErabiltzaileIzena.setBackground(Color.WHITE);
			textPaneErabiltzaileIzena.setBounds(66, 88, 319, 19);
			Erregistratua era=negozioLogika.erregistratuaItzuli(bilatua);
			textPaneErabiltzaileIzena.setText(era.getUser());
		}
		return textPaneErabiltzaileIzena;
	}
	
	private JTextPane getTextPaneJarraitzaileaGehitu() {
		if(textPaneJarraitzaileaGehitu == null) {
			textPaneJarraitzaileaGehitu = new JTextPane();
			textPaneJarraitzaileaGehitu.setFont(new Font("Tahoma", Font.PLAIN, 8));
			textPaneJarraitzaileaGehitu.setText((String) null);
			textPaneJarraitzaileaGehitu.setEditable(false);
			textPaneJarraitzaileaGehitu.setBackground(Color.WHITE);
			textPaneJarraitzaileaGehitu.setBounds(120, 175, 253, 12);
			jContentPane.add(textPaneJarraitzaileaGehitu);
			
		}
		return textPaneJarraitzaileaGehitu;
	}
	private JButton getBtnJarraitu() {
		btnFollow= new JButton(ResourceBundle.getBundle("Etiquetas").getString("Follow"));
		btnFollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Erregistratua erreg=negozioLogika.erregistratuaItzuli(bilatua);
				boolean aurkitua=false;
				for(String erre:erreg.getJarraitzaileak()) {
					System.out.println(erre);
					if(erre.equals(erabiltzailea.getUser()))
						aurkitua=true;
				}
				if(aurkitua) {
					textPaneJarraitzaileaGehitu.setText(ResourceBundle.getBundle("Etiquetas").getString("Jarraitu1"));
				}else {
					textPaneJarraitzaileaGehitu.setText(ResourceBundle.getBundle("Etiquetas").getString("Jarraitu2"));
					negozioLogika.gehituJarraitzailea(bilatutakoa, erabiltzailea);
				}
			}
		});
		btnFollow.setBounds(90, 131, 121, 21);
		
		return btnFollow;
	}
	
	private JButton getBtnJarraitzeazUtzi() {
		btnUnfollow= new JButton(ResourceBundle.getBundle("Etiquetas").getString("UnFollow"));
		btnUnfollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Erregistratua erreg=negozioLogika.erregistratuaItzuli(bilatua);
				boolean aurkitua=false;
				for(String erre:erreg.getJarraitzaileak()) {
					System.out.println(erre);
					if(erre.equals(erabiltzailea.getUser()))
						aurkitua=true;
				}
				if(aurkitua) {
					textPaneJarraitzaileaGehitu.setText(ResourceBundle.getBundle("Etiquetas").getString("EzJarraitu1"));
					negozioLogika.ezabatuJarraitzailea(bilatutakoa, erabiltzailea);
				}else {
					textPaneJarraitzaileaGehitu.setText(ResourceBundle.getBundle("Etiquetas").getString("EzJarraitu2"));
				}
			}
		});
		btnUnfollow.setBounds(277, 131, 121, 21);
		return btnUnfollow;
	}
	
	private void redibujar() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("PerfilaTittle"));
		jLabelPerfila.setText(ResourceBundle.getBundle("Etiquetas").getString("Perfila"));
		lblErabiltzailearenIzena.setText(ResourceBundle.getBundle("Etiquetas").getString("erabIzena"));
		btnFollow.setText(ResourceBundle.getBundle("Etiquetas").getString("Follow"));
		btnUnfollow.setText(ResourceBundle.getBundle("Etiquetas").getString("UnFollow"));
		jLabelPerfila.setText(ResourceBundle.getBundle("Etiquetas").getString("Perfila"));

	}
} 

