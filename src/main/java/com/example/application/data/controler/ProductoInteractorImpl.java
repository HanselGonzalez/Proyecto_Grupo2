package com.example.application.data.controler;

import java.io.IOException;

import com.example.application.data.entity.Producto;
import com.example.application.data.entity.ResponseCategorias;
import com.example.application.data.entity.ResponseProductos;
import com.example.application.data.service.PROYECTORepositoryImpl;
import com.example.application.views.productos.ProductosViewModel;

public class ProductoInteractorImpl implements ProductosInteractor {

		
		private PROYECTORepositoryImpl modelo;
		private ProductosViewModel vista;
		
		public ProductoInteractorImpl(ProductosViewModel vista) {
		super();
		this.modelo = PROYECTORepositoryImpl.getInstance("https://apex.oracle.com/", 800000L);
			this.vista = vista;
		}
	


		@Override
		public void consultarProducto() {
			try {
				ResponseProductos respuesta = this.modelo.getProductos();
				this.vista.refrescarGridProductos(respuesta.getItems());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void consultarCategoria() {
			try {
				ResponseCategorias respuesta = this.modelo.getCategorias();
				this.vista.refrescarComboCategorias(respuesta.getItems());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}



		@Override
		public void crearProducto(Producto nuevo) {
			try {
				boolean respuesta = this.modelo.createProducto(nuevo);
				this.vista.mostrarMensajeCreacion(respuesta);
			}catch(IOException e) {
				e.printStackTrace();
			}			
		}



		@Override
		public void actualizarProducto(Producto actualizar) {
			try {
				boolean respuesta = this.modelo.updateProducto(actualizar);
				this.vista.mostrarMensajeActualizacion(respuesta);
			}catch(IOException e) {
				e.printStackTrace();
			}				
		}



		@Override
		public void eliminarProducto(Integer identidad) {
			try {
				boolean respuesta = this.modelo.deleteProducto(identidad);
				this.vista.mostrarMensajeEliminacion(respuesta);
			}catch(IOException e) {
				e.printStackTrace();
			}				
		}
}
