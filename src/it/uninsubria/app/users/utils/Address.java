package it.uninsubria.app.users.utils;

/**
 * Classe che definisce un indirizzo di residenza/domicilio
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 */
public class Address {
    /**
     * Tipo di strada: Via, Vicolo, Corso, etc.
     */
    private TypeStreet typeStreet;

    /**
     * Nome della strada
     */
    private String nameStreet;

    /**
     * Numero civico
     */
    private int houseNumber;

    /**
     * Codice postale
     */
    private String postalCode;

    /**
     * Nome della città
     */
    private String city;

    /**
     * Nome della provincia
     */
    private String province;


    /**
     * Costruttore di un indirizzo
     * @param typeStreet Enum con il tipo di strada: Via, Vicolo, Corso, etc.
     * @param nameStreet Stringa con il nome della strada
     * @param houseNumber Intero con il numero civico
     * @param postalCode Stringa con il codice postale
     * @param city Stringa con il nome della città
     * @param province Stringa con il nome della provincia
     */
    public Address(TypeStreet typeStreet, String nameStreet, int houseNumber, String postalCode, String city, String province) {
        this.typeStreet = typeStreet;
        this.nameStreet = nameStreet;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
    }

    /**
     * Getter del tipo di strada
     * @return Enum del tipo di strada
     */
    public TypeStreet getTypeStreet() {
        return typeStreet;
    }

    /**
     * Getter del nome della strada
     * @return Stringa con il nome della strada
     */
    public String getNameStreet() {
        return nameStreet;
    }

    /**
     * Getter del numero civico
     * @return Intero che identifica il numero civico
     */
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     * Getter del codice postale
     * @return Stringa con il codice postale
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Getter del nome della città
     * @return Stringa con il nome della città
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter del nome della provincia
     * @return Stringa con il nome della provincia
     */
    public String getProvince() {
        return province;
    }

    /**
     * Ritorna una stringa che contiene le informazioni dell'indirizzo, in un formato più leggibile per l'utente
     * Ritorna una stringa che contiene le informazioni dell'indirizzo, in un formato più leggibile per l'utente
     * @return String che contiene i dati dell'indirizzo formattati per facilitare la lettura dei dati
     */
    public String toAddressString() {
        return typeStreet + " " + nameStreet + ", " + houseNumber + ", " + postalCode + ", " + city + ", " + province;
    }


    /**
     * Ritorna una stringa che contiene le informazioni dell'indirizzo
     * @return String che contiene i dati dell'indirizzo divisi dal separatore ';'
     */
    public String toString(){
        return typeStreet + ";" + nameStreet + ";" + houseNumber + ";" + postalCode + ";" + city + ";" + province;

    }
}
