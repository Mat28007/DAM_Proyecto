package gui.Proveedor;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import colores.JPanelGradientColorVertical;


public class FormPanelProveedor extends JPanelGradientColorVertical {
	private static final long serialVersionUID = 1L;
	
	private JButton crearProveedor = new JButton("Crear proveedor");
	private JButton editarProveedor = new JButton("Modificar-Eliminar un proveedor");
	private JButton crearFactura = new JButton("Facturar un proveedor");
	private JButton visualisarProveedor = new JButton("Visualisar un proveedor");
	
	public FormPanelProveedor() {
		
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		setMinimumSize(dim);
		// met le titre au form panel et cree un ecart
		Border innerBorder = BorderFactory.createTitledBorder("Proveedores");
		Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		crearProveedor.setActionCommand("crearProveedor");
		editarProveedor.setActionCommand("editarProveedor");
		visualisarProveedor.setActionCommand("visualisarProveedor");

		layoutComponents();

	}
	private void layoutComponents() {
		// mise en page
		
				setLayout(new GridBagLayout());
				GridBagConstraints gc = new GridBagConstraints();
				gc.gridy = 0;
				gc.gridx = 0;
				
				// weight est la taille de la cellule par rapport a l autre
				gc.weightx = 1;
				gc.weighty = 0.1;
				
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.FIRST_LINE_START;
				// PRIMERA LINEA//	
				add(crearProveedor, gc);

				// 2 LINEA//
				gc.gridy = 1;
				add(editarProveedor, gc);

				// 3 LINEA//
				gc.gridy = 2;	
				add(crearFactura, gc);

				// 4 LINEA//
				gc.gridy=3;
				gc.weighty = 2;		
				add(visualisarProveedor,gc);		
	}

	//metodo llamado por la class Acciones
	public JButton getVisualisarProveedor() {
        return visualisarProveedor;
		
    }
		public JButton getEditarProveedor() {
	        return editarProveedor;
	    }
		public JButton getCrearProveedor() {
	        return crearProveedor;
	    }
	
	

	
	}