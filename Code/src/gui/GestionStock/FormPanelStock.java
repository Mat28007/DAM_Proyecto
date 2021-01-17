package gui.GestionStock;



import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import colores.JPanelGradientColorVertical;

public class FormPanelStock extends JPanelGradientColorVertical  {
	private static final long serialVersionUID = 1L;
	private JButton anadirArticulo =new JButton("Añadir-Modificar Articulos");
	private JButton visualisarProductos=new JButton("Visualisar Articulos");
	
	public FormPanelStock(){
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		setMinimumSize(dim);
		Border innerBorder = BorderFactory.createTitledBorder("Stock");
		Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		
		visualisarProductos.setActionCommand("visualisarStock");
		anadirArticulo.setActionCommand("anadir producto");
		layoutComponents();
	}

	private void layoutComponents() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		
		// 1 LINEA//	
		add(anadirArticulo, gc);
		
		// 2 LINEA//	
		gc.gridy = 1;
		gc.weighty=2;
		add(visualisarProductos, gc);
	}

	//metodo llamado por la class Acciones
		public JButton getVisualisarStock() {
	        return visualisarProductos;
	    }
		public JButton getAnadirArticulo() {
	        return anadirArticulo;
	    }



}
