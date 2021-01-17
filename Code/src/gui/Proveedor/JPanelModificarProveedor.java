package gui.Proveedor;

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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import tablas.Proveedor;
import model.TraitementCodigoPostal.Posicion;
import tipo.TipoPaneles;
import colores.JLabelGradientColorHorizontal;
import controller.Controller;

public class JPanelModificarProveedor extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel[] label = new JLabel[10]; 
	private JTextField[] text = new JTextField[10]; 
	
	private  JLabelGradientColorHorizontal titulo;
	private  JButton boton1,boton2,search;
	private  Controller controller;
	private  PanelConLayout buscarPanelProveedor;
	private  PanelConLayout2 proveedorPanel;
	private  JScrollPane jScrollPane;
	private	JButton btnCpOk = new JButton("");

	public JPanelModificarProveedor() {
		
		 for(int i = 0; i < label.length; i++) {
             label[i] = new JLabel();    //Llenamos el array de etiquetas
             label[i].setFont(TipoPaneles.jLabelNormalTipo);
		 }
		 for (int i = 0; i < text.length; i++) {
			 text[i] = new JTextField();
             text[i].setFont(TipoPaneles.jTextFieldTipo);
		}

		setLayout(new BorderLayout());
		
		titulo= new JLabelGradientColorHorizontal();
		titulo.setFont(TipoPaneles.jJabelTituloTipo);
	
        text[1] = new JTextField(30);
        text[2] = new JTextField(30);
        text[3] = new JTextField(10);
        text[4] = new JTextField(10);
        text[5]=  new JTextField(10);
        text[6] = new JTextField(20);
        text[7] = new JTextField(20);
        text[8] = new JTextField(50);
        
        
        boton1 = new JButton();
        boton2 = new JButton();
        search=new JButton();
        
        buscarPanelProveedor = new PanelConLayout();
		proveedorPanel = new PanelConLayout2();	
		jScrollPane=new JScrollPane(proveedorPanel);	
		controller=new Controller();
		
					setBackground(Color.white);
					int space = 5;
			        Border innerBorder = BorderFactory.createTitledBorder("Modificar Datos");
					Border outerBorder = BorderFactory.createEmptyBorder(space, space, space, space);
					setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
					
					titulo.setText("Datos de la Empresa");
					label[1].setText("Denominación social");
					label[2].setText("Dirección");
					label[3].setText("Codigo Postal");
					label[4].setText("Ciudad");      
					label[5].setText("Provincia");
					label[6].setText("Teléfono");
					label[7].setText("Email");
					label[8].setText("DNI");		        	        
					label[9].setText("Buscar por nombre de Proveedor"); 
			        
			        boton1.setText("Modificar");
			        boton2.setText("Eliminar");
			        search.setIcon(createIcon("/Images/loupe_opt.jpg "));
			        
				     add(buscarPanelProveedor,BorderLayout.NORTH);
					 add(jScrollPane,BorderLayout.CENTER);
					//telefono
				        text[6].addKeyListener(new KeyAdapter(){
						    @Override
							public void keyTyped(KeyEvent e)
						    {
						       char caracter = e.getKeyChar();
						       // Verificar si la tecla pulsada no es un digito
						       if((caracter < '0') ||(caracter > '9'))  
						       {
						          e.consume();  // ignorar el evento de teclado
						          JOptionPane.showMessageDialog(null, "solo se puede introducir digitos"); 
						       }
						    }
						 }); 
					 
					 
				     // Search cliente
					 search.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String buscarTxt = text[8].getText();
								if (!buscarTxt.equals("")) {
									try {
										Proveedor p = controller.visualizarProveedor2(buscarTxt);
										if (p.getDenominacionSocial() == "") {
											JOptionPane.showMessageDialog(null," El Proveedor buscado no existe ");
										} else {
											
											text[1].setText(p.getDenominacionSocial());
											text[2].setText(p.getDireccion());
											text[3].setText(String.valueOf(p.getCodigoPostal()));
											text[4].setText(p.getProvincia());
											text[5].setText(p.getCiudad());
											
											text[6].setText(String.valueOf(p.getTelefono()));
											text[7].setText(p.getEmail());
											
											text[1].setEditable(false);
											text[1].setBackground(Color.gray);
											text[4].setEditable(false);
											text[4].setBackground(Color.gray);
											text[5].setEditable(false);
											text[5].setBackground(Color.gray);
											 
										}
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								} else {
									JOptionPane.showMessageDialog(null, " Rellenar campo ");
								}
							}
						});
					//Update - Modificar//
						boton1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								
									String denomi=text[1].getText();				
									String dom=text[2].getText();
									
									String ciudad=text[4].getText();	
									String provincia=text[5].getText();	
									int tel=Integer.parseInt(text[6].getText());
									String email=text[7].getText();
									if (!text[3].getText().equals("")) {
										if (validarEmail(email)) {
											if (!ciudad.equals("") || !provincia.equals("")) {
												int codpo = Integer.parseInt(text[3].getText());
									controller.updateProveedor(denomi,dom,codpo,ciudad,provincia,tel,email);
									setText();
											} else {
												JOptionPane
														.showMessageDialog(null,
																"Campos ciudad y provincia no pueden estar vacios");
											}
										} else {
											JOptionPane.showMessageDialog(null, "Email incorrecto");
										}
									} else {
										JOptionPane.showMessageDialog(null,
												"Debe indicar un Codigo postal");
									}
							}
							});		
						//delete
						boton2.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String buscarTxt = text[1].getText();
								if (!buscarTxt.equals("")) {
									try {
										controller.deleteProveedor(buscarTxt);
										setText();
									JOptionPane.showMessageDialog(null, "Proveedor eliminado");
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(null, "Error");
									e1.printStackTrace();
								}
								}
							}
							
						});		
						btnCpOk.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
							
								try {
									controller.connectCP();
								} catch (Exception e2) {
									e2.printStackTrace();
								}
								String codigoPostalTxt=text[3].getText();
								try {
									if (!codigoPostalTxt.equals("")) {
										try {
											
											Posicion position =controller.Cp(codigoPostalTxt);
											text[4].setText(position.getPoblacion());
											text[5].setText(position.getProvincia());	
										} catch (Exception e1) {e1.printStackTrace();}
									} else {JOptionPane.showMessageDialog(null, " rellenar CP");}
								} catch (HeadlessException e1) {e1.printStackTrace();
								}
							}
						});
				
	}
	class PanelConLayout extends JPanel {
		private static final long serialVersionUID = 1L;

		public PanelConLayout(){
			int space = 5;
			Border spaceBorder = BorderFactory.createEmptyBorder(space, space,space, space);
			Border titleBorder = BorderFactory.createTitledBorder("Buscar Proveedor");
			setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder));
		
			GridBagConstraints gc = new GridBagConstraints();
