package models;

public abstract class Person {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private String phoneNumber;

    public Person() {

    }

    public Person(String name, Integer age, String email, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "name: " + name +
                ", age: " + age +
                ", email: " + email +
                ", phoneNumber: " + phoneNumber;
    }

    public boolean equals(Person person) {
        return this.name.equals(person.getName()) &&
                this.age.equals(person.getAge()) &&
                this.email.equals(person.getEmail()) &&
                this.phoneNumber.equals(person.getPhoneNumber());
    }
}
