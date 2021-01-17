package controller;




import gui.GestionStock.FormEventStock;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import tablas.Cliente;
import tablas.Articulo;
import tablas.Categoria;
import tablas.Proveedor;
import tablas.Sociedad;
import tablas.SupplierInvoiceLineItems;
import tablas.SupplierInvoices;
import model.TraitementArticuloDataBase;
import model.TraitementCategoriaDataBase;
import model.TraitementClienteDataBase;
import model.TraitementCodigoPostal;
import model.TraitementProveedorDataBase;
import model.TraitementCodigoPostal.Posicion;
import model.TraitementDocumentoDataBase;
import model.TraitementUsuarioDataBase;
import tablas.Usuario;


//la classe controller implementa los datos de las BDD
public class Controller {
	// referencias a la BDD	
	TraitementUsuarioDataBase dbu = new TraitementUsuarioDataBase();
	TraitementCodigoPostal tcp = new TraitementCodigoPostal();
	TraitementClienteDataBase tcdb =new TraitementClienteDataBase();
	TraitementArticuloDataBase tadb=new TraitementArticuloDataBase();
	TraitementProveedorDataBase tpdb=new TraitementProveedorDataBase();
	TraitementCategoriaDataBase tcadb= new TraitementCategoriaDataBase();
	TraitementDocumentoDataBase tddb=new TraitementDocumentoDataBase();

	//Clientes
	//devuelve una lista de los clientes
	public List<Cliente> getCliente(){
		return tcdb.getCliente();
	}
	public void visualizarCliente() throws SQLException {
		tcdb.visualizarCliente();
	}
	public Cliente visualizarCliente2(String denominacion)throws SQLException {
		return tcdb.visualizarCliente2(denominacion);
	}
	public void saveCliente(Sociedad clt) throws SQLException{
		tcdb.saveCliente(clt);
	}
	
	public boolean	existeCliente(String c)throws SQLException{		
	return tcdb.existeCliente(c);
	}
	
