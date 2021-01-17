package gui.Cliente;

import java.util.List;

import javax.swing.table.AbstractTableModel;



import tablas.Cliente;


public class ClienteTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<Cliente>tcdb;
	public ClienteTableModel (){}

		// Nombre de las columnas
		private String[] colNames = { "Cliente ID","Denominacion", "Domicilio", "Codigo Postal", "Poblacion",
				"Provincia", "Email", "Telefono" };
		
		public void definirData(List<Cliente>tcdb){
			this.tcdb=tcdb;
		}
		
		@Override
		public String getColumnName(int column) {
			return colNames[column];
		}

		//size es el numero de cliente objeto de la lista=numero de rows
	@Override
	public int getRowCount() {
		try{
			return tcdb.size();
			
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
				Cliente cliente = tcdb.get(row);
				// colonnes on passe les champs
				switch (column) {
				case 0:
					return cliente.getId();
				case 1:
					return cliente.getDenominacionSocial();
				case 2:
					return cliente.getDireccion();
				case 3:
					return cliente.getCodigoPostal();
				case 4:
					return cliente.getCiudad();
				case 5:
					return cliente.getProvincia();
				case 6:
					return cliente.getEmail();
				case 7:				
					return cliente.getTelefono();
					

				}
				
				return null;
			}
	
	
	

}
