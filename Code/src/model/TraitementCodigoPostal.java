package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TraitementCodigoPostal extends DataBasePostal {
	public  TraitementCodigoPostal() {}
	
	// buscar provincia con CP
	public Posicion Cp(String cp) throws SQLException {
		String poblacion="";
		String provincia="";
		String checkSql = "select poblacion,provincia from poblacion p join codigopostal c on p.provinciaid=c.provinciaid join provincia pp on pp.provinciaid=c.provinciaid and c.poblacionid=p.poblacionid WHERE c.codigopostalid=?";
		
		PreparedStatement ps = conn.prepareStatement(checkSql);
		// dans ce putain d´ordre!!
		ps.setString(1, cp);
		ResultSet checkResult = ps.executeQuery();
		if (checkResult.next()) {
			 poblacion = checkResult.getString("poblacion");
			 provincia=checkResult.getString("provincia");
		}
		Posicion posicion=new Posicion(provincia, poblacion);
		return posicion;
	}
	
	public class Posicion {
		private String poblacion;
		private String provincia;

		public Posicion(String provincia,String poblacion){
			 this.poblacion=poblacion;
			 this.provincia=provincia;
		}

		public String getPoblacion() {
			return poblacion;
		}

		public void setPoblacion(String poblacion) {
			this.poblacion = poblacion;
		}

		public String getProvincia() {
			return provincia;
		}

		public void setProvincia(String provincia) {
			this.provincia = provincia;
		}
	}
}
	
		
