package gui.Cliente;





import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import colores.JPanelGradientColorVertical;


public class FormPanelCliente extends JPanelGradientColorVertical   {

	private static final long serialVersionUID = 1L;
	private JButton crearCliente = new JButton("Crear cliente");
	private JButton editarCliente = new JButton("Modificar-Eliminar un cliente");
	private JButton crearFactura = new JButton("Facturar un cliente");
	private JButton visualisarCliente = new JButton("Visualisar un cliente");
	
	public FormPanelCliente() {
		
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		setMinimumSize(dim);
		// met le titre au form panel et cree un ecart
		Border innerBorder = BorderFactory.createTitledBorder("Clientes");
		Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		crearCliente.setActionCommand("crear");
		editarCliente.setActionCommand("editar");
		visualisarCliente.setActionCommand("visualisar");

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
				
				add(crearCliente, gc);
				
				
				// 2 LINEA//
				gc.gridy = 1;
				add(editarCliente, gc);
				
				
				// 3 LINEA//
				gc.gridy = 2;	
				add(crearFactura, gc);
	
				
				// 4 LINEA//
				gc.gridy=3;
				gc.weighty = 2;		
				add(visualisarCliente,gc);		
	}

	//metodo llamado por la class Acciones
	public JButton getVisualisarCliente() {
        return visualisarCliente;
		
    }
		public JButton getEditarCliente() {
	        return editarCliente;
	    }
		public JButton getCrearCliente() {
	        return crearCliente;
	    }

	}

