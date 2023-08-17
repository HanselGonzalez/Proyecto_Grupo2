package com.example.application.data.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Entity;

@Entity
public class Factura extends AbstractEntity {

    private Integer npedido;
    private String idcliente;
    private String cliente;
    private Integer idproducto;
    private String producto;
    private Double precio;
    private Integer cantidad;
    private String fechadelpedido ;
    
    
    
	
	
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
	public String getFechadelpedido() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    	Date fecha = new Date();

    	String fechadelpedido = dateFormat.format(fecha);
		return fechadelpedido;
	}
	public void setFechadelpedido(String fechadelpedido) {
		
		this.fechadelpedido = fechadelpedido;
	}

	
	
	public Integer getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getNpedido() {
		return npedido;
	}
	public void setNpedido(Integer npedido) {
		this.npedido = npedido;
	}
	public String getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}
	
	
    
    
    

    
    
}
