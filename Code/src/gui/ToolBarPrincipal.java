package gui;

import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import colores.JPanelGradientColorHorizontal;
import colores.JToolBarGradientColorHorizontal;

public class ToolBarPrincipal extends JToolBarGradientColorHorizontal {
	private static final long serialVersionUID = 1L;

	private JButton bt1 = new JButton();
	private JButton bt2 = new JButton();
	private JButton bt3 = new JButton();
	private JButton bt4 = new JButton();

	public ToolBarPrincipal() {

		Panel p = new Panel();
		SwingUtilities.updateComponentTreeUI(p);
		add(p);
		setFloatable(false);
		// add to where the first button is initialized:
		bt1.setAlignmentY(CENTER_ALIGNMENT);
		// add to where the second button is initialized:
		bt2.setAlignmentY(CENTER_ALIGNMENT);
		// add to where the third button is initialized:
		bt3.setAlignmentY(CENTER_ALIGNMENT);
		bt4.setAlignmentY(CENTER_ALIGNMENT);

		// Para que el boton no tenga borde …
	//	bt1.setBorderPainted(false);
		// Para que no se pinte el boton
	//	bt1.setContentAreaFilled(false);
	//	bt1.setRolloverEnabled(true);
	//	bt1.setFocusable(false);
		bt1.setIcon(createIcon("/Images/client_opt.jpg "));
		bt1.setToolTipText("cliente");

		// Para que el boton no tenga borde …
	//	bt2.setBorderPainted(false);
		// Para que no se pinte el boton
	//	bt2.setContentAreaFilled(true);
	//	bt2.setRolloverEnabled(true);
	//	bt2.setFocusable(false);
		bt2.setIcon(createIcon("/Images/Prove_opt.png"));
		bt2.setToolTipText("proveedor");

		bt3.setIcon(createIcon("/Images/stockCirculant_opt.png "));
		bt3.setToolTipText("articulo");
		
		bt4.setIcon(createIcon("/Images/document_opt.png "));
		bt4.setToolTipText("documento");

	}

	class Panel extends JPanelGradientColorHorizontal {
		private static final long serialVersionUID = 1L;

		public Panel() {
			setLayout(new FlowLayout(FlowLayout.LEFT));
			bt1.setActionCommand("clientePanel");
			bt2.setActionCommand("proveedorPanel");
			bt3.setActionCommand("articulos");
			bt4.setActionCommand("documentoPanel");
			add(bt1);
			add(bt2);
			add(bt3);
			add(bt4);
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

	// metodo llamado por la class Acciones
	public JButton getPanelCliente() {
		return bt1;
	}

	// metodo llamado por la class Acciones
	public JButton getPanelProveedor() {
		return bt2;
	}

	// metodo llamado por la class Acciones
	public JButton getPanelStock() {
		return bt3;
	}
	
	// metodo llamado por la class Acciones
		public JButton getPanelDocumento() {
			return bt4;
		}

}

