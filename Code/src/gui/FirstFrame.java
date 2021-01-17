package gui;
import gui.Cliente.FormPanelCliente;
import gui.Cliente.JPanelCrearCliente;
import gui.Cliente.JPanelModificarCliente;
import gui.Cliente.JPanelStartCliente;
import gui.Cliente.TablePanelCliente;
import gui.Documento.CreacionPdf;
import gui.Documento.FormPanelDocument;
import gui.Documento.JPanelFacturaProveedor;
import gui.Documento.JPanelStartDocumento;
import gui.GestionStock.FormPanelStock;
import gui.GestionStock.JPanelGestionStock;
import gui.GestionStock.JPanelRegistrarArticulo;
import gui.GestionStock.JPanelStartStock;
import gui.GestionStock.TablePanelStock;
import gui.Listener.PrefsListener;
import gui.Proveedor.FormPanelProveedor;
import gui.Proveedor.JPanelCrearProveedor;
import gui.Proveedor.JPanelModificarProveedor;
import gui.Proveedor.JPanelStartProveedor;
import gui.Proveedor.TablePanelProveedor;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import model.Acciones;
import tablas.SupplierInvoices;
import tablas.Usuario;
import tipo.TipoPaneles;
import controller.Controller;

public class FirstFrame extends JFrame   {
	private static final long serialVersionUID = 1L;
	//Cliente
	private ToolBarPrincipal toolbarPrincipal;
	private FormPanelCliente formPanelBotonesCliente;
	private	JPanelCrearCliente jPanelCrearCliente;
	private JPanelModificarCliente jPanelModificarCliente;
	private JPanelStartCliente jPanelStartCliente;
	private TablePanelCliente tablePanelCliente;
	//Stock
	private TablePanelStock tablePanelStock;
	private FormPanelStock formPanelStock;
	private JPanelStartStock jPanelStartStock;
	private JPanelGestionStock jPanelGestionStock;
	private JPanelRegistrarArticulo jPanelregistrarArticulo;
	//Proveedor
	private FormPanelProveedor formPanelProveedor;
	private JPanelStartProveedor jPanelStartProveedor;
	private TablePanelProveedor  tablePanelProveedor;
	private JPanelCrearProveedor jPanelCrearProveedor;
	private JPanelModificarProveedor jPanelModificarProveedor;
	//Documento
	private JPanelStartDocumento jPanelStartDocumento;
	private FormPanelDocument formPanelDocument;
	private JPanelFacturaProveedor jPanelFacturaProveedor;
	private SupplierInvoices supplierInvoice=new SupplierInvoices();
	private JFrameVentana jPanelVentana;
	private JFileChooser fileChooser;
	private Controller controller;
	private PrefsDialog prefsDialog;
	private Preferences prefs;
	private JSplitPane splitPanel1;
	public JTextField jtextUsuario= new JTextField(10);
	private JLabel jNombreUsuario = new JLabel("Usuario : ");
	private CardLayout cardLayout = new CardLayout();
	private JPanel cards; 
	private JPanel cards1; 
	private JScrollPane scrollPane;
	Usuario u=new Usuario();

