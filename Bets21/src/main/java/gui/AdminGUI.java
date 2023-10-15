package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;
import domain.Event;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AdminGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateEvent = null;
	private JButton jButtonQueryQueries = null;
	private JButton jButtonCreateQuestion = null;
	private JButton jButtonLogOut=null;
	private JFrame nirePantaila;

	private BLFacade negozioLogika;
	
    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton EnglishRadioButton;
	private JRadioButton euskeraRdioButton;
	private JRadioButton CastellanoRadioButton;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton jButtonMezuakIkusi;
	
	/**
	 * This is the default constructor
	 */
	public AdminGUI() {
		super();
		nirePantaila=this;
		this.negozioLogika=MainGUI.getBusinessLogic();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

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
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("AdminTittle"));
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
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			jContentPane.add(getPanel());
			jContentPane.add(getCreateQuestion());
			
			jButtonLogOut = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LogOut")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonLogOut.setFont(new Font("Tahoma", Font.PLAIN, 8));
			jButtonLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new MainGUI();
					nirePantaila.setVisible(false);
					a.setVisible(true);
				}
			});
			jButtonLogOut.setBounds(10, 7, 85, 21);
			jContentPane.add(jButtonLogOut);
			jContentPane.add(getJButtonMezuakIkusi());
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateEvent == null) {
			jButtonCreateEvent = new JButton();
			jButtonCreateEvent.setFont(new Font("Tahoma", Font.PLAIN, 14));
			jButtonCreateEvent.setBounds(0, 84, 481, 33);
			jButtonCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
			jButtonCreateEvent.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CreateEventGUI();
					nirePantaila.setVisible(false);
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateEvent;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setFont(new Font("Tahoma", Font.PLAIN, 14));
			jButtonQueryQueries.setBounds(0, 40, 481, 33);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new QueryQueriesAdmin();
                    nirePantaila.setVisible(false);
					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(50, 0, 381, 33);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	
	
	private JRadioButton getEnglishRadioButton() {
		if (EnglishRadioButton == null) {
			EnglishRadioButton = new JRadioButton("English");
			EnglishRadioButton.setSelected(true);
			EnglishRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					EnglishRadioButton.setSelected(true);
					euskeraRdioButton.setSelected(false);
					CastellanoRadioButton.setSelected(false);
					redibujar();				}
			});
			buttonGroup.add(EnglishRadioButton);
		}
		return EnglishRadioButton;
	}
	private JRadioButton geteuskeraRdioButton() {
		if (euskeraRdioButton == null) {
			euskeraRdioButton = new JRadioButton("Euskara");
			euskeraRdioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					CastellanoRadioButton.setSelected(false);
					EnglishRadioButton.setSelected(false);
					redibujar();				}
			});
			buttonGroup.add(euskeraRdioButton);
		}
		return euskeraRdioButton;
	}
	private JRadioButton getCastellanoRadioButton() {
		if (CastellanoRadioButton == null) {
			CastellanoRadioButton = new JRadioButton("Castellano");
			CastellanoRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					euskeraRdioButton.setSelected(false);
					EnglishRadioButton.setSelected(false);
					redibujar();
				}
			});
			buttonGroup.add(CastellanoRadioButton);
		}
		return CastellanoRadioButton;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 205, 481, 38);
			panel.add(geteuskeraRdioButton());
			panel.add(getCastellanoRadioButton());
			panel.add(getEnglishRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		jButtonCreateQuestion.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		jButtonLogOut.setText(ResourceBundle.getBundle("Etiquetas").getString("LogOut"));
		jButtonMezuakIkusi.setText(ResourceBundle.getBundle("Etiquetas").getString("buttonMezuak"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("AdminTittle"));
	}
	
	private JButton getCreateQuestion() {
		if (jButtonCreateQuestion == null) {
			jButtonCreateQuestion = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonCreateQuestion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			jButtonCreateQuestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame CreateQuestionInterfazea = new CreateQuestionGUI(new Vector<Event>());
					nirePantaila.setVisible(false);
					CreateQuestionInterfazea.setVisible(true);
				}
			});
			jButtonCreateQuestion.setBounds(0, 127, 481, 33);
		}
		return jButtonCreateQuestion;
	}
	private JButton getJButtonMezuakIkusi() {
		if (jButtonMezuakIkusi == null) {
			jButtonMezuakIkusi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("buttonMezuak"));
			jButtonMezuakIkusi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a= new MezuakAdminGUI();
					nirePantaila.setVisible(false);
					a.setLocationRelativeTo(null); 
					a.setVisible(true);
				}
			});
			jButtonMezuakIkusi.setFont(new Font("Tahoma", Font.PLAIN, 14));
			jButtonMezuakIkusi.setBounds(0, 170, 481, 33);
		}
		return jButtonMezuakIkusi;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

