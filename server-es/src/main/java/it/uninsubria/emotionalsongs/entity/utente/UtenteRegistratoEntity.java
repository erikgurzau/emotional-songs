package it.uninsubria.emotionalsongs.entity.utente;

/**
 * Questa classe è responsabile della rappresentazione dell'entità relativa ad un utente.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 */
public class UtenteRegistratoEntity {

    /**
     * L'ID dell'utente.
     */
    private Integer id;

    /**
     * Il codice fiscale dell'utente.
     */
    private String cod_fiscale;

    /**
     * Il nome dell'utente.
     */
    private String nome;

    /**
     * Il cognome dell'utente.
     */
    private String cognome;

    /**
     * L'email dell'utente.
     */
    private String email;

    /**
     * La password dell'utente.
     */
    private String psw;

    /**
     * L'indirizzo dell'utente.
     */
    private String indirizzo;

    /**
     * Il CAP dell'utente.
     */
    private String cap;

    /**
     * Il comune dell'utente.
     */
    private String comune;

    /**
     * La provincia dell'utente.
     */
    private String provincia;

    /**
     * Il costruttore vuoto della classe.
     */
    public UtenteRegistratoEntity() { }

    /**
     * Il costruttore con parametri della classe.
     * @param id L'ID dell'utente
     * @param cod_fiscale Il codice fiscale dell'utente
     * @param nome Il nome dell'utente
     * @param cognome Il cognome dell'utente
     * @param email L'email dell'utente
     * @param psw La password dell'utente
     * @param indirizzo L'indirizzo dell'utente
     * @param cap Il CAP dell'utente
     * @param comune Il comune dell'utente
     * @param provincia La provincia dell'utente
     */
    public UtenteRegistratoEntity(Integer id, String cod_fiscale, String nome, String cognome, String email, String psw, String indirizzo, String cap, String comune, String provincia) {
        this.id = id;
        this.cod_fiscale = cod_fiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.psw = psw;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.comune = comune;
        this.provincia = provincia;
    }

    /**
     * Getter dell'ID dell'utente.
     * @return L'intero che rappresenta l'ID dell'utente
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter dell'ID dell'utente.
     * @param id L'intero che rappresenta l'ID dell'utente
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter del codice fiscale dell'utente.
     * @return La stringa che rappresenta il codice fiscale
     */
    public String getCodFiscale() {
        return cod_fiscale;
    }

    /**
     * Setter del codice fiscale dell'utente.
     * @param cod_fiscale La stringa che rappresenta il codice fiscale
     */
    public void setCodFiscale(String cod_fiscale) {
        this.cod_fiscale = cod_fiscale;
    }

    /**
     * Getter del nome dell'utente.
     * @return La stringa che rappresenta il nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter del nome dell'utente.
     * @param nome La stringa che rappresenta il nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Getter del cognome dell'utente.
     * @return La stringa che rappresenta il cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Setter del cognome dell'utente.
     * @param cognome La stringa che rappresenta il cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Getter dell'email dell'utente.
     * @return La stringa che rappresenta l'email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter dell'email dell'utente.
     * @param email La stringa che rappresenta l'email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter della password dell'utente.
     * @return La stringa che rappresenta la password
     */
    public String getPsw() {
        return psw;
    }

    /**
     * Setter della password dell'utente.
     * @param psw La stringa che rappresenta la password
     */
    public void setPsw(String psw) {
        this.psw = psw;
    }

    /**
     * Getter dell'indirizzo dell'utente.
     * @return La stringa che rappresenta l'indirizzo
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Setter dell'indirizzo dell'utente.
     * @param indirizzo La stringa che rappresenta l'indirizzo
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Getter del CAP dell'utente.
     * @return La stringa che rappresenta il CAP
     */
    public String getCap() {
        return cap;
    }

    /**
     * Setter del CAP dell'utente.
     * @param cap La stringa che rappresenta il CAP
     */
    public void setCap(String cap) {
        this.cap = cap;
    }

    /**
     * Getter del comune dell'utente.
     * @return La stringa che rappresenta il comune dell'utente
     */
    public String getComune() {
        return comune;
    }

    /**
     * Setter del comune dell'utente.
     * @param comune La stringa che rappresenta il comune
     */
    public void setComune(String comune) {
        this.comune = comune;
    }

    /**
     * Getter della provincia dell'utente.
     * @return La stringa che rappresenta la provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Setter della provincia dell'utente.
     * @param provincia La stringa che rappresenta la provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

}