	/*
	public void addCliente(FormEventCliente e) {
		// recuperamos la informacion necesaria del event
		String denominacion = e.getDenominacion();
		String domicilio= e.getDomicilio();
		int codPostal = e.getCodigoPostal();
		String poblacion = e.getPoblacion();
		String provincia = e.getProvincia();
		String email = e.getEmail();
		int telefono = e.getTelefono();
		int id=e.getId();
		
		// instanciar el objeto
		Cliente cliente = new Cliente(denominacion,domicilio,email,poblacion,provincia,codPostal,id,telefono);
		// pour ajouter une personne a ma bd
		tcdb.addCliente(cliente);
	}
	*/
	public void updateCliente(String denomi, String dom, int codpo,
			String ciudad, String provincia, int tel, String email) {
		try {
			tcdb.updateCliente(denomi,dom,codpo,ciudad,provincia,tel,email);
			JOptionPane.showMessageDialog(null, "Cliente modificado");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "fuck");
			e.printStackTrace();
		}
		
	}
	
	public void deleteCliente (String deno)throws SQLException{
		tcdb.deleteCliente(deno);
	}

	
	
	
	
	//Codigo Postal
	public Posicion Cp(String cp) throws SQLException{
		return tcp.Cp(cp);
	}
	public void connectCP() throws Exception {
		tcp.connectCP();
	}
	
	
	
	//Usuario
	public void connect() throws Exception {
		dbu.connect();
	}	
	public void saveUsuario(Usuario usr) throws SQLException{
		dbu.saveUsuario(usr);
	}
	public int existeUsuario(String usr) throws SQLException{
		return dbu.existeUsuario(usr);
	}
	public boolean login(Usuario usr) throws SQLException {
		return dbu.login(usr);
	}
	public String cargarUsuario(Usuario usr) throws SQLException{
		return dbu.cargarUsuario(usr);
	}

	//ARTICULO
	//devuele una lista de los articulos
	public List<Articulo> getArticulo(){
		return tadb.getArticulo();
	}

	public void addArticulo(FormEventStock e) {
		// recuperamos la informacion necesaria del event
		Categoria categoria = e.getCategoria();
		String descripcion= e.getDescripcion();
		String nombreArticulo=e.getNombreArticulo();
		double precioV = e.getPrecioVenta();
		double precioC = e.getPrecioCompra();
		int inventario = e.getInventario();
		e.getId();
		
		// instanciar el objeto
		
		Articulo articulo = new Articulo(categoria,nombreArticulo,descripcion,precioV,precioC,inventario);
		
		// para añadir articulo a bd
		tadb.addArticulo(articulo);
		// para añadir categoria a bd
		
	}
	
	public boolean existeCategoria(String cat) throws SQLException{
		return tcadb.existeCategoria(cat);
	}
	
	public int existeArticulo(String articulo) throws SQLException {
		return tadb.existeArticulo(articulo);
	}
	public void crearCategoria(Categoria cate) throws SQLException{
		tcadb.crearCategoria(cate);
	}	
	
	public ArrayList<Categoria> consultarListaCategoria()throws SQLException{
		return tadb.consultarListaCategoria();
	}
	
	public ArrayList<Articulo> consultarListaArticulo(String categoria) throws SQLException{
		return tadb.consultarListaArticulo(categoria);
	}
	public void crearArticulo(Articulo articulo)throws SQLException{
		tadb.crearArticulo(articulo);
	}
	public void updateArticulo(String art,String descr,double precioV,double precioC) {
		try {
			tadb.updateArticulo(art,descr,precioV,precioC);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error");
			e.printStackTrace();
		}
		
	}
	public int getidcategoria(String ca) throws SQLException {
		return tcadb.getidcategoria(ca);
	}
	
	public void visualizarStock() throws SQLException {
		tadb.visualizarStock();
	}
	
	public  Articulo visualizarDetalleArticulo(String arti) throws SQLException {
		return tadb.visualizarDetalleArticulo(arti);
	}

	
	//PROVEEDOR
	public List<Proveedor> getProveedor(){
		return tpdb.getProveedor();
	}
	public void saveProveedor(Sociedad s) throws SQLException{
		tpdb.saveProveedor(s);
	}
	public void visualizarProveedor() throws SQLException {
		tpdb.visualizarProveedor();
	}
	
	
	public Proveedor visualizarProveedor2(String denominacion)throws SQLException {
		return tpdb.visualizarProveedor2(denominacion);
	}
	public void updateProveedor(String denomi, String dom, int codpo,
			String ciudad, String provincia, int tel, String email) {
		try {
			tpdb.updateProveedor(denomi,dom,codpo,ciudad,provincia,tel,email);
			JOptionPane.showMessageDialog(null, "Proveedor modificado");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "fuck");
			e.printStackTrace();
		}	
	}
	public void deleteProveedor (String deno)throws SQLException{
		tpdb.deleteProveedor(deno);
	}
	public ArrayList<Proveedor> consultarListaProveedor() throws SQLException{
		return tpdb.consultarListaProveedor();
	}
	public boolean	existeProveedor(String p)throws SQLException{		
		return tpdb.existeProveedor(p);
}
	
	//Documento
	
	public String cargarUsuario2() throws SQLException{
		return dbu.cargarUsuario2();
	}
	
	public void saveSupplierInvoice(SupplierInvoices si) throws SQLException{
		tddb.saveSupplierInvoice(si);
	}
	public void saveSupplierInvoiceLine(SupplierInvoiceLineItems si) throws SQLException{
		tddb.saveSupplierInvoiceLine(si);
		
	}
	public int idArt(int indexCategoria,String itemArticulo) throws SQLException{
		return tddb.idArt(indexCategoria,itemArticulo);
	}
	public int cargarid(String articuloItem) throws SQLException{
		return tddb.cargarid(articuloItem);
	}
	public int cargaridSupplInvoice(int num_factura) throws SQLException{
		return tddb.cargaridSupplInvoice(num_factura);
	}
	public boolean existeFactura(int factura) throws SQLException {
		return tddb.existeFactura(factura);
	}
	
	public SupplierInvoices getProduct(int id) throws SQLException {
		return tddb.getProduct(id);
	}
	public SupplierInvoiceLineItems getProductDetail(int id) throws SQLException {
		return tddb.getProductDetail(id);
	}
}
