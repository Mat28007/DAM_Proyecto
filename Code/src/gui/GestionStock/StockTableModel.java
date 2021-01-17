package gui.GestionStock;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import tablas.Articulo;

public class StockTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<Articulo>tadb;
	
	//constructor
	public StockTableModel(){}
	
	// Nombre de las columnas
			private String[] colNames = { "Articulo ID","Categoria ID","Articulo","Descripcion","Precio venta",
					"Precio compra","Inventario"};
			
			public void definirDataStock(List<Articulo>tadb){
				this.tadb=tadb;
			}		
			
			
			@Override
			public String getColumnName(int column) {
				return colNames[column];
			}
			

	@Override
	public int getRowCount() {	
		try{
		return tadb.size();	
		
		}catch(IllegalStateException e){
			
			throw new IllegalStateException("Error", e);
			      }
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public Object getValueAt(int row, int column) {	
		// lignes on passe la db
		Articulo articulo = tadb.get(row);
		
		// colonnes on passe les champs
		switch (column) {
		case 0:
			return articulo.getIdarticulo();
		case 1:
			return articulo.getCategoria().getNombreCategoria();
		case 2:
			return articulo.getNombreArticulo();			
		case 3:
			return articulo.getDescripcion();
		case 4:
			return articulo.getPrecioVenta();
		case 5:
			return articulo.getPrecioCompra();
		case 6:
			return articulo.getInventario();
		}
	return null;
	}
}
