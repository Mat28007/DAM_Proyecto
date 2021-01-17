package gui.Login;

import gui.FirstFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Hash;
import tablas.Usuario;
import colores.JLabelGradientColorHorizontal;
import tipo.TipoPaneles;
import controller.Controller;

public class JFrameRegistrarUsuario extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField nombre, usuario;
	private JPasswordField confirmPasswordField, passwordField;
	private Controller controller = new Controller();
	private JLabel lblNombre, lblUsuario, lblPassword, lblRepeatPassword;
	private JButton registrarUsuario_bt,cancel;
	private PanelRegistrarUsuario pru;
	private JLabelGradientColorHorizontal labTitulo;

	private	FirstFrame firstFrame=new FirstFrame();
	
	
	public JFrameRegistrarUsuario() {
		setLayout(new BorderLayout());
		firstFrame.setVisible(false);
		setBackground(Color.WHITE);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		// para gestionar el exit
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.gc();
				System.exit(0);
			}
		});

		nombre = new JTextField(50);
		usuario = new JTextField(50);
		nombre.setFont(TipoPaneles.jTextFieldTipo);
		usuario.setFont(TipoPaneles.jTextFieldTipo);
		passwordField = new JPasswordField(50);
		confirmPasswordField = new JPasswordField(50);

		labTitulo = new JLabelGradientColorHorizontal();
		labTitulo.setFont(TipoPaneles.jJabelTituloTipo);
		labTitulo.setText("Registrar nuevo usuario");

		lblNombre = new JLabel("Nombre:");
		lblUsuario = new JLabel("Usuario:");
		lblPassword = new JLabel("Password:");
		lblRepeatPassword = new JLabel("Repeat Password:");
		lblNombre.setFont(TipoPaneles.jLabelNormalTipo);
		lblUsuario.setFont(TipoPaneles.jLabelNormalTipo);
		lblPassword.setFont(TipoPaneles.jLabelNormalTipo);
		lblRepeatPassword.setFont(TipoPaneles.jLabelNormalTipo);

		registrarUsuario_bt = new JButton("Save");
		cancel = new JButton("Cancel");
		

		try {
		    Image img = ImageIO.read(getClass().getResource("/Images/Save2.png "));
		    registrarUsuario_bt.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {}
		
		
		try {
		    Image img = ImageIO.read(getClass().getResource("/Images/cancel_opt.jpg "));
		    cancel.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {}
		

		
		pru = new PanelRegistrarUsuario();
		add(pru, BorderLayout.CENTER);

		registrarUsuario_bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
				 Usuario user = new Usuario();
				// porque cuadro de contraseña
				String pass = new String(passwordField.getPassword());
				String Confpass = new String(confirmPasswordField.getPassword());
				// comprobar si usuario existe
				try {
					if (controller.existeUsuario(usuario.getText()) == 0) {
						
					String u=usuario.getText();
					String n=nombre.getText();
					if(!(u==("")) || !(n==(""))){
						// si password ok insertamos data en bd
						if (pass.equals(Confpass)) {
							String nuevoPass = Hash.sha1(pass);
							user.setUsuario(u);
							user.setPassword(nuevoPass);
							user.setNombre(n);
							
							String nuevousu = usuario.getText();

							try {							
								controller.saveUsuario(user);
								JOptionPane
										.showMessageDialog(
												null,
												"Usuario : "
														+ nuevousu
														+ " "
														+ "creado correctamente en la base de datos");
								limpiarCampos();
								
								
								try {
									controller.cargarUsuario(user);
									firstFrame.jtextUsuario.setText(user.getNombre());
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								firstFrame.setVisible(true);	
								dispose();
							} catch (SQLException e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "ERROR");
							}
						} else {
							limpiarPassword();
							JOptionPane.showMessageDialog(null,
									"las contraseñas no coinciden");
						}
					}
					else{
						JOptionPane
						.showMessageDialog(null, "Rellenar los campos");
					}	
					} else {
						JOptionPane
								.showMessageDialog(null, "Usuario ya existe");
					}
					
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		String pass = new String(passwordField.getPassword());
		String Confpass = new String(confirmPasswordField.getPassword());
		// que no haya campos vacios
		if (usuario.getText() != null || nombre.getText() != null ) {
		
		if(!pass.equals("")||!Confpass.equals("")) {
		if (pass.equals(Confpass)) {
			registrarUsuario_bt.setEnabled(true);
		}
		}
		}

		nombre.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void changed() {
				if (usuario.getText().equals("") || nombre.getText().equals("")) {
					registrarUsuario_bt.setEnabled(false);
				} else {
					registrarUsuario_bt.setEnabled(true);
				}
			}
		});

		usuario.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void changed() {
				if (usuario.getText().equals("") || nombre.getText().equals("")) {
					registrarUsuario_bt.setEnabled(false);
				} else {
					registrarUsuario_bt.setEnabled(true);
				}
			}
		});
		

		// pour sortir en appuyant sur exit
					cancel.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent arg0) {
										// pour creer le message dialogue
										int action = JOptionPane.showConfirmDialog(JFrameRegistrarUsuario.this,
												"Quieres salir de la app?", "Confirmar salida",
												JOptionPane.OK_CANCEL_OPTION);
										if (action == JOptionPane.OK_OPTION) {
											// ecoute a la methode windowClosing

											WindowListener[] listeners = getWindowListeners();
											for (WindowListener listener : listeners) {
												listener.windowClosing(new WindowEvent(JFrameRegistrarUsuario.this,0));

											}
										}
									}
								});
					
				

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

	private void limpiarCampos() {
		usuario.setText("");
		nombre.setText("");
		passwordField.setText("");
		confirmPasswordField.setText("");
	}

	private void limpiarPassword() {
		passwordField.setText("");
		confirmPasswordField.setText("");
	}

	
	


	public class PanelRegistrarUsuario extends JPanel {
		private static final long serialVersionUID = 1L;

		public PanelRegistrarUsuario() {
			setLayout(new GridBagLayout());
			setBackground(Color.white);
			int space = 15;
			Border innerBorder = BorderFactory
					.createTitledBorder("Crear Usuario");
			Border outerBorder = BorderFactory.createEmptyBorder(space, space,
					space, space);
			setBorder(BorderFactory.createCompoundBorder(innerBorder,
					outerBorder));

			GridBagConstraints gc = new GridBagConstraints();
			// setBackground(Color.WHITE);
			// El componente ocupa 1 fila.
			gc.gridheight = 1;
			// El componente debe ocupar la posición WEST de su celda
			gc.anchor = GridBagConstraints.WEST;

			// PRIMERA Fila/
			// para que se estire sólo en horizontal.
			gc.fill = GridBagConstraints.HORIZONTAL;

			gc.ipady = 30; // make this component tall
			// creer un espace de 5 pixel The inset from the top.
			gc.insets = new Insets(0, 0, 0, 0);
			gc.gridx = 0;
			gc.gridy = 0;
			// Columna 0. Necesita estirarse, ponemos weightx 1
			gc.weightx = 1;
			// El componente ocupa 7 columnas.
			gc.gridwidth = 7;
			// gc.gridwidth = GridBagConstraints.REMAINDER;
			// El componente ocupa 2 filas.
			gc.gridheight = 2;
			// titulo.setBackground(Color.yellow);
			add(labTitulo, gc);

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
			add(lblNombre, gc);

			gc.gridx = 1;
			// El componente ocupa 4 columnas.
			// // gc.gridwidth = 3;
			gc.gridwidth = GridBagConstraints.REMAINDER;
			// Columna 1. Necesita estirarse, ponemos weightx 1
			gc.weightx = 1;
			add(nombre, gc);

			// Tercera Fila//
			gc.gridy = 3;
			gc.gridx = 0;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 0;
			gc.gridwidth = 1;
			add(lblUsuario, gc);

			gc.gridx = 1;
			// El componente ocupa 4 columnas.
			// gc.gridwidth = 3;
			gc.gridwidth = GridBagConstraints.REMAINDER;
			// Columna 1. Necesita estirarse, ponemos weightx 1
			gc.weightx = 1;
			add(usuario, gc);

			// 4 Fila//
			gc.gridy = 4;
			gc.gridx = 0;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 0;
			gc.gridwidth = 1;
			add(lblPassword, gc);

			gc.gridx = 1;
			// El componente ocupa 4 columnas.
			// gc.gridwidth = 3;
			gc.gridwidth = GridBagConstraints.REMAINDER;
			// Columna 1. Necesita estirarse, ponemos weightx 1
			gc.weightx = 1;
			add(passwordField, gc);

			// 5 Fila//
			gc.gridy = 5;
			gc.gridx = 0;
			// Columna 0. No Necesita estirarse, ponemos weightx 0
			gc.weightx = 0;
			gc.gridwidth = 1;
			add(lblRepeatPassword, gc);

			gc.gridx = 1;
			// El componente ocupa 4 columnas.
			// gc.gridwidth = 3;
			gc.gridwidth = GridBagConstraints.REMAINDER;
			// Columna 1. Necesita estirarse, ponemos weightx 1
			gc.weightx = 1;
			add(confirmPasswordField, gc);

			// 6 LINEA//
			gc.gridy = 6;
			gc.gridheight = 2;
			gc.gridwidth = 1;
			gc.weightx = 0;

			gc.anchor = GridBagConstraints.WEST;

			gc.gridx = 3;
			add(registrarUsuario_bt, gc);

			gc.gridx = 4;
			
			gc.gridwidth = GridBagConstraints.REMAINDER; // end of row
			add(cancel, gc);
			
			
			// Ultima//
			gc.gridy = 9;
			gc.weighty = 2;
			add(new JLabel(), gc);
			
			
			
		}

	}
	

}
