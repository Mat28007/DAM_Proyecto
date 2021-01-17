package tablas;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Sociedad implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	// relacion principal Article (many) -> Category (one)
	// implémentée par une clé étrangère (categorie_id) dans Article
	// 1 Article a nécessairement 1 Categorie (nullable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "Idusuario", nullable = false)

	private Usuario usuarios;
	
	@Column(name="denominacionSocial")
	private String denominacionSocial;
	@Column(name="direccion")
	private String direccion;
	@Column(name="codigoPostal")
	private int codigoPostal;
	@Column(name="telefono")
	private int telefono;
	@Column(name="email")
	private String email;
	@Column(name="ciudad")
	private String ciudad;
	@Column(name="provincia")
	private String provincia;
	@Column(name="createdDate")
	private Date createdDate;
	//a faire
	private Set<?> customerInvoiceses = new HashSet<Object>(0);
	//a faire
	private Set<?> supplierInvoiceses = new HashSet<Object>(0);
	
//	@OneToMany(mappedBy="articulo",cascade= CascadeType.ALL)
//	private Set <SupplierInvoiceLineItems> supplierInvoiceLineItemses ;
	
		public Sociedad() {
			super();
		}
		
		public Sociedad(int id) {
			this.id=id;
		}
		
		@Override
		public String toString() {
			return String.format("Sociedad [id=" + id + ", usuarios=" + Usuario.getId()
					+ ", denominacionSocial=" + denominacionSocial
					+ ", direccion=" + direccion + ", codigoPostal="
					+ codigoPostal + ", telefono=" + telefono + ", email="
					+ email + ", ciudad=" + ciudad + ", provincia=" + provincia
					+ ", createdDate=" + createdDate + "]");
		}
		public Sociedad(String denominacionSocial, String direccion, String email,
				String ciudad, String provincia, int codigoPostal,int id,Usuario usuarios,
				int telefono) {
			super();
			this.denominacionSocial = denominacionSocial;
			this.direccion = direccion;
			this.email = email;
			this.ciudad = ciudad;
			this.provincia = provincia;
			this.codigoPostal = codigoPostal;
			this.telefono = telefono;
			this.usuarios=usuarios;
			this.id=id;
		}
		public Sociedad(String denominacionSocial, String direccion, String email,
				String ciudad, String provincia, int codigoPostal,int id,
				int telefono) {
			super();
			this.denominacionSocial = denominacionSocial;
			this.direccion = direccion;
			this.email = email;
			this.ciudad = ciudad;
			this.provincia = provincia;
			this.codigoPostal = codigoPostal;
			this.telefono = telefono;
			this.id=id;
		}
		
		
		

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Usuario getUsuarios() {
			return usuarios;
		}
		public void setUsuarios(Usuario usuarios) {
			this.usuarios = usuarios;
		}
		public String getDenominacionSocial() {
			return denominacionSocial;
		}
		public void setDenominacionSocial(String denominacionSocial) {
			this.denominacionSocial = denominacionSocial;
		}
		public String getDireccion() {
			return direccion;
		}
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
		public int getCodigoPostal() {
			return codigoPostal;
		}
		public void setCodigoPostal(int codigoPostal) {
			this.codigoPostal = codigoPostal;
		}
		public int getTelefono() {
			return telefono;
		}
		public void setTelefono(int telefono) {
			this.telefono = telefono;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getCiudad() {
			return ciudad;
		}
		public void setCiudad(String ciudad) {
			this.ciudad = ciudad;
		}
		public String getProvincia() {
			return provincia;
		}
		public void setProvincia(String provincia) {
			this.provincia = provincia;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public Set<?> getCustomerInvoiceses() {
			return customerInvoiceses;
		}
		public void setCustomerInvoiceses(Set<?> customerInvoiceses) {
			this.customerInvoiceses = customerInvoiceses;
		}
		public Set<?> getSupplierInvoiceses() {
			return supplierInvoiceses;
		}
		public void setSupplierInvoiceses(Set<?> supplierInvoiceses) {
			this.supplierInvoiceses = supplierInvoiceses;
		}

	}


