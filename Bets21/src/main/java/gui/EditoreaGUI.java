package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EditoreaGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;
	private JButton jButtonCreateQuote = null;
	private JFrame nirePantaila;

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
	private JButton jButtonEmaitzaGehitu;
	private JButton jButtonLogOut;
	private JButton jButtonDuplicateEvent;
	
	/**
	 * This is the default constructor
	 */
	public EditoreaGUI() {
		super();
		nirePantaila=this;
		
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
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Editor"));
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
			jContentPane.add(getPanel());
			jContentPane.add(getCreateQuote());
			jContentPane.add(getJButtonEmaitzaGehitu());
			jContentPane.add(getJButtonLogOut());
			jContentPane.add(getjButtonDuplicateEvent());
		}
		return jContentPane;
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
			jButtonQueryQueries.setBounds(0, 35, 481, 33);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new QueryQueriesEditorea();
                    nirePantaila.setVisible(false);
					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	
	private JButton getjButtonDuplicateEvent() {
		if (jButtonDuplicateEvent == null) {
			jButtonDuplicateEvent = new JButton();
			jButtonDuplicateEvent.setText("DUPLICATE EVENT");
			jButtonDuplicateEvent.setFont(new Font("Tahoma", Font.PLAIN, 14));
			jButtonDuplicateEvent.setBounds(0, 162, 481, 33);
			jButtonDuplicateEvent.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new DuplicateEventGUI();
                    nirePantaila.setVisible(false);
					a.setVisible(true);
				}
			});
		}
		return jButtonDuplicateEvent;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(57, -1, 376, 33);
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
		jButtonCreateQuote.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuote"));
		jButtonEmaitzaGehitu.setText(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaJarri"));
		jButtonLogOut.setText(ResourceBundle.getBundle("Etiquetas").getString("LogOut"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Editor"));
	}
	
	private JButton getCreateQuote() {
		if (jButtonCreateQuote == null) {
			jButtonCreateQuote = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuote")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonCreateQuote.setFont(new Font("Tahoma", Font.PLAIN, 14));
			jButtonCreateQuote.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame CreateQuoteInterfazea = new CreateQuoteGUI();
					nirePantaila.setVisible(false);
					CreateQuoteInterfazea.setVisible(true);
				}
			});
			jButtonCreateQuote.setBounds(0, 78, 481, 33);
		}
		return jButtonCreateQuote;
	}
	private JButton getJButtonEmaitzaGehitu() {
		if (jButtonEmaitzaGehitu == null) {
			jButtonEmaitzaGehitu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaJarri")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonEmaitzaGehitu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame e1= new EmaitzaJarriGUI();
					nirePantaila.setVisible(false);
					e1.setVisible(true);
				}
			});
			jButtonEmaitzaGehitu.setFont(new Font("Tahoma", Font.PLAIN, 14));
			jButtonEmaitzaGehitu.setBounds(0, 121, 481, 33);
		}
		return jButtonEmaitzaGehitu;
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
			jButtonLogOut.setBounds(10, 7, 85, 21);
		}
		return jButtonLogOut;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

