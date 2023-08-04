package com.example.application.views.clientes;

import com.example.application.data.controler.ClientesInteractor;
import com.example.application.data.controler.ClientesInteractorImpl;
import com.example.application.data.entity.Clientes;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
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

@PageTitle("Clientes")
@Route(value = "clientes", layout = MainLayout.class)
public class ClientesView extends Div implements BeforeEnterObserver, ClientesViewModel{

    private final String CLIENTES_ID = "clientesID";
    private final String CLIENTES_EDIT_ROUTE_TEMPLATE = "clientes/%s/edit";

    private final Grid<Clientes> grid = new Grid<>(Clientes.class, false);

    private TextField IdCliente;
    private TextField Nombre;
    private TextField Telefono;
    private TextField Direccion;
    private TextField correo;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");


    private Clientes clientes;
    private List<Clientes> cliente;
	private TextField Pedidos;
	private ClientesInteractor controlador;


    public ClientesView() {
       
        addClassNames("clientes-view");
        this.setCliente(new ArrayList<>());
        this.controlador = new ClientesInteractorImpl(this);
        
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        
        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);
      
        add(splitLayout);

        // Configure Grid
        grid.addColumn("idcliente").setAutoWidth(true).setHeader("ID Cliente");
        grid.addColumn("nombre").setAutoWidth(true);
        grid.addColumn("telefono").setAutoWidth(true);
        grid.addColumn("direccion").setAutoWidth(true);
        grid.addColumn("correoelectronico").setAutoWidth(true);
        grid.addColumn("idultimopedido").setAutoWidth(true).setHeader("ID Ultimo Pedido");
        grid.addColumn("pedidosrealizados").setAutoWidth(true).setHeader("Pedidos Realizados");
        //grid.addColumn("pedidos").setAutoWidth(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(CLIENTES_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(ClientesView.class);
            }
        });

        // Configure Form

        this.controlador.consultarCliente();
        
        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.clientes == null) {
                    this.clientes = new Clientes();
                }
                
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(ClientesView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> clientesId = event.getRouteParameters().get(CLIENTES_ID).map(Long::parseLong);
        if (clientesId.isPresent()) {
        }
               // Notification.show(String.format("The requested clientes was not found, ID = %s", clientesId.get()),
                  //      3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
               // refreshGrid();
              //  event.forwardTo(ClientesView.class);
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
        IdCliente = new TextField("ID Cliente");
        Nombre = new TextField("Nombre");
        Nombre.setPrefixComponent(VaadinIcon.USER.create());
        Telefono = new TextField("Telefono");
       Telefono.setPrefixComponent(VaadinIcon.PHONE.create());
        Direccion = new TextField("Direccion");
        correo = new TextField("Correo Electronico");

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
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Clientes value) {
        this.clientes = value;

    }

	@Override
	public void refrescarGridClientes(List<Clientes>cliente) {
		Collection<Clientes> items = cliente;
		grid.setItems(items);	
		this.setCliente(cliente);
	}

	public List<Clientes> getCliente() {
		return cliente;
	}

	public void setCliente(List<Clientes> cliente) {
		this.cliente = cliente;
	}
	

	
    
    
}
