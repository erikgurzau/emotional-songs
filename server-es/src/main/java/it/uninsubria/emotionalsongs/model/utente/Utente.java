package it.uninsubria.emotionalsongs.model.utente;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * La classe Utente rappresenta un utente del sistema.
 * Estende la classe Persona e implementa l'interfaccia Serializable.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class Utente extends Persona implements Serializable {

    /**
     * L'ID dell'utente.
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * L'email dell'utente.
     */
    @JsonProperty("email")
    private String email;

    /**
     * La password dell'utente.
     */
    @JsonProperty("password")
    private String password;

    /**
     * Costruttore di default.
     */
    public Utente() {
        super();
    }

    /**
     * Costruttore con parametri.
     * @param nome il nome dell'utente
     * @param cognome il cognome dell'utente
     * @param codFiscale il codice fiscale dell'utente
     * @param indirizzo l'indirizzo dell'utente
     * @param cap il CAP dell'utente
     * @param comune il comune dell'utente
     * @param provincia la provincia dell'utente
     * @param id l'ID dell'utente
     * @param email l'email dell'utente
     * @param password la password dell'utente
     */
    public Utente(
            String nome, String cognome, String codFiscale,
            String indirizzo, String cap, String comune,
            String provincia, Integer id, String email, String password
    ) {
        super(nome, cognome, codFiscale, indirizzo, cap, comune, provincia);
        this.id = id;
        this.email = email;
        this.password = password;
    }

    /**
     * Restituisce l'ID dell'utente.
     * @return l'ID dell'utente
     */
    public Integer getId() {
        return id;
    }

    /**
     * Imposta l'ID dell'utente.
     * @param id l'ID dell'utente
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Restituisce l'email dell'utente.
     * @return l'email dell'utente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'email dell'utente.
     * @param email l'email dell'utente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce la password dell'utente.
     * @return la password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password dell'utente.
     * @param password la password dell'utente
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Utente.
     * @return una stringa contenente l'ID, l'email, la password e le informazioni della persona dell'utente
     */
    public String toString() {
        return String.join(",",
                !Objects.isNull(getId()) ? getId().toString() : null,
                getEmail(),
                getPassword(),
                super.toString()
        );
    }
}
