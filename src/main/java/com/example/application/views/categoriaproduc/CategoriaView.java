package com.example.application.views.categoriaproduc;

import com.example.application.data.controler.CategoriasInteractor;
import com.example.application.data.controler.CategoriasInteractorImpl;
import com.example.application.data.entity.Categoria;
import com.example.application.data.entity.Clientes;
import com.example.application.views.MainLayout;
import com.example.application.views.facturas.FacturasView.Filters;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
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

@PageTitle("Categorias de Productos")
@Route(value = "categoria", layout = MainLayout.class)
public class CategoriaView extends Div implements CategoriasViewModel {

	private Grid<Categoria> grid;
    private Filters filters;
    
    private List<Categoria> categoria;
    private Categoria categorias;
    private CategoriasInteractor controlador;
	private TextField idcategoria;
	private TextField nombre;
	private TextField descripcion;
	private Button aceptar;
	private Button cancelar;

    public CategoriaView() {
        addClassNames("categoria-view");
       this.categoria = new ArrayList<>();
       this.controlador = new CategoriasInteractorImpl(this);
        
        
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Height.FULL);

        Main content = new Main();
        content.addClassNames(Display.GRID, Gap.XLARGE, AlignItems.START);

        content.add(createCheckoutForm());
        add(content);
        
        filters = new Filters(() -> refreshGrid());
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

        idcategoria = new TextField("Id Categoria");
        nombre = new TextField("Nombre");
        descripcion = new TextField("Descripción");
        aceptar = new Button("Aceptar");
        cancelar = new Button("Cancelar");

        idcategoria.addClassNames(LumoUtility.Padding.Horizontal.SMALL, LumoUtility.Padding.Vertical.SMALL);
        nombre.addClassNames(LumoUtility.Padding.Horizontal.SMALL, LumoUtility.Padding.Vertical.SMALL);
        descripcion.addClassNames(LumoUtility.Padding.Horizontal.SMALL, LumoUtility.Padding.Vertical.SMALL);
        aceptar.addClassNames(Margin.Top.MEDIUM, Padding.LARGE);
        aceptar.addClassName("button-spacing");
        cancelar.addClassNames(Margin.Top.MEDIUM, Padding.LARGE);



        personalDetails.add(idcategoria, nombre, descripcion, aceptar,cancelar);

        return personalDetails;
    }

    

    private Component createGrid() {
        grid = new Grid<>(Categoria.class, false);
        grid.addColumn("idCategoria").setAutoWidth(true).setHeader("Id Categoria");
        grid.addColumn("nombre").setAutoWidth(true).setHeader("Nombre");
        grid.addColumn("descripcion").setAutoWidth(true).setHeader("Descripcion");
        grid.addColumn("cantidad").setAutoWidth(true).setHeader("Cantidad de Productos");
        grid.addColumn("idultimoproductovendido").setAutoWidth(true).setHeader("ID Ultimo Producto Vendido");

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        
        
        
        
        this.controlador.consultarCategoria();
        
        VerticalLayout mainLayout = new VerticalLayout(grid);
        mainLayout.setSizeFull();

        return mainLayout;
    }

    private void refreshGrid() {
        grid.getDataProvider().refreshAll();
    }



	@Override
	public void refrescarGridCategorias(List<Categoria> categoria) {
		Collection<Categoria> items = categoria;
		grid.setItems(items);	
		this.categoria=categoria;
	}
}
