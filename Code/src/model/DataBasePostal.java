package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//classe 25
public class DataBasePostal {

	// Nombre de usuario de mysql
	public String usuario = "root";
	// Clave de usuario de mysql
	public String pwd = "root";
	// Ruta de nuestra base de datos
	String Url = "jdbc:mysql://localhost/postal?useSSL=false";
	// Objeto del tipo Connection para crear la conexión
	public static Connection conn = null;

	// clase37
	public void connectCP() throws Exception {
		if (conn != null)
			return;
		try {
			try {
				// Nuestra librería mysql
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("No database");
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(Url, usuario, pwd);
			if (conn != null) {
			//	System.out.println("Connexion establecida " + Url);
			}
			
		} catch (SQLException e) {
			System.out.println("Error en la connexion " + Url);
			e.printStackTrace();
			
		}

	}

	public void disconect() {
		if (conn != null) {
			try {
				System.out.println("Se ha cerrado la Base de datos");
				conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la Base de datos");
				e.printStackTrace();
			}
		}
	}

}
