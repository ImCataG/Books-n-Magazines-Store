package models;

public class Client extends Person {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Client(String name, Integer age, String email, String phoneNumber, String address) {
        super(name, age, email, phoneNumber);
        this.address = address;
    }

    public Client() {

    }

    @Override
    public String toString() {
        return "models.Client - " + super.toString() +
                ", address: " + address;
    }

}