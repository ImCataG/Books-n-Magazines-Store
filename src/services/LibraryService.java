package services;
import models.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;

import static java.lang.Math.min;

public class LibraryService {
    Library library;

    public LibraryService(Library library) {
        this.library = library;
    }

    public void addBooks(Book book, Integer quantity){
        if(library.getBooks().get(book) != null){
            library.getBooks().put(book, library.getBooks().get(book) + quantity);
        }else{
            library.getBooks().put(book, quantity);
        }
    }

    public void addMagazines(Magazine magazine, Integer quantity){
        if(library.getMagazines().get(magazine) != null){
            library.getMagazines().put(magazine, library.getMagazines().get(magazine) + quantity);
        }else{
            library.getMagazines().put(magazine, quantity);
        }
    }

    public void removeBooks(Book book, Integer quantity){
        if(library.getBooks().get(book) != null){
            library.getBooks().put(book, min(0, library.getBooks().get(book) - quantity));
        }
    }

    public void removeMagazines(Magazine magazine, Integer quantity){
        if(library.getMagazines().get(magazine) != null){
            library.getMagazines().put(magazine, min(0, library.getMagazines().get(magazine) - quantity));
        }
    }

    public void addLibrarian(Librarian librarian){
        library.getLibrarians().add(librarian);
    }

    public void removeLibrarian(Librarian librarian){
        library.getLibrarians().remove(librarian);
    }

    public ArrayList<Librarian> getLibrariansByName(String name){
        ArrayList<Librarian> librarians = new ArrayList<>();
        for(Librarian librarian : library.getLibrarians()){
            if(librarian.getName().equals(name)){
                librarians.add(librarian);
            }
        }
        return librarians;
    }

    public ArrayList<Book> getBooksByName(String name){
        ArrayList<Book> books = new ArrayList<>();
        for (Enumeration<Book> e = library.getBooks().keys(); e.hasMoreElements();) {
            Book key = (Book) e.nextElement();
            if(key.getName().equals(name)){
                books.add(key);
            }
        }
        return books;
    }

    public ArrayList<Magazine> getMagazinesByName(String name){
        ArrayList<Magazine> magazines = new ArrayList<>();
        for (Enumeration<Magazine> e = library.getMagazines().keys(); e.hasMoreElements();) {
            Magazine key = (Magazine) e.nextElement();
            if(key.getName().equals(name)){
                magazines.add(key);
            }
        }
        return magazines;
    }

    public void addClient(Client client){
        library.getClients().add(client);
    }

    public void removeClient(Client client){
        library.getClients().remove(client);
    }

    public void addOrder(Order order){
        library.getOrders().add(order);
    }

    public void removeOrder(Order order){
        library.getOrders().remove(order);
    }

    public Boolean createOrder(Client client, Librarian librarian, Dictionary<Book, Integer> books, Dictionary<Magazine, Integer> magazines){
        Order order = new Order(client, librarian, books, magazines);
        // check stock for books and magazines
        for (Enumeration<Book> e = books.keys(); e.hasMoreElements();) {
            Book key = (Book) e.nextElement();
            if(library.getBooks().get(key) < books.get(key)){
                return false;
            }
        }
        for (Enumeration<Magazine> e = magazines.keys(); e.hasMoreElements();) {
            Magazine key = (Magazine) e.nextElement();
            if(library.getMagazines().get(key) < magazines.get(key)){
                return false;
            }
        }
        // remove books and magazines from stock
        for (Enumeration<Book> e = books.keys(); e.hasMoreElements();) {
            Book key = (Book) e.nextElement();
            library.getBooks().put(key, library.getBooks().get(key) - books.get(key));
        }
        for (Enumeration<Magazine> e = magazines.keys(); e.hasMoreElements();) {
            Magazine key = (Magazine) e.nextElement();
            library.getMagazines().put(key, library.getMagazines().get(key) - magazines.get(key));
        }
        // add order to library
        library.getOrders().add(order);
        return true;
    }

    public Boolean createOrder(Order order){
        // check stock for books and magazines
        for (Enumeration<Book> e = order.getBooks().keys(); e.hasMoreElements();) {
            Book key = (Book) e.nextElement();
            if(library.getBooks().get(key) < order.getBooks().get(key)){
                return false;
            }
        }
        for (Enumeration<Magazine> e = order.getMagazines().keys(); e.hasMoreElements();) {
            Magazine key = (Magazine) e.nextElement();
            if(library.getMagazines().get(key) < order.getMagazines().get(key)){
                return false;
            }
        }
        // remove books and magazines from stock
        for (Enumeration<Book> e = order.getBooks().keys(); e.hasMoreElements();) {
            Book key = (Book) e.nextElement();
            library.getBooks().put(key, library.getBooks().get(key) - order.getBooks().get(key));
        }
        for (Enumeration<Magazine> e = order.getMagazines().keys(); e.hasMoreElements();) {
            Magazine key = (Magazine) e.nextElement();
            library.getMagazines().put(key, library.getMagazines().get(key) - order.getMagazines().get(key));
        }
        // add order to library
        library.getOrders().add(order);
        return true;
    }
}

