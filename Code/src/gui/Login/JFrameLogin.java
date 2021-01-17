package gui.Login;


import gui.FirstFrame;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import controller.Controller;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import tipo.TipoPaneles;
import model.Hash;
import model.TraitementUsuarioDataBase;
import tablas.Usuario;

public class JFrameLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usuario_txt;
	private JPasswordField password_field;
	private JButton usuarioCancelBt, usuarioNuevoBt,usuarioConfirmBt;
	private JFrameRegistrarUsuario registrarUsuario = new JFrameRegistrarUsuario();
	private	JLabel labelFondo = new JLabel("");	
	private Controller controller = new Controller();
	private FirstFrame firstFrame2 = new FirstFrame();
	TraitementUsuarioDataBase tu=new TraitementUsuarioDataBase();
	
	public JFrameLogin() {

		super("PimPam");
		firstFrame2.setVisible(false);
		setUndecorated(true);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		setVisible(true);
		contentPane = new JPanel();
		usuario_txt = new JTextField();
		password_field = new JPasswordField();
		usuarioCancelBt = new JButton("Cancel");
		usuarioNuevoBt = new JButton("Nuevo Usuario");
		usuarioConfirmBt = new JButton("Login");
		// para gestionar el exit
				addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// cierre la app
					//	controller.disconect();
						dispose();
						System.gc();
						System.exit(0);
					}
				});
		

		// diseño
		usuarioFrame();
		
		
		// pour sortir en appuyant sur exit
		usuarioCancelBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int action = JOptionPane.showConfirmDialog(JFrameLogin.this,
						"Quieres salir de la app?", "Confirmar salida",
						JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					WindowListener[] listeners = getWindowListeners();
					for (WindowListener listener : listeners) {
						listener.windowClosing(new WindowEvent(JFrameLogin.this, 0));
					}
				}
			}
		});

		// Crear un nuevo usuario bd

		usuarioNuevoBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registrarUsuario.setVisible(true);
				dispose();
				
			}
		});
	}

	private void usuarioFrame() {

		setForeground(Color.BLACK);
		setTitle("Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 437);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK,
				null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		usuario_txt = new JTextField();
		usuario_txt.setBounds(145, 97, 276, 26);
		contentPane.add(usuario_txt);
		usuario_txt.setColumns(10);

		JLabel lblNewLabel = new JLabel("Usuario ");
		lblNewLabel.setBounds(67, 100, 68, 14);
		lblNewLabel.setFont(TipoPaneles.jLabelNormalTipo);
		lblNewLabel.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password ");
		lblNewLabel_1.setForeground(Color.WHITE);
		
		lblNewLabel_1.setFont(TipoPaneles.jLabelNormalTipo);
		lblNewLabel_1.setBounds(67, 147, 68, 14);
		contentPane.add(lblNewLabel_1);

		password_field = new JPasswordField();
		password_field.setBounds(145, 144, 276, 26);
		contentPane.add(password_field);

		usuarioConfirmBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			connect();
				Usuario user = new Usuario();

				// porque cuadro de contraseña
				String pass = new String(password_field.getPassword());
				// si usuario no = nada
				if (!usuario_txt.getText().equals("")
						&& !password_field.equals("")) {
					String nuevoPass = Hash.sha1(pass);
					user.setUsuario(usuario_txt.getText());
					user.setPassword(nuevoPass);
					// verifie que c´est correcte et initie session
					try {
						if (controller.login(user)) {
						firstFrame2.setVisible(true);	
						try {
							controller.cargarUsuario(user);
							firstFrame2.jtextUsuario.setText(user.getNombre());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									"Datos incorrectos");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ingresar datos");
				}

			}
		});

		usuarioConfirmBt.setForeground(new Color(0, 0, 0));
		usuarioConfirmBt.setBackground(new Color(102, 153, 255));
		usuarioConfirmBt.setBounds(145, 179, 89, 23);
		contentPane.add(usuarioConfirmBt);

		
		usuarioNuevoBt.setForeground(new Color(0, 0, 0));
		usuarioNuevoBt.setBackground(new Color(102, 153, 255));
		usuarioNuevoBt.setBounds(175, 227, 142, 23);
		contentPane.add(usuarioNuevoBt);

		JLabel lblRegisteruser = DefaultComponentFactory.getInstance()
				.createTitle("PIM PAM");
		lblRegisteruser.setFont(new Font("Tahoma", Font.PLAIN, 30));
	
		lblRegisteruser.setForeground(Color.WHITE);
		lblRegisteruser.setBounds(10, 11, 160, 34);
		contentPane.add(lblRegisteruser);

		usuarioCancelBt.setForeground(new Color(0, 0, 0));
		usuarioCancelBt.setBounds(335, 227, 89, 23);
		usuarioCancelBt.setBackground(new Color(102, 153, 255));
		contentPane.add(usuarioCancelBt);

		labelFondo.setIcon(new ImageIcon(JFrameLogin.class
				.getResource("/Images/Blue-Wallpaper-For-Background-7.jpg")));
		labelFondo.setBounds(0, 0, 576, 437);
		
		Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED,
				Color.BLUE, Color.blue);
		labelFondo.setBorder(border);
		contentPane.add(labelFondo);

	}
	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(JFrameLogin.this, "peux pas se connecter",
					"dataBaseConnection", JOptionPane.ERROR_MESSAGE);
		}
	}

}
