package it.uninsubria.model.api;

public class API {

    private String codiceGruppo;
    private String nome;
    private String host;
    private String baseURI;
    private String endpoint;
    private String metodo;
    private int timeout;

    public API(String codiceGruppo, String nome, String host, String baseURI, String endpoint, String metodo, int timeout) {
        this.codiceGruppo = codiceGruppo;
        this.nome = nome;
        this.host = host;
        this.baseURI = baseURI;
        this.endpoint = endpoint;
        this.metodo = metodo;
        this.timeout = timeout;
    }

    public String getCodiceGruppo() {
        return codiceGruppo;
    }

    public void setCodiceGruppo(String codiceGruppo) {
        this.codiceGruppo = codiceGruppo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBaseURI() {
        return baseURI;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
