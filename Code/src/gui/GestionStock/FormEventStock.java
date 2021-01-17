package gui.GestionStock;

import java.util.EventObject;

import tablas.Categoria;

public class FormEventStock  extends EventObject {
	private String descripcion,nombreArticulo;
	private int id,inventario;
	private double precioCompra,precioVenta;
	private Categoria  categoria;
	public FormEventStock(Object source) {
		super(source);
	
	}
	



	public FormEventStock(Object source, Categoria categoria, String descripcion,
			String nombreArticulo, int id, double precioVenta, double precioCompra,
			int inventario) {
		super(source);
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.nombreArticulo = nombreArticulo;
		this.id = id;
		this.precioVenta = precioVenta;
		this.precioCompra = precioCompra;
		this.inventario = inventario;
	}




	public String getNombreArticulo() {
		return nombreArticulo;
	}




	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}




	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public int getInventario() {
		return inventario;
	}

	public void setInventario(int inventario) {
		this.inventario = inventario;
	}

}