//			El componente debe ocupar la posición WEST de su celda 
			gc.anchor = GridBagConstraints.WEST;
			gc.gridheight = 1; 	
			gc.gridy = 0;
			gc.gridx = 0;	
			add(label[9], gc);

			//Columna 1. Necesita estirarse,  ponemos weightx 1		
			gc.weightx  = 1;
			gc.gridx = 1;	
			add(text[8], gc);
			
			gc.gridx = 2;	
			add(search,gc);
			
			
		}
	}
	
		
	class PanelConLayout2 extends JPanel {
		private static final long serialVersionUID = 1L;

		public PanelConLayout2() {
			setBackground(Color.white);
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			
			int space = 5;
			Border spaceBorder = BorderFactory.createEmptyBorder(space, space,space, space);
			Border titleBorder = BorderFactory.createTitledBorder("Proveedor");
			setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder));
			
	
			gc.gridheight = 1; 
			//	El componente debe ocupar la posición WEST de su celda 
			gc.anchor = GridBagConstraints.WEST;
				// PRIMERA Fila/
			//para que se estire sólo en horizontal.
				gc.fill=GridBagConstraints.HORIZONTAL; 
				
				gc.ipady = 15; //make this component tall
				// creer un espace de 5 pixel The inset from the top.
				gc.insets = new Insets(2, 0, 0, 0);
				gc.gridx = 0;	
				gc.gridy = 0;
				//Columna 0. Necesita estirarse,  ponemos weightx 1
				gc.weightx  = 1;
				// El componente ocupa 7 columnas.
			//	gc.gridwidth = 7; 
				gc.gridwidth = GridBagConstraints.REMAINDER;
				// El componente ocupa 2 filas.
				gc.gridheight = 2; 
			//	titulo.setBackground(Color.yellow);
				add(titulo, gc);
				
				// Restauramos valores por defecto
				gc.fill=GridBagConstraints.NONE;
		
				gc.weightx  = 0;
				gc.gridheight = 1; 
				gc.ipady = 0; 
				// creer un espace de 25 pixel de altura entre label y textfield y de 50 pixel de ancho
				gc.insets = new Insets(25, 0, 0, 10);
				
				// Segunda Fila/
				gc.gridy = 2;
				gc.gridx = 0;
				//Columna 0. No Necesita estirarse,  ponemos weightx 0
				gc.weightx = 0;
				// El componente ocupa 1 columnas.
				gc.gridwidth = 1; 			
				add(label[1], gc);
				
				
				gc.gridx=1;
				// El componente ocupa 4 columnas.
				gc.gridwidth = GridBagConstraints.REMAINDER;
			//	gc.gridwidth = 3;
				//Columna 1. Necesita estirarse,  ponemos weightx 1		
				gc.weightx  = 1;
				add(text[1], gc);

				
				// Tercera Fila//
				gc.gridy = 3;
				gc.gridx = 0;
				//Columna 0. No Necesita estirarse,  ponemos weightx 0
				gc.weightx = 0;
				gc.gridwidth = 1; 	
				add(label[2], gc);
								
				gc.gridx=1;
				// El componente ocupa 4 columnas.
				gc.gridwidth = GridBagConstraints.REMAINDER;
		//		gc.gridwidth = 3;
				//Columna 1. Necesita estirarse,  ponemos weightx 1		
				gc.weightx  = 1;
				add(text[2], gc);
				
				// 4 Fila//
				gc.gridy = 4;
				gc.gridx = 0;
				//Columna 0. No Necesita estirarse,  ponemos weightx 0
				gc.weightx = 0;
				gc.gridwidth = 1; 	
				add(label[3], gc);
								
				gc.gridx=1;
				// El componente ocupa 1 columnas.
				gc.gridwidth = 1;
				//Columna 0. No Necesita estirarse,  ponemos weightx 0
				gc.weightx = 0;
				add(text[3], gc);
				
				gc.gridx=2;
				// El componente ocupa 1 columnas.
				gc.gridwidth = 1;
				//Columna 0. No Necesita estirarse,  ponemos weightx 0
				gc.weightx = 1;
				btnCpOk.setIcon(createIcon("/Images/loupe_opt.jpg "));
				add(btnCpOk, gc);
