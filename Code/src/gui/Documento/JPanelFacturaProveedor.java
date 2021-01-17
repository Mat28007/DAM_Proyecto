package gui.Documento;





import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import org.hibernate.HibernateException;
import com.toedter.calendar.JDateChooser;
import controller.Controller;
import tablas.Articulo;
import tablas.Categoria;
import tablas.Proveedor;
import tablas.SupplierInvoiceLineItems;
import tablas.SupplierInvoices;
import tipo.TipoPaneles;
import colores.JLabelGradientColorHorizontal;

public class JPanelFacturaProveedor extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabelGradientColorHorizontal tituloLabel;	
	private JLabel []labels=new JLabel[9];
	private JTextField[] text = new JTextField[5]; 
	private JFormattedTextField textFieldPrecioCompra;
	private int cantidad,ivaIndex;
	private double precioCompra, totalSinIva,iva,totalFactura;
	private JComboBox<String> proveedorComboBox;
	private JComboBox<String> categoriaComboBox;
	private JComboBox<String> articuloComboBox;
	private JComboBox<String> ivaComboBox;
	private PanelFacturarProveedor panelFacturarProveedor;
	private PanelValidarFactura panelValidarFactura;
	private	PanelBotonFactura panelBotonFactura;
	private PanelFacturarProveedor2 panelFacturarProveedor2;
	private JButton validarFecha,validarSupplierInvoice,anadirArticuloJButton,registrarJButton;
	private Controller controller=new Controller();
	private JDateChooser dateChooser;

	private Proveedor p=new Proveedor();
	private SupplierInvoices si=new SupplierInvoices();
	private SupplierInvoiceLineItems sili=new SupplierInvoiceLineItems();
	private Articulo a=new Articulo();


	public JPanelFacturaProveedor(){
	

		 
		dateChooser = new JDateChooser();
		proveedorComboBox=new JComboBox<String>();
		categoriaComboBox=new JComboBox<String>();
		articuloComboBox=new JComboBox<String>();
		String[] IvaArray = new String[] {"0% Intracomunitario","4% Superreducido","10% Reducido","21% Normal" };
		ivaComboBox=new JComboBox<String>(IvaArray);	
		registrarJButton=new JButton("Registrar Factura");
		validarFecha=new JButton("Validar");
		validarSupplierInvoice=new JButton("Validar Proveedor");
		anadirArticuloJButton=new JButton("Añadir Articulo a la factura");
		textFieldPrecioCompra=new JFormattedTextField();
		textFieldPrecioCompra.setFont(TipoPaneles.jTextFieldTipo); 
		textFieldPrecioCompra.setColumns(10);
		
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel();    //Llenamos el array de etiquetas
			labels[i].setFont(TipoPaneles.jLabelNormalTipo);
		 }
		 for (int i = 0; i < text.length; i++) {
			text[i] = new JTextField(10);
            text[i].setFont(TipoPaneles.jTextFieldTipo);
		}
		
		 tituloLabel= new JLabelGradientColorHorizontal();
		 tituloLabel.setFont(TipoPaneles.jJabelTituloTipo);
		 tituloLabel.setText("Factura de Compra");
		 /*
		    text[0] = new JTextField(10);//nºfactura
	        text[2] = new JTextField(10);//cantidad
	        text[4]=  new JTextField(10);//total
	*/
		 	setLayout(new BorderLayout());
	        setBackground(Color.white);
			int space = 2;
	        Border innerBorder = BorderFactory.createTitledBorder("Factura Proveedor");
			Border outerBorder = BorderFactory.createEmptyBorder(space, space, space, space);
			setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
			labels[0].setText("Numero de Factura");
			labels[1].setText("Fecha Factura");
			labels[2].setText("Seleccionar un proveedor");
			labels[3].setText("Seleccionar una categoria");
			labels[4].setText("Seleccionar un articulo");		
			labels[5].setText("Precio sin IVA");
			labels[6].setText("Cantidad");
			labels[7].setText("IVA");
			labels[8].setText("Importe Total");

			panelFacturarProveedor=new PanelFacturarProveedor();
			add(panelFacturarProveedor,BorderLayout.NORTH);
			
		    panelFacturarProveedor2=new PanelFacturarProveedor2();
			add(panelFacturarProveedor2,BorderLayout.CENTER);
			panelFacturarProveedor2.setVisible(false);
			panelValidarFactura = new PanelValidarFactura();
	
		
			
			panelBotonFactura=new PanelBotonFactura();
			add(panelBotonFactura,BorderLayout.SOUTH);
			panelBotonFactura.setVisible(false);

			//nºfactura
			 text[0].addKeyListener(new KeyAdapter(){
			    @Override
				public void keyTyped(KeyEvent e)
			    {
			    soloDigitos(e,text[0]);
			    }
			 });
			//precio compra
			 textFieldPrecioCompra.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					comprobarPrecios(e, textFieldPrecioCompra);
				}
				@Override
				public void keyReleased(KeyEvent e) {
				}
				@Override
				public void keyPressed(KeyEvent e) {
				}
			 });
			//cantidad
			 text[2].addKeyListener(new KeyAdapter(){
				    @Override
					public void keyTyped(KeyEvent ev)
				    {
				   soloDigitos(ev,text[2]);
				    }
			 });
			//total
			 text[4].addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		                super.mouseReleased(e);                    
		                if(textFieldPrecioCompra.getText().isEmpty() && text[2].getText().isEmpty() ){
		      			  JOptionPane.showMessageDialog(null, "precio y cantidad no pueden estar vacios"); 
		      		  }else{
		      			text[4].setText(String.valueOf(sumarArticulos())+"€");
		      		  }
		            }
		        });
			 
			
			
		 
			validarFecha.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					String tx1=text[0].getText();
					
					
					int numFac = Integer.parseInt(text[0].getText());
					//si numero factura y fecha no son  vacios  
					if(!tx1.equals("")&& dateChooser.getDate() != null){
						try {
							// comprobar si numero factura ya existe
							if (controller.existeFactura(numFac) == false) {
							//metodo consular lista Proveedor	
							ArrayList<Proveedor> listaProveedor;
							listaProveedor=controller.consultarListaProveedor();
							//rellenamos el combobox
							for (int i = 0; i < listaProveedor.size(); i++) {
								proveedorComboBox.addItem(listaProveedor.get(i).getDenominacionSocial());
							}
							panelFacturarProveedor2.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "Numero de factura ya existe");
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Rellenar los campos");
					}
					
				}
			});
			
		validarSupplierInvoice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date dd = new Date(dateChooser.getDate().getTime());
				int index = proveedorComboBox.getSelectedIndex();
				int numFac = Integer.parseInt(text[0].getText());
				
				try {
						ArrayList<Categoria> listaCategoria;
						listaCategoria = controller.consultarListaCategoria();
						for (int i = 0; i < listaCategoria.size(); i++) {
							categoriaComboBox.addItem(listaCategoria.get(i).getNombreCategoria());
						}
						p.setId(index);
						si.setFecha(dd);
						si.setNumeroFactura(numFac);
						si.setProveedor(p);
						controller.saveSupplierInvoice(si);

						panelFacturarProveedor2.setVisible(false);
						add(panelValidarFactura, BorderLayout.CENTER);
						panelValidarFactura.setVisible(true);
						panelBotonFactura.setVisible(true);
					
				} catch (SQLException ev) {
					ev.printStackTrace();
				}
			}
		});
			
			
			categoriaComboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					articuloComboBox.removeAllItems();
					articuloComboBox.setSelectedItem("select");		
					String textoCombo = (String) categoriaComboBox.getSelectedItem();
					ArrayList<Articulo> listaArticulo;
					try {
						listaArticulo=controller.consultarListaArticulo(textoCombo);			
						for (int i = 0; i < listaArticulo.size(); i++) {
							articuloComboBox.addItem(listaArticulo.get(i).getNombreArticulo());
						}
					} catch (SQLException ev) {
						ev.printStackTrace();
						}
					}
			});

		anadirArticuloJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ivaSql=0;
				
				if (textFieldPrecioCompra.getText().isEmpty() && text[2].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"error");
				}
				else{
					double art = sumarArticulos();
					totalFactura = totalFactura + art;
					try {
						int numFac=Integer.parseInt(text[0].getText());
						String itemArticulo=(String) articuloComboBox.getSelectedItem();
						int cantidadC=Integer.parseInt(text[2].getText());
						Double precioCompra=Double.parseDouble(textFieldPrecioCompra.getText());
						ivaIndex= ivaComboBox.getSelectedIndex();
						
						if(ivaIndex==0){
							ivaSql=0;
			             }
			             else if(ivaIndex==1){
			            	 ivaSql=4;
			             }
			             else if(ivaIndex==2){
			            	 ivaSql=10;
			             }
			             else{ivaSql=21;}

						if(text[2].getText().length() != 0 || !textFieldPrecioCompra.getText().isEmpty()){
							int idArti=	controller.cargarid(itemArticulo);
							int idSupInv=controller.cargaridSupplInvoice(numFac);
							a.setIdarticulo(idArti);
							
							si.setIdSupplierInvoices(idSupInv);
							sili.setSupplierInvoices(si);
							sili.setArticulo(a);
							sili.setCantidadComprada(cantidadC);
							sili.setPrecioCompra(precioCompra);
							sili.setTaxRateCharged(ivaSql);
							
							controller.saveSupplierInvoiceLine(sili);
							
							
							
						}else {
							JOptionPane.showMessageDialog(null, "Rellenar los campos");
						}
						
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					proveedorComboBox.setEditable(false);
					proveedorComboBox.setEnabled(false);
					dateChooser.setEnabled(false);
					text[0].setEditable(false);
				}
				
			}
		
		});
		
			
			//Registrar Factura
			registrarJButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
				if (textFieldPrecioCompra.getText().isEmpty() && text[2].getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							  "error");
				}else{
					double art = sumarArticulos();
					totalFactura = totalFactura + art;
					
					
					try {
					
						
						int numFact= Integer.parseInt(text[0].getText());
					//	Date fech=dateChooser.getDate();
						String proveedor = (String) proveedorComboBox.getSelectedItem();
						String cat=(String) categoriaComboBox.getSelectedItem();
						String arti=(String) articuloComboBox.getSelectedItem();
						double precio=Integer.parseInt(textFieldPrecioCompra.getText());
						int cant=Integer.parseInt(text[2].getText());
						String ivaC=(String) ivaComboBox.getSelectedItem();
						/*
						documentoCompra.setNumeroFactura(numFact);
						documentoCompra.setFecha(fech);
						documentoCompra.setProveedor(proveedor);
						documentoCompra.setCategoria(cat);
						documentoCompra.setArticulo(arti);
						documentoCompra.setPrecio(precio);
						documentoCompra.setCantidad(cant);
						documentoCompra.setTotal(totalFactura);
						documentoCompra.setIva(ivaC);
						
						session.save(documentoCompra);
						*/
					
					} catch (HibernateException ev) {
				
						
						ev.printStackTrace();
					} finally {
						
					}
					
					
					
				
				}
				JOptionPane.showMessageDialog(null, "factura salvada correctamente"); 
				text[0].setText("");
				dateChooser.setDate(null);
				panelValidarFactura.setVisible(false);
				}
			});
			
	}
	
	
	
	 
	class PanelFacturarProveedor extends JPanel {
		private static final long serialVersionUID = 1L;
		
		public PanelFacturarProveedor(){
			setLayout(new GridBagLayout());
		
			setBorder(BorderFactory.createCompoundBorder());			
			GridBagConstraints gc = new GridBagConstraints();
			setBackground(Color.WHITE);
		
			gc.gridx = 0;	
			gc.gridy = 0;
			gc.gridheight = 1; 
			gc.anchor = GridBagConstraints.WEST;
			gc.fill=GridBagConstraints.HORIZONTAL; 
			gc.ipady = 15; //make this component tall
			gc.weightx  = 1;
			//gc.gridwidth = GridBagConstraints.REMAINDER;
			gc.gridwidth = 5;
			add(tituloLabel, gc);	
			
			// Restauramos valores por defecto
			gc.fill=GridBagConstraints.NONE;	
			gc.gridheight = 1; 
			gc.gridwidth = 1;
			gc.weightx  = 0;
			gc.ipady = 0; 
			gc.insets = new Insets(15, 5, 0, 10);	
			
			// Segunda Fila/
			gc.gridy = 1;
			gc.gridx = 0;
			gc.weightx = 0;		
			add(labels[0], gc);
			
			gc.gridx=1;
			gc.weightx  = 1;
			add(text[0], gc);
			
			// 3 componente/
			

			// 3 Fila/
			gc.gridx = 2;
			gc.weightx = 0;	
			add(labels[1], gc);
			
			gc.gridx=3;
			gc.weightx  = 1;
			add(dateChooser, gc);
	
			gc.gridx=4;
			gc.weightx  = 1;
			add(validarFecha, gc);

			//ultima//
			gc.gridy=2;
			gc.weighty = 2;						
			add(new JLabel(), gc);

			
			
		}

	}
	
	class PanelFacturarProveedor2 extends JPanel{
		public PanelFacturarProveedor2() {
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createCompoundBorder());
			GridBagConstraints gc = new GridBagConstraints();
			setBackground(Color.WHITE);
			gc.anchor = GridBagConstraints.WEST;
			// Restauramos valores por defecto
			gc.fill=GridBagConstraints.NONE;	
			gc.gridheight = 1; 
			gc.gridwidth = 1;
			gc.weightx  = 0;
			gc.ipady = 0; 
			gc.insets = new Insets(15, 5, 0, 10);	

			//select proveedor
			gc.gridy=0;
			gc.gridx = 0;
			gc.weightx = 0;
			add(labels[2], gc);
			gc.gridx = 1;
			gc.weightx = 1;
			add(proveedorComboBox, gc);
			
			gc.gridx=2;
			gc.weightx  = 1;
			add(validarSupplierInvoice, gc);
			
			
			//ultima//
			gc.gridy=2;
			gc.weighty = 2;						
			add(new JLabel(), gc);
		}
	}

	class PanelValidarFactura extends JPanel {
		private static final long serialVersionUID = 1L;

		public PanelValidarFactura() {
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createCompoundBorder());
			GridBagConstraints gc = new GridBagConstraints();
			setBackground(Color.WHITE);
			
			gc.anchor = GridBagConstraints.WEST;
			// Restauramos valores por defecto
			gc.fill=GridBagConstraints.NONE;	
			gc.gridheight = 1; 
			gc.gridwidth = 1;
			gc.weightx  = 0;
			gc.ipady = 0; 
			gc.insets = new Insets(15, 5, 0, 10);					


			// 2 Fila/
			gc.gridy = 1;
			gc.gridx = 0;
			gc.gridwidth = 1;
			gc.weightx = 0;
			add(labels[3], gc);

			gc.gridx = 1;
			// gc.gridwidth = GridBagConstraints.REMAINDER;
			gc.weightx = 1;
			add(categoriaComboBox, gc);

			// 3 Fila/
			gc.gridy = 2;
			gc.gridx = 0;
			gc.gridwidth = 1;
			gc.weightx = 0;
			add(labels[4], gc);

			gc.gridx = 1;
			// gc.gridwidth = GridBagConstraints.REMAINDER;
			gc.weightx = 1;
			add(articuloComboBox, gc);

			// 4 Fila/
			gc.gridy = 3;
			gc.gridx = 0;
			gc.gridwidth = 1;
			gc.weightx = 0;
			add(labels[5], gc);

			gc.gridx = 1;
			gc.gridwidth = GridBagConstraints.REMAINDER;
			gc.weightx = 1;
			add(textFieldPrecioCompra, gc);

			// 5 Fila/
			gc.gridy = 4;
			gc.gridx = 0;
			gc.gridwidth = 1;
			gc.weightx = 0;
			add(labels[6], gc);

			gc.gridx = 1;
			gc.gridwidth = GridBagConstraints.REMAINDER;
			gc.weightx = 1;
			add(text[2], gc);
			
			// 6 Fila/
			gc.gridy = 5;
			gc.gridx = 0;
			gc.gridwidth = 1;
			gc.weightx = 0;
			add(labels[7], gc);
			
			gc.gridx = 1;
			gc.gridwidth = GridBagConstraints.REMAINDER;
			gc.weightx = 1;
			add(ivaComboBox, gc);
			
			
			

			// 7 Fila/
			gc.gridy = 6;
			gc.gridx = 0;
			gc.gridwidth = 1;
			gc.weightx = 0;
			add(labels[8], gc);

			gc.gridx = 1;
			gc.gridwidth = GridBagConstraints.REMAINDER;
			gc.weightx = 1;
			add(text[4], gc);

			//ultima//
			gc.gridy=7;
			gc.weighty = 1;						
			add(new JLabel(), gc);
		}

	}
	class PanelBotonFactura extends JPanel {
		private static final long serialVersionUID = 1L;

		public PanelBotonFactura() {
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createLoweredBevelBorder());
			
			GridBagConstraints gc = new GridBagConstraints();
			setBackground(Color.white);
			gc.gridheight = 1;
			gc.anchor = GridBagConstraints.EAST;
			gc.gridwidth = 1;
			gc.weightx = 0;			
			gc.insets = new Insets(15, 5, 15, 10);
			gc.gridy = 0;
			gc.gridx = 0;
			add(anadirArticuloJButton, gc);
			gc.fill = GridBagConstraints.REMAINDER;
			gc.gridx = 1;
			add(registrarJButton, gc);
		}
		
		}
	public double  sumarArticulos(){
		double total=0.0;
		 precioCompra = Double.parseDouble(textFieldPrecioCompra.getText());
		 cantidad = Integer.parseInt(text[2].getText());
		  ivaIndex= ivaComboBox.getSelectedIndex();
		  if(textFieldPrecioCompra.getText().isEmpty() || text[2].getText().isEmpty() ){
			  JOptionPane.showMessageDialog(null, "precio y cantidad no pueden estar vacios"); 
		  }
		  else  if(precioCompra!=0 &&cantidad!=0){
        	 if(ivaIndex==0){
             	iva=0;
             }
             else if(ivaIndex==1){
             	iva=0.04;
             }
             else if(ivaIndex==2){
             	iva=0.01;
             }
             else{iva=0.21;}
             totalSinIva=precioCompra*cantidad;
             total=(totalSinIva*iva)+totalSinIva;
             
        }else{
        	JOptionPane.showMessageDialog(null, "precio y cantidad no pueden estar iguales a 0");
        }

        return total;
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
	public void soloDigitos(KeyEvent ek,JTextField jt){
		  char caracter = ek.getKeyChar();
	       int limite=7;
	       // Verificar si la tecla pulsada no es un digito
	       if((caracter < '0') ||(caracter > '9') &&(caracter != '\b' /*corresponde a BACK_SPACE*/))  
	       {
	          ek.consume();  // ignorar el evento de teclado
	          JOptionPane.showMessageDialog(null, "solo se puede introducir digitos"); 
	       }
	       if(jt.getText().length()== limite){
	    	   ek.consume();
	    	   JOptionPane.showMessageDialog(null, "no se puede introducir más de 7 digitos");  
	       }
	}
}
