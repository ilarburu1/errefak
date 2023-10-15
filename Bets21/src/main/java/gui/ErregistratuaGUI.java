package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;
import businessLogic.BLFacade;
import java.awt.Color;
import java.awt.Font;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.GroupLayout.Alignment;


public class ErregistratuaGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonDiruaSartu = null;
	private JButton jButtonQueryQueries = null;
	private JButton jButtonApostuaEgin = null;
	private JButton btnMugimenduakIkusi=null;
	private JFrame nirePantaila;
	
    private static BLFacade appFacadeInterface;
	private BLFacade negozioLogika;
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnEnglish;
	private JRadioButton rdbtnEuskera;
	private JRadioButton rdbtnCastellano;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String log;
	private JButton jButtonLogOut;
	private JTextField bilatuField;
	private JButton btnLaguntza;
	
	/**
	 * This is the default constructor
	 */
	public ErregistratuaGUI(String log) {
		super();
		nirePantaila=this;
		this.negozioLogika=MainGUI.getBusinessLogic();
		this.log=log;
		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("RegisterTittle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			
			bilatuField = new JTextField();
			bilatuField.setFont(new Font("Tahoma", Font.PLAIN, 8));
			bilatuField.setColumns(10);

			
			TextArea textArea = new TextArea();
			textArea.setFont(new Font("Dialog", Font.PLAIN, 8));
			textArea.setEditable(false);
			
			JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErregistratuaGUI.lblNewLabel.text"));
			
			JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErregistratuaGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String era=bilatuField.getText();
					if(negozioLogika.UserExistitzenDa(era)){
						JFrame a = new PerfilaGUI(log,era);
						nirePantaila.setVisible(false);
						a.setLocationRelativeTo(null);
						a.setVisible(true);
					}
					else {
						textArea.setText("Erabiltzaile hori ez da existitzen");
					}

				}
			});

			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
			GroupLayout gl_jContentPane = new GroupLayout(jContentPane);
			gl_jContentPane.setHorizontalGroup(
				gl_jContentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(10)
						.addComponent(getJButtonLogOut(), GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
						.addGap(260)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
						.addGap(21))
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(355)
						.addComponent(bilatuField, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
						.addGap(10)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
						.addGap(11))
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(318)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 163, Short.MAX_VALUE))
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(135)
						.addComponent(getLblNewLabel(), GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
						.addGap(138))
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addComponent(getBotonQueryQueries(), GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addGap(33)
						.addComponent(getButtonDiruaSartu(), GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addGap(31)
						.addComponent(getApostuaEgin(), GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(82)
						.addComponent(getBotonMugimenduakIkusi(), GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addGap(35)
						.addComponent(getBtnLaguntza(), GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addGap(86))
					.addComponent(getPanel(), GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
			);
			gl_jContentPane.setVerticalGroup(
				gl_jContentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(7)
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(getJButtonLogOut(), GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
							.addGroup(gl_jContentPane.createSequentialGroup()
								.addGap(3)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(5)))
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_jContentPane.createSequentialGroup()
								.addComponent(bilatuField, GroupLayout.PREFERRED_SIZE, 12, Short.MAX_VALUE)
								.addGap(1))
							.addGroup(gl_jContentPane.createSequentialGroup()
								.addGap(1)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)))
						.addGap(5)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
						.addGap(2)
						.addComponent(getLblNewLabel(), GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
						.addGap(10)
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(getBotonQueryQueries(), GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addComponent(getButtonDiruaSartu(), GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(getApostuaEgin(), GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(getBotonMugimenduakIkusi(), GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addComponent(getBtnLaguntza(), GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGap(36)
						.addComponent(getPanel(), GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
			);
			jContentPane.setLayout(gl_jContentPane);

			
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getButtonDiruaSartu() {
		if (jButtonDiruaSartu == null) {
			jButtonDiruaSartu = new JButton();
			jButtonDiruaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("AddMoney"));
			jButtonDiruaSartu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new AddMoneyGUI(log);
					nirePantaila.setVisible(false);
					a.setVisible(true);
				}
			});
		}
		return jButtonDiruaSartu;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBotonQueryQueries() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new QueryQueriesErregistratua(log);
                    nirePantaila.setVisible(false);
					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	
	private JButton getBotonMugimenduakIkusi() {
		if (btnMugimenduakIkusi == null) {
			btnMugimenduakIkusi = new JButton();
			btnMugimenduakIkusi.setText(ResourceBundle.getBundle("Etiquetas").getString("ConsultMovements"));
			btnMugimenduakIkusi.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new ConsultMovementsGUI(log);
                    nirePantaila.setVisible(false);
                    a.setLocationRelativeTo(null);
					a.setVisible(true);
				}
			});
		}
		return btnMugimenduakIkusi;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnEnglish() {
		if (rdbtnEnglish == null) {
			rdbtnEnglish = new JRadioButton("English");
			rdbtnEnglish.setSelected(true);
			rdbtnEnglish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnEnglish);
		}
		return rdbtnEnglish;
	}
	private JRadioButton getRdbtnEuskera() {
		if (rdbtnEuskera == null) {
			rdbtnEuskera = new JRadioButton("Euskara");
			rdbtnEuskera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnEuskera);
		}
		return rdbtnEuskera;
	}
	private JRadioButton getRdbtnCastellano() {
		if (rdbtnCastellano == null) {
			rdbtnCastellano = new JRadioButton("Castellano");
			rdbtnCastellano.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnCastellano);
		}
		return rdbtnCastellano;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getRdbtnEuskera());
			panel.add(getRdbtnCastellano());
			panel.add(getRdbtnEnglish());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonDiruaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("AddMoney"));
		jButtonApostuaEgin.setText(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
		jButtonLogOut.setText(ResourceBundle.getBundle("Etiquetas").getString("LogOut"));
		btnMugimenduakIkusi.setText(ResourceBundle.getBundle("Etiquetas").getString("ConsultMovements"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("RegisterTittle"));
	}
	
	private JButton getApostuaEgin() {
		if (jButtonApostuaEgin == null) {
			jButtonApostuaEgin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bet")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonApostuaEgin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame registerInterfazea = new BetGUI(log);
					nirePantaila.setVisible(false);
					registerInterfazea.setLocationRelativeTo(null);
					registerInterfazea.setVisible(true);
				}
			});
		}
		return jButtonApostuaEgin;
	}
	private JButton getJButtonLogOut() {
		if (jButtonLogOut == null) {
			jButtonLogOut = new JButton("Log out");
			jButtonLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new MainGUI();
					nirePantaila.setVisible(false);
					a.setVisible(true);
				}
			});
			jButtonLogOut.setFont(new Font("Tahoma", Font.PLAIN, 8));
		}
		return jButtonLogOut;
	}
	private JButton getBtnLaguntza() {
		if (btnLaguntza == null) {
			btnLaguntza = new JButton();
			btnLaguntza.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a= new MezuakErregistratuaGUI(log);
					nirePantaila.setVisible(false);
					a.setVisible(true);
				}
			});
			btnLaguntza.setText(ResourceBundle.getBundle("Etiquetas").getString("ErregistratuaGUI.btnJasotakoMezuak.text"));
		}
		return btnLaguntza;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

