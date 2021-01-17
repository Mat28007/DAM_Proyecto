package gui.GestionStock;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.ArrayList;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;



import tablas.Articulo;
import tablas.Categoria;
import model.ComprobarJtextfielprecio;

import tipo.TipoPaneles;

import colores.JLabelGradientColorHorizontal;
import controller.Controller;


public class JPanelRegistrarArticulo extends JPanel   {
	private static final long serialVersionUID = 1L;

	private JLabelGradientColorHorizontal labTitulo, labTitulo2,labTitulo3;
	private JTextField categoriaText,articuloText,newArticuloText;
	
	private	JFormattedTextField newPrecioVentaText,precioVentaText,precioCompraText,newPrecioCompraText;
	private JFormattedTextField[] text = new JFormattedTextField[4]; 
	
	private JTextArea descripcionText,newDescripcionText;
	
	private JLabel categoriaArticuloLabel, selectCategoriaLabel,
			descripcionArticuloLabel, precioVentaLabel, precioCompraLabel,
			articuloLabel, newArticuloLabel,newDescripcionArticuloLabel,newPrecioVentaLabel,newPrecioCompraLabel,nuevoArticuloLabel;
			
	private JLabel[] label = new JLabel[11]; 
	
	private JScrollPane jScrollPane,newJScrollPane,newJScrollPaneall;
	private JButton categoriaButton,crearArticuloButton,modificarArticuloButton;
	private PanelRegistrarArticulo panelRegistrarArticulo;
	private PanelRegistrarArticulo2 panelRegistrarArticulo2;
	private PanelRegistrarArticuloNoExiste panelRegistrarArticuloNoExiste;
	private Controller controller = new Controller();
	private Articulo art = new Articulo();
	private Categoria oCat=new Categoria();
	private JComboBox<String> comboCategoria,comboArticulo;

