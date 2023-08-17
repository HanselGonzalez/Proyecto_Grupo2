package com.example.application.data.controler;

import java.io.IOException;
import java.math.BigInteger;

import com.example.application.data.entity.Clientes;
import com.example.application.data.entity.ResponseClientes;
import com.example.application.data.service.PROYECTORepositoryImpl;
import com.example.application.views.clientes.ClientesViewModel;

public class ClientesInteractorImpl implements ClientesInteractor {

		
		private PROYECTORepositoryImpl modelo;
		private ClientesViewModel vista;
		
		public ClientesInteractorImpl(ClientesViewModel vista) {
		super();
		this.modelo = PROYECTORepositoryImpl.getInstance("https://apex.oracle.com/", 800000L);
			this.vista = vista;
		}
	


		@Override
		public void consultarCliente() {
			try {
				ResponseClientes respuesta = this.modelo.getClientes();
				this.vista.refrescarGridClientes(respuesta.getItems());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void crearClientes(Clientes nuevo) {
			try {
				boolean respuesta = this.modelo.createClientes(nuevo);
				this.vista.mostrarMensajeCreacion(respuesta);
			}catch(IOException e) {
				e.printStackTrace();
			}			
		}



		@Override
		public void actualizarClientes(Clientes actualizar) {
			try {
				boolean respuesta = this.modelo.updateClientes(actualizar);
				this.vista.mostrarMensajeActualizacion(respuesta);
			}catch(IOException e) {
				e.printStackTrace();
			}				
		}



		@Override
		public void eliminarClientes(String identidadC) {
			try {
				boolean respuesta = this.modelo.deleteClientes(identidadC);
				this.vista.mostrarMensajeEliminacion(respuesta);
			}catch(IOException e) {
				e.printStackTrace();
			}				
		}

		
}
