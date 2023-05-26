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

}

