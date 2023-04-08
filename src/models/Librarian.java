package models;
public class Librarian extends Person {
    private Float rating;

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Librarian(String name, Integer age, String email, String phoneNumber, Float rating) {
        super(name, age, email, phoneNumber);
        this.rating = rating;
    }

    public Librarian() {
    }

    @Override
    public String toString() {
        return "models.Librarian - " + super.toString() +
                ", rating: " + rating;
    }
}
