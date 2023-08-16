package com.example.application.data.service;

import com.example.application.data.entity.Categoria;
import com.example.application.data.entity.Clientes;
import com.example.application.data.entity.Factura;
import com.example.application.data.entity.Producto;
import com.example.application.data.entity.ResponseCategorias;
import com.example.application.data.entity.ResponseClientes;
import com.example.application.data.entity.ResponsePedidos;
import com.example.application.data.entity.ResponseProductos;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface PROYECTORepository {
	
	//PRODUCTO TABLA
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/uth23bd/PDE/productos")
	Call<ResponseProductos>obtenerProductos();
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/uth23bd/PDE/productos")
	Call<ResponseBody>CrearProducto(@Body Producto nuevo);
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/uth23bd/PDE/productos")
	Call<ResponseBody>actualizarProducto(@Body Producto actualizar);
	
	
	@Headers({
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/uth23bd/PDE/productos")
	Call<ResponseBody> eliminarProducto(@Query("id") Integer identidad);
	
	
	
	
	//CLIENTES TABLA
	
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/uth23bd/PDE/clientes")
	Call<ResponseClientes>obtenerClientes();
	
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/uth23bd/PDE/clientes")
	Call<ResponseBody>crearClientes(@Body Clientes nuevo);
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/uth23bd/PDE/clientes")
	Call<ResponseBody>actualizarClientes(@Body Clientes actualizar);
	
	@Headers({
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/uth23bd/PDE/clientes")
	Call<ResponseBody> eliminarClientes(@Query("id") Integer identidadC);
	
	
	//CATEGORIAS TABLA
	
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/uth23bd/PDE/categorias")
	Call<ResponseCategorias>obtenerCategorias();
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/uth23bd/PDE/categorias2")
	Call<ResponseCategorias>obtenerCategorias2(@Query("id") Integer idcategoria);
	
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/uth23bd/PDE/categorias")
	Call<ResponseBody>CrearCategoria(@Body Categoria nuevo);
	
	
	
	
	
	//PEDIDOS TABLA
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/uth23bd/PDE/pedidos")
	Call<ResponsePedidos>obtenerPedidos(@Query("id") Integer idcliente);
	
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/uth23bd/PDE/pedidos")
	Call<ResponseBody>crearPedido(@Body Factura nuevo);
	
	
	
	
	
	

	
}