	public FirstFrame() {
		// llamamos al super class constructor
		super("PimPam");
		setLayout(new BorderLayout());
		setVisible(true);
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
		
		// add mis componentes
		toolbarPrincipal = new ToolBarPrincipal();
		formPanelBotonesCliente = new FormPanelCliente();
		fileChooser = new JFileChooser();
		controller = new Controller();
		jPanelModificarCliente=new JPanelModificarCliente();
		tablePanelCliente=new TablePanelCliente();
		jPanelCrearCliente=new JPanelCrearCliente();
		jPanelStartCliente=new JPanelStartCliente();
		cards= new JPanel();
		cards1= new JPanel();
		formPanelStock=new FormPanelStock();
		jPanelStartStock=new JPanelStartStock();
		jPanelGestionStock=new JPanelGestionStock();
		tablePanelStock=new TablePanelStock();
		jPanelregistrarArticulo=new JPanelRegistrarArticulo();
		//proveedor
		formPanelProveedor=new FormPanelProveedor();
		jPanelStartProveedor=new JPanelStartProveedor();
		tablePanelProveedor=new TablePanelProveedor();
		jPanelCrearProveedor=new JPanelCrearProveedor();
		jPanelModificarProveedor=new JPanelModificarProveedor();
		//Documento
		jPanelStartDocumento=new JPanelStartDocumento();
		formPanelDocument=new FormPanelDocument();
		jPanelFacturaProveedor=new JPanelFacturaProveedor();
		jPanelVentana=new JFrameVentana(this);
		jPanelVentana.setVisible(false);
		prefsDialog = new PrefsDialog(this);
		prefs = Preferences.userRoot().node("db");
		setJMenuBar(createMenuBar());
		
		 add(toolbarPrincipal, BorderLayout.NORTH);
		 cards.setLayout(cardLayout);
		 cards.add(jPanelCrearCliente, "crear");
		 cards.add(jPanelModificarCliente, "editar");
		 cards.add(tablePanelCliente, "visualisar");
		 cards.add(jPanelStartCliente, "panelcentralCliente");
		 cards.add(jPanelStartStock, "panelcentralStock");
		 cards.add(tablePanelStock,"visualisarStock");
		 cards.add(jPanelregistrarArticulo,"anadir producto"); 
		 cards.add(jPanelStartProveedor, "panelcentralProveedor");
		 cards.add(jPanelModificarProveedor, "editarProveedor");
		 cards.add(tablePanelProveedor, "visualisarProveedor");
		 cards.add(jPanelCrearProveedor, "crearProveedor");
		 cards.add(jPanelStartDocumento, "panelcentralDocumento");
		 cards.add(jPanelFacturaProveedor,"crearFacturaProveedor");
		 
		 //cardlayout para definir el panel de los botones de cliente	
		 cards1.setLayout(cardLayout);
		 cards1.add(formPanelDocument,"formPanelDocument");
		 cards1.add(formPanelProveedor,"formPanelProveedor");
		 cards1.add(formPanelBotonesCliente,"formPanelBotonesCliente");
		 cards1.add(formPanelStock,"formPanelStock");
		 
		 //formPanel - toolBar escucha class Acciones
		 formPanelBotonesCliente.getVisualisarCliente().addActionListener(new Acciones(this));
		 formPanelBotonesCliente.getEditarCliente().addActionListener(new Acciones(this));
		 formPanelBotonesCliente.getCrearCliente().addActionListener(new Acciones(this));
		 
		 toolbarPrincipal.getPanelCliente().addActionListener(new Acciones(this));
		 toolbarPrincipal.getPanelStock().addActionListener(new Acciones(this));
		 toolbarPrincipal.getPanelProveedor().addActionListener(new Acciones(this));
		 toolbarPrincipal.getPanelDocumento().addActionListener(new Acciones(this));
		 
		 formPanelStock.getAnadirArticulo().addActionListener(new Acciones(this));
		 formPanelStock.getVisualisarStock().addActionListener(new Acciones(this));
		 
		 formPanelProveedor.getCrearProveedor().addActionListener(new Acciones(this));
		 formPanelProveedor.getVisualisarProveedor().addActionListener(new Acciones(this));
		 formPanelProveedor.getEditarProveedor().addActionListener(new Acciones(this));
		 
		 formPanelDocument.getFacturaProveedorPanel().addActionListener(new Acciones(this));
		 formPanelDocument.getFacturaProveedorPdf().addActionListener(new Acciones(this));

	}
	// methode for the menu bar
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem exportMenuData = new JMenuItem("Export Data");
		JMenuItem importMenuData = new JMenuItem("Import Data");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exportMenuData);
		fileMenu.add(importMenuData);
		// To separate the items
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		JMenu windowMenu = new JMenu("window");
		windowMenu.addSeparator();
		menuBar.add(windowMenu);
		JMenu showMenu = new JMenu("Show");
		JMenuItem showPrefs = new JMenuItem("Preferencias...");
		JMenuItem showFormItem = new JMenuItem("Person Item");
		showMenu.add(showFormItem);

		JMenuItem checkBoxShowFormItem = new JCheckBoxMenuItem("Person Item");
		checkBoxShowFormItem.setSelected(true);
		showMenu.add(checkBoxShowFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(showPrefs);
		////////////////////////////////////////////////////////////////////////////
		//usuario y nombre a la izquierda
		menuBar.add(Box.createHorizontalGlue());
		jtextUsuario.setMaximumSize( jtextUsuario.getPreferredSize() );	
		menuBar.add(jNombreUsuario);
		menuBar.add(jtextUsuario);

		//  menu checkbox
		checkBoxShowFormItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				// declare variable en castant le checkbox
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				if (menuItem.isSelected()) {
					splitPanel1.setDividerLocation((int) formPanelBotonesCliente
							.getMinimumSize().getWidth());
				}
				// permet d´afficher ou non le FormPanel si click ou pas
				formPanelBotonesCliente.setVisible(menuItem.isSelected());
			}
		});

		// on ajoute un actionListener a Preferencias du menu
		showPrefs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);
			}
		});

		// on utilise Preferences pour sauver les passwords et usuario de
		// preferencias avec interface PrefsListener
		// crée une classe anonyme avec cette interface
		prefsDialog.setPrefsListener(new PrefsListener() {
			@Override
			public void preferencesSet(String user, String password,
					Integer port) {
				prefs.put("user", user);
				prefs.put("password", password);
				prefs.putInt("port", port);

			}

		});
		String usera = prefs.get("user", "");
		String password = prefs.get("password", "");
		Integer port = prefs.getInt("port", 3306);
		prefsDialog.setDefaults(usera, password, port);

		// pour sortir en appuyant sur crtl.x
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		importMenuData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				ActionEvent.CTRL_MASK));
		showPrefs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		
		// pour sortir en appuyant sur exit
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// pour creer le message dialogue
				int action = JOptionPane.showConfirmDialog(FirstFrame.this,
						"Quieres salir de la app?", "Confirmar salida",
						JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					// ecoute a la methode windowClosing
					WindowListener[] listeners = getWindowListeners();
					for (WindowListener listener : listeners) {
						listener.windowClosing(new WindowEvent(FirstFrame.this,0));
					}
				}
			}
		});
		fileChooser.addChoosableFileFilter(new FileFilterToolBar()) ;
		importMenuData.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
			
			if(fileChooser.showOpenDialog(FirstFrame.this)==JFileChooser.APPROVE_OPTION) {		
			}				
			}
		});
		exportMenuData.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			if(fileChooser.showSaveDialog(FirstFrame.this)==JFileChooser.APPROVE_OPTION) {
				
			}				
			}
		});
		

		return menuBar;
	}
	
	
	
	// Mostrar panel West Documento
	public void getDocumento(){
		tablePanelCliente.definirData(controller.getCliente());
		tablePanelStock.definirDataStock(controller.getArticulo());
		tablePanelProveedor.definirDataProveedor(controller.getProveedor());
		
		add(cards,BorderLayout.CENTER);
		cardLayout.show(cards, "panelcentralDocumento");
		add(cards1,BorderLayout.WEST);
		cardLayout.show(cards1, "formPanelDocument");
	}
	
	// Mostrar panel West stock
	public void getStock() {	
		tablePanelCliente.definirData(controller.getCliente());
		tablePanelStock.definirDataStock(controller.getArticulo());
		tablePanelProveedor.definirDataProveedor(controller.getProveedor());
		add(cards,BorderLayout.CENTER);
		cardLayout.show(cards, "panelcentralStock");	
		add(cards1,BorderLayout.WEST);
		cardLayout.show(cards1, "formPanelStock");
	}
	
	
	// Mostrar panel West clientes
	public void getClientes() {
		tablePanelCliente.definirData(controller.getCliente());
		tablePanelStock.definirDataStock(controller.getArticulo());
		tablePanelProveedor.definirDataProveedor(controller.getProveedor());
		add(cards,BorderLayout.CENTER);
		cardLayout.show(cards, "panelcentralCliente");
		add(cards1,BorderLayout.WEST);
		cardLayout.show(cards1, "formPanelBotonesCliente");
	}
	
	// Mostrar panel West proveedor
		public void getProveedor() {
			tablePanelCliente.definirData(controller.getCliente());
			tablePanelStock.definirDataStock(controller.getArticulo());
			tablePanelProveedor.definirDataProveedor(controller.getProveedor());
			add(cards,BorderLayout.CENTER);
			cardLayout.show(cards, "panelcentralProveedor");
			add(cards1,BorderLayout.WEST);
			cardLayout.show(cards1, "formPanelProveedor");
		}
		
	//  Mostrar paneles centrales Clientes
	public void getTablePanel() {
		try {
			controller.visualizarCliente();
		} catch (SQLException e) {
			System.out.println("Erreur:"+ e);
			e.printStackTrace();
		}
		add(cards, BorderLayout.CENTER);		
		cardLayout.show(cards, "visualisar");
	}

	public void getModificarPanel() {
		jPanelModificarCliente.setText();
		add(cards, BorderLayout.CENTER);		
		cardLayout.show(cards, "editar");
	}

	public void getCrearClientePanel() {
		jPanelCrearCliente.setText();
		add(cards, BorderLayout.CENTER);	
		cardLayout.show(cards, "crear");

	}
	
