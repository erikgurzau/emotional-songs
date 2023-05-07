package it.uninsubria.repository.utente;


import it.uninsubria.assembler.utente.UtenteAssembler;
import it.uninsubria.config.DatabaseConfig;
import it.uninsubria.model.utente.Utente;
import it.uninsubria.service.LoggerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteRepository {

    private static final String SELECT_USER_BY_ID = "SELECT * FROM UtentiRegistrati WHERE id = ?";
    private static final String INSERT_USER = "INSERT INTO UtentiRegistrati (cod_fiscale, email, password, nome, cognome, indirizzo, cap, comune, provincia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public UtenteRepository() { }

    public Utente trovaUtenteById(int id) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;
        Utente utente;

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_USER_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            utente = resultSet.next() ? UtenteAssembler.toUtente(resultSet) : null;
            connection.close();
            return utente;
        }
        catch (SQLException e) {
            LoggerService.errore(e.getMessage());
            return null;
        }
    }

    public Utente inserisciUtente(Utente utente) {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(INSERT_USER);
            statement.setString(1, utente.getNome());
            statement.setString(2, utente.getEmail());
            statement.executeUpdate();
            return utente;
        }
        catch (SQLException e) {
            LoggerService.errore(e.getMessage());
            return null;
        }
    }



}
