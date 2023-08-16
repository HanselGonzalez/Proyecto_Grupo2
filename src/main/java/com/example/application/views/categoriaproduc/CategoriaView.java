package com.example.application.views.categoriaproduc;

import com.example.application.data.controler.CategoriasInteractor;
import com.example.application.data.controler.CategoriasInteractorImpl;
import com.example.application.data.entity.Categoria;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Categorias de Productos")
@Route(value = "categoria", layout = MainLayout.class)
public class CategoriaView extends Div implements CategoriasViewModel {

	private Grid<Categoria> grid;
    
    private List<Categoria> categoria;

    private Categoria categorias;
    private CategoriasInteractor controlador;
	private TextField idcategoria;
	private TextField nombre;
	private TextField descripcion;
	private Button aceptar;
	private Button cancelar;
	private ComboBox<Categoria> cbcategoria;
	private boolean columnaProductosVisible = false;
	
	private Button consultar;

	private TextField proveedor;

    public CategoriaView() {
        addClassNames("categoria-view");
       this.categoria = new ArrayList<>();
       this.controlador = new CategoriasInteractorImpl(this);
        
        
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

        nombre = new TextField("Nombre");
        descripcion = new TextField("Descripción");
        proveedor = new TextField("Proveedor");
        cbcategoria = new ComboBox<>("Filtrar Categoria");
        aceptar = new Button("Aceptar");
        cancelar = new Button("Cancelar");
        consultar = new Button("Filtrar");
        cbcategoria.setItemLabelGenerator(Categoria::getNombre);
        
        nombre.addClassNames(LumoUtility.Padding.Horizontal.SMALL, LumoUtility.Padding.Vertical.SMALL);
        descripcion.addClassNames(LumoUtility.Padding.Horizontal.SMALL, LumoUtility.Padding.Vertical.SMALL);
        proveedor.addClassNames(LumoUtility.Padding.Horizontal.SMALL, LumoUtility.Padding.Vertical.SMALL);
        cbcategoria.addClassNames(LumoUtility.Padding.Horizontal.SMALL, LumoUtility.Padding.Vertical.SMALL);
        aceptar.addClassNames(Margin.Top.MEDIUM, Padding.LARGE);
        aceptar.addClassName("button-spacing");
        cancelar.addClassNames(Margin.Top.MEDIUM, Padding.LARGE);
        cancelar.addClassName("button-spacing");
        consultar.addClassNames(Margin.Top.MEDIUM, Padding.LARGE);



        

        personalDetails.add( nombre, descripcion,proveedor,cbcategoria, aceptar,cancelar,consultar);

        return personalDetails;
    }

    

    private Component createGrid() {
    	this.controlador.consultarCategoria2();
    	
        grid = new Grid<>(Categoria.class, false);
        grid.addColumn("idcategoria").setAutoWidth(true).setHeader("ID Categoria");
        grid.addColumn("nombre").setAutoWidth(true).setHeader("Categoria");
        grid.addColumn("descripcion").setAutoWidth(true).setHeader("Descripcion");
        grid.addColumn("cantidad").setAutoWidth(true).setHeader("Cantidad de Productos");
        grid.addColumn("proveedor").setAutoWidth(true).setHeader("Proveedor");


        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        
        
        
        this.controlador.consultarCategoria();
        
        consultar.addSingleClickListener(e -> {
        	 grid.removeAllColumns();
        	 grid.addColumn("nombreproductos").setAutoWidth(true).setHeader("Listado de Productos");
             this.controlador.consultarCategoria3(this.cbcategoria.getValue().getIdcategoria());
        });
        
        
        VerticalLayout mainLayout = new VerticalLayout(grid);
        mainLayout.setSizeFull();

        aceptar.addClickListener(e -> {
            try {
                    this.categorias = new Categoria();
                    
                    this.categorias.setNombre(this.nombre.getValue());
                    this.categorias.setProveedor(this.proveedor.getValue());
                    this.categorias.setDescripcion(this.descripcion.getValue());
                    
                    this.controlador.crearCategoria(categorias);
                    
               clearForm();
                refreshGrid();
                UI.getCurrent().navigate(CategoriaView.class);
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

    private void populateForm(Categoria value) {
           this.categorias = value;
           this.nombre.setValue("");
           this.descripcion.setValue("");
           this.proveedor.setValue("");
           this.cbcategoria.setValue(null);
    }
    
    private void refreshGrid() {
        this.controlador.consultarCategoria();
        this.controlador.consultarCategoria2();
        grid.getDataProvider().refreshAll();
    }



	@Override
	public void refrescarGridCategorias(List<Categoria> categoria) {
		Collection<Categoria> items = categoria;
		grid.setItems(items);	
		this.categoria = categoria;
	}

	
	@Override
	public void refrescarComboCategorias(List<Categoria> categoria) {
		Collection<Categoria> listadoCategorias = categoria;
		cbcategoria.setItems(listadoCategorias);
		this.categoria=categoria;

	}

	@Override
	public void mostrarMensajeCreacion(boolean exito) {
		String mensajeMostrar = "Categoria creado exitosamente!";
		if(!exito) {
			mensajeMostrar = "La Categoria no pudo ser creado";
		}
		 Notification.show(mensajeMostrar);		
		
	}









}
