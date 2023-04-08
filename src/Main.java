import models.*;
import services.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("The Lord of the Rings", "The Lord of the Rings is an epic high fantasy novel written by English author and scholar J. R. R. Tolkien. The story began as a sequel to Tolkien's 1937 fantasy novel The Hobbit, but eventually developed into a much larger work. Written in stages between 1937 and 1949, The Lord of the Rings is one of the best-selling novels ever written, with over 150 million copies sold.", 20.0, "J. R. R. Tolkien", "Allen & Unwin", 1954, "978-0-04-823993-3");
        Book book2 = new Book("Harry Potter and the Philosopher's Stone", "Harry Potter and the Philosopher's Stone is a fantasy novel written by British author J. K. Rowling. The first novel in the Harry Potter series and Rowling's debut novel, it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry. Harry makes close friends and a few enemies during his first year at the school, and with the help of his friends, he faces an attempted comeback by the dark wizard Lord Voldemort, who killed Harry's parents, but failed to kill Harry when he was just 15 months old.", 15.0, "J. K. Rowling", "Bloomsbury", 1997, "978-0-7475-3269-9");
        Book book3 = new Book("The Hobbit", "The Hobbit, or There and Back Again is a children's fantasy novel by English author J. R. R. Tolkien. It was published on 21 September 1937 to wide critical acclaim, being nominated for the Carnegie Medal and awarded a prize from the New York Herald Tribune for best juvenile fiction. The book remains popular and is recognized as a classic in children's literature.", 10.0, "J. R. R. Tolkien", "Allen & Unwin", 1937, "978-0-04-823993-3");

        Dictionary<Book, Integer> books = new Hashtable<Book, Integer>();
        books.put(book1, 12);
        books.put(book2, 3);
        books.put(book3, 5);

        Magazine magazine1 = new Magazine("National Geographic", "National Geographic is the official magazine of the National Geographic Society. It has been published continuously since its first issue in 1888, nine months after the Society itself was founded. It primarily contains articles about science, geography, history, and world culture. The magazine is known for its thick square-bound glossy format with a yellow rectangular border and its extensive use of dramatic photographs.", 10.0, "National Geographic Society", 1888, "978-1-4263-0018-1");
        Magazine magazine2 = new Magazine("The Economist", "The Economist is a British weekly magazine of economics and current affairs, published since 1843. It is the world's most widely used and cited current affairs magazine. It is published in 32 countries and 21 languages. The publication is owned by the Economist Group and edited in London. It is 50% owned by the English branch of the Rothschild family and by the Agnelli family through its holding company Exor.", 10.0, "The Economist Group", 1843, "978-1-4263-0018-1");
        Magazine magazine3 = new Magazine("The New Yorker", "The New Yorker is an American magazine of reportage, commentary, criticism, essays, fiction, satire, cartoons, and poetry. It is published by Condé Nast. Started as a weekly in 1925, the magazine is now published 47 times annually, with five of these issues covering two-week spans. Although its reviews and events listings often focus on the cultural life of New York City, The New Yorker has a wide audience outside of New York and USA.", 10.0, "Condé Nast", 1925, "978-1-4263-0018-1");

        Dictionary<Magazine, Integer> magazines = new Hashtable<Magazine, Integer>();
        magazines.put(magazine1, 10);
        magazines.put(magazine2, 5);
        magazines.put(magazine3, 2);

        Librarian librarian1 = new Librarian("Popescu Ion", 30, "popion@fictionmail.com", "0722 222 222", 4.5f);
        Librarian librarian2 = new Librarian("Ionescu Vasile", 40, "iv@fictionmail.com", "0722 333 333", 4.0f);

        ArrayList<Librarian> librarians = new ArrayList<Librarian>();
        librarians.add(librarian1);
        librarians.add(librarian2);

        Client client1 = new Client("Ionescu Ion", 20, "ionionion@ionmail.com", "0722 444 444", "Strada Ion Ion, nr. 1, Bucuresti");
        Client client2 = new Client("Popescu Vasile", 25, "pv@vasilescumail.com", "0722 555 555", "Strada Vasile Popescu, nr. 2, Bucuresti");

        ArrayList<Client> clients = new ArrayList<Client>();
        clients.add(client1);
        clients.add(client2);


        Library library = new Library("Biblioteca Centrala", "Strada Centrala nr. 1, Bucuresti 010101", "0350 000 000", librarians, clients, new ArrayList<Order>(), books, magazines);

        // create order of books
        Order order1 = new Order(client1, librarian1, new Hashtable<Book, Integer>(), new Hashtable<Magazine, Integer>() );
        OrderService.addBookToOrder(order1, book1, 2);
        OrderService.addBookToOrder(order1, book2, 1);
        OrderService.addMagazineToOrder(order1, magazine1, 1);

        LibraryService libraryService = new LibraryService(library);

        // Make order
        Boolean order1Success = libraryService.createOrder(order1);
        if (order1Success) {
            System.out.println("Order created successfully!");
        } else {
            System.out.println("Order failed!");
        }

        // get order and print it
        Order order1FromLibrary = library.getOrders().get(0);
        System.out.println("Order details:");
        System.out.println("Client: " + order1FromLibrary.getClient().getName());
        System.out.println("Librarian: " + order1FromLibrary.getLibrarian().getName());

        System.out.println("Books:");
        Enumeration<Book> booksEnumerator = order1FromLibrary.getBooks().keys();
        while (booksEnumerator.hasMoreElements()) {
            Book book = booksEnumerator.nextElement();
            System.out.println(book.getName() + " - " + order1FromLibrary.getBooks().get(book));
        }

        System.out.println("Magazines:");
        Enumeration<Magazine> magazinesEnumerator = order1FromLibrary.getMagazines().keys();
        while (magazinesEnumerator.hasMoreElements()) {
            Magazine magazine = magazinesEnumerator.nextElement();
            System.out.println(magazine.getName() + " - " + order1FromLibrary.getMagazines().get(magazine));
        }


    }
}
