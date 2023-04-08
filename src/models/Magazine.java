package models;

public class Magazine extends Item{
    private String publisher;
    private Integer year;
    private String issn;

    public Magazine(String name, String description, Double price, String publisher, Integer year, String issn) {
        super(name, description, price);
        this.publisher = publisher;
        this.year = year;
        this.issn = issn;
    }

    public Magazine() {
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

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getIdentifier() {
    	return issn;
    }

    @Override
    public String toString() {
        return "models.Magazine - " + super.toString() +
                ", publisher: " + publisher +
                ", year: " + year +
                ", ISSN: " + issn;
    }
}
