package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tablas.Proveedor;
import tablas.Sociedad;
import tablas.Usuario;

public class TraitementProveedorDataBase extends DataBase {
	private ArrayList<Proveedor> proveedores;

	public TraitementProveedorDataBase() {
		proveedores = new ArrayList<Proveedor>();
	}

	public List<Proveedor> getProveedor() {
		return proveedores;
	}

	public void addProveedor(Proveedor proveedor) {
		proveedores.add(proveedor);
	}

	// Insert Proveedor
	public void saveProveedor(Sociedad s) throws SQLException {
		String insertSql = "INSERT INTO PROVEEDOR (Denominacion_Social,Direccion,Codigo_Postal,Telefono,Email,Ciudad,Provincia,CreatedBy)VALUES(?,?,?,?,?,?,?,? )";
		PreparedStatement ps = conn.prepareStatement(insertSql);
		ps.setString(1, s.getDenominacionSocial());
		ps.setString(2, s.getDireccion());
		ps.setInt(3, s.getCodigoPostal());
		ps.setInt(4, s.getTelefono());
		ps.setString(5, s.getEmail());
		ps.setString(6, s.getCiudad());
		ps.setString(7, s.getProvincia());
		ps.setInt(8, Usuario.getId());

		ps.executeUpdate();
		ps.close();
	}

	public void visualizarProveedor() throws SQLException {
		proveedores.clear();
		String sql = "SELECT Denominacion_Social,Direccion,Codigo_Postal,Telefono,Email,Ciudad,Provincia,id FROM PROVEEDOR  ";
		Statement selectStatement = conn.createStatement();
		ResultSet resultados = selectStatement.executeQuery(sql);

		while (resultados.next()) {

			String denominacion = resultados.getString("Denominacion_Social");
			String domicilio = resultados.getString("Direccion");
			String email = resultados.getString("Email");
			String poblacion = resultados.getString("Ciudad");
			String provincia = resultados.getString("Provincia");
			int codigoPostal = resultados.getInt("Codigo_Postal");
			int telefono = resultados.getInt("Telefono");
			int id = resultados.getInt("id");
			Proveedor proveedor = new Proveedor(denominacion, domicilio, email,
					poblacion, provincia, codigoPostal, id, telefono);
			proveedores.add(proveedor);
		}
		selectStatement.close();
		resultados.close();
	}

	public Proveedor visualizarProveedor2(String denominacion)
			throws SQLException {
		String deno = "", dom = "", email = "", pobl = "", provin = "";
		int codpo = 0, id = 0, tel = 0;

		String sql = "SELECT Denominacion_Social,Direccion,Codigo_Postal,Telefono,Email,Ciudad,Provincia,id FROM PROVEEDOR WHERE Denominacion_Social=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, denominacion);
		ResultSet checkResult = ps.executeQuery();
		if (checkResult.next()) {
			deno = checkResult.getString("Denominacion_Social");
			dom = checkResult.getString("Direccion");
			email = checkResult.getString("Email");
			pobl = checkResult.getString("Ciudad");
			provin = checkResult.getString("Provincia");
			codpo = checkResult.getInt("Codigo_Postal");
			id = checkResult.getInt("id");
			tel = checkResult.getInt("Telefono");

		}
		Proveedor proveedor = new Proveedor(deno, dom, email, pobl, provin,
				codpo, id, tel);
		return proveedor;
	}

	// UPDATE
	public void updateProveedor(String denomina, String dirrec, int codpo,
			String ciu, String prov, int tel, String email) throws SQLException {
		String updateSql = "UPDATE PROVEEDOR SET Direccion=?,Codigo_Postal=?,Ciudad=?,Provincia=?,Telefono=?,Email=? WHERE Denominacion_Social = ? ";
		PreparedStatement ps = conn.prepareStatement(updateSql);

		// set the preparedstatement parameters
		ps.setString(1, dirrec);
		ps.setInt(2, codpo);
		ps.setString(3, ciu);
		ps.setString(4, prov);
		ps.setInt(5, tel);
		ps.setString(6, email);
		ps.setString(7, denomina);
		// call executeUpdate to execute our sql update statement
		ps.executeUpdate();
		ps.close();
	}

	// DELETE
	public void deleteProveedor(String denominacion) throws SQLException {
		String deleteSql = "DELETE from PROVEEDOR WHERE Denominacion_Social=?";
		PreparedStatement preparedStmt = conn.prepareStatement(deleteSql);
		preparedStmt.setString(1, denominacion);
		preparedStmt.execute();
	}

	// Llenar el comboBox Proveedor
	public ArrayList<Proveedor> consultarListaProveedor() {
		ArrayList<Proveedor> proveedorList = new ArrayList<Proveedor>();
		String consulta = "SELECT DISTINCT Denominacion_Social FROM PROVEEDOR";
		Proveedor nombre;
		try {
			PreparedStatement sentencia = conn.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				nombre = new Proveedor();
				nombre.setDenominacionSocial(resultado
						.getString("Denominacion_Social"));
				proveedorList.add(nombre);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proveedorList;
	}

	// comprobar si existe Proveedor al crearlo
	public boolean existeProveedor(String prov) throws SQLException {
		String checkSql = "SELECT Denominacion_social FROM PROVEEDOR WHERE Denominacion_social=?";
		PreparedStatement ps = conn.prepareStatement(checkSql);
		// dans ce putain d´ordre!!
		ps.setString(1, prov);
		ResultSet checkResult = ps.executeQuery();

		if (checkResult.next()) {
			return true;
		} else {
			return false;
		}
	}

}
