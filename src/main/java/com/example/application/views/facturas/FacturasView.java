package com.example.application.views.facturas;

import com.example.application.data.controler.FacturasInteractor;
import com.example.application.data.controler.FacturasInteractorImpl;
import com.example.application.data.entity.Factura;
import com.example.application.data.entity.Producto;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

@PageTitle("Facturas")
@Route(value = "facturas", layout = MainLayout.class)

public class FacturasView extends Div implements FacturasViewModel {

    private Grid<Factura> grid;
    private Filters filters;

    private List<Factura> pedidos;
    private FacturasInteractor controlador;
    
    
    public FacturasView() {
        setSizeFull();
        addClassNames("facturas-view");
        this.pedidos = new ArrayList<>();
        this.controlador = new FacturasInteractorImpl(this);
        
        
        filters = new Filters(() -> refreshGrid());
        VerticalLayout layout = new VerticalLayout(createMobileFilters(), filters, createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }

    private HorizontalLayout createMobileFilters() {

        HorizontalLayout mobileFilters = new HorizontalLayout();
        mobileFilters.setWidthFull();
        mobileFilters.addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.BoxSizing.BORDER,
                LumoUtility.AlignItems.CENTER);
        mobileFilters.addClassName("mobile-filters");

        Icon mobileIcon = new Icon("lumo", "plus");
        Span filtersHeading = new Span("Filtros");
        mobileFilters.add(mobileIcon, filtersHeading);
        mobileFilters.setFlexGrow(1, filtersHeading);
        mobileFilters.addClickListener(e -> {
            if (filters.getClassNames().contains("visible")) {
                filters.removeClassName("visible");
                mobileIcon.getElement().setAttribute("icon", "lumo:plus");
            } else {
                filters.addClassName("visible");
                mobileIcon.getElement().setAttribute("icon", "lumo:minus");
            }
        });
        return mobileFilters;
    }

    public static class Filters extends Div implements Specification<Factura> {

        private final TextField nPedido = new TextField("NPedido");
        private final TextField nombre = new TextField("Nombre");
        private final TextField telefono = new TextField("Telefono");
        private final TextField direccion = new TextField("Direccion");

        public Filters(Runnable onSearch) {

            setWidthFull();
            addClassName("filter-layout");
            addClassNames(LumoUtility.Padding.Horizontal.LARGE, LumoUtility.Padding.Vertical.MEDIUM,
                    LumoUtility.BoxSizing.BORDER);


            Button resetBtn = new Button("Resetear");
            resetBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            resetBtn.addClickListener(e -> {
                nPedido.clear();
                nombre.clear();
                telefono.clear();
                direccion.clear();
                onSearch.run();
            });
            Button searchBtn = new Button("Buscar");
            searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            searchBtn.addClickListener(e -> onSearch.run());

            Div actions = new Div(resetBtn, searchBtn);
            actions.addClassName(LumoUtility.Gap.SMALL);
            actions.addClassName("actions");

            add(nPedido, nombre, telefono, direccion, actions);
        }

        @Override
          public Predicate toPredicate(Root<Factura> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();

            if (!nPedido.isEmpty()) {
                String enteredCode = nPedido.getValue();
                Predicate codigoMatch = criteriaBuilder.equal(root.get("codigo"), enteredCode);
                predicates.add(codigoMatch);
            }

            if (!nombre.isEmpty()) {
                
            }
            if (!telefono.isEmpty()) {
            
            }
            if (!direccion.isEmpty()) {
               
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        }
    }

    private Component createGrid() {
        grid = new Grid<>(Factura.class, false);
        grid.addColumn("npedido").setAutoWidth(true).setHeader("# Pedido");
        grid.addColumn("idcliente").setAutoWidth(true).setHeader("ID Cliente");
        grid.addColumn("datoscliente").setAutoWidth(true).setHeader("Cliente");
        grid.addColumn("idproducto").setAutoWidth(true).setHeader("ID Producto");
        grid.addColumn("datosproducto").setAutoWidth(true).setHeader("Producto");
        grid.addColumn("cantidad").setAutoWidth(true).setHeader("Cantidad");
        grid.addColumn("precio").setAutoWidth(true).setHeader("Precio");
        grid.addColumn("subtotal").setAutoWidth(true).setHeader("Subtotal");
        grid.addColumn("total").setAutoWidth(true).setHeader("Total");

        
        
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        
        this.controlador.consultarPedido();
        
        VerticalLayout mainLayout = new VerticalLayout(grid);
        mainLayout.setSizeFull();

        return mainLayout;
    }

    private void refreshGrid() {
        grid.getDataProvider().refreshAll();
    }

	@Override
	public void refrescarGridPedidos(List<Factura> pedidos) {
		Collection<Factura> items = pedidos;
		grid.setItems(items);
		this.pedidos = pedidos;
		
	}
}