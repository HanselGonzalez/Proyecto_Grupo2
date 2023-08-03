package com.example.application.data.controler;

import java.io.IOException;

import com.example.application.data.entity.ResponseClientes;
import com.example.application.data.service.PROYECTORepositoryImpl;
import com.example.application.views.clientes.ClientesViewModel;

public class ClientesInteractorImpl implements ClientesInteractor {

		
		private PROYECTORepositoryImpl modelo;
		private ClientesViewModel vista;
		
		public ClientesInteractorImpl(ClientesViewModel vista) {
		super();
		this.modelo = PROYECTORepositoryImpl.getInstance("https://apex.oracle.com/", 600000L);
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
}
