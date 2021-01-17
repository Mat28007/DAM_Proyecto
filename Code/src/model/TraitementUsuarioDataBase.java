package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tablas.Usuario;

public class TraitementUsuarioDataBase extends DataBase {
	// Insert usuario
	public void saveUsuario(Usuario usr) throws SQLException {
		String insertSql = "INSERT INTO USUARIO(usuario,password,nombre)VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insertSql);
		ps.setString(1, usr.getUsuario());
		ps.setString(2, usr.getPassword());
		ps.setString(3, usr.getNombre());
		ps.executeUpdate();
		ps.close();
	}

	// comprobar si existe usuario al crearlo
	public int existeUsuario(String usr) throws SQLException {
		String checkSql = "SELECT COUNT(idusuario)FROM USUARIO WHERE usuario=?";
		PreparedStatement ps = conn.prepareStatement(checkSql);
		// dans ce putain d´ordre!!
		ps.setString(1, usr);
		ResultSet checkResult = ps.executeQuery();

		if (checkResult.next()) {
			return checkResult.getInt(1);
		} else {
			return 1;
		}
	}

	// Login
	public boolean login(Usuario usr) throws SQLException {
		String checkSql = "SELECT idusuario,usuario,password,nombre FROM USUARIO WHERE usuario=?";
		PreparedStatement ps = conn.prepareStatement(checkSql);
		// dans ce putain d´ordre!!
		ps.setString(1, usr.getUsuario());
		ResultSet checkResult = ps.executeQuery();
		if (checkResult.next()) {
			if (usr.getPassword().equals(checkResult.getString(3))) {
				usr.setId(checkResult.getInt(1));
				usr.setNombre(checkResult.getString(4));
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public String cargarUsuario(Usuario user) throws SQLException {
		String checkSql = "SELECT idusuario,usuario,password,nombre FROM USUARIO WHERE usuario=?";
		PreparedStatement smt = conn.prepareStatement(checkSql);
		smt.setString(1, user.getNombre());
		ResultSet rs = smt.executeQuery();
		if (rs.next()) {
			return rs.getString(2);
		} else {
			return "error";
		}
	}
		

	public String cargarUsuario2() throws SQLException {
		String checkSql = "SELECT * FROM USUARIO";
		PreparedStatement smt = conn.prepareStatement(checkSql);
		ResultSet rs = smt.executeQuery();
		if (rs.next()) {
			return rs.getString(2);
		} 
		else {
			return "error";
		}
	}
}
		
		
		/*
		String nombreUsuario="";
		String checkSql = "SELECT nombre FROM USUARIOS WHERE usuario= ?";
		PreparedStatement ps = conn.prepareStatement(checkSql);
		// dans ce putain d´ordre!!
		ps.setString(1, user.getNombre());
		ResultSet checkResult = ps.executeQuery(checkSql);
		if (checkResult.next()) {
			nombreUsuario = checkResult.getString("nombre");	
		}
		System.out.println("nombre Usuario : "+nombreUsuario);
		return nombreUsuario;
		*/


