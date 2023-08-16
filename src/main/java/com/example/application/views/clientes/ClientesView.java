package com.example.application.views.clientes;

import com.example.application.data.controler.ClientesInteractor;
import com.example.application.data.controler.ClientesInteractorImpl;
import com.example.application.data.entity.Clientes;
import com.example.application.data.entity.Producto;
import com.example.application.views.MainLayout;
import com.example.application.views.productos.ProductosView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@SuppressWarnings("serial")
@PageTitle("Clientes")
@Route(value = "clientes/:clientes?/:action?(edit)", layout = MainLayout.class)
public class ClientesView extends Div implements BeforeEnterObserver, ClientesViewModel{

    private final String CLIENTES_ID = "clientesID";
    private final String CLIENTES_EDIT_ROUTE_TEMPLATE = "clientes/%s/edit";

    private final Grid<Clientes> grid = new Grid<>(Clientes.class, false);

    private TextField IdCliente;
    private TextField Nombre;
    private TextField Telefono;
    private TextField Direccion;
    private EmailField correo;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");


    private Clientes clientes;
    private List<Clientes> cliente;
	private ClientesInteractor controlador;


    public ClientesView() {
       
        addClassNames("clientes-view");
        cliente = new ArrayList<>();
        this.controlador = new ClientesInteractorImpl(this);
        
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        
        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);
      
        add(splitLayout);

        // Configure Grid
        grid.addColumn("idcliente").setAutoWidth(true).setHeader("Identidad ");
        grid.addColumn("nombre").setAutoWidth(true);
        grid.addColumn("telefono").setAutoWidth(true);
        grid.addColumn("direccion").setAutoWidth(true);
        grid.addColumn("correoelectronico").setAutoWidth(true).setHeader("Correo Electronico");
        //grid.addColumn("pedidos").setAutoWidth(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(CLIENTES_EDIT_ROUTE_TEMPLATE, event.getValue().getIdcliente()));
            } else {
                clearForm();
                UI.getCurrent().navigate(ClientesView.class);
            }
        });

        GridContextMenu<Clientes> menu = grid.addContextMenu();
        menu.addItem("Eliminar", event -> {
        	ConfirmDialog dialog = new ConfirmDialog();
        	dialog.setHeader("Confirmar Eliminación de "+event.getItem().get().getNombre());
        	dialog.setText(
        	        "¿Confirmas que deseas eliminar el producto seleccionado?");

        	dialog.setCancelable(true);

        	dialog.setConfirmText("Eliminar");
        	dialog.setConfirmButtonTheme("error primary");
        	dialog.addConfirmListener(event2 -> {
        		this.controlador.eliminarClientes(event.getItem().get().getIdcliente());
        	});
        	dialog.open();
        });

        this.controlador.consultarCliente();
        
        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
            	if (this.clientes == null) {                	
                    this.clientes = new Clientes();
                    
                        String idclientetext = this.IdCliente.getValue();
                        int idcliente = Integer.parseInt(idclientetext);
                        this.clientes.setIdcliente(idcliente);
                        
                    
	                    this.clientes.setNombre(this.Nombre.getValue());
	                    this.clientes.setDireccion(this.Direccion.getValue());
	                    this.clientes.setTelefono(this.Telefono.getValue());
	                    this.clientes.setCorreoelectronico(this.correo.getValue());
	                    this.controlador.crearClientes(clientes);
                    
                }else {
                	
                        
	                	String idclientetext = this.IdCliente.getValue();
	                    int idcliente = Integer.parseInt(idclientetext);
	                    this.clientes.setIdcliente(idcliente);
	                    
	                
		                this.clientes.setNombre(this.Nombre.getValue());
		                this.clientes.setDireccion(this.Direccion.getValue());
		                this.clientes.setTelefono(this.Telefono.getValue());
		                this.clientes.setCorreoelectronico(this.correo.getValue());
		                this.controlador.actualizarClientes(clientes);
                }
                
                clearForm();
                refreshGrid();
                UI.getCurrent().navigate(ClientesView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error al almacenar los datos. Revisa tu conexión e intenta nuevamente");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> clientesId = event.getRouteParameters().getLong(CLIENTES_ID);
        boolean encontrado = false;
        if (clientesId.isPresent()) {
            for(Clientes c: this.cliente) {
            	if(c.getIdcliente().equals(clientesId.get())) {
            	populateForm(c);
            	encontrado = true;
            	break;
            }
    	}
            if (!encontrado) {
            	Notification.show(String.format("El cliente con identidad = %s", clientesId.get()+" no fue encontrado"),
                        3000, Notification.Position.BOTTOM_START);
                
            	
                refreshGrid();
                event.forwardTo(ClientesView.class);
            }
        
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);
        
        H3 header = new H3("Consultar Cliente");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);
        
        FormLayout formLayout = new FormLayout();
        IdCliente = new TextField("Identidad");
        Nombre = new TextField("Nombre");
        Nombre.setPrefixComponent(VaadinIcon.USER.create());
        Telefono = new TextField("Telefono");
       Telefono.setPrefixComponent(VaadinIcon.PHONE.create());
        Direccion = new TextField("Direccion");
        correo = new EmailField("Correo Electronico");

        grid.asSingleSelect().addValueChangeListener(event -> {
            Clientes selectedProducto = event.getValue();
            populateForm(selectedProducto);
        });
        
        formLayout.add(header, IdCliente, Nombre, Telefono, Direccion, correo);

        
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
    	this.controlador.consultarCliente();
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Clientes value) {
        this.clientes = value;
        if(value == null) {
        	this.IdCliente.setValue("");
        	this.Nombre.setValue("");
        	this.Telefono.setValue("");
        	this.Direccion.setValue("");
        	this.correo.setValue("");
        }else {
        	this.IdCliente.setValue(String.valueOf(value.getIdcliente()));
        	this.Nombre.setValue(value.getNombre());
        	this.Telefono.setValue(value.getTelefono());
        	this.Direccion.setValue(value.getDireccion());
        	this.correo.setValue(value.getCorreoelectronico());
        }

    }

	@Override
	public void refrescarGridClientes(List<Clientes>cliente) {
		Collection<Clientes> items = cliente;
		grid.setItems(items);	
		this.cliente = cliente;
	}

	public List<Clientes> getCliente() {
		return cliente;
	}

	public void setCliente(List<Clientes> cliente) {
		this.cliente = cliente;
	}

	@Override
	public void mostrarMensajeCreacion(boolean exito) {
		String mensajeMostrar = "Cliente creado exitosamente!";
		if(!exito) {
			mensajeMostrar = "Cliente no pudo ser creado";
		}
		 Notification.show(mensajeMostrar);	
		
	}

	@Override
	public void mostrarMensajeActualizacion(boolean exito) {
		String mensajeMostrar = "Cliente actualizado exitosamente!";
		if(!exito) {
			mensajeMostrar = "Cliente no pudo ser actualizado";
		}
		 Notification.show(mensajeMostrar);	
		
	}

	@Override
	public void mostrarMensajeEliminacion(boolean exito) {
		String mensajeMostrar = "Cliente eliminado exitosamente!";
		if(!exito) {
			mensajeMostrar = "Cliente no pudo ser eliminado";
		}
		 Notification.show(mensajeMostrar);
		 this.controlador.consultarCliente();	
		
	}
	

	
    
    
}
