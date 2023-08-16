package com.example.application.views.categoriaproduc;

import java.util.List;

import com.example.application.data.entity.Categoria;
import com.example.application.data.entity.Clientes;
import com.example.application.data.entity.Producto;

public interface CategoriasViewModel {
void refrescarGridCategorias(List<Categoria> categoria);
void refrescarComboCategorias(List<Categoria> categoria);
void mostrarMensajeCreacion(boolean exito);
}
