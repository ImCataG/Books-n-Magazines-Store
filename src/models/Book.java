package models;

public class Book extends Item {
    private String author;
    private String publisher;
    private Integer year;
    private String isbn;

    public Book(String name, String description, Double price, String author, String publisher, Integer year, String isbn) {
        super(name, description, price);
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.isbn = isbn;
    }

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdentifier() {
        return isbn;
    }

    @Override
    public String toString() {
        return "models.Book - " + super.toString() +
                ", author: " + author +
                ", publisher: " + publisher +
                ", year: " + year +
                ", ISBN: " + isbn;
    }
}
