package com.example.application.views.facturas;

import java.util.List;

import com.example.application.data.entity.Factura;

public interface FacturasViewModel {
void refrescarGridPedidos(List<Factura> pedidos);
}
