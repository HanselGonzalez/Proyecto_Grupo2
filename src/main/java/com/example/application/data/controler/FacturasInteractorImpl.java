package com.example.application.data.controler;

import java.io.IOException;

import com.example.application.data.entity.Factura;
import com.example.application.data.entity.ResponseClientes;
import com.example.application.data.entity.ResponsePedidos;
import com.example.application.data.entity.ResponseProductos;
import com.example.application.data.service.PROYECTORepositoryImpl;
import com.example.application.views.facturas.FacturasViewModel;

public class FacturasInteractorImpl implements FacturasInteractor {

		
		private PROYECTORepositoryImpl modelo;
		private FacturasViewModel vista;
		
		public FacturasInteractorImpl(FacturasViewModel vista) {
		super();
		this.modelo = PROYECTORepositoryImpl.getInstance("https://apex.oracle.com/", 800000L);
			this.vista = vista;
		}
	


		@Override
		public void consultarPedido(Integer idcliente) {
			try {
				ResponsePedidos respuesta = this.modelo.getPedidos(idcliente);
				this.vista.refrescarGridPedidos(respuesta.getItems());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}



		@Override
		public void crearPedidos(Factura nuevo) {
			try {
				boolean respuesta = this.modelo.createPedidos(nuevo);
				this.vista.mostrarMensajeCreacion(respuesta);
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}



		@Override
		public void consultarCliente() {
				try {
					ResponseClientes respuesta = this.modelo.getClientes();
					this.vista.refrescarComboClientes(respuesta.getItems());
				}catch(IOException e) {
					e.printStackTrace();
				}
			}



		@Override
		public void consultarProducto() {
			try {
				ResponseProductos respuesta = this.modelo.getProductos();
				this.vista.refrescarComboProductos(respuesta.getItems());
			}catch(IOException e) {
				e.printStackTrace();
			}			
		}
			
		
}
