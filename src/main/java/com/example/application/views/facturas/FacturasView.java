package com.example.application.views.facturas;

import com.example.application.data.controler.FacturasInteractor;
import com.example.application.data.controler.FacturasInteractorImpl;
import com.example.application.data.entity.Categoria;
import com.example.application.data.entity.Clientes;
import com.example.application.data.entity.Factura;
import com.example.application.data.entity.Producto;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Pedidos")
@Route(value = "facturas", layout = MainLayout.class)

public class FacturasView extends Div implements FacturasViewModel {

    private Grid<Factura> grid;

    private Button realizar;
    private Button consultar;
	private Button cancelar;
    private Factura pedido;
    private List<Factura> pedidos;
    private FacturasInteractor controlador;
	private ComboBox<Clientes> clientes;
	private List<Clientes> cliente;
	private TextField idproducto;
	
	private List<Producto> productos;
	private ComboBox<Producto> producto;

	private TextField cantidad;

    
    public FacturasView() {
        addClassNames("facturas-view");
        this.pedidos = new ArrayList<>();
        this.controlador = new FacturasInteractorImpl(this);
        
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Height.FULL);

        


        Main content = new Main();
        content.addClassNames(Display.GRID, Gap.XLARGE, AlignItems.START);

        content.add(createCheckoutForm());
        add(content);
        
        VerticalLayout layout = new VerticalLayout(createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }

    private Component createCheckoutForm() {
        VerticalLayout checkoutForm = new VerticalLayout();

        checkoutForm.add(createPersonalDetailsSection());

        return checkoutForm;
    }

    private Section createPersonalDetailsSection() {
    	Section personalDetails = new Section();
        personalDetails.addClassNames(Display.FLEX, FlexDirection.ROW, Margin.Bottom.XSMALL, Margin.Top.SMALL, AlignItems.CENTER);

        clientes = new ComboBox<>("Cliente");
        producto = new ComboBox<>("Producto");
        cantidad = new TextField("Cantidad");
        realizar = new Button("Realizar Pedido");
        cancelar = new Button("Cancelar");
        consultar = new Button("Consultar");
        
        clientes.setItemLabelGenerator(Clientes::getNombre);
        producto.setItemLabelGenerator(Producto::getNombre);

        
        
        clientes.addClassNames(LumoUtility.Padding.Horizontal.SMALL, LumoUtility.Padding.Vertical.SMALL);
        producto.addClassNames(LumoUtility.Padding.Horizontal.SMALL, LumoUtility.Padding.Vertical.SMALL);
        cantidad.addClassNames(LumoUtility.Padding.Horizontal.SMALL, LumoUtility.Padding.Vertical.SMALL);

        realizar.addClassNames(Margin.Top.MEDIUM, Padding.LARGE);
        realizar.addClassName("button-spacing");
        cancelar.addClassNames(Margin.Top.MEDIUM, Padding.LARGE);
        consultar.addClassNames(Margin.Top.MEDIUM, Padding.LARGE);


        

        personalDetails.add(clientes, producto,cantidad, realizar,cancelar,consultar);

        return personalDetails;
    }

    private Component createGrid() {
    	
    	this.controlador.consultarCliente();
        this.controlador.consultarProducto();
        
    	
        
        
        grid = new Grid<>(Factura.class, false);
        grid.addColumn(Factura::getNpedido).setHeader("# Pedido").setAutoWidth(true);
        grid.addColumn(Factura::getCliente).setHeader("Cliente").setAutoWidth(true);
        grid.addColumn(Factura::getProducto).setHeader("Producto").setAutoWidth(true);
        grid.addColumn(Factura::getPrecio).setHeader("Precio").setAutoWidth(true);
        grid.addColumn(Factura::getCantidad).setHeader("Cantidad").setAutoWidth(true);
        grid.addColumn(Factura::getFechadelpedido).setHeader("Fecha del Pedido").setAutoWidth(true);
        
        
        consultar.addClickListener(e -> {
            
        	
            this.controlador.consultarPedido(this.clientes.getValue().getIdcliente());
            
        });	
        
        
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        grid.addClassNames("rounded-corners");

        
        
        VerticalLayout mainLayout = new VerticalLayout(grid);
        mainLayout.setSizeFull();

        
        
        
        realizar.addClickListener(e -> {
            try {
                         	
                    this.pedido = new Factura();
                    
                    this.pedido.setIdcliente(this.clientes.getValue().getIdcliente());
                    this.pedido.setIdproducto(this.producto.getValue().getIdProducto());
                    
                    String cantidadtext = this.cantidad.getValue();
                    int cantidad = Integer.parseInt(cantidadtext);
                    this.pedido.setCantidad(cantidad);
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                	Date fecha = new Date();
                	String fechadelpedido = dateFormat.format(fecha);
                	
                	this.pedido.setFechadelpedido(fechadelpedido);
                	
            		
                    
                    
                    
                    this.controlador.crearPedidos(pedido);
                    
               clearForm();
                refreshGrid();
                UI.getCurrent().navigate(FacturasView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error al almacenar los datos. Revisa tu conexión e intenta nuevamente");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        
        return mainLayout;
    }

    
    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Factura value) {
        this.pedido = value;
           this.producto.setValue(null);
           this.cantidad.setValue("");
           this.clientes.setValue(null);

    }
    

	private void refreshGrid() {
    	this.controlador.consultarPedido(this.clientes.getValue().getIdcliente());
        grid.getDataProvider().refreshAll();
    }

	@Override
	public void refrescarGridPedidos(List<Factura> pedidos) {
		Collection<Factura> items = pedidos;
		grid.setItems(items);
		this.pedidos = pedidos;
		
	}

	@Override
	public void refrescarComboClientes(List<Clientes> cliente) {
		Collection<Clientes> listadoClientes = cliente;
		clientes.setItems(listadoClientes);
		this.cliente=cliente;
	}
	
	
	public void refrescarComboProductos(List<Producto> productos) {
		Collection<Producto> listadoProductos = productos;
		producto.setItems(listadoProductos);
		this.productos=productos;
	}
	
	
	@Override
	public void mostrarMensajeCreacion(boolean exito) {
		String mensajeMostrar = "Pedido realizado exitosamente!";
		if(!exito) {
			mensajeMostrar = "Pedido no pudo ser realizado";
		}
		 Notification.show(mensajeMostrar);	
		
	}
}