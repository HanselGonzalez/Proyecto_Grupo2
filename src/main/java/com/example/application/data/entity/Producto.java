package com.example.application.data.entity;

import jakarta.persistence.Entity;

@Entity
public class Producto extends AbstractEntity {

    private Integer idproducto;
    private String idcategoria;
    private String nombre;
    private Double precio;
    private Integer cantidad;
    private String proveedor;
    private Integer pedidosrealizados;


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
    
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getIdCategoria() {
		return idcategoria;
	}
	public void setIdCategoria(String idCategoria) {
		this.idcategoria = idCategoria;
	}
	public Integer getPedidosrealizados() {
		return pedidosrealizados;
	}
	public void setPedidosrealizados(Integer pedidosrealizados) {
		this.pedidosrealizados = pedidosrealizados;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
