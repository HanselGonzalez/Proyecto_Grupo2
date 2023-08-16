package com.example.application.data.controler;

import com.example.application.data.entity.Factura;

public interface FacturasInteractor {
	void consultarPedido(Integer idcliente);
	void consultarCliente();
	void consultarProducto();
	void crearPedidos(Factura nuevo);
}
