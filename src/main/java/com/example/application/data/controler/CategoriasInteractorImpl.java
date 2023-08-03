package com.example.application.data.controler;

import java.io.IOException;

import com.example.application.data.entity.ResponseCategorias;
import com.example.application.data.entity.ResponseProductos;
import com.example.application.data.service.PROYECTORepositoryImpl;
import com.example.application.views.categoriaproduc.CategoriasViewModel;
import com.example.application.views.productos.ProductosViewModel;

public class CategoriasInteractorImpl implements CategoriasInteractor {

		
		private PROYECTORepositoryImpl modelo;
		private CategoriasViewModel vista;
		
		public CategoriasInteractorImpl(CategoriasViewModel vista) {
		super();
		this.modelo = PROYECTORepositoryImpl.getInstance("https://apex.oracle.com/", 600000L);
			this.vista = vista;
		}
	


		@Override
		public void consultarCategoria() {
			try {
				ResponseCategorias respuesta = this.modelo.getCategorias();
				this.vista.refrescarGridCategorias(respuesta.getItems());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
}