///////////
				
				gc.gridx=3;
				//Columna 0. No Necesita estirarse,  ponemos weightx 0
				gc.weightx = 0;
				gc.gridwidth = 1; 	
				 gc.gridwidth = GridBagConstraints.RELATIVE;
				add(label[4], gc);
				
				gc.gridx=4;
				// El componente ocupa 2 columnas.
			//	gc.gridwidth = 2;
				//Columna 1. Necesita estirarse,  ponemos weightx 1		
				gc.weightx  = 1;
				gc.gridwidth = GridBagConstraints.REMAINDER; //end of row
				add(text[4], gc);
				
				// 5 Fila//
				gc.gridy = 5;
				gc.gridx=3;
				//Columna 0. No Necesita estirarse,  ponemos weightx 0
				gc.weightx = 0;
				gc.gridwidth = 1; 	
				 gc.gridwidth = GridBagConstraints.RELATIVE;
				add(label[5], gc);
				
				gc.gridx=4;
				// El componente ocupa 2 columnas.
			//	gc.gridwidth = 2;
				//Columna 1. Necesita estirarse,  ponemos weightx 1		
				gc.weightx  = 1;
				gc.gridwidth = GridBagConstraints.REMAINDER; //end of row
				add(text[5], gc);
				
				// 6 LINEA//
				gc.gridy = 6;
				gc.gridx=0;
				//Columna 0. No Necesita estirarse,  ponemos weightx 0
				gc.weightx = 0;
				// El componente ocupa 1 columnas.
				gc.gridwidth = 1; 			
				add(label[6], gc);
				
				gc.gridx=1;
				// El componente ocupa 4 columnas.
				gc.gridwidth = 3;
				//Columna 1. Necesita estirarse,  ponemos weightx 1		
				gc.weightx  = 1;
				add(text[6], gc);
				
				// 7 LINEA//
				gc.gridy = 7;
				gc.gridx=0;
				//Columna 0. No Necesita estirarse,  ponemos weightx 0
				gc.weightx = 0;
				// El componente ocupa 1 columnas.
				gc.gridwidth = 1; 			
				add(label[7], gc);
				
				gc.gridx=1;
				// El componente ocupa 4 columnas.
				gc.gridwidth = 3;
				//Columna 1. Necesita estirarse,  ponemos weightx 1		
				gc.weightx  = 1;
				add(text[7], gc);
				
				
					
				// 8 LINEA//
				// creer un espace de 25 pixel de altura entre label y textfield y de 50 pixel de ancho
				gc.insets = new Insets(20, 0, 0, 20);
				gc.gridy=8;
				gc.gridx=0;
				//Columna 0. No Necesita estirarse,  ponemos weightx 0
				gc.weightx = 0;
				// El componente ocupa 1 columnas.
				gc.gridwidth = 1;
				gc.gridheight = 1;				
				add(boton1,gc);
				
				gc.gridx=1;
				add(boton2,gc);
				
				//ultima//
				gc.gridy=9;
				gc.weighty = 2;
				add(new JLabel(), gc);	
			}  			
	}

	private ImageIcon createIcon(String path) {
		URL url = getClass().getResource(path);
		if (url == null) {
			System.err.println("peux pas charger image" + path);
		}
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}
	public boolean validarEmail(String email){
		 Pattern pattern = Pattern
	                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
  	Matcher matcher = pattern.matcher(email);
  	if (matcher.find() == true) {
  		 return true;
  	}else{
  		return false;
  	}
	}
	public void setText(){
		text[1].setText("");
		text[2].setText("");
		text[3].setText("");
		text[4].setText("");
		text[5].setText("");
		text[6].setText("");
		text[7].setText("");
	}
	
	
	
	}
		
        
	

	

