package com.example.application.data.controler;

import com.example.application.data.entity.Categoria;

public interface CategoriasInteractor {
	void consultarCategoria();
	void crearCategoria(Categoria nuevo);
	void consultarCategoria2();
	void consultarCategoria3(Integer idcategoria);
}
