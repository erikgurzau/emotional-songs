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

public class UtenteRepository extends Repository<UtenteRegistratoEntity> {

    public UtenteRepository() { }

    public List<UtenteRegistratoEntity> findAll() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT * FROM Utenti_Registrati";
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

    public Optional<UtenteRegistratoEntity> findById(Integer id) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT * FROM Utenti_Registrati WHERE id = :id";
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

    public boolean createUtente(Utente utente) {
        Connection connection;
        PreparedStatement statement;
        String query =
                "INSERT INTO Utenti_Registrati (cod_fiscale, email, psw, nome, cognome, indirizzo, cap, comune, provincia) " +
                        "VALUES (:cod_fiscale, :email, :psw, :nome, :cognome, :indirizzo, :cap , :comune, :provincia)";
        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("cod_fiscale", utente.getCodFiscale());
        mapParams.put("email", utente.getEmail());
        mapParams.put("psw", utente.getPassword());
        mapParams.put("nome", utente.getNome());
        mapParams.put("cognome", utente.getCognome());
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

    public Optional<UtenteRegistratoEntity> findByEmailAndPassword(String email, String password) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT * FROM Utenti_Registrati WHERE email = :email AND psw = :password";
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
