
package com.cart.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name ="fullname", length = 100, nullable = false)
    private String fullname;
	
	@Column(name ="email", length = 100, nullable = false)
    private String email;
	
	@Column(name ="created_date")
    private Date createDate;
	
	@Column(name = "total")
	private Double total = (double) 0;
	
	@Column(name = "status")
	private String status = "NEW";
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	private List<DetalleCartCompra> detalleCompra;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<DetalleCartCompra> getDetalleCompra() {
		return detalleCompra;
	}

	public void setDetalleCompra(List<DetalleCartCompra> detalleCompra) {
		this.detalleCompra = detalleCompra;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
