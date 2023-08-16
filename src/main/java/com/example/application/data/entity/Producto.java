package com.example.application.data.entity;



public class Producto  {
	
    private Integer idproducto;
    private Integer idcategoria;
    private String nombre;
    private Double precio;
    private Integer cantidad;
    private String categoria;


    public Integer getIdProducto() {
        return idproducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idproducto = idProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
	
	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Integer getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
	}
	
}
