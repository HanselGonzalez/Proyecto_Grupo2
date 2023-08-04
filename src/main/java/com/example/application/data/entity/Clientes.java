package com.example.application.data.entity;

import jakarta.persistence.Entity;

@Entity
public class Clientes extends AbstractEntity {

    private Integer idcliente;
    private String nombre;
    private String correoelectronico;
    private String idultimopedido;
    private String telefono;
    private String direccion;
    private Integer pedidosrealizados;
    
	public Integer getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getPedidosrealizados() {
		return pedidosrealizados;
	}
	public void setPedidosrealizados(Integer pedidosrealizados) {
		this.pedidosrealizados = pedidosrealizados;
	}
	public String getCorreoelectronico() {
		return correoelectronico;
	}
	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}
	public String getIdultimopedido() {
		return idultimopedido;
	}
	public void setIdultimopedido(String idultimopedido) {
		this.idultimopedido = idultimopedido;
	}
    
    
	
    


}
