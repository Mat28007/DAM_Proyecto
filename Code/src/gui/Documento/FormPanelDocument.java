package gui.Documento;





import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

import colores.JPanelGradientColorVertical;

public class FormPanelDocument extends JPanelGradientColorVertical   {
	private static final long serialVersionUID = 1L;
	private JButton facturaCompra = new JButton("Factura de Compra");
	private JButton facturaCompraPdf = new JButton("Pdf Factura Compra");
	
	public FormPanelDocument(){
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		setMinimumSize(dim);
		// met le titre au form panel et cree un ecart
		Border innerBorder = BorderFactory.createTitledBorder("Documentos");
		Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		facturaCompra.setActionCommand("facturaCompra");
		facturaCompraPdf.setActionCommand("facturaCompraPdf");

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
		add(facturaCompra, gc);
		
		gc.gridy = 1;
		add(facturaCompraPdf, gc);
		
		//Ultima
		gc.gridy = 2;
		gc.weighty = 2;
		add(new JLabel(), gc);
		
	}
	//metodo llamado por la class Acciones
		public JButton getFacturaProveedorPanel() {
	        return facturaCompra;
			
	    }
		public JButton getFacturaProveedorPdf() {
	        return facturaCompraPdf;
			
	    }
		

	}


