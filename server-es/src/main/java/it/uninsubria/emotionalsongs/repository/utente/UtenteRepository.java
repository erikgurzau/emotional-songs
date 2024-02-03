package it.uninsubria.emotionalsongs.repository.utente;

import it.uninsubria.emotionalsongs.config.DatabaseConfig;
import it.uninsubria.emotionalsongs.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.repository.Repository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Questa classe è responsabile dell'interazione con i dati nel database
 * fornendo metodi relativi ai report emozionali delle canzoni.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.config.DatabaseConfig
 * @see it.uninsubria.emotionalsongs.entity.utente.UtenteRegistratoEntity
 * @see it.uninsubria.emotionalsongs.model.utente.Utente
 * @see it.uninsubria.emotionalsongs.repository.Repository
 * @see it.uninsubria.emotionalsongs.utils.Logger
 */
public class UtenteRepository extends Repository<UtenteRegistratoEntity> {

    /**
     * Il costruttore della classe.
     */
    public UtenteRepository() { }

    /**
     * Recupera un elenco di tutti gli utenti registrati.
     * @return Una lista di entità di tipo utente registrato
     */
    public List<UtenteRegistratoEntity> findAll() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT * FROM utenti_registrati";
        Logger.info("UtenteRepository: findAll " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, UtenteRegistratoEntity.class);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Restituisce l'utente corrispondente all'ID specificato, se presente.
     * @param id L'ID dell'utente da cercare
     * @return Un'entità di tipo utente registrato
     */
    public Optional<UtenteRegistratoEntity> findById(Integer id) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT * FROM utenti_registrati WHERE id = :id";
        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("id", id);
        query = replaceNamedParams(query, mapParams);
        Logger.info(this.getClass().getSimpleName() + ": findById " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, UtenteRegistratoEntity.class).stream().findFirst();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Inserisce un nuovo utente registrato utilizzando i dati dell'oggetto Utente fornito come argomento.
     * @param utente L'utente da inserire
     * @return {@code true} se l'inserimento avviene con successo, {@code false} altrimenti
     */
    public boolean createUtente(Utente utente) {
        Connection connection;
        PreparedStatement statement;
        String query =
                "INSERT INTO utenti_registrati (cod_fiscale, nome, cognome, email, psw,  indirizzo, cap, comune, provincia) " +
                        "VALUES (:cod_fiscale, :nome, :cognome, :email, :psw,  :indirizzo, :cap , :comune, :provincia)";
        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("cod_fiscale", utente.getCodFiscale());
        mapParams.put("nome", utente.getNome());
        mapParams.put("cognome", utente.getCognome());
        mapParams.put("email", utente.getEmail());
        mapParams.put("psw", utente.getPassword());
        mapParams.put("indirizzo", utente.getIndirizzo());
        mapParams.put("cap", utente.getCap());
        mapParams.put("comune", utente.getComune());
        mapParams.put("provincia", utente.getProvincia());
        query = replaceNamedParams(query, mapParams);
        Logger.info(this.getClass().getSimpleName() + ": createUtente " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Restituisce gli utenti che possiedono l'email e la password forniti come argomento.
     * @param email L'email dell'utente da cercare
     * @param password La password dell'utente da cercare
     * @return Una lista di entità di tipo utente registrato
     */
    public Optional<UtenteRegistratoEntity> findByEmailAndPassword(String email, String password) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT * FROM utenti_registrati WHERE email = :email AND psw = :password";
        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("email", email);
        mapParams.put("password", password);
        query = replaceNamedParams(query, mapParams);
        Logger.info(this.getClass().getSimpleName() + ": findById " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, UtenteRegistratoEntity.class).stream().findFirst();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}