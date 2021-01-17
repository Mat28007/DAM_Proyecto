package gui.GestionStock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import tipo.TipoPaneles;
import colores.JLabelGradientColorHorizontal;

public class JPanelGestionStock extends JPanel {
	private static final long serialVersionUID = 1L;
	private TablePanelStock tablePanelStock;
	private JLabelGradientColorHorizontal titulo;
	private PanelTitulo panelTitulo;

	
	public JPanelGestionStock(){

		titulo=new JLabelGradientColorHorizontal();
		titulo.setText("Gestion del Stock");
		titulo.setFont(TipoPaneles.jJabelTituloTipo);
		
		panelTitulo=new PanelTitulo();
		tablePanelStock=new TablePanelStock();

		
		setLayout(new BorderLayout());	 
		add(panelTitulo,BorderLayout.NORTH);
		add(tablePanelStock,BorderLayout.CENTER);
	}
	
	class PanelTitulo extends JPanel {
		private static final long serialVersionUID = 1L;
		
		public PanelTitulo() {
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createCompoundBorder());
			GridBagConstraints gc = new GridBagConstraints();
			setBackground(Color.WHITE);
			
			gc.gridx = 0;
			gc.gridy = 0;
			// El componente ocupa 1 fila.
			gc.gridheight = 1;
			gc.gridwidth = GridBagConstraints.REMAINDER;
			// El componente debe ocupar la posición WEST de su celda
			gc.anchor = GridBagConstraints.WEST;
			// PRIMERA Fila/
			// para que se estire sólo en horizontal.
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.ipady = 15; // make this component tall
			// Columna 0. Necesita estirarse, ponemos weightx 1
			gc.weightx = 1;
			// El componente ocupa 7 columnas.
			// gc.gridwidth = 7;
			add(titulo, gc);
		}
	}

}
