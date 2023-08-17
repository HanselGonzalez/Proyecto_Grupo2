package com.example.application.data.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Categoria {

    private Integer idcategoria;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private String nombreproductos;
    private String fechadecreacion;
    
	public Integer getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
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
	public String getNombreproductos() {
		return nombreproductos;
	}
	public void setNombreproductos(String nombreproductos) {
		this.nombreproductos = nombreproductos;
	}
	
	public String getFechadecreacion() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    	Date fecha = new Date();

    	String fechadelpedido = dateFormat.format(fecha);
		return fechadelpedido;
	}
	public void setFechadecreacion(String fechadecreacion) {
		this.fechadecreacion = fechadecreacion;
	}
	
	
	
	
    
	
   
    
}
