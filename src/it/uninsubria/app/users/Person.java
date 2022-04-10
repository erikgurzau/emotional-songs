package it.uninsubria.app.users;


import it.uninsubria.app.users.utils.Address;

public class Person {
    private String name, surname;
    private String cf;
    private Address address;

    public Person(String name, String surname, String cf, Address address) {
        this.name = name;
        this.surname = surname;
        this.cf = cf;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCf() {
        return cf;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return name + ";" + surname + ";" + cf + ";" + address.toString();
    }
}