	public JPanelRegistrarArticulo() {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		 for (int i = 0; i < text.length; i++) {
			 text[i] = new JFormattedTextField();
             text[i].setFont(TipoPaneles.jTextFieldTipo); 
             text[i].setColumns(30);
             text[i].addKeyListener(new ComprobarJtextfielprecio(this,text[i]));
		}

	    newPrecioVentaText =text[0];
	    newPrecioCompraText=text[1];
	    precioVentaText=text[2];
	    precioCompraText=text[3];
	    
	    
	    
		comboCategoria=new JComboBox<>();
		comboArticulo=new JComboBox<>();
		comboArticulo.setEditable(true);
		categoriaButton = new JButton();
		categoriaButton.setText("OK");
		
		crearArticuloButton= new JButton();
		crearArticuloButton.setText("Crear");
		modificarArticuloButton= new JButton();
		modificarArticuloButton.setText("Cargar");

		categoriaText = new JTextField(30);
		categoriaText.setFont(TipoPaneles.jTextFieldTipo);
		
		articuloText= new JTextField(30);
		articuloText.setFont(TipoPaneles.jTextFieldTipo); 	
		
		newArticuloText= new JTextField(30);
		newArticuloText.setFont(TipoPaneles.jTextFieldTipo);

		descripcionText = new JTextArea(2,30);
		descripcionText.setLineWrap(true);
		descripcionText.setWrapStyleWord(true);
		descripcionText.setFont(TipoPaneles.jTextFieldTipo);
		
		newDescripcionText= new JTextArea(2,30);
		newDescripcionText.setLineWrap(true);
		newDescripcionText.setWrapStyleWord(true);
		newDescripcionText.setFont(TipoPaneles.jTextFieldTipo);
		
		
		jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(descripcionText);
		jScrollPane.setWheelScrollingEnabled(true);
		
		
		newJScrollPane= new JScrollPane();
		newJScrollPane.setViewportView(newDescripcionText);
		newJScrollPane.setWheelScrollingEnabled(true);
		
		
		
		labTitulo = new JLabelGradientColorHorizontal();
		labTitulo.setFont(TipoPaneles.jJabelTituloTipo);
		labTitulo.setText("Registrar nuevo articulo");

		labTitulo2 = new JLabelGradientColorHorizontal();
		labTitulo2.setFont(TipoPaneles.jJabelTituloTipo);
		labTitulo2.setText("Registrar nuevo articulo 2");

		labTitulo3 = new JLabelGradientColorHorizontal();
		labTitulo3.setFont(TipoPaneles.jJabelTituloTipo);
		labTitulo3.setText("Crear del todo el articulo");
	
		for(int j=0;j<label.length;j++){
			label[j]=new JLabel();
			label[j].setFont(TipoPaneles.jLabelNormalTipo);
		}
		categoriaArticuloLabel=label[0];
		selectCategoriaLabel=label[1];
		descripcionArticuloLabel=label[2];
		newDescripcionArticuloLabel=label[3];
		precioVentaLabel=label[4];
		newPrecioVentaLabel=label[5];
		precioCompraLabel=label[6];
		newPrecioCompraLabel=label[7];
		articuloLabel=label[8];
		newArticuloLabel=label[9];
		nuevoArticuloLabel=label[10];
		
		categoriaArticuloLabel.setText("Categoria del articulo");
		selectCategoriaLabel.setText("Selecionnar categoria");
		descripcionArticuloLabel.setText("Descripción Articulo");
		newDescripcionArticuloLabel.setText("Descripción Articulo");
		precioVentaLabel.setText("Precio de venta al cliente");		
		newPrecioVentaLabel.setText("Precio de venta al cliente");	
		precioCompraLabel.setText("Precio de compra al proveedor");
		newPrecioCompraLabel.setText("Precio de compra al proveedor");
		articuloLabel.setText("Nombre articulo");
		newArticuloLabel.setText("Nombre articulo");
		nuevoArticuloLabel.setText("O Crear Nuevo articulo");
		

		panelRegistrarArticulo = new PanelRegistrarArticulo();
		panelRegistrarArticuloNoExiste= new PanelRegistrarArticuloNoExiste();
		panelRegistrarArticulo2 = new PanelRegistrarArticulo2();
		newJScrollPaneall= new JScrollPane(panelRegistrarArticulo2);
		
		add(panelRegistrarArticulo, BorderLayout.NORTH);
		panelRegistrarArticuloNoExiste.setVisible(false);
		newJScrollPaneall.setVisible(false);

		
		categoriaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
				String categoria = categoriaText.getText();
			    Object[] message = {"La categoria '"+categoria+"' no existe ¿Desea crearla? "};
			    Object[] options = { "Yes", "No" };
			    oCat.setNombreCategoria(categoria);
				if (!categoria.equals("")) {
					try {
						if (controller.existeCategoria(categoriaText.getText()) == false) {
						    int n = JOptionPane.showOptionDialog(new JFrame(),
						            message, "",
						            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						            options, options[1]);
						    if(n == JOptionPane.YES_OPTION){ // Afirmative
						    	panelRegistrarArticulo2.setVisible(false);
						    	add(panelRegistrarArticuloNoExiste, BorderLayout.CENTER);
								panelRegistrarArticuloNoExiste.setVisible(true);								
								controller.crearCategoria(oCat);

						    }
						    else if(n == JOptionPane.NO_OPTION){ // negative
						    	panelRegistrarArticuloNoExiste.setVisible(false);
						    	add(panelRegistrarArticulo2,BorderLayout.CENTER);
						    	panelRegistrarArticulo2.setVisible(true);
						    	cargarCategoriasComboBox();
						    	categoriaText.setText("");
						    }
						    else  if(n == JOptionPane.CLOSED_OPTION){ // closed the dialog
						    }
						} else {
							JOptionPane.showMessageDialog(null,"La categoria ya existe.");
							cargarCategoriasComboBox();
							categoriaText.setText("");
							panelRegistrarArticuloNoExiste.setVisible(false);
							add(panelRegistrarArticulo2,BorderLayout.CENTER);
					    	panelRegistrarArticulo2.setVisible(true);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Campo vacio");
				}
			}
		});
		
