package com.libraryfront;

import com.libraryfront.domain.Book;
import com.libraryfront.domain.BookService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private BookForm form = new BookForm(this);
    private BookService bookService = BookService.getInstance();
    private Grid<Book> grid = new Grid<>(Book.class);
    private TextField filter = new TextField();
    private Button addNewBook = new Button("Add new book");
    private Button redirectionTest = new Button("Redirection Test");

    public MainView() {
        form.setBook(null);
        filter.setPlaceholder("Filter by title...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());

        addNewBook.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setBook(new Book());
        });

        HorizontalLayout toolBar = new HorizontalLayout(filter, addNewBook);

        grid.setColumns("title", "author", "publicationYear", "type");
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        redirectionTest.addClickListener(e ->
                redirectionTest.getUI().ifPresent(ui ->
                        ui.navigate("some/path"))
        );

        add(toolBar, mainContent, redirectionTest);
        setSizeFull();
        refresh();
        grid.asSingleSelect().addValueChangeListener(event -> form.setBook(grid.asSingleSelect().getValue()));
    }

    private void update(){
        grid.setItems(bookService.findByTitle(filter.getValue()));
    }
    public void refresh() {
        grid.setItems(bookService.getBooks());
    }
}
