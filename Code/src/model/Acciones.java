package model;

import gui.FirstFrame;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Acciones implements ActionListener {
	private FirstFrame frame;
	CardLayout cards;
	public Acciones(FirstFrame frame) {

		this.frame = frame; // Con esto se maneja el objeto de forma global en
							// esta clase.
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Depediendo de la toolBar, frameWest:
		if (e.getActionCommand().equals("clientePanel")) {
			frame.getClientes();
		}

		else if (e.getActionCommand().equals("articulos")) {
			frame.getStock();
		}

		else if (e.getActionCommand().equals("proveedorPanel")) {
			frame.getProveedor();
		}

		else if (e.getActionCommand().equals("documentoPanel")) {
			frame.getDocumento();
		}

		// Botonnes clientes
		else if (e.getActionCommand().equals("visualisar")) {
			frame.getTablePanel();
		} else if (e.getActionCommand().equals("editar")) {
			frame.getModificarPanel();
		} else if (e.getActionCommand().equals("crear")) {
			frame.getCrearClientePanel();
		}

		// Botonnes Proveedor
		else if (e.getActionCommand().equals("visualisarProveedor")) {
			frame.getTablePanelProveedor();
		} else if (e.getActionCommand().equals("editarProveedor")) {
			frame.getModificarPanelProveedor();
		}

		else if (e.getActionCommand().equals("crearProveedor")) {
			frame.getCrearProveedorPanel();
		}

		// Botonnes Stock
		else if (e.getActionCommand().equals("visualisarStock")) {
			frame.getTablePanelStock();
		}

		else if (e.getActionCommand().equals("anadir producto")) {
			frame.getAnadirArticuloPanel();
		}
		// Botonnes Documentos
		else if (e.getActionCommand().equals("facturaCompra")) {
			frame.getFacturaProveedorPanel();
		}
		else if (e.getActionCommand().equals("facturaCompraPdf")) {
			frame.getFacturaProveedorPdf();
		}

	}
}
