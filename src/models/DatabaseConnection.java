package models;

import services.LibraryService;
import services.OrderService;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    private final Connection connection;

    private static Audit audit;

    private DatabaseConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/library";
        String username = "admin";
        String password = "admin";
        connection = DriverManager.getConnection(url, username, password);
    }

    public static DatabaseConnection getInstance() throws SQLException {

        if (instance == null) {
            try{
                audit = Audit.getInstance();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public ArrayList<Book> getBooks() throws SQLException{
        ArrayList<Book> books = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
        audit.logCommand("SELECT * FROM books");
        while (resultSet.next()) {
            Book book = new Book();
            book.setName(resultSet.getString("name"));
            book.setDescription(resultSet.getString("description"));
            book.setPrice(resultSet.getDouble("price"));
            book.setAuthor(resultSet.getString("author"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setYear(resultSet.getInt("year"));
            book.setIsbn(resultSet.getString("isbn"));
            books.add(book);
        }
        return books;
    }

    public ArrayList<Magazine> getMagazines() throws SQLException{
        ArrayList<Magazine> magazines = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM magazines");
        audit.logCommand("SELECT * FROM magazines");
        while (resultSet.next()) {
            Magazine magazine = new Magazine();
            magazine.setName(resultSet.getString("name"));
            magazine.setDescription(resultSet.getString("description"));
            magazine.setPrice(resultSet.getDouble("price"));
            magazine.setPublisher(resultSet.getString("publisher"));
            magazine.setYear(resultSet.getInt("year"));
            magazine.setIssn(resultSet.getString("issn"));
            magazines.add(magazine);
        }
        return magazines;
    }

    public ArrayList<Librarian> getLibrarians() throws SQLException{
        ArrayList<Librarian> librarians = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM librarians");
        audit.logCommand("SELECT * FROM librarians");
        while (resultSet.next()) {
            Librarian librarian = new Librarian();
            librarian.setName(resultSet.getString("name"));
            librarian.setAge(resultSet.getInt("age"));
            librarian.setEmail(resultSet.getString("email"));
            librarian.setPhoneNumber(resultSet.getString("phone_number"));
            librarian.setRating(resultSet.getFloat("rating"));
            librarians.add(librarian);
        }
        return librarians;
    }

    public ArrayList<Order> getOrders() throws SQLException{

        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
        audit.logCommand("SELECT * FROM orders");
        ArrayList<Client> clients = getClients();
        ArrayList<Librarian> librarians = getLibrarians();
        ArrayList<Book> books = getBooks();
        ArrayList<Magazine> magazines = getMagazines();
        while (resultSet.next()) {
            Order order = new Order();
            order.setLibrarian(librarians.get(resultSet.getInt("librarianemail")));
            order.setClient(clients.get(resultSet.getInt("clientemail")));
            Object[] tupleArray = (Object[]) resultSet.getArray("books").getArray();
            for (Object tuple : tupleArray) {
                Object[] magazineTuple = (Object[]) tuple;
                String isbn = (String) magazineTuple[0];
                int quantity = (int) magazineTuple[1];
                Book book = getBookByISBN(isbn);
                OrderService orderService = new OrderService(order);
                orderService.addBooks(book, quantity);
            }

            tupleArray = (Object[]) resultSet.getArray("magazines").getArray();
            for (Object tuple : tupleArray) {
                Object[] magazineTuple = (Object[]) tuple;
                String issn = (String) magazineTuple[0];
                int quantity = (int) magazineTuple[1];
                Magazine magazine = getMagazineByISSN(issn);
                OrderService orderService = new OrderService(order);
                orderService.addMagazines(magazine, quantity);
            }

            orders.add(order);
            orders.add(order);
        }
        return orders;
    }

    public ArrayList<Client> getClients() throws SQLException{
        ArrayList<Client> clients = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
        audit.logCommand("SELECT * FROM clients");
        while (resultSet.next()) {
            Client client = new Client();
            client.setName(resultSet.getString("name"));
            client.setAge(resultSet.getInt("age"));
            client.setEmail(resultSet.getString("email"));
            client.setPhoneNumber(resultSet.getString("phonenumber"));
            client.setAddress(resultSet.getString("address"));
            clients.add(client);
        }
        return clients;
    }

    public Client getCLientByEmail(String email) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE email = '" + email + "'");
        audit.logCommand("SELECT * FROM clients WHERE email = '" + email + "'");
        Client client = new Client();
        while (resultSet.next()) {
            client.setName(resultSet.getString("name"));
            client.setAge(resultSet.getInt("age"));
            client.setEmail(resultSet.getString("email"));
            client.setPhoneNumber(resultSet.getString("phonenumber"));
            client.setAddress(resultSet.getString("address"));
        }
        return client;
    }

    public Librarian getLibrarianByEmail(String email) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM librarians WHERE email = '" + email + "'");
        audit.logCommand("SELECT * FROM librarians WHERE email = '" + email + "'");
        Librarian librarian = new Librarian();
        while (resultSet.next()) {
            librarian.setName(resultSet.getString("name"));
            librarian.setAge(resultSet.getInt("age"));
            librarian.setEmail(resultSet.getString("email"));
            librarian.setPhoneNumber(resultSet.getString("phone_number"));
            librarian.setRating(resultSet.getFloat("rating"));
        }
        return librarian;
    }

    public Book getBookByISBN(String isbn) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books WHERE isbn = '" + isbn + "'");
        audit.logCommand("SELECT * FROM books WHERE isbn = '" + isbn + "'");
        Book book = new Book();
        while (resultSet.next()) {
            book.setName(resultSet.getString("name"));
            book.setDescription(resultSet.getString("description"));
            book.setPrice(resultSet.getDouble("price"));
            book.setAuthor(resultSet.getString("author"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setYear(resultSet.getInt("year"));
            book.setIsbn(resultSet.getString("isbn"));
        }
        return book;
    }

    public Magazine getMagazineByISSN(String issn) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM magazines WHERE issn = '" + issn + "'");
        audit.logCommand("SELECT * FROM magazines WHERE issn = '" + issn + "'");
        Magazine magazine = new Magazine();
        while (resultSet.next()) {
            magazine.setName(resultSet.getString("name"));
            magazine.setDescription(resultSet.getString("description"));
            magazine.setPrice(resultSet.getDouble("price"));
            magazine.setPublisher(resultSet.getString("publisher"));
            magazine.setYear(resultSet.getInt("year"));
            magazine.setIssn(resultSet.getString("issn"));
        }
        return magazine;
    }

    public ArrayList<Order> getOrdersByClientEmail(String email) throws SQLException{
        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE clientemail = '" + email + "'");
        audit.logCommand("SELECT * FROM orders WHERE clientemail = '" + email + "'");
        while (resultSet.next()) {
            Order order = new Order();
            order.setClient(getCLientByEmail(email));
            order.setLibrarian(getLibrarianByEmail(resultSet.getString("librarianemail")));

            Object[] tupleArray = (Object[]) resultSet.getArray("books").getArray();
            for (Object tuple : tupleArray) {
                Object[] magazineTuple = (Object[]) tuple;
                String isbn = (String) magazineTuple[0];
                int quantity = (int) magazineTuple[1];
                Book book = getBookByISBN(isbn);
                OrderService orderService = new OrderService(order);
                orderService.addBooks(book, quantity);
            }


            tupleArray = (Object[]) resultSet.getArray("magazines").getArray();
            for (Object tuple : tupleArray) {
                Object[] magazineTuple = (Object[]) tuple;
                String issn = (String) magazineTuple[0];
                int quantity = (int) magazineTuple[1];
                Magazine magazine = getMagazineByISSN(issn);
                OrderService orderService = new OrderService(order);
                orderService.addMagazines(magazine, quantity);
            }

            orders.add(order);
        }
        return orders;
    }

    public ArrayList<Order> getOrdersByLibrarianEmail(String name) throws SQLException{
        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE librarianemail = '" + name + "'");
        audit.logCommand("SELECT * FROM orders WHERE librarianemail = '" + name + "'");
        while (resultSet.next()) {
            Order order = new Order();
            order.setClient(getCLientByEmail(resultSet.getString("clientemail")));
            order.setLibrarian(getLibrarianByEmail(name));

            Object[] tupleArray = (Object[]) resultSet.getArray("books").getArray();
            for (Object tuple : tupleArray) {
                Object[] magazineTuple = (Object[]) tuple;
                String isbn = (String) magazineTuple[0];
                int quantity = (int) magazineTuple[1];
                Book book = getBookByISBN(isbn);
                OrderService orderService = new OrderService(order);
                orderService.addBooks(book, quantity);
            }


            tupleArray = (Object[]) resultSet.getArray("magazines").getArray();
            for (Object tuple : tupleArray) {
                Object[] magazineTuple = (Object[]) tuple;
                String issn = (String) magazineTuple[0];
                int quantity = (int) magazineTuple[1];
                Magazine magazine = getMagazineByISSN(issn);
                OrderService orderService = new OrderService(order);
                orderService.addMagazines(magazine, quantity);
            }
            orders.add(order);
        }
        return orders;
    }

    public boolean addBook(Book book) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books WHERE isbn = '" + book.getIsbn() + "'");
        audit.logCommand("SELECT * FROM books WHERE isbn = '" + book.getIsbn() + "'");
        if(resultSet.next()){
            return false;
        }
        statement.executeUpdate("INSERT INTO books (name, description, price, author, publisher, year, isbn) VALUES ('" + book.getName() + "', '" + book.getDescription() + "', " + book.getPrice() + ", '" + book.getAuthor() + "', '" + book.getPublisher() + "', " + book.getYear() + ", '" + book.getIsbn() + "')");
        audit.logCommand("INSERT INTO books (name, description, price, author, publisher, year, isbn) VALUES ('" + book.getName() + "', '" + book.getDescription() + "', " + book.getPrice() + ", '" + book.getAuthor() + "', '" + book.getPublisher() + "', " + book.getYear() + ", '" + book.getIsbn() + "')");
        return true;
    }

    public boolean addMagazine(Magazine magazine) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM magazines WHERE issn = '" + magazine.getIssn() + "'");
        audit.logCommand("SELECT * FROM magazines WHERE issn = '" + magazine.getIssn() + "'");
        if(resultSet.next()){
            return false;
        }
        statement.executeUpdate("INSERT INTO magazines (name, description, price, publisher, year, issn) VALUES ('" + magazine.getName() + "', '" + magazine.getDescription() + "', " + magazine.getPrice() + ", '" + magazine.getPublisher() + "', " + magazine.getYear() + ", '" + magazine.getIssn() + "')");
        audit.logCommand("INSERT INTO magazines (name, description, price, publisher, year, issn) VALUES ('" + magazine.getName() + "', '" + magazine.getDescription() + "', " + magazine.getPrice() + ", '" + magazine.getPublisher() + "', " + magazine.getYear() + ", '" + magazine.getIssn() + "')");
        return true;
    }

    public boolean addClient(Client client) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE email = '" + client.getEmail() + "'");
        audit.logCommand("SELECT * FROM clients WHERE email = '" + client.getEmail() + "'");
        if(resultSet.next()){
            return false;
        }
        statement.executeUpdate("INSERT INTO clients (name, age, email, phonenumber, address) VALUES ('" + client.getName() + "', " + client.getAge() + ", '" + client.getEmail() + "', '" + client.getPhoneNumber() + "', '" + client.getAddress() + "')");
        audit.logCommand("INSERT INTO clients (name, age, email, phonenumber, address) VALUES ('" + client.getName() + "', " + client.getAge() + ", '" + client.getEmail() + "', '" + client.getPhoneNumber() + "', '" + client.getAddress() + "')");
        return true;
    }

    public boolean addLibrarian(Librarian librarian) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM librarians WHERE email = '" + librarian.getEmail() + "'");
        audit.logCommand("SELECT * FROM librarians WHERE email = '" + librarian.getEmail() + "'");
        if(resultSet.next()){
            return false;
        }
        statement.executeUpdate("INSERT INTO librarians (name, age, email, phonenumber, rating) VALUES ('" + librarian.getName() + "', " + librarian.getAge() + ", '" + librarian.getEmail() + "', '" + librarian.getPhoneNumber() + "', '" + librarian.getRating() + "')");
        audit.logCommand("INSERT INTO librarians (name, age, email, phonenumber, rating) VALUES ('" + librarian.getName() + "', " + librarian.getAge() + ", '" + librarian.getEmail() + "', '" + librarian.getPhoneNumber() + "', '" + librarian.getRating() + "')");
        return true;
    }

    public boolean addLibrary(Library library) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM libraries WHERE name = '" + library.getName() + "'");
        audit.logCommand("SELECT * FROM libraries WHERE name = '" + library.getName() + "'");
        if(resultSet.next()){
            return false;
        }
        statement.executeUpdate("INSERT INTO libraries (name, address) VALUES ('" + library.getName() + "', '" + library.getAddress() + "')");
        audit.logCommand("INSERT INTO libraries (name, address) VALUES ('" + library.getName() + "', '" + library.getAddress() + "')");
        return true;
    }

    public boolean updateBookPrice(String isbn, double price) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books WHERE isbn = '" + isbn + "'");
        audit.logCommand("SELECT * FROM books WHERE isbn = '" + isbn + "'");
        if(!resultSet.next()){
            return false;
        }
        statement.executeUpdate("UPDATE books SET price = " + price + " WHERE isbn = '" + isbn + "'");
        audit.logCommand("UPDATE books SET price = " + price + " WHERE isbn = '" + isbn + "'");
        return true;
    }

    public boolean updateMagazinePrice(String issn, double price) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM magazines WHERE issn = '" + issn + "'");
        audit.logCommand("SELECT * FROM magazines WHERE issn = '" + issn + "'");
        if(!resultSet.next()){
            return false;
        }
        statement.executeUpdate("UPDATE magazines SET price = " + price + " WHERE issn = '" + issn + "'");
        audit.logCommand("UPDATE magazines SET price = " + price + " WHERE issn = '" + issn + "'");
        return true;
    }

    public boolean updateLibrarianRating(String email, float rating) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM librarians WHERE email = '" + email + "'");
        audit.logCommand("SELECT * FROM librarians WHERE email = '" + email + "'");
        if(!resultSet.next()){
            return false;
        }
        statement.executeUpdate("UPDATE librarians SET rating = " + rating + " WHERE email = '" + email + "'");
        audit.logCommand("UPDATE librarians SET rating = " + rating + " WHERE email = '" + email + "'");
        return true;
    }
    public boolean updateClientPhoneNumber(String email, String phoneNumber) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE email = '" + email + "'");
        audit.logCommand("SELECT * FROM clients WHERE email = '" + email + "'");
        if(!resultSet.next()){
            return false;
        }
        statement.executeUpdate("UPDATE clients SET phonenumber = '" + phoneNumber + "' WHERE email = '" + email + "'");
        audit.logCommand("UPDATE clients SET phonenumber = '" + phoneNumber + "' WHERE email = '" + email + "'");
        return true;
    }

    public boolean deleteBook(String isbn) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books WHERE isbn = '" + isbn + "'");
        audit.logCommand("SELECT * FROM books WHERE isbn = '" + isbn + "'");
        if(!resultSet.next()){
            return false;
        }
        statement.executeUpdate("DELETE FROM books WHERE isbn = '" + isbn + "'");
        audit.logCommand("DELETE FROM books WHERE isbn = '" + isbn + "'");
        return true;
    }

    public boolean deleteMagazine(String issn) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM magazines WHERE issn = '" + issn + "'");
        audit.logCommand("SELECT * FROM magazines WHERE issn = '" + issn + "'");
        if(!resultSet.next()){
            return false;
        }
        statement.executeUpdate("DELETE FROM magazines WHERE issn = '" + issn + "'");
        audit.logCommand("DELETE FROM magazines WHERE issn = '" + issn + "'");
        return true;
    }

    public boolean deleteLibrarian(String email) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM librarians WHERE email = '" + email + "'");
        audit.logCommand("SELECT * FROM librarians WHERE email = '" + email + "'");
        if(!resultSet.next()){
            return false;
        }
        statement.executeUpdate("DELETE FROM librarians WHERE email = '" + email + "'");
        audit.logCommand("DELETE FROM librarians WHERE email = '" + email + "'");
        return true;
    }

    public boolean deleteClient(String email) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE email = '" + email + "'");
        audit.logCommand("SELECT * FROM clients WHERE email = '" + email + "'");
        if(!resultSet.next()){
            return false;
        }
        statement.executeUpdate("DELETE FROM clients WHERE email = '" + email + "'");
        audit.logCommand("DELETE FROM clients WHERE email = '" + email + "'");
        return true;
    }
}