//  Mostrar paneles centrales Stock
	public void getTablePanelStock() {	
		try {
			controller.visualizarStock();
		} catch (SQLException e) {
			System.out.println("Erreur:"+ e);
			e.printStackTrace();
		}
		add(cards, BorderLayout.CENTER);		
		cardLayout.show(cards, "visualisarStock");
	}
	
	public void getAnadirArticuloPanel() {
		add(cards, BorderLayout.CENTER);		
		cardLayout.show(cards, "anadir producto");
	}
	
	
	
	//Proveedores
	
//  Mostrar paneles centrales Proveedores
	public void getTablePanelProveedor() {
		try {
			controller.visualizarProveedor();
		} catch (SQLException e) {
			System.out.println("Erreur:"+ e);
			e.printStackTrace();
		}
		add(cards, BorderLayout.CENTER);		
		cardLayout.show(cards, "visualisarProveedor");
	}
	public void getModificarPanelProveedor() {
		jPanelModificarProveedor.setText();
		add(cards, BorderLayout.CENTER);		
		cardLayout.show(cards, "editarProveedor");
	}
	public void getCrearProveedorPanel() {
		jPanelCrearProveedor.setText();
		add(cards, BorderLayout.CENTER);		
		cardLayout.show(cards, "crearProveedor");
	}

	//Documentos
