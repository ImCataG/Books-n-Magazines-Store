package models;

import java.util.ArrayList;
import java.util.Dictionary;

public class Library {
    private String name;
    private String address;
    private String phoneNumber;
    private ArrayList<Librarian> librarians;
    private ArrayList<Client> clients;
    private ArrayList<Order> orders;
    private Dictionary<Book, Integer> books;
    private Dictionary<Magazine, Integer> magazines;

    public Library(String name, String address, String phoneNumber, ArrayList<Librarian> librarians, ArrayList<Client> clients, ArrayList<Order> orders, Dictionary<Book, Integer> books, Dictionary<Magazine, Integer> magazines){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.librarians = librarians;
        this.clients = clients;
        this.books = books;
        this.magazines = magazines;
        this.orders = orders;

    }
    public Library() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Librarian> getLibrarians() {
        return librarians;
    }

    public void setLibrarians(ArrayList<Librarian> librarians) {
        this.librarians = librarians;
    }
    public Dictionary<Book, Integer> getBooks() {
        return books;
    }

    public void setBooks(Dictionary<Book, Integer> books) {
        this.books = books;
    }

    public Dictionary<Magazine, Integer> getMagazines() {
        return magazines;
    }

    public void setMagazines(Dictionary<Magazine, Integer> magazines) {
        this.magazines = magazines;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }
    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "models.Library{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", librarians=" + librarians +
                ", clients=" + clients +
                ", books=" + books +
                ", magazines=" + magazines +
                '}';
    }

}
