package com.example.application.data.entity;

import jakarta.persistence.Entity;

@Entity
public class Categoria extends AbstractEntity {

    private String idcategoria;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private Integer idultimoproductovendido;
    
    
	public String getIdCategoria() {
		return idcategoria;
	}
	public void setIdCategoria(String idCategoria) {
		this.idcategoria = idCategoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getIdultimoproductovendido() {
		return idultimoproductovendido;
	}
	public void setIdultimoproductovendido(Integer idultimoproductovendido) {
		this.idultimoproductovendido = idultimoproductovendido;
	}
	
	
    
	
   
    
}
