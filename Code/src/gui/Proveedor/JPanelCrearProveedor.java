package gui.Proveedor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import tipo.TipoPaneles;
import tablas.Sociedad;
import tablas.Usuario;
import model.TraitementCodigoPostal.Posicion;
import colores.JLabelGradientColorHorizontal;
import controller.Controller;

public class JPanelCrearProveedor extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField[] text = new JTextField[10];
	private JLabel[] label = new JLabel[9];
	private PanelCrearProveedor panelCrearProveedor;
	private JRadioButton rdbtnClienteProveedor = new JRadioButton(
			"Es Cliente y Proveedor.");
	private JScrollPane jScrollPane;
	private Controller controller = new Controller();
	private JButton btnCpOk = new JButton("");
	private JButton btnRegistrarProveedor = new JButton("Registrar Proveedor");
	private JLabelGradientColorHorizontal lblCrearProveedor;
	private Usuario u;
	public JPanelCrearProveedor() {

		for (int i = 0; i < label.length; i++) {
			label[i] = new JLabel(); // Llenamos el array de etiquetas
			label[i].setFont(TipoPaneles.jLabelNormalTipo);
		}
		for (int i = 0; i < text.length; i++) {
			text[i] = new JTextField();
			text[i].setFont(TipoPaneles.jTextFieldTipo);
		}

		setLayout(new BorderLayout());

		lblCrearProveedor = new JLabelGradientColorHorizontal();
		lblCrearProveedor.setFont(TipoPaneles.jJabelTituloTipo);
		text[1] = new JTextField(30);
		text[2] = new JTextField(30);
		text[3] = new JTextField(10);
		text[4] = new JTextField(10);
		text[5] = new JTextField(10);
		text[6] = new JTextField(20);
		text[7] = new JTextField(20);
		text[8] = new JTextField(50);
		text[1].setText("denominación de la empresa...");
		text[2].setText("dirección de la empresa...");
		text[3].setText("cp...");
		text[6].setText("125");
		text[7].setText("email...");
		text[1].addFocusListener(new FooocusAdapter());
		text[2].addFocusListener(new FooocusAdapter());
		text[3].addFocusListener(new FooocusAdapter());
		text[4].addFocusListener(new FooocusAdapter());
		text[5].addFocusListener(new FooocusAdapter());
		text[6].addFocusListener(new FooocusAdapter());
		text[7].addFocusListener(new FooocusAdapter());

		setBackground(Color.white);
		int space = 5;
		Border innerBorder = BorderFactory
				.createTitledBorder("Crear Proveedor");
		Border outerBorder = BorderFactory.createEmptyBorder(space, space,
				space, space);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));

		lblCrearProveedor.setText("Crear un nuevo Proveedor");
		label[1].setText("Denominación Social");
		label[2].setText("Dirección");
		label[3].setText("Codigo Postal");
		label[4].setText("Ciudad");
		label[5].setText("Provincia");
		label[6].setText("Teléfono");
		label[7].setText("Email");
		label[8].setText("DNI");

		panelCrearProveedor = new PanelCrearProveedor();
		jScrollPane = new JScrollPane(panelCrearProveedor);
		add(jScrollPane, BorderLayout.CENTER);
		// create a line border with the specified color and width
		final Border border = BorderFactory.createLineBorder(new Color(115,
				164, 209), 3);

		// codigo postal
		text[3].addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				int limite = 5;
				// Verificar si la tecla pulsada no es un digito
				if ((caracter < '0') || (caracter > '9')
						&& (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
					JOptionPane.showMessageDialog(null,
							"solo se puede introducir digitos");
				}
				if (text[3].getText().length() == limite) {
					e.consume();
					JOptionPane.showMessageDialog(null,
							"no se puede introducir más de 5 digitos");
				}
			}
		});
		// telefono
		text[6].addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				// Verificar si la tecla pulsada no es un digito
				if ((caracter < '0') || (caracter > '9')) {
					e.consume(); // ignorar el evento de teclado
					JOptionPane.showMessageDialog(null,
							"solo se puede introducir digitos");
				}
			}
		});
		btnRegistrarProveedor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
				
				Sociedad s = new Sociedad();	
				Usuario u1=new Usuario();
				s.setUsuarios(u1);
				
				s.setDenominacionSocial(text[1].getText());
				s.setDireccion(text[2].getText());
				s.setCiudad(text[4].getText());
				s.setProvincia(text[5].getText());
				s.setTelefono(Integer.parseInt(text[6].getText()));
				s.setEmail(text[7].getText());
				s.setId(s.getId());

				// comprobar si cliente existe
				try {
					if (controller.existeProveedor(text[1].getText()) == false) {
						// campos no vacios
						if (text[1].getText().length() != 0
								&& text[2].getText().length() != 0) {
							if (!text[3].getText().equals("")) {
								if (text[4].getText().length() != 0
										&& text[5].getText().length() != 0) {
									// validar email
									String email = text[7].getText();
									if (validarEmail(email)) {
										// si radioButon activado
										if (rdbtnClienteProveedor.isSelected()) {
											s.setCodigoPostal(Integer
													.parseInt(text[3].getText()));
											controller.saveCliente(s);
											controller.saveProveedor(s);
											JOptionPane
													.showMessageDialog(null,
															"Sociedad añadida como cliente y proveedor");
										} else {
											s.setCodigoPostal(Integer
													.parseInt(text[3].getText()));
											 controller.saveProveedor(s);
											 /*
											Session session = HibernateUtils
													.getSessionFactory()
													.openSession();
											session.beginTransaction();
											session.saveOrUpdate(u);
											s.setUsuarios(u);
											System.out.println("3 "+s.getUsuarios().getId());
											System.out.println("4 "+s.getUsuarios());
											session.saveOrUpdate(s);
											session.getTransaction().commit();
											session.close();
											*/
											JOptionPane.showMessageDialog(null,
													"Proveedor Guardado");
										}
										// elimina el texto de los Jtextfields
										setText();
									} else {
										JOptionPane.showMessageDialog(null,
												"Email no valido");
									}
								} else {
									text[4].setBorder(border);
									text[5].setBorder(border);
								}

							} else {

								text[3].setBorder(border);
								JOptionPane.showMessageDialog(null,
										"Debe indicar un Codigo postal ");
							}
						} else {
							JOptionPane
									.showMessageDialog(null,
											"Denominación social o Direccíon no pueden estar vacios ");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Proveedor ya existe");
					}
				} catch (NumberFormatException | HeadlessException
						| SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		btnCpOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.connectCP();
				} catch (Exception ev) {
					ev.printStackTrace();
					JOptionPane.showMessageDialog(JPanelCrearProveedor.this,
							"No se puede conectar", "dataBaseConnection",
							JOptionPane.ERROR_MESSAGE);
				}
				String codigoPostalTxt = text[3].getText();
				try {
					if (!codigoPostalTxt.equals("")) {
						try {

							Posicion position = controller.Cp(codigoPostalTxt);
							text[4].setText(position.getPoblacion());
							text[5].setText(position.getProvincia());
							text[4].setVisible(true);
							text[5].setVisible(true);
							label[4].setVisible(true);
							label[5].setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, " rellenar CP");
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	class PanelCrearProveedor extends JPanel {
		private static final long serialVersionUID = 1L;

		public PanelCrearProveedor() {
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createCompoundBorder());
			GridBagConstraints gc = new GridBagConstraints();
			setBackground(Color.WHITE);

			gc.gridheight = 1;
			gc.anchor = GridBagConstraints.WEST;
			// PRIMERA Fila/
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.ipady = 30; // make this component tall
			gc.insets = new Insets(0, 0, 0, 0);
			gc.gridx = 0;
			gc.gridy = 0;
			gc.weightx = 1;
			gc.gridwidth = GridBagConstraints.REMAINDER;
			// El componente ocupa 2 filas.
			gc.gridheight = 2;
			// titulo.setBackground(Color.yellow);
			add(lblCrearProveedor, gc);
			// Restauramos valores por defecto
			gc.fill = GridBagConstraints.NONE;
			gc.weightx = 0;
			gc.gridheight = 1;
			gc.ipady = 0;
			// creer un espace de 25 pixel de altura entre label y textfield y
			// de 50 pixel de ancho
			gc.insets = new Insets(25, 5, 0, 10);

			// Segunda Fila/
			gc.gridy = 2;
			gc.gridx = 0;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 0;
			// El componente ocupa 1 columnas.
			gc.gridwidth = 1;
			add(label[1], gc);

			gc.gridx = 1;
			// El componente ocupa 4 columnas.
			// // gc.gridwidth = 3;
			gc.gridwidth = GridBagConstraints.REMAINDER;
			// Columna 1. Necesita estirarse, ponemos weightx 1
			gc.weightx = 1;
			add(text[1], gc);

			// Tercera Fila//
			gc.gridy = 3;
			gc.gridx = 0;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 0;
			gc.gridwidth = 1;
			add(label[2], gc);

			gc.gridx = 1;
			// El componente ocupa 4 columnas.
			// gc.gridwidth = 3;
			gc.gridwidth = GridBagConstraints.REMAINDER;
			// Columna 1. Necesita estirarse, ponemos weightx 1
			gc.weightx = 1;
			add(text[2], gc);

			// 4 Fila//
			gc.gridy = 4;
			gc.gridx = 0;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 0;
			gc.gridwidth = 1;
			add(label[3], gc);

			gc.gridx = 1;
			// El componente ocupa 1 columnas.
			gc.gridwidth = 1;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 0;
			add(text[3], gc);

			gc.gridx = 2;
			// El componente ocupa 1 columnas.
			gc.gridwidth = 1;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 1;
			btnCpOk.setIcon(createIcon("/Images/loupe_opt.jpg "));
			add(btnCpOk, gc);

			gc.gridx = 3;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 0;
			// El componente ocupa 1 columnas.
			gc.gridwidth = 1;
			gc.gridwidth = GridBagConstraints.RELATIVE;
			add(label[4], gc);

			gc.gridx = 4;
			// El componente ocupa 1 columnas.
			// gc.gridwidth = 3;
			gc.gridwidth = GridBagConstraints.REMAINDER; // end of row
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 1;
			add(text[4], gc);

			// 5 Fila//
			gc.gridy = 5;
			gc.gridx = 3;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 0;
			gc.gridwidth = 1;
			gc.gridwidth = GridBagConstraints.RELATIVE;
			add(label[5], gc);

			gc.gridx = 4;
			// El componente ocupa 1 columnas.
			// gc.gridwidth = 3;
			gc.gridwidth = GridBagConstraints.REMAINDER; // end of row
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 1;
			add(text[5], gc);

			// 6 LINEA//
			gc.gridy = 6;
			gc.gridx = 0;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 0;
			// El componente ocupa 1 columnas.
			gc.gridwidth = 1;
			add(label[6], gc);

			gc.gridx = 1;
			// El componente ocupa 4 columnas.
			gc.gridwidth = 3;
			// Columna 1. Necesita estirarse, ponemos weightx 1
			gc.weightx = 1;
			add(text[6], gc);

			// 7 LINEA//
			gc.gridy = 7;
			gc.gridx = 0;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 0;
			// El componente ocupa 1 columnas.
			gc.gridwidth = 1;
			add(label[7], gc);

			gc.gridx = 1;
			// El componente ocupa 4 columnas.
			gc.gridwidth = 3;
			// Columna 1. Necesita estirarse, ponemos weightx 1
			gc.weightx = 1;
			add(text[7], gc);

			// 8 LINEA//
			// creer un espace de 25 pixel de altura entre label y textfield y
			// de 50 pixel de ancho
			gc.insets = new Insets(20, 0, 0, 20);
			gc.gridy = 8;
			gc.gridx = 0;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 0;
			// El componente ocupa 1 columnas.
			gc.gridwidth = 1;
			gc.gridheight = 1;
			add(rdbtnClienteProveedor, gc);

			// ultima//
			gc.gridy = 9;
			gc.gridx = 3;
			gc.weighty = 2;
			add(btnRegistrarProveedor, gc);

		}

		public ImageIcon createIcon(String path) {
			URL url = getClass().getResource(path);
			if (url == null) {
				System.err.println("peux pas charger image" + path);
			}
			ImageIcon icon = new ImageIcon(url);
			return icon;
		}
	}

	class FooocusAdapter implements FocusListener {
		@Override
		public void focusGained(FocusEvent e) {
			JTextField c = (JTextField) e.getSource();
			c.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) {

		}
	}

	public boolean validarEmail(String email) {
		Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.find() == true) {
			return true;
		} else {
			return false;
		}
	}

	public void setText() {
		text[1].setText("denominación de la empresa...");
		text[2].setText("");
		text[3].setText("");
		text[4].setText("");
		text[5].setText("");
		text[6].setText("125");
		text[7].setText("email...");
	}

	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "peux pas se connecter",
					"dataBaseConnection", JOptionPane.ERROR_MESSAGE);
		}
	}

}