//  Mostrar paneles centrales Documentos
	public void getFacturaProveedorPanel() {
		add(cards, BorderLayout.CENTER);
		cardLayout.show(cards, "crearFacturaProveedor");
		
	}
	
//  Mostrar pdf  Documentos
	public void getFacturaProveedorPdf() {
		jPanelVentana.setVisible(true);
	}

	public class JFrameVentana extends JDialog  {
		private static final long serialVersionUID = 1L;
		private JLabel texto;
		private JTextField caja; 
		private JButton okButton,cancelButton;
		
		public JFrameVentana(JFrame parent) {
			super(parent, "Factura", false);
			setSize(340, 250);
			setLocationRelativeTo(parent);
			 
			 texto = new JLabel();
		     caja = new JTextField(10);
		     okButton = new JButton("Validar");
		     cancelButton=new JButton("Cancel");
		     
		texto.setText("Numero de la factura a imprimir"); 
		caja.setFont(TipoPaneles.jTextFieldTipo);
		initCompo();
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int nume=Integer.parseInt(caja.getText());			
				try {
					File file = new File(CreacionPdf.DEST);
			        file.getParentFile().mkdirs();
					int idSupInv=controller.cargaridSupplInvoice(nume);
					supplierInvoice.setIdSupplierInvoices(idSupInv);
					new CreacionPdf().createPdf(supplierInvoice);
					
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		}
		public void initCompo () {
			JPanel controlsPanel = new JPanel();
			JPanel buttonsPanel = new JPanel();
			int space = 15;
			Border spaceBorder = BorderFactory.createEmptyBorder(space, space,
					space, space);
			Border titleBorder = BorderFactory
					.createTitledBorder("Factura preferencias");

			controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,
					titleBorder));

			controlsPanel.setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			gc.gridy = 0;
			Insets rightPad = new Insets(0, 0, 0, 15);
			Insets noPad = new Insets(0, 0, 0, 0);
			// /premiere ligne////
			gc.weightx = 1;
			gc.weighty = 1;
			gc.fill = GridBagConstraints.NONE;
			
			
			gc.gridx = 0;
			gc.anchor = GridBagConstraints.WEST;
			gc.insets = rightPad;
			controlsPanel.add(texto, gc);
			gc.gridy=1;
	
			
			gc.insets = noPad;
			controlsPanel.add(caja, gc);
			// ///Ligne des bouttons///
			gc.gridy++;
			gc.gridx = 0;
			buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonsPanel.add(okButton);
			buttonsPanel.add(cancelButton);
			// met les deux boutons de la meme taille
			Dimension btnSize = cancelButton.getPreferredSize();
			okButton.setPreferredSize(btnSize);

			// / ajoute un sub panel à dialogue
			setLayout(new BorderLayout());
			add(controlsPanel, BorderLayout.CENTER);
			add(buttonsPanel, BorderLayout.SOUTH);
			
		
		}

	}
}




