package it.uninsubria.app.users.utils;

import it.uninsubria.app.users.exceptions.TypeStreetException;

public enum TypeStreet {
    Corso, Largo, Piazza, Via, Vicolo, Viale;

    public static TypeStreet decode(String street) throws TypeStreetException{
        street = street.toLowerCase();
        return switch (street) {
            case "via" -> Via;
            case "largo" -> Largo;
            case "piazza" -> Piazza;
            case "corso" -> Corso;
            case "vicolo" -> Vicolo;
            case "viale" -> Viale;
            default -> throw new TypeStreetException("Errore conversione tipo strada");
        };
    }


}
