package models;

import java.util.Dictionary;
import java.util.Enumeration;

public class Order {
    private Integer id;
    private Client client;
    private Librarian librarian;
    private Dictionary<Book, Integer> books;
    private Dictionary<Magazine, Integer> magazines;
    private Integer total;
    public Order() {
    }

    public Order(Client client, Librarian librarian, Dictionary<Book, Integer> books, Dictionary<Magazine, Integer> magazines) {
        this.client = client;
        this.librarian = librarian;
        this.books = books;
        this.magazines = magazines;
        int total = 0;
        for (Enumeration<Book> e = books.keys(); e.hasMoreElements();) {
            Book key = (Book) e.nextElement();
            total += key.getPrice() * books.get(key);
        }
        for (Enumeration<Magazine> e = magazines.keys(); e.hasMoreElements();) {
            Magazine key = (Magazine) e.nextElement();
            total += key.getPrice() * magazines.get(key);
        }

        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "models.Order{" +
                "id=" + id +
                ", client=" + client +
                ", librarian=" + librarian +
                ", books=" + books +
                ", magazines=" + magazines +
                ", total=" + total +
                '}';
    }


}
