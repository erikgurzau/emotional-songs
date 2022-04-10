package it.uninsubria.app.users;

import it.uninsubria.app.users.utils.Address;

public class User extends Person {
   private String userId;
    private String email;
   private String psw;


    public User(String name, String surname, String cf, Address address, String userId, String email, String psw) {
        super(name, surname, cf, address);
        this.userId = userId;
        this.email = email;
        this.psw = psw;
    }

    public String getUserId() {
        return userId;
    }

    public String getPsw() {
        return psw;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkLoginInfo(String email, String cryptPsw) {
        return this.email.equals(email) && psw.equals(cryptPsw);
    }


    @Override
    public String toString() {
        return userId + ";" + email + ";" + psw + ";" + super.toString();
    }
}
