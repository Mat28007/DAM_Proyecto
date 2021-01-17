package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tablas.Cliente;
import tablas.Sociedad;
import tablas.Usuario;

public class TraitementClienteDataBase extends DataBase{

	private ArrayList<Cliente>clientes;
	
	public TraitementClienteDataBase(){
		clientes=new ArrayList<Cliente>();
	}
	public List<Cliente> getCliente(){
		return clientes;
	}
	public void addCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	Usuario iu=new Usuario();
	
	// Insert Cliente
		public void saveCliente(Sociedad clt) throws SQLException {
			
			String insertSql="INSERT INTO cliente(Denominacion_Social,Direccion,Codigo_Postal,Telefono,Email,Ciudad,Provincia,CreatedBy)VALUES(?,?,?,?,?,?,?,? )";
			PreparedStatement ps = conn.prepareStatement(insertSql);
			ps.setString(1, clt.getDenominacionSocial());
			ps.setString(2, clt.getDireccion());
			ps.setInt(3, clt.getCodigoPostal());
			ps.setInt(4, clt.getTelefono());
			ps.setString(5, clt.getEmail());
			ps.setString(6, clt.getCiudad());
			ps.setString(7, clt.getProvincia());
			ps.setInt(8,Usuario.getId());
			ps.executeUpdate();
			ps.close();
		}
		// comprobar si existe cliente al crearlo
		public boolean existeCliente(String cli) throws SQLException {
			String checkSql = "SELECT Denominacion_social FROM CLIENTE WHERE Denominacion_social=?";
			PreparedStatement ps = conn.prepareStatement(checkSql);
			// dans ce putain d´ordre!!
			ps.setString(1, cli);
			ResultSet checkResult = ps.executeQuery();

			if (checkResult.next()) {
				return true;
			} else {
				return false;
			}
		}
		
		
		
		
		public void visualizarCliente() throws SQLException {
			clientes.clear();
			String sql = "SELECT Denominacion_Social,Direccion,Codigo_Postal,Telefono,Email,Ciudad,Provincia,id FROM CLIENTE  ";
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
				Cliente cliente = new Cliente(denominacion,domicilio,email,poblacion,provincia,codigoPostal,id,telefono);
				clientes.add(cliente);	
			}
			selectStatement.close();
			resultados.close();
		}
		
		public  Cliente visualizarCliente2(String denominacion) throws SQLException {
			String deno="",dom="",email="",pobl="",provin="";
			int codpo=0,id=0,tel=0;
	
			String sql = "SELECT Denominacion_Social,Direccion,Codigo_Postal,Telefono,Email,Ciudad,Provincia,id FROM CLIENTE WHERE Denominacion_Social=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, denominacion);
			ResultSet checkResult = ps.executeQuery();
			if (checkResult.next()) {
				 deno = checkResult.getString("Denominacion_Social");
				 dom=checkResult.getString("Direccion");
				 email=checkResult.getString("Email");
				 pobl=checkResult.getString("Ciudad");
				 provin=checkResult.getString("Provincia");
				 codpo=checkResult.getInt("Codigo_Postal");
				 id=checkResult.getInt("id");
				 tel=checkResult.getInt("Telefono");
				 
			}
				Cliente cliente = new Cliente(deno,dom,email,pobl,provin,codpo,id,tel);	
				return cliente;
}
		
		
		//UPDATE
		public void updateCliente(String denomina,String dirrec,int codpo,String ciu,String prov,int tel,String email) throws SQLException {		
			String updateSql = "UPDATE CLIENTE SET Direccion=?,Codigo_Postal=?,Ciudad=?,Provincia=?,Telefono=?,Email=? WHERE Denominacion_Social = ? ";
		    PreparedStatement ps = conn.prepareStatement(updateSql);
		   
		    // set the preparedstatement parameters
		    ps.setString(1,dirrec);
		    ps.setInt(2,codpo);
		    ps.setString(3,ciu);
		    ps.setString(4,prov);
		    ps.setInt(5,tel);
		    ps.setString(6,email);
		    ps.setString(7,denomina);
		    // call executeUpdate to execute our sql update statement	 
		    ps.executeUpdate();
		    ps.close();
		  }	
		
		
		//DELETE
		public void deleteCliente(String denominacion)throws SQLException{		
			String deleteSql="DELETE from cliente WHERE Denominacion_Social=?";	
			 PreparedStatement preparedStmt = conn.prepareStatement(deleteSql);
			 preparedStmt.setString(1, denominacion);
			 preparedStmt.execute();

			 
		}
}
		
		


