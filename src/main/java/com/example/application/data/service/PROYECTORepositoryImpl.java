package com.example.application.data.service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigInteger;

import com.example.application.data.entity.Categoria;
import com.example.application.data.entity.Clientes;
import com.example.application.data.entity.Factura;
import com.example.application.data.entity.Producto;
import com.example.application.data.entity.ResponseCategorias;
import com.example.application.data.entity.ResponseClientes;
import com.example.application.data.entity.ResponsePedidos;
import com.example.application.data.entity.ResponseProductos;

import okhttp3.ResponseBody;

public class PROYECTORepositoryImpl {

	private static PROYECTORepositoryImpl instance;
	private RepositoryClient client;
	
	private PROYECTORepositoryImpl(String url, Long timeout) {
		
		this.client = new RepositoryClient(url, timeout);
		
	}
	
	public static PROYECTORepositoryImpl getInstance(String url, Long timeout) {
		if(instance == null){
			synchronized(PROYECTORepositoryImpl.class) {
				if(instance == null){
					instance = new PROYECTORepositoryImpl(url, timeout);
				}
			}
		}
		return instance;
	}
	
	
	
	
	//PRODUCTOS
	public ResponseProductos getProductos() throws IOException {
		Call<ResponseProductos> call = client.getDatabaseService().obtenerProductos();
		Response<ResponseProductos> response = call.execute();
		if(response.isSuccessful()) {
			return response.body();
		}else {
			return null;
		}
	}
	
	public boolean createProducto(Producto nuevo) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().CrearProducto(nuevo);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
		
	}
	
	public boolean updateProducto(Producto actualizar) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().actualizarProducto(actualizar);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
		
	}
	
	
	public boolean deleteProducto(Integer identidad) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().eliminarProducto(identidad);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
		
	}
	
	
	
	
	//CLIENTES
	public ResponseClientes getClientes() throws IOException {
		Call<ResponseClientes> call = client.getDatabaseService().obtenerClientes();
		Response<ResponseClientes> response = call.execute();
		if(response.isSuccessful()) {
			return response.body();
		}else {
			return null;
		}
	}
	
	public boolean createClientes(Clientes nuevo) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().crearClientes(nuevo);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
		
	}
	
	public boolean updateClientes(Clientes actualizar) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().actualizarClientes(actualizar);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
		
	}
	
	
	public boolean deleteClientes(String identidadC) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().eliminarClientes(identidadC);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
		
	}
	
	
	
	
	//CATEGORIAS
	public ResponseCategorias getCategorias() throws IOException {
		Call<ResponseCategorias> call = client.getDatabaseService().obtenerCategorias();
		Response<ResponseCategorias> response = call.execute();
		if(response.isSuccessful()) {
			return response.body();
		}else {
			return null;
		}
	}
	
	public ResponseCategorias getCategorias2(Integer idcategoria) throws IOException {
		Call<ResponseCategorias> call = client.getDatabaseService().obtenerCategorias2(idcategoria);
		Response<ResponseCategorias> response = call.execute();
		if(response.isSuccessful()) {
			return response.body();
		}else {
			return null;
		}
	}
	
	

	
	public boolean createCategoria(Categoria nuevo) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().CrearCategoria(nuevo);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
		
	}
	
	
	
	//PEDIDOS
	
	public ResponsePedidos getPedidos(String idcliente) throws IOException {
		Call<ResponsePedidos> call = client.getDatabaseService().obtenerPedidos(idcliente);
		Response<ResponsePedidos> response = call.execute();
		if(response.isSuccessful()) {
			return response.body();
		}else {
			return null;
		}
	}
	
	public boolean createPedidos(Factura nuevo) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().crearPedido(nuevo);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
		
	}
	
}
