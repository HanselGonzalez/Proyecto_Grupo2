package com.example.application.data.entity;

import jakarta.persistence.Entity;

@Entity
public class Factura extends AbstractEntity {

    private String npedido;
    private String idcliente;
    private String datoscliente;
    private String idproducto;
    private String datosproducto;
    private Integer cantidad;
    private Integer precio;
    private Integer subtotal;
    private Integer total;
    
    
    
	public String getNpedido() {
		return npedido;
	}
	public void setNpedido(String npedido) {
		this.npedido = npedido;
	}
	public String getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}
	public String getDatoscliente() {
		return datoscliente;
	}
	public void setDatoscliente(String datoscliente) {
		this.datoscliente = datoscliente;
	}
	public String getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(String idproducto) {
		this.idproducto = idproducto;
	}
	public String getDatosproducto() {
		return datosproducto;
	}
	public void setDatosproducto(String datosproducto) {
		this.datosproducto = datosproducto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public Integer getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
    
    
    

    
    
}
