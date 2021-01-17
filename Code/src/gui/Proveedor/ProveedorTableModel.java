package gui.Proveedor;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import tablas.Proveedor;

public class ProveedorTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Proveedor>tpdb;
	public ProveedorTableModel (){}

		// Nombre de las columnas
		private String[] colNames = { "Proveedor ID","Denominacion", "Domicilio", "Codigo Postal", "Poblacion",
				"Provincia", "Email", "Telefono" };
		
		public void definirDataProveedor(List<Proveedor>tpdb){
			this.tpdb=tpdb;
		}
		@Override
		public String getColumnName(int column) {
			return colNames[column];
		}

		//size es el numero de cliente objeto de la lista=numero de rows
	@Override
	public int getRowCount() {
		try{
			return tpdb.size();
			
		}catch(IllegalStateException e){
		
		throw new IllegalStateException("Error", e);
		      }
		
		
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public Object getValueAt(int row, int column) {
				// lignes on passe la db
		Proveedor proveedor = tpdb.get(row);
				// colonnes on passe les champs
				switch (column) {
				case 0:
					return proveedor.getId();
				case 1:
					return proveedor.getDenominacionSocial();
				case 2:
					return proveedor.getDireccion();
				case 3:
					return proveedor.getCodigoPostal();
				case 4:
					return proveedor.getCiudad();
				case 5:
					return proveedor.getProvincia();
				case 6:
					return proveedor.getEmail();
				case 7:				
					return proveedor.getTelefono();
					

				}
				
				return null;
			}

}

