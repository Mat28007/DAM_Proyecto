package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tablas.Categoria;

public class TraitementCategoriaDataBase extends DataBase {
	private ArrayList<Categoria> categorias;
	public TraitementCategoriaDataBase() {
		categorias = new ArrayList<Categoria>();
	}
	public List<Categoria> getCategoria() {
		return categorias;
	}
	public void addCategoria(Categoria categoria) {
		categorias.add(categoria);
	}
	// Insert Categoria
		public void crearCategoria(Categoria categoria) throws SQLException {
			String insertSql = "INSERT INTO categoria VALUES(?,?)";
			PreparedStatement ps = conn.prepareStatement(insertSql);
			ps.setInt(1, categoria.getIdcategoria() );
			ps.setString(2, categoria.getNombreCategoria());
			ps.executeUpdate();
			ps.close();
		}
		// comprobar si existe categoria a crearla
				public boolean existeCategoria(String categoria) throws SQLException {
					String checkSql = "SELECT nombre_categoria FROM CATEGORIA WHERE nombre_categoria=?";
					PreparedStatement ps = conn.prepareStatement(checkSql);
					ps.setString(1, categoria);
					ResultSet checkResult = ps.executeQuery();
					if (checkResult.next()) {
						return true;
					} else {return false;}
				}
				public int getidcategoria(String cat)throws SQLException {
					String checkSql ="Select idcategoria from categoria where nombre_categoria=?";
					PreparedStatement ps = conn.prepareStatement(checkSql);
					// dans ce putain d´ordre!!
					ps.setString(1, cat);
					ResultSet checkResult = ps.executeQuery();
					if (checkResult.next()) {
						return checkResult.getInt(1);
					} else {
						return 1;
					}
			}
}
