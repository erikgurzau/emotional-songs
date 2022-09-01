package it.uninsubria.app.users.utils;

import it.uninsubria.app.input.exceptions.InputException;
import java.util.Arrays;

/**
 * Classe che definisce un indirizzo di residenza/domicilio
 * @author  Erik Gurzau (749400, VA)
 * @author  Alessia Metaj (738945, VA)
 * @author  Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public enum TypeStreet {
    /**
     * Tipi di strada esistenti
     */
    Corso, Largo, Piazza, Via, Vicolo, Viale;

    /**
     * Converte una stringa nel tipo "TypeStreet" corrispondente
     * @param street Stringa da convertire con il tipo di strada
     * @return Enum del tipo di strada
     * @throws InputException Se la stringa da convertire non corrisponde a nessun tipo di strada
     */
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
