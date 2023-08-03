package com.example.application.data.controler;

import java.io.IOException;

import com.example.application.data.entity.ResponsePedidos;
import com.example.application.data.service.PROYECTORepositoryImpl;
import com.example.application.views.facturas.FacturasViewModel;

public class FacturasInteractorImpl implements FacturasInteractor {

		
		private PROYECTORepositoryImpl modelo;
		private FacturasViewModel vista;
		
		public FacturasInteractorImpl(FacturasViewModel vista) {
		super();
		this.modelo = PROYECTORepositoryImpl.getInstance("https://apex.oracle.com/", 600000L);
			this.vista = vista;
		}
	


		@Override
		public void consultarPedido() {
			try {
				ResponsePedidos respuesta = this.modelo.getPedidos();
				this.vista.refrescarGridPedidos(respuesta.getItems());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
}
