package it.uninsubria.app.users.utils;

public enum TypeStreet {
    Corso, Largo, Piazza, Via, Vicolo, Viale;


    public static TypeStreet decode(String street) {
        switch (street){
            case "Via":
                return Via;
            case "Largo":
                return Largo;
            case "Piazza":
                return Piazza;
            case "Corso":
                return Corso;
            case "Vicolo":
                return Vicolo;
            case "Viale":
                return Viale;
            default: return null;
        }
    }
}
