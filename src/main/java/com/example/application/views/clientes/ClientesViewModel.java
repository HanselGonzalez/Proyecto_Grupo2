package com.example.application.views.clientes;

import java.util.List;

import com.example.application.data.entity.Clientes;

public interface ClientesViewModel {
void refrescarGridClientes(List<Clientes> clientes);
void mostrarMensajeCreacion(boolean exito);
void mostrarMensajeActualizacion(boolean exito);
void mostrarMensajeEliminacion(boolean exito);
}
