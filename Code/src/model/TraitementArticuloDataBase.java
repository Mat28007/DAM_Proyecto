package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tablas.Articulo;
import tablas.Categoria;

public class TraitementArticuloDataBase extends DataBase {
	private ArrayList<Articulo> articulos;
	Categoria oCategoria=new Categoria();
	public TraitementArticuloDataBase() {
		articulos = new ArrayList<Articulo>();
	}

	public List<Articulo> getArticulo() {
		return articulos;
	}

	public void addArticulo(Articulo articulo) {
		articulos.add(articulo);
	}

	

	
	

		// comprobar si existe articulo 
		public int existeArticulo(String articulo) throws SQLException {
			String checkSql = "SELECT COUNT(idarticulo)FROM ARTICULO WHERE nombre_articulo=?";
			PreparedStatement ps = conn.prepareStatement(checkSql);
			// dans ce putain d´ordre!!
			ps.setString(1, articulo);
			ResultSet checkResult = ps.executeQuery();
			if (checkResult.next()) {
				return checkResult.getInt(1);
			} else {return 1;}
		}
		
		
		// Llenar el comboBox Categoria
	public ArrayList<Categoria> consultarListaCategoria() {
		ArrayList<Categoria> categoriaList=new ArrayList<>();
		String consulta="SELECT DISTINCT nombre_categoria FROM categoria";
		Categoria cateArt;
		try {
			PreparedStatement sentencia = conn.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()){
				cateArt=new Categoria();
				cateArt.setNombreCategoria(resultado.getString("nombre_categoria"));
				categoriaList.add(cateArt);
			}	
	}catch(SQLException e) {
	 e.printStackTrace();
	}
return categoriaList;
	}
	
		
/*
		// Llenar el comboBox Categoria
	public ArrayList<Articulo> consultarListaCategoria() {
		ArrayList<Articulo> categoriaList=new ArrayList<>();
		String consulta="SELECT DISTINCT nombre_categoria FROM categoria";
		Categoria cateArt;
		try {
			PreparedStatement sentencia = conn.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()){
				cateArt=new Categoria();
				cateArt.setNombreCategoria(resultado.getString("categoria"));
				categoriaList.add(cateArt);
			}	
	}catch(SQLException e) {
	 e.printStackTrace();
	}
return categoriaList;
	}
	*/
	
	// Llenar el comboBox Articulo
public ArrayList<Articulo> consultarListaArticulo(String categoria) {
	ArrayList<Articulo> articuloList=new ArrayList<>();
	String consulta="SELECT DISTINCT nombre_articulo FROM articulo AS a \r\n" + 
			"LEFT OUTER JOIN categoria AS c\r\n" + 
			"ON a.fkcategoria = c.idcategoria \r\n" + 
			"WHERE c.nombre_categoria=?;";
	
	Articulo cateArt;
	try {
		PreparedStatement sentencia = conn.prepareStatement(consulta);
		sentencia.setString(1, categoria);
		ResultSet resultado = sentencia.executeQuery();
		while(resultado.next()){
			cateArt=new Articulo();
			cateArt.setNombreArticulo(resultado.getString("nombre_articulo"));
			articuloList.add(cateArt);
		}	
}catch(SQLException e) {
 e.printStackTrace();
}
return articuloList;
}

	// Insert Articulo
	public void crearArticulo(Articulo articulo) throws SQLException {
		String insertSql = "INSERT INTO articulo(fkcategoria,nombre_articulo,descripcion,precio_venta,precio_compra)VALUES((SELECT idcategoria from categoria WHERE nombre_categoria=?),?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insertSql);
		ps.setString(1, articulo.getCategoria().getNombreCategoria());
		ps.setString(2, articulo.getNombreArticulo());
		ps.setString(3, articulo.getDescripcion());
		ps.setDouble(4, articulo.getPrecioVenta());
		ps.setDouble(5, articulo.getPrecioCompra());
	//	ps.setInt(6, articulo.getId());
		ps.executeUpdate();
		ps.close();
	}
	
	//UPDATE
public void updateArticulo(String art,String descr,double pv,double pc) throws SQLException {		
				String updateSql = "UPDATE ARTICULO SET descripcion=?,precio_venta=?,precio_compra=? WHERE nombre_articulo = ? ";
			    PreparedStatement ps = conn.prepareStatement(updateSql);		   
			    // set the preparedstatement parameters
			  
			    ps.setString(1,descr);		   
			    ps.setDouble(2,pv);
			    ps.setDouble(3,pc);
			    ps.setString(4,art);
			    // call executeUpdate to execute our sql update statement	 
			    ps.executeUpdate();
			    ps.close();
			  }	

//Select all para tablePanelStock
public void visualizarStock() throws SQLException {
	articulos.clear();
	
String sql = "SELECT fkcategoria,nombre_articulo,descripcion,precio_venta,precio_compra,inventario,idarticulo FROM ARTICULO  ";
//	String sql = "SELECT * FROM articulo AS a left outer join categoria AS c on a.fkcategoria = c.idcategoria ";
	Statement selectStatement = conn.createStatement();
	ResultSet resultados = selectStatement.executeQuery(sql);
	
	while (resultados.next()) {
		
		Articulo articulo = new Articulo();
		Categoria categoria=new Categoria();
		
		articulo.setIdarticulo(resultados.getInt("idarticulo"));
		categoria.setNombreCategoria(resultados.getString("fkcategoria"));
		articulo.setCategoria(categoria);
		articulo.setNombreArticulo(resultados.getString("nombre_articulo"));
		articulo.setDescripcion(resultados.getString("descripcion"));
		articulo.setPrecioVenta(resultados.getDouble("precio_venta"));
		articulo.setPrecioCompra(resultados.getDouble("precio_compra"));
		articulo.setInventario(resultados.getInt("inventario"));
		articulos.add(articulo);	
		
	}
	selectStatement.close();
	resultados.close();
}

public  Articulo visualizarDetalleArticulo(String arti) throws SQLException {
	String des="",na="";
	double pc=0,pv=0;
	int inv=0,id=0;
	Categoria cat=null;
	String sql = "SELECT fkcategoria,nombre_articulo,descripcion,precio_venta,precio_compra,inventario,idarticulo FROM ARTICULO WHERE nombre_articulo=?";

	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, arti);
	ResultSet checkResult = ps.executeQuery();
	
	if (checkResult.next()) {
		na=checkResult.getString("nombre_articulo");
		des = checkResult.getString("descripcion");
		pv=checkResult.getDouble("precio_venta");
		pc=checkResult.getDouble("precio_compra");
		inv=checkResult.getInt("inventario");
		id=checkResult.getInt("idarticulo");
	}

	Articulo articulo = new Articulo(cat,na,des,pv,pc,inv);	
	return articulo;
}



}

