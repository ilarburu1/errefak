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
import java.awt.TextArea;
import javax.swing.GroupLayout.Alignment;


public class AddMoneyGUI extends JFrame {
	
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JFrame nirePantaila;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSartuDiruaText;
	private JRadioButton EnglishRadioButton;
	private JRadioButton euskeraRdioButton;
	private JRadioButton CastellanoRadioButton;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private JButton gehituDiruaButton;

	private BLFacade negozioLogika;
	private String log;
	private TextArea textArea;
	private JButton atzera;
	private JLabel lblOrainDuzunDirua;
	private JTextPane textPane_DiruKopurua;
	
	/**
	 * This is the default constructor
	 */
	public AddMoneyGUI(String log) {
		super();
		nirePantaila=this;
		negozioLogika= MainGUI.getBusinessLogic();
		this.log=log;
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
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MoneyTittle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			GroupLayout gl_jContentPane = new GroupLayout(jContentPane);
			gl_jContentPane.setHorizontalGroup(
				gl_jContentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(10)
						.addComponent(getAtzera(), GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addComponent(getLblNewLabel(), GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(103)
						.addComponent(getTextField(), GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(41)
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_jContentPane.createSequentialGroup()
								.addGap(108)
								.addComponent(getGehituDiruaButton(), GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_jContentPane.createSequentialGroup()
								.addGap(317)
								.addComponent(getLblOrainDuzunDirua(), GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_jContentPane.createSequentialGroup()
								.addGap(317)
								.addComponent(getTextPane_DiruKopurua(), GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
							.addComponent(getTextArea(), GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)))
					.addComponent(getPanel(), GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)
			);
			gl_jContentPane.setVerticalGroup(
				gl_jContentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(10)
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(getAtzera(), GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_jContentPane.createSequentialGroup()
								.addGap(19)
								.addComponent(getLblNewLabel(), GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
						.addGap(10)
						.addComponent(getTextField(), GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addGap(13)
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_jContentPane.createSequentialGroup()
								.addGap(44)
								.addComponent(getGehituDiruaButton(), GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_jContentPane.createSequentialGroup()
								.addGap(37)
								.addComponent(getLblOrainDuzunDirua(), GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_jContentPane.createSequentialGroup()
								.addGap(58)
								.addComponent(getTextPane_DiruKopurua(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addComponent(getTextArea(), GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addComponent(getPanel(), GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
			);
			jContentPane.setLayout(gl_jContentPane);
		}
		return jContentPane;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSartuDiruaText == null) {
			jLabelSartuDiruaText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddMoney"));
			jLabelSartuDiruaText.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSartuDiruaText.setForeground(Color.BLACK);
			jLabelSartuDiruaText.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSartuDiruaText;
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
			panel.add(geteuskeraRdioButton());
			panel.add(getCastellanoRadioButton());
			panel.add(getEnglishRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSartuDiruaText.setText(ResourceBundle.getBundle("Etiquetas").getString("AddMoney"));
		lblOrainDuzunDirua.setText(ResourceBundle.getBundle("Etiquetas").getString("duzunDiruaText"));
		gehituDiruaButton.setText(ResourceBundle.getBundle("Etiquetas").getString("AddMoney"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MoneyTittle"));
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	private JButton getGehituDiruaButton() {
		if (gehituDiruaButton == null) {
			gehituDiruaButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddMoney")); //$NON-NLS-1$ //$NON-NLS-2$
			gehituDiruaButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Double diruKop=Double.parseDouble(textField.getText());
					if(diruKop < 10.0) {
						textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("GutxienekoDirua"));
					}
					else {
						negozioLogika.addMoney(log, diruKop);
						textPane_DiruKopurua.setText(String.valueOf(negozioLogika.erregistratuaItzuli(log).getKontuDirua()) + "€");
						textField.setText("");
						textArea.setText(ResourceBundle.getBundle("Etiquetas").getString("diruaGehituText"));	
					}
					
				}
			});
		}
		return gehituDiruaButton;
	}
	private TextArea getTextArea() {
		if (textArea == null) {
			textArea = new TextArea();
			textArea.setEditable(false);
		}
		return textArea;
	}
	private JButton getAtzera() {
		if (atzera == null) {
			atzera = new JButton();
			atzera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame e1 = new ErregistratuaGUI(log);
					nirePantaila.setVisible(false);
					e1.setVisible(true);
				}
			});
			atzera.setText("<--");
			atzera.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return atzera;
	}
	private JLabel getLblOrainDuzunDirua() {
		if (lblOrainDuzunDirua == null) {
			lblOrainDuzunDirua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("duzunDiruaText"));
			lblOrainDuzunDirua.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
		return lblOrainDuzunDirua;
	}
	private JTextPane getTextPane_DiruKopurua() {
		if (textPane_DiruKopurua == null) {
			textPane_DiruKopurua = new JTextPane();
			textPane_DiruKopurua.setText(String.valueOf(negozioLogika.erregistratuaItzuli(log).getKontuDirua()) + "€");
			textPane_DiruKopurua.setEditable(false);
			textPane_DiruKopurua.setBackground(Color.WHITE);
		}
		return textPane_DiruKopurua;
	}
} // @jve:decl-index=0:visual-constraint="0,0"


