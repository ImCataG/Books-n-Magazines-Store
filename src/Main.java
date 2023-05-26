import models.*;
import services.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void viewMenu() throws SQLException {
        System.out.println("View");
        System.out.println("Choose an option:");
        System.out.println("1. View books");
        System.out.println("2. View magazines");
        System.out.println("3. View librarians");
        System.out.println("4. View clients");
        System.out.println("5. View orders");
        System.out.println("6. View orders by client");
        System.out.println("7. View orders by librarian");

        // read the user option
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        // call the service using DatabaseConnection singleton
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        switch(option){
            case 1:
                List<Book> books = databaseConnection.getBooks();
                for(Book book : books){
                    System.out.println(book);
                }
                break;
            case 2:
                List<Magazine> magazines = databaseConnection.getMagazines();
                for(Magazine magazine : magazines){
                    System.out.println(magazine);
                }
                break;
            case 3:
                List<Librarian> librarians = databaseConnection.getLibrarians();
                for(Librarian librarian : librarians){
                    System.out.println(librarian);
                }
                break;
            case 4:
                List<Client> clients = databaseConnection.getClients();
                for(Client client : clients){
                    System.out.println(client);
                }
                break;
            case 5:
                List<Order> orders = databaseConnection.getOrders();
                for(Order order : orders){
                    System.out.println(order);
                }
                break;
            case 6:
                System.out.println("Enter client email:");
                String clientEmail = scanner.nextLine();
                List<Order> ordersByClient = databaseConnection.getOrdersByClientEmail(clientEmail);
                for(Order order : ordersByClient){
                    System.out.println(order);
                }
                break;
            case 7:
                System.out.println("Enter librarian email:");
                String librarianEmail = scanner.nextLine();
                List<Order> ordersByLibrarian = databaseConnection.getOrdersByLibrarianEmail(librarianEmail);
                for(Order order : ordersByLibrarian){
                    System.out.println(order);
                }
                break;
            default:
                System.out.println("Invalid option");
                break;
        }


    }
    public static void addMenu() throws SQLException {
        System.out.println("Add");
        System.out.println("Choose an option:");
        System.out.println("1. Add book");
        System.out.println("2. Add magazine");
        System.out.println("3. Add librarian");
        System.out.println("4. Add client");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        switch (option){
            case 1:
                System.out.println("Enter book title:");
                String bookTitle = scanner.nextLine();
                System.out.println("Enter book description:");
                String bookDescription = scanner.nextLine();
                System.out.println("Enter book price:");
                Double bookPrice = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter book author:");
                String bookAuthor = scanner.nextLine();
                System.out.println("Enter book publisher:");
                String bookPublisher = scanner.nextLine();
                System.out.println("Enter book year:");
                Integer bookYear = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter book ISBN:");
                String bookISBN = scanner.nextLine();
                Book book = new Book(bookTitle, bookDescription, bookPrice, bookAuthor, bookPublisher, bookYear, bookISBN);


                boolean wasAdded0 = databaseConnection.addBook(book);
                if(wasAdded0){
                    System.out.println("Book was added successfully");
                } else {
                    System.out.println("Book was not added");
                }
                break;
            case 2:
                System.out.println("Enter magazine title:");
                String magazineTitle = scanner.nextLine();
                System.out.println("Enter magazine description:");
                String magazineDescription = scanner.nextLine();
                System.out.println("Enter magazine price:");
                Double magazinePrice = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter magazine publisher:");
                String magazinePublisher = scanner.nextLine();
                System.out.println("Enter magazine year:");
                Integer magazineYear = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter magazine ISBN:");
                String magazineISBN = scanner.nextLine();
                Magazine magazine = new Magazine(magazineTitle, magazineDescription, magazinePrice, magazinePublisher, magazineYear, magazineISBN);

                boolean wasAdded1 = databaseConnection.addMagazine(magazine);
                if(wasAdded1){
                    System.out.println("Magazine was added successfully");
                } else {
                    System.out.println("Magazine was not added");
                }
                break;
            case 3:
                // name age email phonenumber rating
                System.out.println("Enter librarian name:");
                String librarianName = scanner.nextLine();
                System.out.println("Enter librarian age:");
                Integer librarianAge = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter librarian email:");
                String librarianEmail = scanner.nextLine();
                System.out.println("Enter librarian phone number:");
                String librarianPhoneNumber = scanner.nextLine();
                System.out.println("Enter librarian rating:");
                Float librarianRating = scanner.nextFloat();
                scanner.nextLine();
                Librarian librarian = new Librarian(librarianName, librarianAge, librarianEmail, librarianPhoneNumber, librarianRating);

                boolean wasAdded2 = databaseConnection.addLibrarian(librarian);
                if(wasAdded2){
                    System.out.println("Librarian was added successfully");
                } else {
                    System.out.println("Librarian was not added");
                }
                break;
            case 4:
                // name age email phonenumber address
                System.out.println("Enter client name:");
                String clientName = scanner.nextLine();
                System.out.println("Enter client age:");
                Integer clientAge = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter client email:");
                String clientEmail = scanner.nextLine();
                System.out.println("Enter client phone number:");
                String clientPhoneNumber = scanner.nextLine();
                System.out.println("Enter client address:");
                String clientAddress = scanner.nextLine();
                Client client = new Client(clientName, clientAge, clientEmail, clientPhoneNumber, clientAddress);

                boolean wasAdded3 = databaseConnection.addClient(client);
                if(wasAdded3){
                    System.out.println("Client was added successfully");
                } else {
                    System.out.println("Client was not added");
                }
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
    public static void updateMenu() throws SQLException{
        System.out.println("Update");
        System.out.println("Choose an option:");
        System.out.println("1. Update price of book by isbn");
        System.out.println("2. Update price of magazine by issn");
        System.out.println("3. Update rating of librarian by email");
        System.out.println("4. Update phone number of client by email");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        switch(option){
            case 1:
                System.out.println("Enter book ISBN:");
                String bookISBN = scanner.nextLine();
                System.out.println("Enter new book price:");
                Double bookPrice = scanner.nextDouble();
                scanner.nextLine();
                boolean wasUpdated0 = databaseConnection.updateBookPrice(bookISBN, bookPrice);
                if(wasUpdated0){
                    System.out.println("Book price was updated successfully");
                } else {
                    System.out.println("Book price was not updated");
                }
                break;
            case 2:
                System.out.println("Enter magazine ISSN:");
                String magazineISSN = scanner.nextLine();
                System.out.println("Enter new magazine price:");
                Double magazinePrice = scanner.nextDouble();
                scanner.nextLine();
                boolean wasUpdated1 = databaseConnection.updateMagazinePrice(magazineISSN, magazinePrice);
                if(wasUpdated1){
                    System.out.println("Magazine price was updated successfully");
                } else {
                    System.out.println("Magazine price was not updated");
                }
                break;
            case 3:
                System.out.println("Enter librarian email:");
                String librarianEmail = scanner.nextLine();
                System.out.println("Enter new librarian rating:");
                Float librarianRating = scanner.nextFloat();
                scanner.nextLine();
                boolean wasUpdated2 = databaseConnection.updateLibrarianRating(librarianEmail, librarianRating);
                if(wasUpdated2){
                    System.out.println("Librarian rating was updated successfully");
                } else {
                    System.out.println("Librarian rating was not updated");
                }
                break;
            case 4:
                System.out.println("Enter client email:");
                String clientEmail = scanner.nextLine();
                System.out.println("Enter new client phone number:");
                String clientPhoneNumber = scanner.nextLine();
                boolean wasUpdated3 = databaseConnection.updateClientPhoneNumber(clientEmail, clientPhoneNumber);
                if(wasUpdated3){
                    System.out.println("Client phone number was updated successfully");
                } else {
                    System.out.println("Client phone number was not updated");
                }
                break;
        }
    }
    public static void deleteMenu() throws SQLException{
        System.out.println("Delete");
        System.out.println("Choose an option:");
        System.out.println("1. Delete book by isbn");
        System.out.println("2. Delete magazine by issn");
        System.out.println("3. Delete librarian by email");
        System.out.println("4. Delete client by email");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        switch(option){
            case 1:
                System.out.println("Enter book ISBN:");
                String bookISBN = scanner.nextLine();
                boolean wasDeleted0 = databaseConnection.deleteBook(bookISBN);
                if(wasDeleted0){
                    System.out.println("Book was deleted successfully");
                } else {
                    System.out.println("Book was not deleted");
                }
                break;
            case 2:
                System.out.println("Enter magazine ISSN:");
                String magazineISSN = scanner.nextLine();
                boolean wasDeleted1 = databaseConnection.deleteMagazine(magazineISSN);
                if(wasDeleted1){
                    System.out.println("Magazine was deleted successfully");
                } else {
                    System.out.println("Magazine was not deleted");
                }
                break;
            case 3:
                System.out.println("Enter librarian email:");
                String librarianEmail = scanner.nextLine();
                boolean wasDeleted2 = databaseConnection.deleteLibrarian(librarianEmail);
                if(wasDeleted2){
                    System.out.println("Librarian was deleted successfully");
                } else {
                    System.out.println("Librarian was not deleted");
                }
                break;
            case 4:
                System.out.println("Enter client email:");
                String clientEmail = scanner.nextLine();
                boolean wasDeleted3 = databaseConnection.deleteClient(clientEmail);
                if(wasDeleted3){
                    System.out.println("Client was deleted successfully");
                } else {
                    System.out.println("Client was not deleted");
                }
                break;
        }
    }
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


        Library library = new Library("Biblioteca Centrala", "Strada Centrala nr. 1, Bucuresti 010101", "0350 000 000", librarians, clients, books, magazines);

        // create order of books
        Order order1 = new Order(client1, librarian1, new Hashtable<Book, Integer>(), new Hashtable<Magazine, Integer>() );
        OrderService.addBookToOrder(order1, book1, 2);
        OrderService.addBookToOrder(order1, book2, 1);
        OrderService.addMagazineToOrder(order1, magazine1, 1);

        LibraryService libraryService = new LibraryService(library);


        Scanner scanner = new Scanner(System.in);
        // get audit instance
        try {
            Audit auditService = Audit.getInstance();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            // implement a menu with options view, add, update, delete, quit
            System.out.println("Choose an option:");
            System.out.println("1. View");
            System.out.println("2. Add");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Quit");

            int option = scanner.nextInt();
            scanner.nextLine();
            switch(option) {
                case 1:
                    try {
                        viewMenu();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                case 2:
                    try{
                        addMenu();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                case 3:
                    try{
                        updateMenu();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try{
                        deleteMenu();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Quit");
                    try {
                        Audit.getInstance().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }

    }
}
