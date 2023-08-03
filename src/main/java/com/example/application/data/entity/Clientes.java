package com.example.application.data.entity;

import jakarta.persistence.Entity;

@Entity
public class Clientes extends AbstractEntity {

    private Integer idcliente;
    private String nombre;
    private String telefono;
    private String direccion;
    private Integer pedidos;
    
    
	public Integer getIdCliente() {
		return idcliente;
	}
	public void setIdCliente(Integer idCliente) {
		idcliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Integer getPedidos() {
		return pedidos;
	}
	public void setPedidos(Integer pedidos) {
		this.pedidos = pedidos;
	}


}
