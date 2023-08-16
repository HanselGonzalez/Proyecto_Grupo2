package com.example.application.data.controler;

import com.example.application.data.entity.Producto;

public interface ProductosInteractor {
	void consultarProducto();
	void consultarCategoria();
	void crearProducto(Producto nuevo);
	void actualizarProducto(Producto actualizar);
	void eliminarProducto(Integer identidad);

}
