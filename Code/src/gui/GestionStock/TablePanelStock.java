package gui.GestionStock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tablas.Articulo;


public class TablePanelStock extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private StockTableModel stockTableModel;
	
	// constructor
		public TablePanelStock() {
			stockTableModel =new StockTableModel();
			table=new JTable(stockTableModel);
			
			setLayout(new BorderLayout());		
			add(new JScrollPane(table), BorderLayout.CENTER);
			
			table.setShowHorizontalLines( true );
			table.setShowVerticalLines( true );
			table.setRowSelectionAllowed( true );
			table.setColumnSelectionAllowed( true );
			table.setForeground(Color.blue);
			// cambia el tipo de letra del encabezado de la tabla
			table.getTableHeader().setFont(new Font("Cooper ", 1, 12));
			// cambia el color de la letra del encabezado de la tabla
			table.getTableHeader().setForeground(Color.BLACK);
			// Cambiamos el color de la zona seleccionada (rojo/blanco)
			table.setSelectionForeground( Color.white );
			table.setSelectionBackground( Color.red );
			
}
		
		// metodo para pasar la lista de los clientes del tablePanel al controler 
		public void definirDataStock(List<Articulo> tadb) {
			stockTableModel.definirDataStock(tadb);
		}

		// para añadir linea al cuadro
		public void refreshStock() {
			stockTableModel.fireTableDataChanged();
		}
}