		comboCategoria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboArticulo.removeAllItems();
				comboArticulo.setSelectedItem("select");		
				String textoCombo = (String) comboCategoria.getSelectedItem();
				ArrayList<Articulo> listaArticulo;
				try {
					listaArticulo=controller.consultarListaArticulo(textoCombo);	
					for (int i = 0; i < listaArticulo.size(); i++) {
						 comboArticulo.addItem(listaArticulo.get(i).getNombreArticulo());
					}
				} catch (SQLException ev) {
					ev.printStackTrace();
				}
				}
		});
		
		comboArticulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String textoComboArt = (String) comboArticulo.getSelectedItem();
				Articulo a;
				try {
					a = controller.visualizarDetalleArticulo(textoComboArt);
					descripcionText.setText(a.getDescripcion());
					precioVentaText.setText(String.valueOf(a.getPrecioVenta()));
					precioCompraText.setText(String.valueOf(a.getPrecioCompra()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			});
			
		
		modificarArticuloButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String textoComboCat = (String) comboCategoria.getSelectedItem();
				String nombre_articulo=(String) comboArticulo.getSelectedItem();
				String nombre_articuloNuevo=articuloText.getText();
				String descripcion=descripcionText.getText();
				double precioV=Double.parseDouble(precioVentaText.getText());
				double precioC=Double.parseDouble(precioCompraText.getText());	
				//para crear articulo			
				Categoria cat1=new Categoria(textoComboCat);
				
				if(nombre_articuloNuevo.length()==0){					
						controller.updateArticulo(nombre_articulo, descripcion, precioV, precioC);
						JOptionPane.showMessageDialog(null,"Articulo modificado correctamente");
				}
				else{
				
					
					art.setCategoria(cat1);
					art.setNombreArticulo(nombre_articuloNuevo);			
					art.setDescripcion(descripcion);
					art.setPrecioVenta(precioV);
					art.setPrecioCompra(precioC);
					art.setIdarticulo(art.getIdarticulo());
					try {
						if(controller.existeArticulo(nombre_articuloNuevo)==0){
							controller.crearArticulo(art);
							JOptionPane.showMessageDialog(null,"Articulo creado correctamente");
						}
						else{
							
							JOptionPane.showMessageDialog(null,"Articulo "+nombre_articuloNuevo+" ya existe");
						}
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,"Error");
						e1.printStackTrace();
					}
				}
				
			}
		});

	
		crearArticuloButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String catego=categoriaText.getText();
				Categoria cat1=new Categoria(catego);
				
				String nombre_articulo=newArticuloText.getText();
				String descripcion=newDescripcionText.getText();
				double precioV = Double.parseDouble(newPrecioVentaText.getText());
				double precioC=Double.parseDouble(newPrecioCompraText.getText());

				art.setCategoria(cat1);
				art.setNombreArticulo(nombre_articulo);			
				art.setDescripcion(descripcion);
				art.setPrecioVenta(precioV);
				art.setPrecioCompra(precioC);
				//art.setIdarticulo(art.getIdarticulo());
				// comprobar si articulo existe
				try {
					if(controller.existeArticulo(nombre_articulo)==0){
						controller.crearArticulo(art);
						JOptionPane.showMessageDialog(null,"Articulo creado correctamente");
					}
					else{
						JOptionPane.showMessageDialog(null,"Articulo ya existe");
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	
	}

	class PanelRegistrarArticulo extends JPanel {
		private static final long serialVersionUID = 1L;

		public PanelRegistrarArticulo() {
			setLayout(new GridBagLayout());
			setBackground(Color.white);
			int space = 5;
			Border innerBorder = BorderFactory
					.createTitledBorder("Articulos");
			Border outerBorder = BorderFactory.createEmptyBorder(space, space,
					space, space);
			setBorder(BorderFactory.createCompoundBorder(innerBorder,
					outerBorder));

			GridBagConstraints gc = new GridBagConstraints();
			gc.gridx = 0;
			gc.gridy = 0;
			gc.anchor = GridBagConstraints.WEST;

			// PRIMERA Fila/
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.ipady = 15; // make this component tall
			// Columna 0. Necesita estirarse, ponemos weightx 1
			gc.weightx = 1;
			// El componente ocupa 7 columnas.
			 gc.gridwidth = 4;
			//gc.gridwidth = GridBagConstraints.REMAINDER;
			// El componente ocupa 2 filas.
			gc.gridheight = 1;
			// titulo.setBackground(Color.yellow);
			add(labTitulo, gc);

			// Restauramos valores por defecto
			gc.fill = GridBagConstraints.NONE;
			gc.weightx = 0;
			gc.gridheight = 1;
			gc.ipady = 0;
			gc.insets = new Insets(25, 5, 0, 10);

			// Segunda Fila/
			gc.gridy = 1;
			gc.gridx = 0;
			// El componente ocupa 1 columnas.
			gc.gridwidth = 1;
			add(categoriaArticuloLabel, gc);

			gc.gridx = 1;
			gc.weightx = 0;
			add(categoriaText, gc);

			gc.gridx = 2;
			gc.weightx = 1;
			add(categoriaButton, gc);

			// Ultima//
			gc.gridy = 2;
			gc.weighty = 2;
			add(new JLabel(), gc);

		}
	}

       class PanelRegistrarArticulo2 extends JPanel {
		private static final long serialVersionUID = 1L;
		
		public PanelRegistrarArticulo2() {

			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			int space = 2;
			setBackground(Color.white);
			Border innerBorder = BorderFactory.createTitledBorder("");
			Border outerBorder = BorderFactory.createEmptyBorder(space, space,space, space);
			setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder));

			gc.gridx = 0;
			gc.gridy = 0;
			gc.anchor = GridBagConstraints.WEST;
			gc.gridheight = 1;
				
			//valores por defecto
			gc.fill = GridBagConstraints.NONE;
			gc.ipady = 0;
			gc.gridwidth = 1;
			gc.insets = new Insets(5, 5, 0, 10);	
			
			//Linea 1
			gc.gridy = 0;
			gc.weightx = 0;		
			add(selectCategoriaLabel, gc);

			// lista de categorias
			gc.gridx = 1;
			gc.weightx = 1;
			add(comboCategoria, gc);
			
			//Linea 2 articulo
			gc.gridy = 1;
			gc.gridx = 0;
			gc.weightx = 0;
			add(articuloLabel, gc);
			
			gc.gridx = 1;
			gc.weightx = 1;
			add(comboArticulo, gc);
			
			//valores
			gc.fill = GridBagConstraints.NONE;
			gc.weightx = 0;
			gc.gridwidth = 1;
			
			//Linea 3 articulo
			gc.gridy = 2;
			gc.gridx = 0;
			add(nuevoArticuloLabel, gc);
			
			gc.gridx = 1;
			add(articuloText, gc);

			//Linea 4 descripcion
			gc.gridy = 3;
			gc.gridx = 0;
			add(descripcionArticuloLabel, gc);
			
			gc.gridx = 1;
			gc.weightx = 1;
			//gc.gridheight = 2;
			add(jScrollPane,gc);
			
			//valores
			gc.fill = GridBagConstraints.NONE;
			gc.weightx = 0;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			
			//Linea 5 precios
			gc.gridy = 4;
			gc.gridx = 0;
			add(precioVentaLabel, gc);
			
			gc.gridx = 1;
			add(precioVentaText, gc);
			
			//Linea 6 precios
			gc.gridy = 5;
			gc.gridx = 0;
			add(precioCompraLabel, gc);
			
			gc.gridx = 1;
			add(precioCompraText, gc);
			
			//Linea 7 Boton crear
			
			gc.gridy = 6;
			gc.gridx = 1;
			add(modificarArticuloButton, gc);
			
			
			// Ultima//
			gc.gridy = 7;
			gc.weighty = 2;
			add(new JLabel(), gc);
		}
	}

       
       class PanelRegistrarArticuloNoExiste extends JPanel {
		private static final long serialVersionUID = 1L;
		
		public PanelRegistrarArticuloNoExiste(){
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			int space = 2;
			setBackground(Color.white);
			Border innerBorder = BorderFactory.createTitledBorder("");
			Border outerBorder = BorderFactory.createEmptyBorder(space, space,space, space);
			setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder));
			
			gc.gridx = 0;
			gc.gridy = 0;
			gc.anchor = GridBagConstraints.WEST;
			gc.gridheight = 1;
			
			// PRIMERA Fila/
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.ipady = 15; // make this component tall
			gc.weightx = 1;
			gc.gridwidth = 4;
			add(new JLabel(), gc);
		//	add(labTitulo3, gc);
			
			//Linea 2 articulo
			gc.gridy = 1;
			gc.gridx = 0;
			gc.fill = GridBagConstraints.NONE;
			gc.weightx = 0;
			gc.gridwidth = 1;
			gc.ipady = 0;
			gc.insets = new Insets(25, 5, 0, 10);	
			add(newArticuloLabel, gc);
			
			gc.gridx = 1;
			add(newArticuloText, gc);

			//Linea 3 descripcion
			gc.gridy = 2;
			gc.gridx = 0;
			add(newDescripcionArticuloLabel, gc);
			
			gc.gridx = 1;
			gc.weightx = 1;
			add(newJScrollPane,gc);
			
			//Linea 4 precios
			gc.gridy = 3;
			gc.gridx = 0;
			gc.weightx = 0;
			gc.gridwidth = 1;
			add(newPrecioVentaLabel, gc);
			
			gc.gridx = 1;
			add(newPrecioVentaText, gc);
			
			//Linea 5 precios
			gc.gridy = 4;
			gc.gridx = 0;
			add(newPrecioCompraLabel, gc);
			
			gc.gridx = 1;
			add(newPrecioCompraText, gc);
			
			//Linea 7 Boton crear
			
			gc.gridy = 5;
			gc.gridx = 1;
			add(crearArticuloButton, gc);
			
			
			// Ultima//
			gc.gridy = 6;
			gc.weighty = 2;
			add(new JLabel(), gc);
			
			
		}  	   
       }

	private void cargarCategoriasComboBox() {
		comboCategoria.removeAllItems();
		ArrayList<Categoria> listaCategoria;
		try {
			listaCategoria = controller.consultarListaCategoria();
			for (int i = 0; i < listaCategoria.size(); i++) {
				comboCategoria.addItem(listaCategoria.get(i).getNombreCategoria());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void comprobarPrecios(KeyEvent e, JFormattedTextField jt) {
		char caracter = e.getKeyChar();
		int limite = 8;

		if (jt.getText().length() == limite) {
			e.consume();
			JOptionPane.showMessageDialog(null,
					"no se puede introducir más de 8 digitos");
		}
		if (((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)
                && (caracter != '.') ) {
            e.consume();
			JOptionPane
					.showMessageDialog(null, "solo se puede utilizar el '.'");
		}

		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		format.setRoundingMode(RoundingMode.HALF_UP);
		jt = new JFormattedTextField(format);
		jt.setValue(new Float(3.14));
		// Verificar si la tecla pulsada no es un digito
		if (Character.isLetter(caracter)) {
			e.consume(); // ignorar el evento de teclado
			JOptionPane.showMessageDialog(null,
					"solo se puede introducir digitos");
		}
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
