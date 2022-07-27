package it.uninsubria.app.users.utils;

public class Address {
    private TypeStreet typeStreet;
    private String nameStreet;
    private int houseNumber;
    private String postalCode;
    private String city, province;


    public Address(TypeStreet typeStreet, String nameStreet, int houseNumber, String postalCode, String city, String province) {
        this.typeStreet = typeStreet;
        this.nameStreet = nameStreet;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
    }

    public TypeStreet getTypeStreet() {
        return typeStreet;
    }

    public String getNameStreet() {
        return nameStreet;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String toString(){
        return typeStreet + ";" + nameStreet + ";" + houseNumber + ";" + postalCode + ";" + city + ";" + province;

    }
}
