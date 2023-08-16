package com.example.application.views.productos;

import com.example.application.data.controler.ProductoInteractorImpl;
import com.example.application.data.controler.ProductosInteractor;
import com.example.application.data.entity.Categoria;
import com.example.application.data.entity.Producto;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@SuppressWarnings("serial")
@PageTitle("Dispositivos Electronicos")
@Route(value = "productos/:productos?/:action?(edit)", layout = MainLayout.class)
public class ProductosView extends Div implements BeforeEnterObserver, ProductosViewModel {

    private final String PRODUCTO_ID = "productoID";
    private final String PRODUCTO_EDIT_ROUTE_TEMPLATE = "productos/%s/edit";

    private final Grid<Producto> grid = new Grid<>(Producto.class, false);

    private TextField idProducto;
    private ComboBox<Categoria> categoria;
    private TextField nombre;
    private TextField precio;
    private TextField stock;
    private List<Producto> productos;
    private List<Categoria> categorias;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");


    private Producto producto;
    private ProductosInteractor controlador;

    public ProductosView() {      
        addClassNames("productos-view");
        this.productos = new ArrayList<>();
        this.controlador = new ProductoInteractorImpl(this);
        
        
        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("idProducto").setAutoWidth(true).setHeader("ID Producto");
        grid.addColumn("nombre").setAutoWidth(true).setHeader("Producto");
        grid.addColumn("idcategoria").setAutoWidth(true).setHeader("ID Categoria");
        grid.addColumn("categoria").setAutoWidth(true).setHeader("Categoria");
        grid.addColumn("precio").setAutoWidth(true);
        grid.addColumn("cantidad").setAutoWidth(true);
        
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        
        
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(PRODUCTO_EDIT_ROUTE_TEMPLATE, event.getValue().getIdProducto()));
            } else {
                clearForm();
                UI.getCurrent().navigate(ProductosView.class);
            }
        });

        grid.asSingleSelect().addValueChangeListener(event -> {
            Producto selectedProducto = event.getValue();
            populateForm(selectedProducto);
        });
        
        GridContextMenu<Producto> menu = grid.addContextMenu();
        menu.addItem("Eliminar", event -> {
        	ConfirmDialog dialog = new ConfirmDialog();
        	dialog.setHeader("Confirmar Eliminación de "+event.getItem().get().getNombre());
        	dialog.setText(
        	        "¿Confirmas que deseas eliminar el producto seleccionado?");

        	dialog.setCancelable(true);

        	dialog.setConfirmText("Eliminar");
        	dialog.setConfirmButtonTheme("error primary");
        	dialog.addConfirmListener(event2 -> {
        		this.controlador.eliminarProducto(event.getItem().get().getIdProducto());
        	});
        	dialog.open();
        });
        
        
        this.controlador.consultarProducto();
        this.controlador.consultarCategoria();

        
        
        
        // Configure Form

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.producto == null) {                	
                    this.producto = new Producto();
                    
                        
                        String preciotext = this.precio.getValue();
                        double precio = Double.parseDouble(preciotext);
                        this.producto.setPrecio(precio);
                        
                        String cantidadtext = this.stock.getValue();
                        int cantidad = Integer.parseInt(cantidadtext);
                        this.producto.setCantidad(cantidad);
                        
                        
                    
                    this.producto.setIdcategoria(this.categoria.getValue().getIdcategoria());
                    this.producto.setNombre(this.nombre.getValue());
                    this.controlador.crearProducto(producto);
                    
                }else {
                	
                        
                        String preciotext = this.precio.getValue();
                        double precio = Double.parseDouble(preciotext);
                        this.producto.setPrecio(precio);
                        
                        String cantidadtext = this.stock.getValue();
                        int cantidad = Integer.parseInt(cantidadtext);
                        this.producto.setCantidad(cantidad);
                        
                   
                    this.producto.setIdcategoria(this.categoria.getValue().getIdcategoria());
                    this.producto.setNombre(this.nombre.getValue());
                    this.controlador.actualizarProducto(producto);
                }
                
                clearForm();
                refreshGrid();
                UI.getCurrent().navigate(ProductosView.class);
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
    	
    	Optional<Long> productoId = event.getRouteParameters().getLong(PRODUCTO_ID);
        boolean encontrado = false;
    	if (productoId.isPresent()) {
            for(Producto p: this.productos) {
            	if(p.getIdProducto().equals(productoId.get())) {
            	populateForm(p);
            	encontrado = true;
            	break;
            }
    	}
    	
    	if (!encontrado) {
        	Notification.show(String.format("El producto con identidad = %s", productoId.get()+" no fue encontrado"),
                    3000, Notification.Position.BOTTOM_START);
            // when a row is selected but the data is no longer available,
            // refresh grid
            refreshGrid();
            event.forwardTo(ProductosView.class);
        }
    }
}

        
    

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);
        
        H3 header = new H3("Crear Producto");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);
        
        FormLayout formLayout = new FormLayout();
        nombre = new TextField("Nombre");
        stock = new TextField("Cantidad");
        precio = new TextField("Precio");
        precio.setPrefixComponent(VaadinIcon.MONEY.create());
        categoria = new ComboBox<>("Categoria");
        
        categoria.setItemLabelGenerator(Categoria::getNombre);
        
       
        formLayout.add(header,  nombre, categoria,precio, stock);

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
        this.controlador.consultarProducto();
    	grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Producto value) {
        this.producto = value;
        if (value == null) {
           this.categoria.setValue(null);
           this.nombre.setValue("");
           this.precio.setValue("0.0");
           this.stock.setValue("");
        } else {
            this.nombre.setValue(value.getNombre());
            this.precio.setValue(String.valueOf(value.getPrecio()));
            this.stock.setValue(String.valueOf(value.getCantidad()));
            this.categoria.setValue(buscarCategoriaSeleccionada(value.getIdcategoria()));
        }

    }


	private Categoria buscarCategoriaSeleccionada(Integer idCategoria) {
		Categoria seleccionado = new Categoria();
		for (Categoria c: categorias) {
			if(c.getIdcategoria().equals(idCategoria)) {
				seleccionado = c;
				break;
			}
		}
		return seleccionado;
	}

	@Override
	public void refrescarGridProductos(List<Producto> productos) {
		Collection<Producto> items = productos;
		grid.setItems(items);
		this.productos = productos;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public void refrescarComboCategorias(List<Categoria> categorias) {
		Collection<Categoria> listadoCategorias = categorias;
		categoria.setItems(listadoCategorias);
		this.categorias=categorias;
	}

	@Override
	public void mostrarMensajeCreacion(boolean exito) {
		String mensajeMostrar = "Producto creado exitosamente!";
		if(!exito) {
			mensajeMostrar = "Producto no pudo ser creado";
		}
		 Notification.show(mensajeMostrar);		
	}

	@Override
	public void mostrarMensajeActualizacion(boolean exito) {
		String mensajeMostrar = "Producto actualizado exitosamente!";
		if(!exito) {
			mensajeMostrar = "Producto no pudo ser actualizado";
		}
		 Notification.show(mensajeMostrar);		
	}

	@Override
	public void mostrarMensajeEliminacion(boolean exito) {
		String mensajeMostrar = "Producto eliminado exitosamente!";
		if(!exito) {
			mensajeMostrar = "Producto no pudo ser eliminado";
		}
		 Notification.show(mensajeMostrar);
		 this.controlador.consultarProducto();		
	}
}
