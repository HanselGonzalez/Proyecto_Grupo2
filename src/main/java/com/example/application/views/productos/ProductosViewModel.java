package com.example.application.views.productos;

import java.util.List;

import com.example.application.data.entity.Producto;

public interface ProductosViewModel {
void refrescarGridProductos(List<Producto> productos);
}
