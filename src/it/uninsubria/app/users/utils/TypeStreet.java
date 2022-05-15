package it.uninsubria.app.users.utils;

import it.uninsubria.app.input.exceptions.InputException;

import java.util.Arrays;

public enum TypeStreet {
    Corso, Largo, Piazza, Via, Vicolo, Viale;

    public static TypeStreet decode(String street) throws InputException {
        street = street.toLowerCase();
        return switch (street) {
            case "via" -> Via;
            case "largo" -> Largo;
            case "piazza" -> Piazza;
            case "corso" -> Corso;
            case "vicolo" -> Vicolo;
            case "viale" -> Viale;
            default -> throw new InputException("Errore, non esiste nessun tipo di strada indicato: \n   Prova con " +
                    Arrays.toString(TypeStreet.values()) + "\n");
        };
    }


}
