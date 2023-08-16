package com.example.application.views.productos;

import java.util.List;

import com.example.application.data.entity.Categoria;
import com.example.application.data.entity.Producto;

public interface ProductosViewModel {
void refrescarGridProductos(List<Producto> productos);
void refrescarComboCategorias(List<Categoria> categorias);
void mostrarMensajeCreacion(boolean exito);
void mostrarMensajeActualizacion(boolean exito);
void mostrarMensajeEliminacion(boolean exito);

}
