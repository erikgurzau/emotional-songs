package it.uninsubria.app.users;


import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.utils.Address;

import java.util.regex.Pattern;

public class Person {
    private String name, surname;
    private String cf;
    private Address address;

    public static final byte LENGTH_CF = 16;


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


    public static boolean isCFValid(String cf) throws UserException {
        if (cf.length() != LENGTH_CF)
            throw new UserException("Il codice fiscale deve composto da 16 caratteri alfanumerici\n");

        cf = cf.toUpperCase();
        Pattern pattern = Pattern.compile("([a-zA-Z]+([0-9]+[a-zA-Z]+)+)");
        if (!pattern.matcher(cf).matches())
            throw new UserException("Il codice fiscale deve essere composto da 6 " +
                    "Lettere + 2 numeri + 1 lettera + 2 numeri + 1 lettera + 3 numeri + 1 lettera\n");

        return true;
    }

    @Override
    public String toString() {
        return name + ";" + surname + ";" + cf + ";" + address.toString();
    }
}


