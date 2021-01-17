package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tablas.SupplierInvoiceLineItems;
import tablas.SupplierInvoices;

public class TraitementDocumentoDataBase extends DataBase {
	protected PreparedStatement getProduct;
	public TraitementDocumentoDataBase () {}
	
	// Insert Supplier Invoice
	public void saveSupplierInvoice(SupplierInvoices si) throws SQLException{
		String insertSql="INSERT INTO supplier_invoices (fecha,idProveedor,numero_factura)VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insertSql);
		
		ps.setDate(1, si.getFecha());
		ps.setInt(2, si.getProveedor().getId());
		//ps.setTimestamp(4, new java.sql.Timestamp(client.getBirthday().getTime()));
		ps.setInt(3, si.getNumeroFactura());
		ps.executeUpdate();
		ps.close();
		
	}
	
	// Insert Supplier Invoice
		public void saveSupplierInvoiceLine(SupplierInvoiceLineItems si) throws SQLException{
			String insertSql="INSERT INTO supplier_invoice_line_items (id_producto,id_num_factura,cantidad_comprada,precio_compra,tax_rate_charged)VALUES(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insertSql);
			
			ps.setInt(1, si.getArticulo().getIdarticulo());
			ps.setInt(2, si.getSupplierInvoices().getIdSupplierInvoices());
			ps.setInt(3, si.getCantidadComprada());
			ps.setDouble(4, si.getPrecioCompra());
			ps.setInt(5, si.getTaxRateCharged());
			ps.executeUpdate();
			ps.close();
			
		}
	
public int idArt(int cat,String art)	throws SQLException{
	int id=0;
	String selSql="SELECT idarticulo FROM articulo WHERE fkcategoria=? AND nombre_articulo=?";
	PreparedStatement ps = conn.prepareStatement(selSql);
	ps.setInt(1, cat);
	ps.setString(2,art);
	ResultSet checkResult = ps.executeQuery();
	if (checkResult.next()) {
	//	id = checkResult.getInt("idarticulo");
		return checkResult.getInt(2);
	}else {
		return 1;
	}
	
}

public int cargarid(String art) throws SQLException {
	String checkSql = "SELECT idarticulo FROM ARTICULO WHERE nombre_articulo=?";
	PreparedStatement smt = conn.prepareStatement(checkSql);
	smt.setString(1, art);
	ResultSet rs = smt.executeQuery();
	

	if (rs.next()) {
		return rs.getInt(1);
	
	} 
	else {
		return -1;
	}
}

public int cargaridSupplInvoice(int num_factura) throws SQLException {
	String checkSql = "SELECT idSupplier_invoices FROM supplier_invoices WHERE numero_factura=?";
	PreparedStatement smt = conn.prepareStatement(checkSql);
	smt.setInt(1, num_factura);
	ResultSet rs = smt.executeQuery();
	

	if (rs.next()) {
		return rs.getInt(1);
	
	} 
	else {
		return -1;
	}
}

//comprobar si existe numero factura al crearla
	public boolean existeFactura(int factura) throws SQLException {
		String checkSql = "SELECT numero_factura FROM supplier_invoices WHERE numero_factura=?";
		PreparedStatement ps = conn.prepareStatement(checkSql);
		ps.setInt(1, factura);
		ResultSet checkResult = ps.executeQuery();
		if (checkResult.next()) {
			return true;
		} else {
			return false;
		}
	}

	// select all para pdf
	public int printFactura(int factura) throws SQLException {
		String checkSql = "SELECT idproveedor,numero_factura FROM supplier_invoices WHERE idSupplier_invoices=?";
		PreparedStatement ps = conn.prepareStatement(checkSql);
		ResultSet checkResult = ps.executeQuery();
		while (checkResult.next()) {
		return	 checkResult.getInt("idproveedor");
	//	return   checkResult.getInt("numero_factura");
		}
		return factura;
	}
	
	public SupplierInvoices getProduct(int id) throws SQLException {
		String checkSql = "SELECT numero_factura,fecha FROM supplier_invoices WHERE idSupplier_invoices=?";
		PreparedStatement ps = conn.prepareStatement(checkSql);
		ps.setInt(1, id);
		ResultSet checkResult = ps.executeQuery();
	    if (checkResult.next()) {
	    	SupplierInvoices product = new SupplierInvoices();
	        product.setNumeroFactura(checkResult.getInt("numero_factura"));
	        product.setFecha(checkResult.getDate("fecha"));
	        return product;       
	    }
	    return null;
	}
	public SupplierInvoiceLineItems getProductDetail(int id) throws SQLException {
		String checkSql = "SELECT cantidad_comprada,precio_compra FROM supplier_invoice_line_items WHERE id_num_factura=?";
		PreparedStatement ps = conn.prepareStatement(checkSql);
		ps.setInt(1, id);
		ResultSet checkResult = ps.executeQuery();
	    while (checkResult.next()) {
	    	SupplierInvoiceLineItems product = new SupplierInvoiceLineItems();
	        product.setCantidadComprada(checkResult.getInt("cantidad_comprada"));
	        product.setPrecioCompra(checkResult.getDouble("precio_compra"));
	        return product;       
	    }
	    return null;
	}
}
	
	

