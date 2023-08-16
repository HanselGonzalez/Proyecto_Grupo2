package com.example.application.data.controler;

import java.io.IOException;

import com.example.application.data.entity.Categoria;
import com.example.application.data.entity.ResponseCategorias;
import com.example.application.data.service.PROYECTORepositoryImpl;
import com.example.application.views.categoriaproduc.CategoriasViewModel;

public class CategoriasInteractorImpl implements CategoriasInteractor {

		
		private PROYECTORepositoryImpl modelo;
		private CategoriasViewModel vista;
		
		public CategoriasInteractorImpl(CategoriasViewModel vista) {
		super();
		this.modelo = PROYECTORepositoryImpl.getInstance("https://apex.oracle.com/", 800000L);
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
		
		@Override
		public void consultarCategoria2() {
			try {
				ResponseCategorias respuesta = this.modelo.getCategorias();
				this.vista.refrescarComboCategorias(respuesta.getItems());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void consultarCategoria3(Integer idcategoria) {
			try {
				ResponseCategorias respuesta = this.modelo.getCategorias2(idcategoria);
				this.vista.refrescarGridCategorias(respuesta.getItems());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}




		@Override
		public void crearCategoria(Categoria nuevo) {
			try {
				boolean respuesta = this.modelo.createCategoria(nuevo);
				this.vista.mostrarMensajeCreacion(respuesta);
			}catch(IOException e) {
				e.printStackTrace();
			}			
		}



		
}
