package com.example.application.data.entity;

import jakarta.persistence.Entity;

@Entity
public class Producto extends AbstractEntity {

    private Integer idproducto;
    private String idcategoria;
    private String nombre;
    private Integer precio;
    private Integer stock;
    private String proovedor;

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
    public Integer getPrecio() {
        return precio;
    }
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public String getProovedor() {
        return proovedor;
    }
    public void setProovedor(String proovedor) {
        this.proovedor = proovedor;
    }
	public String getIdCategoria() {
		return idcategoria;
	}
	public void setIdCategoria(String idCategoria) {
		this.idcategoria = idCategoria;
	}

}
