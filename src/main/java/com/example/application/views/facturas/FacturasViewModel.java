package com.example.application.views.facturas;

import java.util.List;

import com.example.application.data.entity.Clientes;
import com.example.application.data.entity.Factura;
import com.example.application.data.entity.Producto;

public interface FacturasViewModel {
void refrescarGridPedidos(List<Factura> pedidos);
void mostrarMensajeCreacion(boolean exito);
void refrescarComboClientes(List<Clientes> cliente);
void refrescarComboProductos(List<Producto> productos);

}
