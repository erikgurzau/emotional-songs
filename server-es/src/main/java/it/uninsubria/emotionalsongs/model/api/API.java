package it.uninsubria.emotionalsongs.model.api;

import it.uninsubria.emotionalsongs.utils.Utils;

/**
 * Questa classe è responsabile della rappresentazione e della gestione di un'API.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.utils.Utils
 */
public class API {

    /**
     * Il percorso dell'API.
     */
    private String path;

    /**
     * Il metodo HTTP dell'API.
     */
    private String httpMethod;

    /**
     * Costruttore della classe.
     * @param path Il percorso dell'API
     * @param httpMethod Il metodo HTTP
     */
    public API(String path, String httpMethod) {
        this.path = path;
        this.httpMethod = httpMethod;
    }

    /**
     * Getter del percorso dell'API.
     * @return La stringa che rappresenta il percorso dell'API
     */
    public String getPath() {
        return path;
    }

    /**
     * Getter del metodo HTTP dell'API.
     * @return La stringa che rappresenta il metodo HTTP
     */
    public String getHttpMethod() {
        return httpMethod;
    }

    /**
     * Verifica l'uguaglianza di due API.
     * @param other L'API da confrontare
     * @return {@code true} se l'API su cui è chiamato il metodo è uguale all'API specificata, altrimenti {@code false}
     */
    public boolean equals(API other) {
        return this.path.equals(other.path) && this.httpMethod.equals(other.getHttpMethod());
    }

    /**
     * Verifica la corrispondenza di un API con il percorso e il metodo HTTP forniti.
     * @param path Il percorso dell'API
     * @param httpMethod Il metodo HTTP
     * @return {@code true} se il percorso e il metodo HTTP corrispondono con l'API su cui è chiamato il metodo, altrimenti {@code false}
     */
    public boolean match(String path, String httpMethod) {
        return Utils.isPathMatching(this.path, path) && this.httpMethod.equals(httpMethod);
    }

}