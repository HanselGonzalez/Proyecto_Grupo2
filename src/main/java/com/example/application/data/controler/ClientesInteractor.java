package com.example.application.data.controler;

import com.example.application.data.entity.Clientes;

public interface ClientesInteractor {
	void consultarCliente();
	void crearClientes(Clientes nuevo);
	void actualizarClientes(Clientes actualizar);
	void eliminarClientes(Integer identidadC);
}
