package services;

import models.*;
public class OrderService {
    Order order;

    public OrderService(Order order) {
        this.order = order;
    }

    public static void addBookToOrder(Order order1, Book book1, int i) {
        order1.getBooks().put(book1, i);
    }
    public static void addMagazineToOrder(Order order1, Magazine magazine1, int i) {
        order1.getMagazines().put(magazine1, i);
    }

    public void addBookToOrder(Book book1, int i) {
        order.getBooks().put(book1, i);
    }

    public void addMagazineToOrder(Magazine magazine1, int i) {
        order.getMagazines().put(magazine1, i);
    }

    public void removeBookFromOrder(Book book1) {
        order.getBooks().remove(book1);
    }

    public void removeMagazineFromOrder(Magazine magazine1) {
        order.getMagazines().remove(magazine1);
    }

    public void removeBookFromOrder(Book book1, int i) {
        order.getBooks().put(book1, order.getBooks().get(book1) - i);
    }

    public void removeMagazineFromOrder(Magazine magazine1, int i) {
        order.getMagazines().put(magazine1, order.getMagazines().get(magazine1) - i);
    }

    public void printOrder() {
        System.out.println(order);
    }

    public void addBooks(Book book, Integer quantity) {
        if (order.getBooks().get(book) != null) {
            order.getBooks().put(book, order.getBooks().get(book) + quantity);
        } else {
            order.getBooks().put(book, quantity);
        }
    }

    public void addMagazines(Magazine magazine, Integer quantity) {
        if (order.getMagazines().get(magazine) != null) {
            order.getMagazines().put(magazine, order.getMagazines().get(magazine) + quantity);
        } else {
            order.getMagazines().put(magazine, quantity);
        }
    }
}
