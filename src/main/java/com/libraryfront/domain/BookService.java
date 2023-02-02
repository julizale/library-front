package com.libraryfront.domain;

import java.util.HashSet;
import java.util.Set;

public class BookService {

    private Set<Book> books;
    private static BookService bookService;

    private BookService() {
        this.books = exampleData();
    }

    public static BookService getInstance() {
        if (bookService == null) {
            bookService = new BookService();
        }
        return bookService;
    }

    public Set<Book> getBooks() {
        return new HashSet<>(books);
    }

    public Set<Book> findByTitle(String title) {
        Set<Book> bookSet = new HashSet<>();
        for (Book book : books) {
            if (book.getTitle().contains(title)){
                bookSet.add(book);
            }
        }
        return bookSet;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void save(Book book) {
        this.books.add(book);
    }

    public void delete(Book book) {
        this.books.remove(book);
    }

    private Set<Book> exampleData() {
        Set<Book> books = new HashSet<>();
        books.add(new Book("Cracking the Coding Interview: 189 Programming Questions and Solutions", "Gayle Laakmann McDowell",  "2015", BookType.IT));
        books.add(new Book("Introduction to Algorithms", "The MIT Press", "2009", BookType.IT));
        books.add(new Book("Introduction to the Theory of Computation", "Michael Sipser", "2012", BookType.IT));
        books.add(new Book("Operating System Concepts", "Silberschatz, Galvin, Gagne", "2012", BookType.IT));
        books.add(new Book("The Silent Patient", "Alex Michaelides", "2019", BookType.THRILLER));
        books.add(new Book("The Handmaid's Tale", "Margaret Atwood", "2019", BookType.CLASSIC));
        books.add(new Book("Watch Us Rise", "Renée Watson,  Ellen Hagan", "2019", BookType.POETRY));
        return books;
    }
}